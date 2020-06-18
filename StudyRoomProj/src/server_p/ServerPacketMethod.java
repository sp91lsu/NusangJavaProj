package server_p;

import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScSignInUpAck;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
import data_p.user_p.UserData;
import dbOracle_p.*;
import packetBase_p.EResult;
import packetBase_p.PacketBase;

public interface ServerPacketMethod {

	void action(PacketClient client, PacketBase packet);
}

//class MethLogOutSyn implements PacketMethod {
//
//	@Override
//	public void action(PacketClient client, PacketBase packet) {
//		CsLoginSyn recPacket = (CsLoginSyn) packet;
//
//		System.out.println("rcPacket");
//	}
//}

class MethLoginSyn implements ServerPacketMethod {

	public void action(PacketClient client, PacketBase packet) {
		CsLoginSyn recPacket = (CsLoginSyn) packet;

		// ������ ���̽� üũ
		ScLoginAck ack = null;
		if (true) {
			ack = new ScLoginAck(client.uuid, EResult.SUCCESS);
		} else {
			ack = new ScLoginAck(client.uuid, EResult.NOT_FOUND_DATA);
		}

		System.out.println("CsLoginSyn");
		System.out.println("id" + recPacket.id);
		System.out.println("pw" + recPacket.pw);
		System.out.println("isID" + recPacket.isID);
		client.sendPacket(ack);
		// client.sendPacket(recPacket);

	}
}

class MethSignUpSyn implements ServerPacketMethod {

	public void action(PacketClient client, PacketBase packet) {

		ScSignInUpAck ack = null;
		try {
			CsSignUpSyn recPacket = (CsSignUpSyn) packet;
			UserData userData = new UserData(recPacket.name, recPacket.id, recPacket.pw, recPacket.phone,
					recPacket.birth, recPacket.cType);

			String ctype = userData.cType;

			if (DBProccess.getInstance().haveData(ETable.MANAGERKEY, "key", "key = '" + userData.cType + "'")) {
				ctype = EClientType.MANAGER.name();
			}
			DBProccess.getInstance().close();

			String calum = "name,id,pw,birth,phone,ctype";
			String values;
			values = DBProccess.valueStr(userData.name, userData.id, userData.pw, userData.birth, userData.phone,
					ctype);
			DBProccess.getInstance().insertData(ETable.ACCOUNT, calum, values);

			ack = new ScSignInUpAck(client.uuid, EResult.SUCCESS);

		} catch (Exception e) {
			ack = new ScSignInUpAck(client.uuid, EResult.NOT_FOUND_DATA);
			e.printStackTrace();
		}

		client.sendPacket(ack);
	}
}
