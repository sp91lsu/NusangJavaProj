package server_p.packet_p.ack_p;

import java.util.ArrayList;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScBuyRoomAck extends ResultPacketBase {

	ArrayList<RoomProduct> roomList;

	public ScBuyRoomAck(EResult eResult) {
		super(eResult);
		// TODO Auto-generated constructor stub
	}
}