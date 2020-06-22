package dbOracle_p;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

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
		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
		// 룸 상품 찾기

		ArrayList<Calendar> timeList = new ArrayList<Calendar>();
		try {
			ResultSet rs = roomDao.getRoomInfoRS("*", "UUID = '" + uuid + "'");

			while (rs.next()) {
				Timestamp timeStamp = rs.getTimestamp("STARTDATE");

				Calendar current = Calendar.getInstance();

				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(timeStamp.getTime());

				// 앞으로의 이용 정보 유저에게 줌
				if (cal.get(Calendar.YEAR) >= current.get(Calendar.YEAR)
						&& cal.get(Calendar.MONTH) >= current.get(Calendar.MONTH)
						&& cal.get(Calendar.DATE) >= current.get(Calendar.DATE)) {

					RoomProduct room = null;
					RoomProduct roomModel = DataManager.getInstance().roomMap.get(rs.getInt("ID"));
					room = new RoomProduct(roomModel.id, roomModel.name, roomModel.price, rs.getInt("PERSONNUM"));
					timeList.add(cal);
					room.setDate(rs.getString("UUID"), timeList);
					roomList.add(room);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roomList;
	}
}
