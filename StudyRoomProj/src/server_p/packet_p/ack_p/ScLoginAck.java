package server_p.packet_p.ack_p;

import java.util.ArrayList;
import java.util.UUID;

import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScLoginAck extends ResultPacketBase {

	public UserData userdata;
	public ArrayList<RoomProduct> roomList;

	public ScLoginAck(EResult eResult, UserData userdata, ArrayList<RoomProduct> roomList) {
		super(eResult);
		this.userdata = userdata;
		this.roomList = roomList;
	}

}
