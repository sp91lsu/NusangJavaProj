package dbOracle_p;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
import data_p.product_p.room_p.RoomTimeData;
import oracle.net.aso.d;

public class RoomDao extends DBProcess {

	public boolean insertRoomInfo(String userUUID, RoomProduct room) {
		String[] calumArr = { "ID", "STARTDATE", "UUID", "ISEXIT", "PUID" };

		String calumQuery = getColum(calumArr);
		String calumNum = getColumNum(calumArr.length);
		String puid = UUID.randomUUID().toString();
		try {
			insertQuery(ETable.INVENTORY, calumQuery, calumNum);
			stmt = con.prepareStatement(query);

			for (Calendar cal : room.calendarList) {
				stmt.setInt(1, room.id);
				Timestamp timeStamp = new Timestamp(cal.getTimeInMillis());
				stmt.setTimestamp(2, timeStamp);
				stmt.setString(3, userUUID);
				stmt.setInt(4, 0);
				stmt.setString(5, puid);
				rs = stmt.executeQuery();
			}

			close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	// �ڸ��̵�
	public boolean moveSeat(String userUUID, RoomProduct originRoom, int moveID) {

		try {
			updateQuery(ETable.INVENTORY, "ID", "?",
					"uuid = ? and startdate <= sysdate + 1 and startdate >= to_char(sysdate,'yyyymmddhh24')");

			stmt = con.prepareStatement(query);
			stmt.setInt(1, moveID);
			stmt.setString(2, userUUID);
			stmt.executeUpdate();

			close();
		} catch (

		SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	public void exitRoom(RoomProduct room) {

		int exitValue = room.isExit ? 1 : 0;

		try {

			updateQuery(ETable.INVENTORY, "ISEXIT", "?",
					"uuid = ? and startdate <= sysdate + 1 and startdate >= to_char(sysdate,'yyyymmddhh24')");

			stmt = con.prepareStatement(query);
			stmt.setInt(1, exitValue);
			stmt.setString(2, room.userUUID);
			stmt.executeUpdate();

			close();
		} catch (

		SQLException e1) {
			e1.printStackTrace();
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
			for (int i = 0; i < roomTDList.size(); i++) {
				RoomTimeData t = roomTDList.get(i);
				// ������ ������ �߰��ϰ�
				if (t.roomName.equals(roomN) && t.userName.equals(userN)) {
					t.hourList.add(hour);
					// ������ ������ ���� �����
				} else {
					RoomTimeData rtd = new RoomTimeData(roomN, userN);
					rtd.hourList = new ArrayList<String>();
					rtd.hourList.add(hour);
					roomTDList.add(rtd);
				}
			}
		}
		rs.close();
		return roomTDList;
	}

	public ArrayList<RoomProduct> getRoomInfo(String... keys) throws Exception {

		rs = getRS(ETable.INVENTORY, keys);

		ArrayList<RoomProduct> roomList = resToList(rs);

		return roomList;
	}

	// ��� ������ �������� �ҷ�����(Ŭ�󿡼� ���� ���� �ѷ��ֱ����� )
	public ArrayList<RoomProduct> getReservationListNonExit() {
		try {
			return getRoomInfo("*", "ISEXIT = 0");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// ���� �� ������������ �ҷ�����
	public ArrayList<RoomProduct> currentRoomList() {

		ArrayList<RoomProduct> cRoomList = new ArrayList<RoomProduct>();

		// �� �ð����� ������ �ٷ� ������
		try {
			rs = getRS(ETable.INVENTORY, "*", "startdate >= sysdate and startdate < to_char(sysdate + 1,'yyyymmdd')");
			cRoomList = resToList(rs);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cRoomList;

	}

	// ���ǿ� �ش��ϴ� �� ������ ���� �� �ִ���
	public boolean hasGetRoom(int i) {
		try {
			rs = getRS(ETable.INVENTORY, "*", "startdate >= sysdate and startdate < to_char(sysdate + 1,'yyyymmdd')");

			return rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<RoomProduct> resToList(ResultSet rs) {
		HashMap<Integer, RoomProduct> roomMap = new HashMap<Integer, RoomProduct>();
		try {
			while (rs.next()) {

				int roomID = rs.getInt("ID");

				RoomProduct roomModel = DataManager.getInstance().roomMap.get(roomID);
				Timestamp timeStamp = rs.getTimestamp("STARTDATE");

				Calendar current = Calendar.getInstance();
				current.set(Calendar.MINUTE, 0);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(timeStamp.getTime());

				// �� ������ ������
				if (!roomMap.containsKey(roomID)) {

					RoomProduct room = new RoomProduct(roomModel.id, roomModel.name, roomModel.price,
							roomModel.personNum);

					if (rs.getInt("ISEXIT") == 1) {
						room.isExit = true;
					}

					roomMap.put(room.id, room);
					roomMap.get(roomID).userUUID = rs.getString("UUID");
				}

				roomMap.get(roomID).calendarList.add(cal);
			}
			rs.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();

		roomList.addAll(roomMap.values());

		return roomList;
	}
}
