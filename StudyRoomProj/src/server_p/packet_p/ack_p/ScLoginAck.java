package server_p.packet_p.ack_p;

import java.util.UUID;

import data_p.user_p.UserData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScLoginAck extends ResultPacketBase {

	public UserData userdata;

	public ScLoginAck(EResult eResult, UserData userdata) {
		super(eResult);
		this.userdata = userdata;
	}

}
