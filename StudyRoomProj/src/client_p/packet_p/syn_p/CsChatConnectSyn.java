package client_p.packet_p.syn_p;

import data_p.user_p.UserData;
import packetBase_p.PacketBase;

public class CsChatConnectSyn extends PacketBase {

	public UserData userData;

	public CsChatConnectSyn(UserData userData) {
		this.userData = userData;
	}
}
