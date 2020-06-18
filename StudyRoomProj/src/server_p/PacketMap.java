package server_p;

import java.util.HashMap;

import client_p.packet_p.syn_p.*;
import packetBase_p.*;

public class PacketMap {

	HashMap<Class, ServerPacketMethod> map = new HashMap<Class, ServerPacketMethod>();

	PacketClient client;

	PacketMap() {
		map.put(CsLoginSyn.class, new MethLoginSyn()); // �α���
		map.put(CsSignUpSyn.class, new MethSignUpSyn()); // ȸ������
		map.put(CsChatConnectSyn.class, new MethSignUpSyn()); //ä�ÿ��� ��û 
		map.put(CsChatSyn.class, new MethSignUpSyn()); //ä�� 
		map.put(CsVerifySyn.class, new MethSignUpSyn()); //���� 
		
	}

	void receivePacket(PacketClient pClient, PacketBase packet) {

		System.out.println("SERVER RECEIVE :" + packet.getClass());
		map.get(packet.getClass()).action(pClient, packet);
	}

}
