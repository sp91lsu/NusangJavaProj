package dbOracle_p;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.DataManager;
import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;

public class LockerDao extends DBProcess {

	public boolean insertLocker(String userUUID, LockerData lockerData) {
		String[] columArr = { "UUID", "ID", "PW", "ISEXIT", "TIME" };

		String columQuery = getColum(columArr);
		String columNum = getColumNum(columArr.length);

		try {
			insertQuery(ETable.LOCKER, columQuery, columNum);

			stmt.setString(1, userUUID);
			stmt.setInt(2, lockerData.id);
			stmt.setString(3, lockerData.pw);
			stmt.setInt(4, 0);
			Timestamp time = new Timestamp(Calendar.getInstance().getTimeInMillis());
			stmt.setTimestamp(5, time);
			rs = stmt.executeQuery();

			close();

		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	public LockerData findUserLocker(String uuid) {

		try {
			findQuery(ETable.LOCKER, "*", "uuid = ? and ISEXIT = 0");

			stmt.setString(1, uuid);
			rs = stmt.executeQuery();
			if (rs.next()) {

				for (LockerData locker : DataManager.getInstance().lockerList) {
					if (locker.id == rs.getInt("id")) {
						LockerData data = new LockerData(locker.id, locker.name, locker.price);
						data.setPW(rs.getString("pw"));
						return locker;
					}

				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<LockerData> getLockerIDList() {

		ArrayList<LockerData> list = new ArrayList<LockerData>();
		try {
			ResultSet rs = getRS(ETable.LOCKER, "*", "ISEXIT = 0");

			while (rs.next()) {
				for (LockerData data : DataManager.getInstance().lockerList) {

					if (data.id == rs.getInt("ID")) {
						data.setPW(rs.getString("pw"));
						list.add(data);
					}
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public boolean exitLocker(String uuid) {
		updateQuery(ETable.LOCKER, "ISEXIT", "1", "uuid = ?");

		try {
			stmt.setString(1, uuid);
			rs = stmt.executeQuery();

			close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}
}
