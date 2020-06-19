package client_p;

import client_p.packet_p.syn_p.CsBuyRoomSyn;
import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
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

			RoomProduct rp = DataManager.getInstance().roomList.get(0);

			System.out.println("------------------------------" + ack.userdata.uuid);

			DataManager.getInstance().userData = ack.userdata;

			rp.setDate(2020, 10, 1, DataManager.getInstance().timeList);

		
			
			CsBuyRoomSyn roomPacket = new CsBuyRoomSyn(rp, DataManager.getInstance().userData.uuid);

			MyServer.getInstance().sendPacket(roomPacket);
		}
	}
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
