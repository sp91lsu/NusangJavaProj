package manager_p.ack_p;

import packetBase_p.PacketBase;

public class MsChatConnectAck extends PacketBase {

	public boolean isConnect;

	public MsChatConnectAck(boolean isConnect) {
		super();
		this.isConnect = isConnect;
	}

}
