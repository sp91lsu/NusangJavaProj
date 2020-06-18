package data_p.product_p;

import java.util.ArrayList;

import data_p.product_p.room_p.PrivateRoomProduct;
import data_p.product_p.room_p.PublicRoomProduct;
import data_p.product_p.room_p.RoomProduct;
import dbOracle_p.DBProccess;
import dbOracle_p.ETable;
import dbOracle_p.QueryObject;

public class ProductManager {

	ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();

	ProductManager() {

	}

	void RoomSetting() {
		//QueryObject qo = new QueryObject(null, null);
		//DBProccess.getInstance().findData(ETable.PRODUCT, "");
	}
}
