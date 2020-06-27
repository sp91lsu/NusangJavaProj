package client_p.packet_p.syn_p;

import packetBase_p.PacketBase;

public class CsDuplicatePhoneSyn extends PacketBase{

	public String Phone;

	public CsDuplicatePhoneSyn(String phone) {
		super();
		Phone = phone;
	}
	
}
