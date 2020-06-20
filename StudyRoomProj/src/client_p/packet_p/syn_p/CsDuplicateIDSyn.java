package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsDuplicateIDSyn extends PacketBase {
	public String id;

	CsDuplicateIDSyn(String id) {
		this.id = id;
	}
}
