package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class MsLoginSyn extends PacketBase {

	public String id;
	public String pw;

	public MsLoginSyn(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

}
