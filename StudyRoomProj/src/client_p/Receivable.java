package client_p;

import client_p.packet_p.syn_p.CsBuyRoomSyn;
import client_p.ui_p.BaseFrame;
import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.*;

//Ŭ���̾�Ʈ�� ó���� ��Ŷ �޼ҵ� 

public interface Receivable {

	void receive(PacketBase packet);
}

//����
class ReceiveVerifyAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScBuyRoomAck ack = (ScBuyRoomAck) packet;
	}
}

//ä�� ����õ� ���� 
class ReceiveChatConnectAck implements Receivable {
	@Override
	public void receive(PacketBase packet) {

		ScChatConnectAck ack = (ScChatConnectAck) packet;

	}
}
