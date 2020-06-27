package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsDuplicateIDSyn extends PacketBase {
	public String id;
	public boolean is_hp;

	public CsDuplicateIDSyn(String id, boolean is_hp) {
		this.id = id;
		this.is_hp=is_hp;
	}
}
