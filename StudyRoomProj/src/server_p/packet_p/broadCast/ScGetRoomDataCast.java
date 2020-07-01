package server_p.packet_p.broadCast;

import java.util.HashMap;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScGetRoomDataCast extends ResultPacketBase {

	public HashMap<Integer, RoomProduct> roomMap = new HashMap<Integer, RoomProduct>();

	public ScGetRoomDataCast(EResult eResult, HashMap<Integer, RoomProduct> roomMap) {
		super(eResult);
		this.roomMap = roomMap;
	}

}
