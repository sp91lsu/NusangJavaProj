package server_p.packet_p.ack_p;

import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScChatConnectAck extends ResultPacketBase {

	public String cip;
	public String mip;

	public ScChatConnectAck(EResult eResult, String cip, String mip) {
		super(eResult);
		this.cip = cip;
		this.mip = mip;
		// TODO Auto-generated constructor stub
	}

}
