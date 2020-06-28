package server_p.packet_p.ack_p;

import java.util.ArrayList;

import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScExitAck extends ResultPacketBase {

	public ArrayList<RoomProduct> reserListAll;
	public ArrayList<RoomProduct> myReserList;
	public ArrayList<RoomProduct> myExitList;
	public ArrayList<LockerData> lockerList;

	public ScExitAck(EResult eResult, ArrayList<RoomProduct> reserListAll, ArrayList<RoomProduct> myReserList,
			ArrayList<RoomProduct> myExitList, ArrayList<LockerData> lockerList) {
		super(eResult);
		this.reserListAll = reserListAll;
		this.myReserList = myReserList;
		this.myExitList = myExitList;
		this.lockerList = lockerList;
	}

}
