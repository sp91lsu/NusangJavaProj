package server_p;

import java.util.HashMap;

import client_p.packet_p.ack_p.MsChatConnectAck;
import client_p.packet_p.syn_p.*;
import packetBase_p.*;

public class PacketMap {

	HashMap<Class, ServerPacketMethod> map = new HashMap<Class, ServerPacketMethod>();

	SocketClient client;

	PacketMap() {
		map.put(CsLoginSyn.class, new MethLoginSyn()); // 로그인
		map.put(CsSignUpSyn.class, new MethSignUpSyn()); // 회원가입
		map.put(CsChatConnectSyn.class, new MethChatConnectSyn()); // 채팅연결 요청

		map.put(MsChatConnectAck.class, new MethMSChatConnectAck());
		map.put(CsChatSyn.class, new MethCsChatSyn());
		map.put(CsBuyRoomSyn.class, new MethBuyRoomSyn()); // 결제
		map.put(CsMoveSeatSyn.class, new MethMoveSeatSyn()); // 자리이동
		map.put(CsCloseSyn.class, new MethCloseSyn()); // 소켓 종료
		
		map.put(CsUpdateRoomSyn.class, new MethUpdateRoomSyn()); // 룸 정보 업데이트

	}

	void receivePacket(SocketClient pClient, PacketBase packet) {

		System.out.println("SERVER RECEIVE :" + packet.getClass());
		map.get(packet.getClass()).receive(pClient, packet);
	}

}
