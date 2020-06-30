package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class MsLoginSyn extends PacketBase {

	public String id;
	public String pw;
	public boolean isID;

	public MsLoginSyn(String id, String pw, boolean isID) {
		this.id = id;
		this.pw = pw;
		this.isID = isID;
	}

}
