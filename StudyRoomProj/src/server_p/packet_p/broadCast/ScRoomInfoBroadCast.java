package server_p.packet_p.broadCast;

import java.util.ArrayList;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;

public class ScRoomInfoBroadCast extends PacketBase {

	public ArrayList<RoomProduct> roomList;

	ScRoomInfoBroadCast(ArrayList<RoomProduct> roomList) {
		this.roomList = roomList;
	}
}
