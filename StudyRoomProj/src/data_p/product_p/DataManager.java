package data_p.product_p;

import java.util.ArrayList;
import java.util.HashMap;

import client_p.Receivable;
import client_p.ui_p.BaseFrame;
import data_p.ExcelReader;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScGetRoomDataAck;

public class DataManager implements Receivable {

	private static DataManager instance;

	public static DataManager getInstance() {
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	public UserData userData = null;
	public HashMap<Integer, RoomProduct> roomMap = new HashMap<Integer, RoomProduct>();
	public ArrayList<LockerData> lockerList = new ArrayList<LockerData>();
	public ArrayList<TimeData> timeList = new ArrayList<TimeData>();

	String managerKey = "";
	private ArrayList<String> idList;
	private ArrayList<String> nameList;

	DataManager() {
		// RoomSetting();
		keySetting();
		TimeDataSetting();
		lockerSetting();
	}

	public static void main(String[] args) {
		DataManager pm = new DataManager();
	}

	public String roomName(String roomID) {
		String str = "";
		for (int i = 0; i < idList.size(); i++) {
			if (idList.get(i).equals(roomID)) {
				str = nameList.get(i);
			}
		}
		return str;
	}

//	void RoomSetting() {
//		ExcelReader reader = new ExcelReader();
//		reader.read("RoomData.xlsx");
//
//		idList = reader.getList("ID");
//		nameList = reader.getList("Name");
//		ArrayList<String> priceList = reader.getList("Price");
//		ArrayList<String> pNumList = reader.getList("PersonNum");
//
//		for (int i = 0; i < idList.size(); i++) {
//
//			RoomProduct roomProduct = new RoomProduct(Integer.parseInt(idList.get(i)), nameList.get(i),
//					Integer.parseInt(priceList.get(i)), Integer.parseInt(pNumList.get(i)));
//
//			roomMap.put(roomProduct.id, roomProduct);
//		}
//	}

	void TimeDataSetting() {
		ExcelReader reader = new ExcelReader();
		reader.read("TimeTable.xlsx");

		ArrayList<String> idList = reader.getList("id");
		ArrayList<String> valueList = reader.getList("value");
		ArrayList<String> priceList = reader.getList("price");

		for (int i = 0; i < idList.size(); i++) {

			TimeData data = new TimeData(Integer.parseInt(idList.get(i)), Integer.parseInt(valueList.get(i)),
					Long.parseLong(priceList.get(i)));

			timeList.add(data);
		}
	}

	void keySetting() {
		ExcelReader roomReader = new ExcelReader();
		roomReader.read("ManagerKeyData.xlsx");

		ArrayList<String> keyList = roomReader.getList("key");
		for (int i = 0; i < keyList.size(); i++) {
			managerKey = keyList.get(i);
			// System.out.println(managerKey);
		}
	}

	void lockerSetting() {
		ExcelReader roomReader = new ExcelReader();
		roomReader.read("LockerData.xlsx");

		ArrayList<String> idList = roomReader.getList("id");
		ArrayList<String> nameList = roomReader.getList("name");
		ArrayList<String> pwList = roomReader.getList("pw");
		ArrayList<String> priceList = roomReader.getList("price");

		for (int i = 0; i < idList.size(); i++) {

			int id = Integer.parseInt(idList.get(i));
			long price = Long.parseLong(priceList.get(i));

			LockerData data = new LockerData(id, nameList.get(i), price);
			lockerList.add(data);
		}
	}

	public LockerData getLockerData(int id) {
		for (LockerData lockerData : lockerList) {
			if (lockerData.id == id) {
				return lockerData;
			}
		}

		return null;
	}

	@Override
	public void receive(PacketBase packet) {

		if (packet.getClass() == ScGetRoomDataAck.class) {
			ScGetRoomDataAck ack = (ScGetRoomDataAck) packet;

			roomMap = ack.roomMap;
			BaseFrame.getInstance().startFrame();
		}
	}
}
