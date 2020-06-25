package server_p.packet_p.ack_p;

import java.util.ArrayList;
import java.util.UUID;

import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class SmCurrMemListAck extends ResultPacketBase {

	public ArrayList<UserData> userList;

	public SmCurrMemListAck(EResult eResult, ArrayList<UserData> userList) {
		super(eResult);
		this.userList = userList;
	}

}
