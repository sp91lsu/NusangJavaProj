package dbOracle_p;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;

public class RoomDao extends DBProcess {

	public boolean insertRoomInfo(String userUUID, RoomProduct room) {
		String[] calumArr = { "ID", "STARTDATE", "UUID", "ISEXIT" };

		String calumQuery = getCalum(calumArr);
		String calumNum = getCalumNum(calumArr.length);

		try {
			insertQuery(ETable.INVENTORY, calumQuery, calumNum);
			stmt = con.prepareStatement(query);

			for (Calendar cal : room.calendarList) {
				stmt.setInt(1, room.id);
				Timestamp timeStamp = new Timestamp(cal.getTimeInMillis());
				stmt.setTimestamp(2, timeStamp);
				stmt.setString(3, userUUID);
				stmt.setInt(4, 0);
				rs = stmt.executeQuery();
			}

			close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	// 자리이동
	public boolean moveSeat(String userUUID, RoomProduct originRoom, int moveID) {

		try {
			updateQuery(ETable.INVENTORY, "ID", "?",
					"uuid = ? and startdate <= sysdate + 1/24 and startdate >= to_char(sysdate,'yyyymmddhh24')");

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
					"uuid = ? and startdate <= sysdate + 1/24 and startdate >= to_char(sysdate,'yyyymmddhh24')");

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

	public ResultSet getRoomInfoRS(String... keys) throws SQLException {

		findQuery(ETable.INVENTORY, keys);
		stmt = con.prepareStatement(query);

		return stmt.executeQuery();
	}

	// 재고 모든 정보 불러오기
	public ArrayList<RoomProduct> getRoomInfo(String... keys) throws Exception {

		rs = getRoomInfoRS(keys);

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();

		System.out.println("로그인 시 룸 데이터");
		while (rs.next()) {

			int roomID = rs.getInt("ID");
			if (DataManager.getInstance().roomMap.containsKey(roomID)) {

				ArrayList<Calendar> timeList = new ArrayList<Calendar>();

				Timestamp time = rs.getTimestamp("STARTDATE");
				RoomProduct room = null;
				RoomProduct roomModel = DataManager.getInstance().roomMap.get(roomID);
				System.out.println(roomModel);
				room = new RoomProduct(roomModel.id, roomModel.name, roomModel.price, rs.getInt("PERSONNUM"));

				if (rs.getInt("ISEXIT") == 1) {
					room.isExit = true;
				}

				Calendar cal = Calendar.getInstance();

				cal.setTimeInMillis(time.getTime());
				timeList.add(cal);
				room.setDate(rs.getString("UUID"), timeList);
				roomList.add(room);
			}
		}

		System.out.println("룸 리스트 갯수" + roomList.size());
		rs.close();
		return roomList;
	}

	// 현재 룸 정보 불러오기
	public ArrayList<RoomProduct> currentRoomState() {
		ArrayList<RoomProduct> cRoomList = new ArrayList<RoomProduct>();
		try {
			rs = getRoomInfoRS("*", "startdate <= sysdate + 1/24 and startdate >= to_char(sysdate,'yyyymmddhh24')");
			cRoomList = resToList(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cRoomList;
	}

	// 내 룸 정보 불러오기

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

				// 룸 정보가 없으면
				if (!roomMap.containsKey(roomID)) {

					RoomProduct room = new RoomProduct(roomModel.id, roomModel.name, roomModel.price,
							roomModel.personNum);

					if (rs.getInt("ISEXIT") == 1) {
						room.isExit = true;
					}

					roomMap.put(room.id, room);
					roomMap.get(roomID).userUUID = rs.getString("UUID");
					System.out.println(room.name);
				}

				roomMap.get(roomID).calendarList.add(cal);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();

		roomList.addAll(roomMap.values());

		return roomList;
	}
}
