package dbOracle_p;

import java.sql.SQLException;
import java.sql.Timestamp;
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
}
