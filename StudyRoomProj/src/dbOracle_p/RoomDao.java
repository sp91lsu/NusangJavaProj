//roomdao

package dbOracle_p;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
import data_p.product_p.room_p.RoomTimeData;
import data_p.sales_p.SalesBySeat;
import data_p.sales_p.SalesData;
import data_p.sales_p.SalesRecord;
import data_p.sales_p.SalesTot;

public class RoomDao extends DBProcess {

	public boolean insertRoomInfo(String userUUID, RoomProduct room) {

		new RoomDao().updateExitRoom();

		String[] calumArr = { "ID", "STARTDATE", "UUID", "ISEXIT", "PUID", "PRICE" };

		String calumQuery = getColum(calumArr);
		String calumNum = getColumNum(calumArr.length);
		String puid = room.pUid != null ? room.pUid : UUID.randomUUID().toString();

		try {
			insertQuery(ETable.INVENTORY, calumQuery, calumNum);

			for (Calendar cal : room.calendarList) {

				stmt.setInt(1, room.id);
				cal.set(Calendar.MILLISECOND, 0);
				Timestamp timeStamp = new Timestamp(cal.getTimeInMillis());
				stmt.setTimestamp(2, timeStamp);
				stmt.setString(3, userUUID);
				stmt.setInt(4, 0);
				stmt.setString(5, puid);
				stmt.setInt(6, (int) room.price);
				rs = stmt.executeQuery();
			}

		} catch (

		SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally {
			close();
		}
		return true;
	}

	// 자리이동
	public boolean moveSeat(String userUUID, RoomProduct room, int moveID) {

		new RoomDao().updateExitRoom();
		long price = room.price;

		if (DataManager.getInstance().roomMap.get(moveID).price > price) {
			price = DataManager.getInstance().roomMap.get(moveID).price;
		}

		try {

			String calum = getUpdateColum("ID", "PRICE");

			updateQuery2(ETable.INVENTORY, calum,
					"uuid = ? and startdate  < to_char(sysdate + 1,'yyyymmdd') and startdate >= to_char(sysdate,'yyyymmddhh24')");

			stmt.setInt(1, moveID);
			stmt.setInt(2, (int) price);
			stmt.setString(3, userUUID);
			stmt.executeUpdate();

		} catch (

		SQLException e1) {
			e1.printStackTrace();
			return false;
		} finally {
			close();
		}
		return true;

	}

	public void updateExitRoom() {
		try {

			updateQuery(ETable.INVENTORY, "ISEXIT", "?", "startdate + 1/24 < sysdate");

			stmt.setInt(1, 1);
			stmt.executeUpdate();

		} catch (

		SQLException e1) {
			e1.printStackTrace();
		} finally {
			close();
		}
	}

	public void exitRoom(RoomProduct room) {

		new RoomDao().updateExitRoom();
		int exitValue = room.isExit ? 1 : 0;

		try {

			updateQuery(ETable.INVENTORY, "ISEXIT", "?",
					"uuid = ? and startdate < to_char(sysdate + 1,'yyyymmdd') and startdate >= to_char(sysdate,'yyyymmddhh24')");

			stmt.setInt(1, exitValue);
			stmt.setString(2, room.userUUID);
			stmt.executeUpdate();

		} catch (

		SQLException e1) {
			e1.printStackTrace();
		} finally {
			close();
		}
	}

	// select id,uuid,substr(to_char(startdate),10,2) from inventory where
	// TRUNC(startdate) = TO_DATE('2020-06-01', 'YYYY-MM-DD');
	// 관리자 예약현황에 쓸 RoomTimeDataList
	public ArrayList<RoomTimeData> rTimeDataList(String yyyy, String mm, String dd) throws Exception {

		query = "select id,uuid,substr(to_char(startdate),10,2) as hour from inventory " + "where TRUNC(startdate) "
				+ "= TO_DATE('" + yyyy + "-" + mm + "-" + dd + "', 'YYYY-MM-DD')";
		stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();

		ArrayList<RoomTimeData> roomTDList = new ArrayList<RoomTimeData>();
		ArrayList<String> chk = new ArrayList<String>();
		while (rs.next()) {
			String roomN = DataManager.getInstance().roomName(rs.getString("ID"));
			System.out.println(roomN);
			String userN = new AccountDao().userName(rs.getString("uuid"));
			System.out.println(userN);
			String hour = rs.getString("hour");
			System.out.println(hour);

			if (roomTDList.size() == 0) {
				RoomTimeData rtd = new RoomTimeData(roomN, userN);
				rtd.hourList = new ArrayList<String>();
				rtd.hourList.add(hour);
				roomTDList.add(rtd);
			}
			int ss = roomTDList.size();
			for (int i = 0; i < ss; i++) {
				RoomTimeData t = roomTDList.get(i);
				// 기존에 있으면 추가a하고
				if (t.roomName.equals(roomN) && t.userName.equals(userN)) {
					t.hourList.add(hour);
					// 기존에 없으면 새로 만들고
				} else {
					RoomTimeData rtd = new RoomTimeData(roomN, userN);
					rtd.hourList = new ArrayList<String>();
					rtd.hourList.add(hour);
					roomTDList.add(rtd);
				}
				System.out.println(roomTDList.size());
			}
		}
		close();
		return roomTDList;
	}

	public ArrayList<RoomProduct> getRoomInfo(String... keys) {
		new RoomDao().updateExitRoom();
		ArrayList<RoomProduct> roomList = null;
		try {
			rs = getRS(ETable.INVENTORY, keys);
			roomList = resToList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return roomList;

	}

	// 퇴실 제외한 예약정보 불러오기(클라에서 예약 정보 뿌려주기위함 )
	public ArrayList<RoomProduct> getReservationListAll() {
		try {
			return getRoomInfo("*", "ISEXIT = 0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return null;
	}
//
//	// 조건에 해당하는 룸 정보를 받을 수 있는지
//	public boolean hasGetRoom(int i) {
//		try {
//			rs = getRS(ETable.INVENTORY, "*", "startdate >= sysdate and startdate < to_char(sysdate + 1,'yyyymmdd')");
//
//			return rs.next();
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//	}

	public ArrayList<RoomProduct> resToList(ResultSet rs) {
		HashMap<String, RoomProduct> roomMap = new HashMap<String, RoomProduct>();

		try {
			while (rs.next()) {

				int roomID = rs.getInt("ID");

				String pUid = rs.getString("PUID");
				RoomProduct roomModel = DataManager.getInstance().roomMap.get(roomID).getClone();
				Timestamp timeStamp = rs.getTimestamp("STARTDATE");

				Calendar current = Calendar.getInstance();
				current.set(Calendar.MINUTE, 0);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(timeStamp.getTime());

				// 룸 정보가 없으면
				if (!roomMap.containsKey(pUid)) {

					RoomProduct room = new RoomProduct(roomModel.id, roomModel.name, rs.getInt("PRICE"),
							roomModel.personNum);

					room.isExit = rs.getInt("ISEXIT") == 1;
					room.userUUID = rs.getString("UUID");
					room.pUid = pUid;
					roomMap.put(pUid, room);
				}

				if (roomMap.get(pUid).calendarList.size() == 0) {
					roomMap.get(pUid).calendarList.add(cal);
				} else {
					ArrayList<Calendar> calList = roomMap.get(pUid).calendarList;
					boolean middleSet = false;
					for (int i = 0; i < calList.size(); i++) {
						if (calList.get(i).getTimeInMillis() > cal.getTimeInMillis()) {
							calList.add(i, cal);
							middleSet = true;
							break;
						}
					}
					if (!middleSet) {
						calList.add(cal);
					}
				}
				roomMap.get(pUid).totPrice += rs.getInt("PRICE");
				if (roomMap.get(pUid).price < rs.getInt("PRICE")) {
					roomMap.get(pUid).price = rs.getInt("PRICE");
				}
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();

		roomList.addAll(roomMap.values());

		for (int i = 0; i < roomList.size() - 1; i++) {

			RoomProduct room1 = roomList.get(i);
			Calendar time1 = room1.calendarList.get(0);
			long iTime = time1.getTimeInMillis();
			for (int j = i + 1; j < roomList.size(); j++) {

				RoomProduct room2 = roomList.get(j);
				Calendar time2 = room2.calendarList.get(0);
				long jTime = time2.getTimeInMillis();
				if (iTime > jTime) {
					Collections.swap(roomList, i, j);
				}
			}
		}
		return roomList;
	}

	// 예약한 룸정보 불러오기
	public ArrayList<RoomProduct> findUserRoom(String uuid, boolean isExit) {

		new RoomDao().updateExitRoom();

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();

		int isExitNum = isExit ? 1 : 0;
		try {
			ResultSet rs = getRS(ETable.INVENTORY, "*", "uuid = '" + uuid + "' and ISEXIT = " + isExitNum);
			// Listener refused the connection with the following error:
			roomList = resToList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return roomList;
	}

	// 예약 가능한지
	public boolean availableMove(int seatID) {

		try {
			findQuery(ETable.INVENTORY, "*", "id = ? and startdate = to_char(sysdate,'yyyymmddhh24') and ISEXIT = 0");

			stmt.setInt(1, seatID);
			rs = stmt.executeQuery();

			return !rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return true;
	}

	// 예약 가능한지
	public boolean availableBuy(int id, Calendar cal) {

		try {
			findQuery(ETable.INVENTORY, "*", "id = ? and startdate = to_char(?,'yyyymmddhh24') and ISEXIT = 0");

			Timestamp time = new Timestamp(cal.getTimeInMillis());
			stmt.setInt(1, id);
			stmt.setTimestamp(2, time);
			rs = stmt.executeQuery();

			return !rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return true;
	}

	// 예약 가능한지
	public boolean availableBuyRoom(RoomProduct room) {

		try {
			for (Calendar cal : room.calendarList) {
				if (!new RoomDao().availableBuy(room.id, cal)) {
					return false;
				}
			}
		} finally {
			close();
		}
		return true;
	}

	// 판매 기록 및 매출 조회

	// 참고 쿼리문
//  SELECT substr(startdate,0,8) ,SUM(room_price), COUNT(*) 
//  FROM 
//
//  (select I.id, R.room_name, r.room_price, i.startdate, a.name, a.id
//  from inventory I , now_room_data R, account A
//  where I.id = r.room_id AND substr(i.startdate,0,8) = '20/06/27' and i.uuid = a.uuid(+))
//
//  GROUP BY substr(startdate,0,8);

	public SalesData salesData(String year, String month, String day) {
		// ex) year,month,day --> 2020/0/0 or 2020/12/0 or 2020/12/07
		// ex)
		System.out.println("salesData 들어옴");

		// 멤버변수 세팅
		int dateSortN;// 해만 받았으면 2, 해,월 받으면 5, 해,월,일 받으면 8
		String yearSR = "", monthSR = "", daySR = ""; // 클래스에 담을 날짜 변수
		String yearQ = year.substring(2);
		String dateQuery = "";// 쿼리에 담을 날짜정보
		String startdate = "";

		if (month.equals("0") && day.equals("0")) {
			dateSortN = 2;
			dateQuery = "'" + yearQ + "'";
		} else if (!month.equals("0") && day.equals("0")) {
			dateSortN = 5;
			dateQuery = "'" + yearQ + "/" + month + "'";
		} else {
			dateSortN = 8;
			dateQuery = "'" + yearQ + "/" + month + "/" + day + "'";
		}

		System.out.println("dateSortN:" + dateSortN);
		System.out.println("dateQuery:" + dateQuery);

		ArrayList<SalesRecord> salesRecordArrL = new ArrayList<SalesRecord>();
		ArrayList<SalesBySeat> saleBySeatArrL = new ArrayList<SalesBySeat>();
		SalesTot tot = new SalesTot(0, "", "");
		SalesData sd = null;

//		// 공통으로 들어가는 쿼리문
//			String primequery = "(select I.id, R.room_name, r.room_price, i.startdate, a.name, a.id as ida, CONCAT(R.room_name,a.id) as sort "
//					+ "from inventory I , now_room_data R, account A " + "where I.id = r.room_id AND substr(i.startdate,0,"
//					+ dateSortN + ") = " + dateQuery + "and i.uuid = a.uuid) ";

		// 새 프라임 쿼리문
		// (select substr(i.startdate,0,8) as dates, I.id, R.room_name, r.room_price,
		// i.startdate, a.name, a.id, substr(i.startdate,10,2) as time,
		// substr(i.startdate,0,8)||R.room_name||a.id as sort from inventory I ,
		// now_room_data R, account A where I.id = r.room_id AND substr(i.startdate,0,2)
		// = '20'and i.uuid = a.uuid)
		String primequery = "(select substr(i.startdate,0,8) as dates, R.room_id, R.room_name, r.room_price, a.name, a.id, substr(i.startdate,10,2) as time, i.puid, r.personnum "
				+ "from inventory I , now_room_data R, account A " + "where I.id = r.room_id AND substr(i.startdate,0,"
				+ dateSortN + ") = " + dateQuery + "and i.uuid = a.uuid) ";
		String countquery = "(select distinct dates, room_id, room_name, room_price, name, id, personnum " + "from "
				+ primequery + ") ";

		try {

			// 1. ArrayList<SalesRecord>
			// 쿼리문작성
			query = "select dates, room_id, room_name, personnum , sum(room_price), name, id ,SUBSTR("
					+ "        XMLAGG(" + "            XMLELEMENT(COL ,',', time) ORDER BY time).EXTRACT('//text()'"
					+ "        ).GETSTRINGVAL()" + "       , 2) as time, sum(personnum), sum(room_price) " + "from "
					+ primequery + "group by dates, room_id, room_name, personnum , room_price, name, id "
					+ "order by dates, room_id, name";
			System.out.println("ArrayList<SalesRecord> 쿼리문:" + query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			// ResultSet -> ArrL에 담기
			while (rs.next()) {
				salesRecordArrL.add(new SalesRecord(rs.getString(1), rs.getString(3), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8)));
			}
			System.out.println("salesRecordArrL.size(): " + salesRecordArrL.size());
			rs.close();

			// 2. ArrayList<SalesBySeat>
			// 이용석별 이용객수 <SalesBySeat.cnt> = <SUM(PERSONNUM)> 쿼리문작성
			query = "select room_id,room_name, sum(personnum) " + "from " + countquery + "group by room_id, room_name "
					+ "order by room_id ";
			System.out.println("이용석별 이용객수 쿼리문: " + query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			// ResultSet -> ArrL에 담기
			while (rs.next()) {
				saleBySeatArrL.add(new SalesBySeat(rs.getString(2), "", rs.getString(3)));
			}

			// 이용석별 매출 <SalesBySeat.sum> = <SUM(ROOM_PRICE)> 쿼리문작성
			query = "select room_name, sum(room_price) " + "from " + primequery + "group by room_id, room_name "
					+ "order by room_id ";
			System.out.println("이용석별 매출 쿼리문: " + query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			// ResultSet -> ArrL에 담기
			int cnt = 0;
			while (rs.next()) {
				saleBySeatArrL.get(cnt++).sum = rs.getString(2);
			}
			// 3. ArrayList<SalesTot>
			// 총 이용객수 <SalesTot.cntTot> = <SUM(PERSONNUM)> 쿼리문작성
			query = "select substr(dates,0," + dateSortN + "), sum(personnum) " + "from " + countquery
					+ "group by substr(dates,0," + dateSortN + ") ";
			System.out.println("총 이용객수 쿼리문: " + query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			if (rs.next())
				tot = new SalesTot(dateSortN, rs.getString(2), "");

			// 총 매출 <SalesTot.sumTot> = <SUM(ROOM_PRICE)> 쿼리문작성
			query = "select substr(dates,0," + dateSortN + "), sum(room_price) " + "from " + primequery
					+ "group by substr(dates,0," + dateSortN + ") ";
			System.out.println("총 매출 쿼리문: " + query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			if (rs.next())
				tot.sumTot = rs.getString(2);

			// 4. SalesData ( 1,2,3 종합 )
			sd = new SalesData(salesRecordArrL, saleBySeatArrL, tot);
			System.out.println("salesRecordArrL.size(): " + salesRecordArrL.size());
			System.out.println("saleBySeatArrL.size(): " + saleBySeatArrL.size());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("salesData 맨끝");

		// 5. 종료
		close();
		return sd;
	}
}