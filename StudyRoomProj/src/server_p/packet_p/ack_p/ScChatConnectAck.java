package server_p.packet_p.ack_p;

import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class ScChatConnectAck extends ResultPacketBase {

	public String cip;
	public String mip;
	public boolean isConnect;

	public ScChatConnectAck(EResult eResult, String cip, String mip, boolean isConnect) {
		super(eResult);
		this.cip = cip;
		this.mip = mip;
		this.isConnect = isConnect;
		// TODO Auto-generated constructor stub
	}

}
