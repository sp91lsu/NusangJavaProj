package dbOracle_p;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
import data_p.product_p.room_p.RoomTimeData;
import oracle.net.aso.d;

public class RoomNowDao extends DBProcess {

	// 룸 정보 세팅하기
	public void settingRoomData() {

		try {
			ResultSet rs = getRS(ETable.NOW_ROOM_DATA, "*");
			// Listener refused the connection with the following error:
			DataManager.getInstance().roomMap = resToMap(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public HashMap<Integer, RoomProduct> resToMap(ResultSet rs) {
		HashMap<Integer, RoomProduct> roomMap = new HashMap<Integer, RoomProduct>();
		try {
			while (rs.next()) {

				RoomProduct roomProduct = new RoomProduct(rs.getInt("Room_ID"), rs.getString("Room_Name"),
						rs.getLong("Room_Price"), rs.getInt("PersonNum"));

				roomMap.put(roomProduct.id, roomProduct);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roomMap;
	}

	// update now_room_data
	// set
	// room_price = 2222
	// where room_id = 1000;
	public void upt_NowRoomData(int room_id, int room_price) {
		query = "update now_room_data set room_price = " + room_price + " where room_id = " + room_id;
		try {
			stmt = con.prepareStatement(query);
			stmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		close();
	}

	public boolean verifyRoom(int id, long price) {

		boolean pass = false;
		findQuery(ETable.NOW_ROOM_DATA, "ROOM_PRICE", "ROOM_ID = ?");

		try {
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			if (rs.next()) {
				pass = rs.getInt("ROOM_PRICE") == price;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return pass;
	}

}
