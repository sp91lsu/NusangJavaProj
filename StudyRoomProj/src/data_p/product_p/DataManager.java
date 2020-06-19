package data_p.product_p;

import java.util.ArrayList;

import data_p.ExcelReader;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;

public class DataManager {

	private static DataManager instance;

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	public UserData userData = null;
	public ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
	public ArrayList<TimeData> timeList = new ArrayList<TimeData>();

	String managerKey = "";

	DataManager() {
		RoomSetting();
		keySetting();
		TimeDataSetting();
	}

	public static void main(String[] args) {
		DataManager pm = new DataManager();
	}

	void RoomSetting() {
		ExcelReader reader = new ExcelReader();
		reader.read("RoomData.xlsx");

		ArrayList<String> idList = reader.getList("ID");
		ArrayList<String> nameList = reader.getList("Name");
		ArrayList<String> priceList = reader.getList("Price");
		ArrayList<String> pNumList = reader.getList("PersonNum");

		for (int i = 0; i < idList.size(); i++) {

			RoomProduct roomProduct = new RoomProduct(idList.get(i), nameList.get(i),
					Integer.parseInt(priceList.get(i)), Integer.parseInt(pNumList.get(i)));

			System.out.println(roomProduct);
			roomList.add(roomProduct);
		}
	}

	void TimeDataSetting() {
		ExcelReader reader = new ExcelReader();
		reader.read("TimeTable.xlsx");

		ArrayList<String> idList = reader.getList("id");
		ArrayList<String> startList = reader.getList("start");
		ArrayList<String> endList = reader.getList("end");
		ArrayList<String> priceList = reader.getList("price");

		for (int i = 0; i < idList.size(); i++) {

//			System.out.println(idList.get(i));
//			System.out.println(startList.get(i));
//			System.out.println(endList.get(i));
//			System.out.println(priceList.get(i));
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
			managerKey = keyList.get(i);
			//System.out.println(managerKey);
		}
	}
}
