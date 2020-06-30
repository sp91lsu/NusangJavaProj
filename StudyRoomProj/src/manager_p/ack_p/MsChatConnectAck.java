package manager_p.ack_p;

import packetBase_p.PacketBase;

public class MsChatConnectAck extends PacketBase {

	public String managerIp;
	public String cIp;
	public boolean isConnect;

	public MsChatConnectAck(boolean isConnect) {
		super();
		this.isConnect = isConnect;
	}

	public void setManagerIp(String mip) {
		managerIp = mip;
	}

	public void setCip(String cip) {
		this.cIp = cip;
	}
}
