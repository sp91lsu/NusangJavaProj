package client_p.packet_p.syn_p;

import data_p.product_p.room_p.RoomProduct;
import packetBase_p.PacketBase;

public class CsUpdateRoomSyn extends PacketBase {

	public String uuid;

	public CsUpdateRoomSyn(String uuid) {
		this.uuid = uuid;
	}
}
