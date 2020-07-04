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

	// �ڸ��̵�
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
	// ������ ������Ȳ�� �� RoomTimeDataList
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
				// ������ ������ �߰�a�ϰ�
				if (t.roomName.equals(roomN) && t.userName.equals(userN)) {
					t.hourList.add(hour);
					// ������ ������ ���� �����
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

	// ��� ������ �������� �ҷ�����(Ŭ�󿡼� ���� ���� �ѷ��ֱ����� )
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
//	// ���ǿ� �ش��ϴ� �� ������ ���� �� �ִ���
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

				// �� ������ ������
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

	// ������ ������ �ҷ�����
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

	// ���� ��������
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

	// ���� ��������
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

	// ���� ��������
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

	// �Ǹ� ��� �� ���� ��ȸ

	// ���� ������
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
		System.out.println("salesData ����");

		// ������� ����
		int dateSortN;// �ظ� �޾����� 2, ��,�� ������ 5, ��,��,�� ������ 8
		String yearSR = "", monthSR = "", daySR = ""; // Ŭ������ ���� ��¥ ����
		String yearQ = year.substring(2);
		String dateQuery = "";// ������ ���� ��¥����
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
		SalesTot tot = new SalesTot(0, 0, 0);
		SalesData sd = null;

//		// �������� ���� ������
//			String primequery = "(select I.id, R.room_name, r.room_price, i.startdate, a.name, a.id as ida, CONCAT(R.room_name,a.id) as sort "
//					+ "from inventory I , now_room_data R, account A " + "where I.id = r.room_id AND substr(i.startdate,0,"
//					+ dateSortN + ") = " + dateQuery + "and i.uuid = a.uuid) ";

		// �� ������ ������
		// (select substr(i.startdate,0,8) as dates, I.id, R.room_name, r.room_price,
		// i.startdate, a.name, a.id, substr(i.startdate,10,2) as time,
		// substr(i.startdate,0,8)||R.room_name||a.id as sort from inventory I ,
		// now_room_data R, account A where I.id = r.room_id AND substr(i.startdate,0,2)
		// = '20'and i.uuid = a.uuid)
		String primequery = "(select substr(i.startdate,0," + dateSortN
				+ ") as dates, R.room_id, R.room_name, r.room_price, i.startdate, a.name, a.id, substr(i.startdate,10,2) as time, substr(i.startdate,0,8)||R.room_name||a.id as sort "
				+ "from inventory I , now_room_data R, account A " + "where I.id = r.room_id AND substr(i.startdate,0,"
				+ dateSortN + ") = " + dateQuery + "and i.uuid = a.uuid) ";

		try {

			// 1. ArrayList<SalesRecord>
			// �������ۼ�
			query = primequery + "order by dates, r.room_id, a.name, a.id, time";
			System.out.println("������ ������:" + primequery);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			// �ʱⰪ
			String rn = "", un = "", ui = "", bf = "";
			int rp = 0;
			ArrayList<String> hourList = new ArrayList<String>();

			// DB ����� �� �پ� �����ϰ� SalesRecord ���� ArrL ����
			while (rs.next()) {
				// ù 1ȸ������ ����
				if (bf.equals("")) {
//						yearSR = rs.getString(1).substring(0, 2);
//						monthSR = rs.getString(1).substring(3, 5);
//						daySR = rs.getString(1).substring(6, 8);
					rn = rs.getString("room_name");
					rp = Integer.parseInt(rs.getString("room_price"));
					un = rs.getString("name");
					System.out.println("un: " + un);
					ui = rs.getString(7);
					bf = rs.getString(9);
					System.out.println("bf: " + bf);
				}
				// �ð��� �� ȸ�� ��̿� ��� ������ �������� �ð��� �� ���� �� ���� ���
				String hour = rs.getString(8) + "��";
				hourList.add(hour);

				// ���а��� sort ���� �ٲ�� �� ȸ���� ����� ���� �Ű�������� SalesRecord ����
				if (!bf.equals(rs.getString(9))) {
					SalesRecord record = new SalesRecord(rs.getString(1), rn, rp, un, ui, hourList);
					hourList = new ArrayList<String>();
					salesRecordArrL.add(record);
				}

				if (!rs.next()) {
					break;
				}

//					yearSR = rs.getString(1).substring(0, 2);
//					monthSR = rs.getString(1).substring(3, 5);
//					daySR = rs.getString(1).substring(6, 8);
				rn = rs.getString("room_name");
				rp = Integer.parseInt(rs.getString("room_price"));
				un = rs.getString("name");
				ui = rs.getString(7);
				bf = rs.getString(9);
			}
			System.out.println("salesRecordArrL.size(): " + salesRecordArrL.size());

			// 2. ArrayList<SalesBySeat>

			query = "SELECT room_id,room_name ,SUM(room_price), COUNT(DISTINCT id) as id_count " + "FROM " + primequery
					+ "GROUP BY room_id,room_name" + " order by room_id";
			System.out.println("�̿뼮 ���� ����: " + query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				SalesBySeat seat = new SalesBySeat(rs.getString("room_name"),
						Integer.parseInt(rs.getString("SUM(room_price)")),
						Integer.parseInt(rs.getString("COUNT(DISTINCT id)")));
				saleBySeatArrL.add(seat);
			}

			// 3. ArrayList<SalesTot>
			query = "SELECT substr(startdate,0," + dateSortN + ") ,SUM(room_price), COUNT(*) " + "FROM " + primequery
					+ "GROUP BY substr(startdate,0," + dateSortN + ")";
			System.out.println("�Ѹ��� ����: " + query);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			if (rs.next())
				tot = new SalesTot(dateSortN, Integer.parseInt(rs.getString("SUM(room_price)")),
						Integer.parseInt(rs.getString("COUNT(*)")));

			// 4. SalesData ( 1,2,3 ���� )
			sd = new SalesData(salesRecordArrL, saleBySeatArrL, tot);
			System.out.println("salesRecordArrL.size(): " + salesRecordArrL.size());
			System.out.println("saleBySeatArrL.size(): " + saleBySeatArrL.size());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("salesData �ǳ�");

		// 5. ����
		close();
		return sd;
	}
}