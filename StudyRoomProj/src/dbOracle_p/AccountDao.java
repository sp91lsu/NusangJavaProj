package dbOracle_p;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import data_p.product_p.DataManager;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;

public class AccountDao extends DBProcess {

	public void createAccount(UserData userData) {

		String[] calumArr = { "uuid", "name", "id", "pw", "birth", "phone", "ctype" };

		String calumQuery = getCalum(calumArr);
		String calumNum = getCalumNum(calumArr.length);

		try {
			stmt.setString(1, userData.uuid);
			stmt.setString(2, userData.name);
			stmt.setString(3, userData.id);
			stmt.setString(4, userData.pw);
			stmt.setString(5, userData.birth);
			stmt.setString(6, userData.phone);
			stmt.setString(7, userData.cType);

			insertQuery(ETable.INVENTORY, calumQuery, calumNum);
			rs = stmt.executeQuery(query);
			close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public UserData findUser(String idOrPhone, String id, String pw) throws Exception {

		findQuery(ETable.ACCOUNT, "*", idOrPhone + " = ? and pw = ?");
		stmt = con.prepareStatement(query);

		stmt.setString(1, id);
		stmt.setString(2, pw);
		rs = stmt.executeQuery();

		UserData userdata = null;
		if (rs.next()) {
			userdata = new UserData(rs.getString("uuid"), rs.getString("name"), rs.getString("id"),
					rs.getString("phone"), rs.getString("birth"));
		}
		rs.close();
		return userdata;
	}

	public ArrayList<RoomProduct> findUserRoom(String uuid) {
		RoomDao roomDao = new RoomDao();
		HashMap<Integer, RoomProduct> roomMap = new HashMap<Integer, RoomProduct>();
		try {
			ResultSet rs = roomDao.getRoomInfoRS("*", "UUID = '" + uuid + "'");

			while (rs.next()) {

				int roomID = rs.getInt("ID");
				
				RoomProduct roomModel = DataManager.getInstance().roomMap.get(roomID);
				Timestamp timeStamp = rs.getTimestamp("STARTDATE");

				Calendar current = Calendar.getInstance();
				current.set(Calendar.MINUTE, 0);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(timeStamp.getTime());

				// 앞으로의 이용 정보 유저에게 줌
				if (cal.getTimeInMillis() >= current.getTimeInMillis()) {
					// 룸 정보가 없으면
					if (!roomMap.containsKey(roomID)) {

						RoomProduct room = new RoomProduct(roomModel.id, roomModel.name, roomModel.price,
								roomModel.personNum);
						roomMap.put(room.id, room);
						roomMap.get(roomID).userUUID = rs.getString("UUID");
						System.out.println(room.name);
					}

					roomMap.get(roomID).calendarList.add(cal);
				}
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
