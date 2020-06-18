package server_p;

import java.util.HashMap;

import client_p.packet_p.syn_p.*;
import packetBase_p.*;

public class PacketMap {

	HashMap<Class, ServerPacketMethod> map = new HashMap<Class, ServerPacketMethod>();

	PacketClient client;

	PacketMap() {
		map.put(CsLoginSyn.class, new MethLoginSyn()); // 로그인
		map.put(CsSignUpSyn.class, new MethSignUpSyn()); // 회원가입
		map.put(CsChatConnectSyn.class, new MethSignUpSyn()); //채팅연결 요청 
		map.put(CsChatSyn.class, new MethSignUpSyn()); //채팅 
		map.put(CsVerifySyn.class, new MethSignUpSyn()); //결제 
		
	}

	void receivePacket(PacketClient pClient, PacketBase packet) {

		System.out.println("SERVER RECEIVE :" + packet.getClass());
		map.get(packet.getClass()).action(pClient, packet);
	}

}
