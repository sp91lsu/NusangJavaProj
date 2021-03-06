package client_p.packet_p.syn_p;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;

public class CsMoveSeatSyn extends PacketBase {

	public String userUUID;
	public RoomProduct originRoom;
	public int moveSeatID;
	public long movePrice;

	public CsMoveSeatSyn(String userUUID, RoomProduct originRoom, int moveSeatID, long movePrice) {
		this.userUUID = userUUID;
		this.originRoom = originRoom;
		this.moveSeatID = moveSeatID;
		this.movePrice = movePrice;
	}
}
