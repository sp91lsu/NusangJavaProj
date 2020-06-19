package server_p;

import java.util.HashMap;

import client_p.packet_p.syn_p.*;
import packetBase_p.*;

public class PacketMap {

	HashMap<Class, ServerPacketMethod> map = new HashMap<Class, ServerPacketMethod>();

	SocketClient client;

	PacketMap() {
		map.put(CsLoginSyn.class, new MethLoginSyn()); // �α���
		map.put(CsSignUpSyn.class, new MethSignUpSyn()); // ȸ������
		map.put(CsChatConnectSyn.class, new MethSignUpSyn()); //ä�ÿ��� ��û 
		map.put(CsChatSyn.class, new MethChatSyn()); //ä�� 
		map.put(CsBuyRoomSyn.class, new MethBuyRoomSyn()); //���� 
	}

	void receivePacket(SocketClient pClient, PacketBase packet) {

		System.out.println("SERVER RECEIVE :" + packet.getClass());
		map.get(packet.getClass()).receive(pClient, packet);
	}

}
