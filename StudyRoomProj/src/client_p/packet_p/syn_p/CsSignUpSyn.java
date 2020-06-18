package client_p.packet_p.syn_p;

import data_p.user_p.UserData;
import packetBase_p.PacketBase;

public class CsSignUpSyn extends PacketBase {

	public UserData userData;

	public CsSignUpSyn(UserData userData) {
		super();
		this.userData = userData;
	}

}
