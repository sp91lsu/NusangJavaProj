package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsCloseSyn extends PacketBase {

	public String uuid;

	public CsCloseSyn(String uuid) {
		super();
		this.uuid = uuid;
	}
}
