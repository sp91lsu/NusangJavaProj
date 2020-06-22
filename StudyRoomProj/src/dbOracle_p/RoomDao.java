package dbOracle_p;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import data_p.product_p.DataManager;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;

public class RoomDao extends DBProcess {

	public boolean insertRoomInfo(String userUUID, RoomProduct room) {
		String[] calumArr = { "UUID", "ID", "PRICE", "STARTDATE" };

		String calumQuery = getCalum(calumArr);
		String calumNum = getCalumNum(calumArr.length);

		try {
			for (Calendar cal : room.calendarList) {
				stmt.setString(1, userUUID);
				stmt.setInt(2, room.id);
				stmt.setLong(3, room.price);
				Timestamp timeStamp = new Timestamp(cal.getTimeInMillis());
				stmt.setTimestamp(4, timeStamp);

				insertQuery(ETable.ACCOUNT, calumQuery, calumNum);

				rs = stmt.executeQuery(query);
			}

			close();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return true;
	}

	// ��� ��� ���� �ҷ�����
	public ArrayList<RoomProduct> getTodayRoomInfo() throws Exception {
		findQuery(ETable.INVENTORY, "*");

		rs = stmt.executeQuery(query);

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
		ArrayList<Calendar> timeList = new ArrayList<Calendar>();

		if (rs.next()) {

			Timestamp time = rs.getTimestamp("STARTDATE");

			// ������ �� ����
			if (time.getDate() == Calendar.getInstance().get(Calendar.DATE)) {
				RoomProduct roomModel = DataManager.getInstance().roomMap.get(rs.getInt("ID"));
				RoomProduct room = new RoomProduct(roomModel.id, roomModel.name, roomModel.price, roomModel.personNum);
				Calendar cal = Calendar.getInstance();

				cal.setTimeInMillis(time.getTime());
				timeList.add(cal);
				room.setDate(timeList);
				roomList.add(room);
			}
		}

		rs.close();
		return roomList;
	}
}
