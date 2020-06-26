package data_p.product_p.room_p;

import java.io.Serializable;
import java.util.ArrayList;

public class RoomTimeData implements Serializable{
	public String roomName, userName, uuID;
	public ArrayList<String> hourList = new ArrayList<String>();
	public int roomID;
	public RoomTimeData(String roomName, String userName, String uuID, ArrayList<String> hourList, int date,
			int roomID) {
		this.roomName = roomName;
		this.userName = userName;
		this.uuID = uuID;
		this.hourList = hourList;
		this.roomID = roomID;
	}
	public RoomTimeData(String roomName, String userName) {
		this.roomName = roomName;
		this.userName = userName;
	}
	public RoomTimeData(String uuID, ArrayList<String> hourList, int date, int roomID) {
		this.uuID = uuID;
		this.hourList = hourList; 
		this.roomID = roomID;
	}
	
	String roomName(int roomID) {
		String str = "";
		
		return str;
	}
	
	
	
}
