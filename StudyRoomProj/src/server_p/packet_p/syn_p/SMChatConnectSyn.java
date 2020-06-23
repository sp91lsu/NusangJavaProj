package server_p.packet_p.syn_p;

import packetBase_p.EResult;
import packetBase_p.PacketBase;
import packetBase_p.ResultPacketBase;

public class SMChatConnectSyn extends ResultPacketBase {

	public SMChatConnectSyn(EResult eResult) {
		super(eResult);
	}

	public String managerIp;
	public String clientIp;

	public void setCIP(String cip) {
		clientIp = cip;
	}

	public void setManagerIp(String managerIp) {
		this.managerIp = managerIp;
	}
}
