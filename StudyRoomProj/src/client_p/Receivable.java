package client_p;

import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.*;

//Ŭ���̾�Ʈ�� ó���� ��Ŷ �޼ҵ� 

public interface Receivable {

	void receive(PacketBase packet);
}

//ȸ������ 
class ReceiveSignUpAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScSignInUpAck ack = (ScSignInUpAck) packet;
		
	}
}

//�α��� ���� 
class ReceiveLoginAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScLoginAck ack = (ScLoginAck) packet;
		if (ack.eResult == EResult.SUCCESS) {
			
		}
	}
}

//����
class ReceiveVerifyAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScBuyAck ack = (ScBuyAck) packet;
		
	}
}

//ä�� ����õ� ���� 
class ReceiveChatConnectAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScChatConnectAck ack = (ScChatConnectAck) packet;
		
	}
}


