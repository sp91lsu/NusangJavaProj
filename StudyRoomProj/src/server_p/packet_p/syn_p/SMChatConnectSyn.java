package server_p.packet_p.syn_p;

import packetBase_p.EResult;
import packetBase_p.PacketBase;

public class SMChatConnectSyn extends PacketBase {

	public String managerIp;
	public String clientIp;

	public void setCIP(String cip) {
		clientIp = cip;
	}

	public void setManagerIp(String managerIp) {
		this.managerIp = managerIp;
	}
}
