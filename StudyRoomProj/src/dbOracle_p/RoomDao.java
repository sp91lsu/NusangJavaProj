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
			for (TimeData time : room.timeList) {
				stmt.setString(1, userUUID);
				stmt.setInt(2, room.id);
				stmt.setLong(3, room.price);
				Timestamp timeStamp = new Timestamp(room.calendar.getTimeInMillis());
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

	// 재고 모든 정보 불러오기
	public ArrayList<RoomProduct> getTodayRoomInfo() throws Exception {
		findQuery(ETable.INVENTORY, "*");

		rs = stmt.executeQuery(query);

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
		ArrayList<TimeData> timeList = new ArrayList<TimeData>();

		if (rs.next()) {

			rs.getString("");
			Timestamp time = rs.getTimestamp("STARTDATE");

			// 오늘자 룸 정보
			if (time.getDate() == Calendar.getInstance().get(Calendar.DATE)) {
				RoomProduct roomModel = DataManager.getInstance().roomMap.get(rs.getInt("ID"));
				RoomProduct room = new RoomProduct(roomModel.id, roomModel.name, roomModel.price, roomModel.personNum);
				timeList.add(new TimeData(0, time.getHours(), 0));
				room.setDate(time.getMonth(), timeList);

				roomList.add(room);
			}
		}

		rs.close();
		return roomList;
	}
}
