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
	ArrayList<TimeData> timeList = new ArrayList<TimeData>();
	String key = "";

	DataManager() {
		RoomSetting();
		keySetting();
		TimeDataSetting();
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

	void TimeDataSetting() {
		ExcelReader roomReader = new ExcelReader();
		roomReader.read("TimeTable.xlsx");

		ArrayList<String> idList = roomReader.getList("id");
		ArrayList<String> startList = roomReader.getList("start");
		ArrayList<String> endList = roomReader.getList("end");
		ArrayList<String> priceList = roomReader.getList("price");

		for (int i = 0; i < idList.size(); i++) {

			System.out.println(idList.get(i));
			System.out.println(startList.get(i));
			System.out.println(endList.get(i));
			System.out.println(priceList.get(i));
			TimeData data = new TimeData(Integer.parseInt(idList.get(i)), Integer.parseInt(startList.get(i)),
					Integer.parseInt(endList.get(i)), Long.parseLong(priceList.get(i)));
			
			timeList.add(data);
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
