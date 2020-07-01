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

				RoomProduct roomProduct = new RoomProduct(rs.getInt("ID"), rs.getString("Name"), rs.getLong("Price"),
						rs.getInt("PersonNum"));

				roomMap.put(roomProduct.id, roomProduct);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return roomMap;
	}
}
