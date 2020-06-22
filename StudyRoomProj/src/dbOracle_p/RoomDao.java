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
		String[] calumArr = { "ID", "PRICE", "STARTDATE", "UUID" };

		String calumQuery = getCalum(calumArr);
		String calumNum = getCalumNum(calumArr.length);

		try {
			insertQuery(ETable.INVENTORY, calumQuery, calumNum);

			for (Calendar cal : room.calendarList) {
				stmt = con.prepareStatement(query);
				stmt.setInt(1, room.id);
				stmt.setLong(2, room.price);
				Timestamp timeStamp = new Timestamp(cal.getTimeInMillis());
				stmt.setTimestamp(3, timeStamp);
				stmt.setString(4, userUUID);

				rs = stmt.executeQuery();
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
		stmt = con.prepareStatement(query);

		rs = stmt.executeQuery();

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
		ArrayList<Calendar> timeList = new ArrayList<Calendar>();

		if (rs.next()) {

			Timestamp time = rs.getTimestamp("STARTDATE");

			// 오늘자 룸 정보
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
