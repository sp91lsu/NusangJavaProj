package server_p;

import java.util.HashMap;

import client_p.packet_p.syn_p.CsBuyLockerSyn;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import client_p.packet_p.syn_p.CsCloseSyn;
import client_p.packet_p.syn_p.CsDuplicateIDSyn;
import client_p.packet_p.syn_p.CsExitSyn;
import client_p.packet_p.syn_p.CsGetRoomDataSyn;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.packet_p.syn_p.CsMoveSeatSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
import client_p.packet_p.syn_p.CsUpdateRoomSyn;
import manager_p.ack_p.MsChatConnectAck;
import manager_p.ack_p.MsUptRoomPrSyn;
import manager_p.syn_p.MsAllMemListSyn;
import manager_p.syn_p.MsCurrMemListSyn;
import manager_p.syn_p.MsGiveMeResvRoomSyn;
import manager_p.syn_p.MsMemSearchSyn;
import manager_p.syn_p.MsSalesInquirySyn;
import packetBase_p.PacketBase;

public class PacketMap {

	HashMap<Class, ServerPacketMethod> map = new HashMap<Class, ServerPacketMethod>();

	SocketClient client;

	PacketMap() {
		map.put(CsLoginSyn.class, new MethLoginSyn()); // �α���
		map.put(CsSignUpSyn.class, new MethSignUpSyn()); // ȸ������
		map.put(CsChatConnectSyn.class, new MethChatConnectSyn()); // ä�ÿ��� ��û
		map.put(MsChatConnectAck.class, new MethMSChatConnectAck());
		map.put(CsChatSyn.class, new MethCsChatSyn());
		map.put(CsBuyRoomSyn.class, new MethBuyRoomSyn()); // ����
		map.put(CsMoveSeatSyn.class, new MethMoveSeatSyn()); // �ڸ��̵�
		map.put(CsCloseSyn.class, new MethCloseSyn()); // ���� ����
		map.put(CsExitSyn.class, new MethExitSyn()); // ���� ����
		map.put(CsUpdateRoomSyn.class, new MethUpdateRoomSyn()); // �� ���� ������
		map.put(MsCurrMemListSyn.class, new MethMsCurrMemListSyn()); // ���� ȸ������Ʈ
		map.put(MsAllMemListSyn.class, new MethMsAllMemListSyn()); // ���� ȸ������Ʈ
		map.put(MsMemSearchSyn.class, new MethMsMemSearchSyn()); // ȸ�� �˻�
		map.put(CsBuyLockerSyn.class, new MethBuyLockerSyn()); // �繰�� ����
		map.put(CsDuplicateIDSyn.class, new MethDuplicateIDSyn()); // �ߺ����̵� Ȯ��
		map.put(MsGiveMeResvRoomSyn.class, new MethMsGiveMeResvRoomSyn()); // �����
		map.put(MsSalesInquirySyn.class, new MethMsSalesInquirySyn()); // ������ȸ
		map.put(CsGetRoomDataSyn.class, new MethCsGetRoomDataSyn()); // �� ������ ���ε� 
		map.put(MsUptRoomPrSyn.class, new MethMsUptRoomPrSyn()); // �� ������ ���ε� 
	}

	void receivePacket(SocketClient pClient, PacketBase packet) {

		System.out.println("SERVER RECEIVE :" + packet.getClass().getSimpleName());
		map.get(packet.getClass()).receive(pClient, packet);
	}

}
