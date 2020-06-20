package client_p.packet_p.broadCast;

import java.util.ArrayList;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;

public class ScRoomInfoCast extends PacketBase {

	public ArrayList<RoomProduct> roomInfoList;

	ScRoomInfoCast(ArrayList<RoomProduct> roomInfoList) {
		this.roomInfoList = roomInfoList;
	}
}
