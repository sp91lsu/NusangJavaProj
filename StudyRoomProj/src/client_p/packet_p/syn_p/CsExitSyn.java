package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsExitSyn extends PacketBase {
 
	public String uuid;
	
	public CsExitSyn(String uuid) {
		this.uuid = uuid;
	}
}
