package client_p;

import java.util.HashMap;

import packetBase_p.ResultPacketBase;
import server_p.packet_p.ack_p.*;

public class PacketMap {

	private static PacketMap instance;

	public static PacketMap getInstance() {
		if (instance == null) {
			instance = new PacketMap();
		}

		return instance;
	}

	public HashMap<Class, Receivable> map = new HashMap<Class, Receivable>();

	PacketMap() {

	//	map.put(ScLoginAck.class, new ReceiveLoginAck()); // 로그인 응답
		map.put(ScSignInUpAck.class, new ReceiveSignUpAck()); // 회원가입
		map.put(ScBuyRoomAck.class, new ReceiveVerifyAck()); // 회원가입
		map.put(ScChatConnectAck.class, new ReceiveChatConnectAck()); // 채팅 연결 요청에 대한 응답

	}

	void receivePacket(PacketProccess pClient, ResultPacketBase packet) {

		System.out.println("CLIENT RECEIVE : " + packet.getClass());
		System.out.println("RESULT : " + packet.eResult + "\n");
		map.get(packet.getClass()).receive(packet);
	}

}
