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
			insertQuery(ETable.ACCOUNT, calumQuery, calumNum);
			stmt = con.prepareStatement(query);
			stmt.setString(1, userData.uuid);
			stmt.setString(2, userData.name);
			stmt.setString(3, userData.id);
			stmt.setString(4, userData.pw);
			stmt.setString(5, userData.birth);
			stmt.setString(6, userData.phone);
			stmt.setString(7, userData.cType);

			rs = stmt.executeQuery();
			close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public UserData loginUser(String idOrPhone, String id, String pw) throws Exception {

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

	public boolean duplicateIDChk(String id) throws SQLException {

		boolean hasID = false;
		findQuery(ETable.ACCOUNT, "*", "id = ?");
		stmt = con.prepareStatement(query);

		stmt.setString(1, id);
		rs = stmt.executeQuery();

		hasID = rs.next();
		rs.close();

		return hasID;
	}

	public ArrayList<UserData> getAllUserList() throws Exception {
		ArrayList<UserData> userList = new ArrayList<UserData>();
		findQuery(ETable.ACCOUNT, "*");
		stmt = con.prepareStatement(query);

		rs = stmt.executeQuery();

		while (rs.next()) {
			UserData userdata = new UserData(rs.getString("uuid"), rs.getString("name"), rs.getString("id"),
					rs.getString("phone"), rs.getString("birth"));
			userList.add(userdata);
			System.out.println(userdata.uuid);
		}
		System.out.println(userList.size());
		rs.close();
		return userList;
	}

	public ArrayList<UserData> getCurrentUserList() throws Exception {
		ArrayList<String> uuidList = new ArrayList<String>();
		ArrayList<UserData> userList = new ArrayList<UserData>();
		findQuery(ETable.INVENTORY, "UUID",
				"startdate <= sysdate + 1/24 and startdate >= to_char(sysdate,'yyyymmddhh24')");
		stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();

		while (rs.next()) {
			uuidList.add(rs.getString("uuid"));
		}

		for (String str : uuidList) {
			String s = "UUID = " + "'" + str + "'";
			findQuery(ETable.ACCOUNT, "*", s);
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
		}

		while (rs.next()) {
			UserData userdata = new UserData(rs.getString("uuid"), rs.getString("name"), rs.getString("id"),
					rs.getString("phone"), rs.getString("birth"));
			userList.add(userdata);
		}

		rs.close();
		return userList;
	}

	// 오늘 현재시간부터 나중 예약한 룸정보 불러오기
	public ArrayList<RoomProduct> findUserRoom(String uuid) {
		RoomDao roomDao = new RoomDao();
		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
		try {
			ResultSet rs = roomDao.getRoomInfoRS("*", "uuid = '" + uuid + "'");
			// Listener refused the connection with the following error:
			roomList = roomDao.resToList(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roomList;
	}
}
