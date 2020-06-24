package server_p.packet_p.broadCast;

import java.util.ArrayList;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import packetBase_p.ResultPacketBase;

public class ScRoomInfoBroadCast extends ResultPacketBase {

	public ArrayList<RoomProduct> roomList;

	public ScRoomInfoBroadCast(EResult eResult, ArrayList<RoomProduct> roomList) {
		super(eResult);
		this.roomList = roomList;
		// TODO Auto-generated constructor stub
	}
}
