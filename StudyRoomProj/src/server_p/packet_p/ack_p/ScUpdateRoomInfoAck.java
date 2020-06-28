package server_p.packet_p.ack_p;

import java.util.ArrayList;

import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScUpdateRoomInfoAck extends ResultPacketBase {

	public ArrayList<RoomProduct> roomListAll;
	public ArrayList<RoomProduct> myReserList;
	public ArrayList<RoomProduct> myExitList;
	public ArrayList<LockerData> lockerList;

	public ScUpdateRoomInfoAck(EResult eResult, ArrayList<RoomProduct> roomListAll, ArrayList<RoomProduct> myReserList,
			ArrayList<RoomProduct> myExitList, ArrayList<LockerData> lockerList) {
		super(eResult);
		this.roomListAll = roomListAll;
		this.myReserList = myReserList;
		this.myExitList = myExitList;
		this.lockerList = lockerList;

		// TODO Auto-generated constructor stub
	}
}
