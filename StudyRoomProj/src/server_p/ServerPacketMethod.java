package server_p;

import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScDuplicateIDAck;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScSignUpAck;

import java.net.InetAddress;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import client_p.packet_p.syn_p.CsDuplicateIDSyn;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import data_p.product_p.DataManager;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import dbOracle_p.*;
import jdk.nashorn.internal.runtime.FindProperty;
import packetBase_p.EResult;
import packetBase_p.PacketBase;

public interface ServerPacketMethod {

	void receive(SocketClient client, PacketBase packet);
}

class MethLoginSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsLoginSyn recPacket = (CsLoginSyn) packet;

		String idOrPhone = recPacket.isID == true ? "id" : "phone";

		AccountDao accountDao = new AccountDao();

		UserData userData = null;
		ScLoginAck ack = null;
		try {
			userData = accountDao.findUser(idOrPhone, recPacket.id, recPacket.pw);

			if (userData != null) {

				RoomDao roomDao = new RoomDao();

				ack = new ScLoginAck(EResult.SUCCESS, userData, roomDao.getTodayRoomInfo());
			} else {
				ack = new ScLoginAck(EResult.NOT_FOUND_DATA, null, null);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.sendPacket(ack);
	}

}

class MethSignUpSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		ScSignUpAck ack = null;
		try {
			CsSignUpSyn recPacket = (CsSignUpSyn) packet;

			AccountDao accountDao = new AccountDao();

			UserData userData = new UserData(UUID.randomUUID().toString(), recPacket.name, recPacket.id, recPacket.pw,
					recPacket.phone, recPacket.birth, recPacket.cType);

			accountDao.createAccount(userData);

			ack = new ScSignUpAck(EResult.SUCCESS, userData.name);

		} catch (Exception e) {
			ack = new ScSignUpAck(EResult.NOT_FOUND_DATA, "회원가입에 실패하였습니다.");
			e.printStackTrace();
		}
		client.sendPacket(ack);
	}
}

class MethChatConnectSyn implements ServerPacketMethod {

	@Override
	public void receive(SocketClient client, PacketBase packet) {
		CsChatConnectSyn resPacket = (CsChatConnectSyn) packet;
		String managerIp = "/192.168.0.7";
		SocketClient sc = MyServer.getInstance().findClient(managerIp);

		ScChatConnectAck ack = null;
		if (sc != null && !sc.isChat) {
			ack = new ScChatConnectAck(EResult.SUCCESS);
			sc.sendPacket(ack);
		} else {
			ack = new ScChatConnectAck(EResult.FAIL);
			sc.sendPacket(ack);
		}

	}
}

//클라이언트로부터 채팅 데이터 전송 
class MethChatSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsChatSyn recPacket = (CsChatSyn) packet;

		SocketClient findClient = MyServer.getInstance().findClient(recPacket.address.toString());

		if (findClient != null) {
			if (findClient.doChatting) {
				findClient.sendPacket(recPacket);
			} else {

				client.sendPacket(recPacket);
			}
		} else {

		}
	}
}

class MethBuyRoomSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsBuyRoomSyn recPacket = (CsBuyRoomSyn) packet;

		System.out.println("들어온 상품 정보 ");
		System.out.println(recPacket.RoomProduct.calendarList.size());
		ScBuyRoomAck ack = null;

		// 타임별로 룸 구매
		for (Calendar cal : recPacket.RoomProduct.calendarList) {

			Timestamp ts = new Timestamp(cal.getTimeInMillis());

			RoomDao roomDao = new RoomDao();

			System.out.println(cal.getTime());

			roomDao.insertRoomInfo(recPacket.uuid, recPacket.RoomProduct);
		}

		ack = new ScBuyRoomAck(EResult.SUCCESS);

		client.sendPacket(ack);
	}

}

//class MethDuplicateIDSyn implements ServerPacketMethod {
//
//	@Override
//	public void receive(SocketClient client, PacketBase packet) {
//
//		CsDuplicateIDSyn resPacket = (CsDuplicateIDSyn) packet;
//
//		ScDuplicateIDAck ack;
//
//		qo.findQuery(ETable.ACCOUNT, "id", "id = " + resPacket.id);
//
//		try {
//			ResultSet res = DBProcess.getInstance().findData(qo);
//
//			if (res.next()) {
//				ack = new ScDuplicateIDAck(EResult.DUPLICATEED_ID);
//			} else {
//				ack = new ScDuplicateIDAck(EResult.SUCCESS);
//			}
//		} catch (SQLException e) {
//			ack = new ScDuplicateIDAck(EResult.FAIL);
//			e.printStackTrace();
//		}
//	}
//
//}