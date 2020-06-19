package data_p.product_p;

import java.util.ArrayList;

import data_p.ExcelReader;
import data_p.product_p.room_p.RoomProduct;

public class DataManager {

	private static DataManager instance;

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
	String key = "";

	DataManager() {
		RoomSetting();
		keySetting();
	}

	public static void main(String[] args) {
		DataManager pm = new DataManager();
	}

	void RoomSetting() {
		ExcelReader roomReader = new ExcelReader();
		roomReader.read("RoomData.xlsx");

		ArrayList<String> idList = roomReader.getList("ID");
		ArrayList<String> nameList = roomReader.getList("Name");
		ArrayList<String> priceList = roomReader.getList("Price");
		ArrayList<String> pNumList = roomReader.getList("PersonNum");
		for (int i = 0; i < idList.size(); i++) {

			RoomProduct roomProduct = new RoomProduct(idList.get(i), nameList.get(i),
					Integer.parseInt(priceList.get(i)), Integer.parseInt(pNumList.get(i)));

			System.out.println(roomProduct);
			roomList.add(roomProduct);
		}
	}

	void keySetting() {
		ExcelReader roomReader = new ExcelReader();
		roomReader.read("ManagerKeyData.xlsx");

		ArrayList<String> keyList = roomReader.getList("key");
		for (int i = 0; i < keyList.size(); i++) {
			key = keyList.get(i);
			System.out.println(key);
		}
	}
}
