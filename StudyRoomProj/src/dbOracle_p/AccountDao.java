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

		String calumQuery = getColum(calumArr);
		String calumNum = getColumNum(calumArr.length);

		try {
			insertQuery(ETable.ACCOUNT, calumQuery, calumNum);
			stmt.setString(1, userData.uuid);
			stmt.setString(2, userData.name);
			stmt.setString(3, userData.id);
			stmt.setString(4, userData.pw);
			stmt.setString(5, userData.birth);
			stmt.setString(6, userData.phone);
			stmt.setInt(7, 0);
			rs = stmt.executeQuery();
			close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	UserData rsToUser(ResultSet rs) {
		UserData userData = null;
		try {
			userData = new UserData(rs.getString("uuid"), rs.getString("name"), rs.getString("id"),
					rs.getString("phone"), rs.getString("birth"), rs.getInt("cType"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userData;
	}

	// 로그인해서 유저 데이터 반환
	public UserData loginUser(String idOrPhone, String id, String pw, int cType) throws Exception {

		findQuery(ETable.ACCOUNT, "*", idOrPhone + " = ? and pw = ? and CTYPE = ?");

		stmt.setString(1, id);
		stmt.setString(2, pw);
		stmt.setInt(3, cType);
		rs = stmt.executeQuery();
		UserData userData = null;
		if (rs.next()) {
			userData = rsToUser(rs);
			new AccountDao().loginCheck(userData.uuid, true);
		}
		close();
		return userData;
	}

	public boolean loginCheck(String uuid, boolean isLogin) {
		try {
			updateQuery(ETable.ACCOUNT, "ISLOGIN", "?", "uuid = ?");

			int loginValue = isLogin ? 1 : 0;
			stmt.setInt(1, loginValue);
			stmt.setString(2, uuid);
			rs = stmt.executeQuery();
			close();
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
	}

	public void ipCheck(String uuid, String ip) {

		try {
			updateQuery(ETable.ACCOUNT, "ip", "?", "uuid = ?");

			stmt.setString(1, ip);
			stmt.setString(2, uuid);
			rs = stmt.executeQuery();
			close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// 유저찾기
	public UserData findnUser(String uuID) throws Exception {
		findQuery(ETable.ACCOUNT, "*", "uuid = ?");

		stmt.setString(1, uuID);
		rs = stmt.executeQuery();

		UserData userData = rsToUser(rs);
		close();
		return userData;
	}

	// 매니저찾기
	public ArrayList<String> findManagersIp() {

		ArrayList<String> ipList = new ArrayList<String>();
		findQuery(ETable.ACCOUNT, "*", "ctype = 1 and ISLOGIN = 1");

		try {
			rs = stmt.executeQuery();

			while (rs.next()) {
				ipList.add(rs.getString("ip"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close();
		return ipList;
	}

	public String userName(String uuID) throws Exception {
		String un = "";
		query = "select name,uuid from account";
		stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();

		while (rs.next()) {
			if (rs.getString("name") == null)
				continue;
			if (uuID.equals(rs.getString("uuid"))) {
				un = rs.getString("name");
				break;
			}
		}
		close();

		return un;
	}

	public boolean duplicateIDChk(String idOrPhone, String id) throws SQLException {

		boolean hasID = false;
		findQuery(ETable.ACCOUNT, "*", idOrPhone + " = ?");

		stmt.setString(1, id);
		rs = stmt.executeQuery();

		hasID = rs.next();

		close();

		return hasID;
	}

	public ArrayList<UserData> getAllUserList() throws Exception {
		ArrayList<UserData> userList = new ArrayList<UserData>();
		findQuery(ETable.ACCOUNT, "*");

		rs = stmt.executeQuery();

		while (rs.next()) {

			UserData userdata = rsToUser(rs);
			userList.add(userdata);
			System.out.println(userdata.uuid);
		}
		close();
		return userList;
	}

	public ArrayList<UserData> getCurrentUserList() throws Exception {
		ArrayList<UserData> userList = new ArrayList<UserData>();

		query = "select name,a.id,phone,r.room_name from inventory I, account A,now_room_data R where startdate = to_char(sysdate,'yyyymmddhh24') and isexit = 0 and i.uuid = a.uuid and I.id = r.room_id  order by name";
		stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();

		while (rs.next()) {
			UserData userdata = new UserData(rs.getString("name"), rs.getString("id"), rs.getString("phone"),
					rs.getString("room_name"));
			userList.add(userdata);
		}

		close();
		return userList;
	}

}
