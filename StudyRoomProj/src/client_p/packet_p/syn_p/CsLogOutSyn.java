package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsLogOutSyn extends PacketBase {

	public String uuid;

	public CsLogOutSyn(String uuid) {
		super();
		this.uuid = uuid;
	}
}
