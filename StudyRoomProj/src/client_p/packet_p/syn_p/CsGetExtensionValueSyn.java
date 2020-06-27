package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsGetExtensionValueSyn extends PacketBase {

	public String uuid;
	public int pid;

	public CsGetExtensionValueSyn(String uuid, int pid) {
		super();
		this.uuid = uuid;
		this.pid = pid;
	}

}
