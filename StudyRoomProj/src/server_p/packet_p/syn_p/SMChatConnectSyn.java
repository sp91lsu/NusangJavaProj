package server_p.packet_p.syn_p;

import data_p.user_p.UserData;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import packetBase_p.ResultPacketBase;

public class SMChatConnectSyn extends ResultPacketBase {
	public String managerIp;
	public String clientIp;
	public UserData userdata;
	

	public SMChatConnectSyn(EResult eResult,UserData userdata) {
		super(eResult);
		this.userdata = userdata;
	}


	public void setCIP(String cip) {
		clientIp = cip;
	}

	public void setManagerIp(String managerIp) {
		this.managerIp = managerIp;
	}
}
