package server_p.packet_p.ack_p;

import java.util.ArrayList;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScBuyRoomAck extends ResultPacketBase {

	public ArrayList<RoomProduct> roomList;
	public ArrayList<RoomProduct> myReserList;
	public ArrayList<RoomProduct> exitList;

	public ScBuyRoomAck(EResult eResult, ArrayList<RoomProduct> roomList, ArrayList<RoomProduct> myReserList,
			ArrayList<RoomProduct> myExitList) {
		super(eResult);

		this.roomList = roomList;
		this.myReserList = myReserList;
		this.exitList = myExitList;
	}
}