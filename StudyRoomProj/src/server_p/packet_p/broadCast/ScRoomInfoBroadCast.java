package server_p.packet_p.broadCast;

import java.util.ArrayList;

import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import packetBase_p.ResultPacketBase;

public class ScRoomInfoBroadCast extends ResultPacketBase {

	public ArrayList<RoomProduct> roomListAll;

	public ScRoomInfoBroadCast(EResult eResult, ArrayList<RoomProduct> roomListAll) {
		super(eResult);
		this.roomListAll = roomListAll;

		// TODO Auto-generated constructor stub
	}
}
