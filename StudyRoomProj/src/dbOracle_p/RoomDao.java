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
				// stmt.setInt(5, room.personNum);
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

	public ResultSet getRoomInfoRS(String... keys) throws SQLException {

		findQuery(ETable.INVENTORY, keys);
		stmt = con.prepareStatement(query);

		return stmt.executeQuery();
	}

	// 재고 모든 정보 불러오기
	public ArrayList<RoomProduct> getRoomInfo(String... keys) throws Exception {

		rs = getRoomInfoRS(keys);

		ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();

		System.out.println("로그인 시 룸 데이터");
		while (rs.next()) {

			ArrayList<Calendar> timeList = new ArrayList<Calendar>();

			Timestamp time = rs.getTimestamp("STARTDATE");
			RoomProduct room = null;
			RoomProduct roomModel = DataManager.getInstance().roomMap.get(rs.getInt("ID"));
			room = new RoomProduct(roomModel.id, roomModel.name, roomModel.price, rs.getInt("PERSONNUM"));
			Calendar cal = Calendar.getInstance();

			cal.setTimeInMillis(time.getTime());
			timeList.add(cal);
			room.setDate(rs.getString("UUID"), timeList);
			roomList.add(room);
		}

		System.out.println("룸 리스트 갯수" + roomList.size());
		rs.close();
		return roomList;
	}

}
