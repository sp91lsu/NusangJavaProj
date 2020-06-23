package server_p.packet_p.ack_p;

import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScChatConnectAck extends ResultPacketBase {

	public String managerIp;
	public String clientIp;

	public ScChatConnectAck(EResult eResult) {
		super(eResult);
	}

	public void setCIP(String cip) {
		clientIp = cip;
	}

	public void setManagerIp(String managerIp) {
		this.managerIp = managerIp;
	}
}
