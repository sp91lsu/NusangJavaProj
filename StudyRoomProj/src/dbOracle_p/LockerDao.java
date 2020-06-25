package dbOracle_p;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;

public class LockerDao extends DBProcess {

	public boolean insertLocker(String userUUID, LockerData lockerData) {
		String[] calumArr = { "UUID", "ID", "PW" };

		String calumQuery = getCalum(calumArr);
		String calumNum = getCalumNum(calumArr.length);

		try {
			insertQuery(ETable.LOCKER, calumQuery, calumNum);

			stmt = con.prepareStatement(query);

			stmt.setString(1, userUUID);
			stmt.setInt(2, lockerData.id);
			stmt.setString(3, lockerData.pw);

			rs = stmt.executeQuery();

			close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<Integer> getLockerIDList() {

		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			ResultSet rs = getRS(ETable.LOCKER, "*");

			while (rs.next()) {
				list.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public boolean deleteLocker(String uuid) {
		deleteQuery(ETable.LOCKER, "uuid = ?");

		try {
			stmt = con.prepareStatement(query);

			stmt.setString(1, uuid);

			rs = stmt.executeQuery();

			close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
