package server_p;

import java.util.HashMap;

import client_p.packet_p.syn_p.CsBuyLockerSyn;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import client_p.packet_p.syn_p.CsCloseSyn;
import client_p.packet_p.syn_p.CsDuplicateIDSyn;
import client_p.packet_p.syn_p.CsExitSyn;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.packet_p.syn_p.CsMoveSeatSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
import client_p.packet_p.syn_p.CsUpdateRoomSyn;
import manager_p.ack_p.SmChatConnectAck;
import manager_p.syn_p.MsAllMemListSyn;
import manager_p.syn_p.MsCurrMemListSyn;
import manager_p.syn_p.MsGiveMeResvRoomSyn;
import manager_p.syn_p.MsMemSearchSyn;
import packetBase_p.PacketBase;

public class PacketMap {

	HashMap<Class, ServerPacketMethod> map = new HashMap<Class, ServerPacketMethod>();

	SocketClient client;

	PacketMap() {
		map.put(CsLoginSyn.class, new MethLoginSyn()); // 로그인
		map.put(CsSignUpSyn.class, new MethSignUpSyn()); // 회원가입
		map.put(CsChatConnectSyn.class, new MethChatConnectSyn()); // 채팅연결 요청
		map.put(SmChatConnectAck.class, new MethMSChatConnectAck());
		map.put(CsChatSyn.class, new MethCsChatSyn());
		map.put(CsBuyRoomSyn.class, new MethBuyRoomSyn()); // 결제
		map.put(CsMoveSeatSyn.class, new MethMoveSeatSyn()); // 자리이동
		map.put(CsCloseSyn.class, new MethCloseSyn()); // 소켓 종료
		map.put(CsExitSyn.class, new MethExitSyn()); // 소켓 종료
		map.put(CsUpdateRoomSyn.class, new MethUpdateRoomSyn()); // 룸 정보 업데이
		map.put(MsCurrMemListSyn.class, new MethMsCurrMemListSyn()); //현재 회원리스트
		map.put(MsAllMemListSyn.class, new MethMsAllMemListSyn()); //현재 회원리스트
		map.put(MsMemSearchSyn.class, new MethMsMemSearchSyn()); //회원 검색
		map.put(CsBuyLockerSyn.class, new MethBuyLockerSyn()); //사물함 구매
		map.put(CsDuplicateIDSyn.class, new MethDuplicateIDSyn()); //중복아이디 확인 
		map.put(MsGiveMeResvRoomSyn.class, new MethMsGiveMeResvRoomSyn()); //예약룸  
	}

	void receivePacket(SocketClient pClient, PacketBase packet) {

		System.out.println("SERVER RECEIVE :" + packet.getClass());
		map.get(packet.getClass()).receive(pClient, packet);
	}

}
