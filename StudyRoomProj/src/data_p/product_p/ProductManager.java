package data_p.product_p;

import java.util.ArrayList;
import java.util.HashMap;

import data_p.ExcelReader;
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

	public static void main(String[] args) {
		ProductManager pm = new ProductManager();
		pm.RoomSetting();
	}

	void RoomSetting() {
		ExcelReader roomReader = new ExcelReader();
		roomReader.read("RoomData.xlsx");

		ArrayList<String> idList = roomReader.getList("ID");
		ArrayList<String> nameList = roomReader.getList("Name");
		ArrayList<String> priceList = roomReader.getList("Price");

		for (int i = 0; i < idList.size(); i++) {

			RoomProduct roomProduct = new RoomProduct(idList.get(i), nameList.get(i),
					Integer.parseInt(priceList.get(i)));

			roomList.add(roomProduct);
		}

	}

}
