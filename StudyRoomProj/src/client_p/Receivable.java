package client_p;

import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.*;

//클라이언트가 처리할 패킷 메소드 

public interface Receivable {

	void receive(PacketBase packet);
}

//회원가입 
class ReceiveSignUpAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScSignInUpAck ack = (ScSignInUpAck) packet;
		
	}
}

//로그인 응답 
class ReceiveLoginAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScLoginAck ack = (ScLoginAck) packet;
		if (ack.eResult == EResult.SUCCESS) {
			
		}
	}
}

//결제
class ReceiveVerifyAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScBuyAck ack = (ScBuyAck) packet;
		
	}
}

//채팅 연결시도 응답 
class ReceiveChatConnectAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScChatConnectAck ack = (ScChatConnectAck) packet;
		
	}
}


