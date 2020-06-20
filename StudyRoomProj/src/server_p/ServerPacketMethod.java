package server_p;

import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScDuplicateIDAck;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScSignUpAck;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import client_p.packet_p.syn_p.CsDuplicateIDSyn;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import dbOracle_p.*;
import packetBase_p.EResult;
import packetBase_p.PacketBase;

public interface ServerPacketMethod {

	void receive(SocketClient client, PacketBase packet);
}

class MethLoginSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsLoginSyn recPacket = (CsLoginSyn) packet;

		QueryObject qo = new QueryObject();
		String idOrPhone = recPacket.isID == true ? "id" : "phone";

		qo.findQuery(ETable.ACCOUNT, "*", idOrPhone + " = '" + recPacket.id + "' and pw = '" + recPacket.pw + "'");

		ResultSet rs = null;
		try {
			rs = DBProcess.getInstance().findData(qo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ScLoginAck ack = null;

		try {
			if (rs.next()) {

//				System.out.println("UserData !!!!!!!!!!!!!!!!!!!");
//				System.out.println(rs.getString("uuid"));
//				System.out.println(rs.getString("name"));
//				System.out.println(rs.getString("id"));
//				System.out.println(rs.getString("phone"));
//				System.out.println(rs.getString("birth"));
				UserData userdata = new UserData(rs.getString("uuid"), rs.getString("name"), rs.getString("id"),
						rs.getString("phone"), rs.getString("birth"));
				rs.close();
				ack = new ScLoginAck(EResult.SUCCESS, userdata);
			} else {
				ack = new ScLoginAck(EResult.NOT_FOUND_DATA, null);
			}
		} catch (SQLException e) {
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

			System.out.println();
			UserData userData = new UserData(UUID.randomUUID().toString(), recPacket.name, recPacket.id, recPacket.pw,
					recPacket.phone, recPacket.birth, recPacket.cType);

			QueryObject qo = new QueryObject();

			qo.createQuery("uuid,name,id,pw,birth,phone,ctype", userData.uuid, userData.name, userData.id, userData.pw,
					userData.birth, userData.phone, userData.cType);

			DBProcess.getInstance().insertData(ETable.ACCOUNT, qo);

			ack = new ScSignUpAck(EResult.SUCCESS, userData.name);

		} catch (Exception e) {
			ack = new ScSignUpAck(EResult.NOT_FOUND_DATA, "ȸ�����Կ� �����Ͽ����ϴ�.");
			e.printStackTrace();
		}
		client.sendPacket(ack);
	}
}

class MethChatConnectSyn implements ServerPacketMethod {

	@Override
	public void receive(SocketClient client, PacketBase packet) {
		CsChatConnectSyn resPacket = (CsChatConnectSyn) packet;

	}
}

class MethVerifySyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsBuyRoomSyn recPacket = (CsBuyRoomSyn) packet;

		QueryObject qo = new QueryObject();
		qo.findQuery(ETable.ACCOUNT, "uuid", "uuid = " + recPacket.uuid);
		ResultSet rs = null;
		try {
			rs = DBProcess.getInstance().findData(qo);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (rs.next()) {

				System.out.println(rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		recPacket.product;
//		recPacket.uuid
	}
}

//Ŭ���̾�Ʈ�κ��� ä�� ������ ���� 
class MethChatSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsChatSyn recPacket = (CsChatSyn) packet;

		SocketClient findClient = MyServer.getInstance().findClient(recPacket.address);

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

		System.out.println("���� ��ǰ ���� ");
		System.out.println(recPacket.uuid);
		System.out.println(recPacket.RoomProduct.id);
		System.out.println(recPacket.RoomProduct.name);
		System.out.println(recPacket.RoomProduct.price);
		for (TimeData timedata : recPacket.RoomProduct.timeList) {
			System.out.println(timedata.toString());
		}

		RoomProduct rp = recPacket.RoomProduct;

		ScBuyRoomAck ack = null;
		// Ÿ�Ӻ��� �� ����
		for (TimeData timeData : rp.timeList) {

			rp.calendar.set(Calendar.HOUR, timeData.value);

			Date date = rp.calendar.getTime(); // �������� �ð�

			Timestamp ts = new Timestamp(date.getTime());

			System.out.println(ts);

			QueryObject qo = new QueryObject();
			qo.createQuery("UUID,PRODUCTID,PRICE,STARTDATE,ENDDATE", recPacket.uuid, rp.id, rp.price + timeData.price,
					ts.toString(), ts.toString());

			try {
				DBProcess.getInstance().insertData(ETable.INVENTORY, qo);

			} catch (SQLException e) {
				e.printStackTrace();
				ack = new ScBuyRoomAck(EResult.NOT_FOUND_DATA);
				return;
			}
		}

		ack = new ScBuyRoomAck(EResult.SUCCESS);
		client.sendPacket(ack);
	}

}

class MethDuplicateIDSyn implements ServerPacketMethod {

	@Override
	public void receive(SocketClient client, PacketBase packet) {

		CsDuplicateIDSyn resPacket = (CsDuplicateIDSyn) packet;

		ScDuplicateIDAck ack;

		QueryObject qo = new QueryObject();
		qo.findQuery(ETable.ACCOUNT, "id", "id = " + resPacket.id);

		try {
			ResultSet res = DBProcess.getInstance().findData(qo);

			if (res.next()) {
				ack = new ScDuplicateIDAck(EResult.DUPLICATEED_ID);
			} else {
				ack = new ScDuplicateIDAck(EResult.SUCCESS);
			}
		} catch (SQLException e) {
			ack = new ScDuplicateIDAck(EResult.FAIL);
			e.printStackTrace();
		}
	}

}