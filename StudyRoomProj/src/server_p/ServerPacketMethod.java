package server_p;

import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScSignInUpAck;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
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

		// 데이터 베이스 체크
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
		try {
			CsSignUpSyn recPacket = (CsSignUpSyn) packet;

			String calum = "name,id,pw,birth,phone,ctype";

			String ctype = recPacket.cType;

			boolean res = DBProccess.getInstance().haveData(ETable.MANAGERKEY, "key",
					"key = '" + recPacket.cType + "'");

			if (res) {
				ctype = EClientType.MANAGER.name();
			}
			DBProccess.getInstance().close();
			String values;

			values = DBProccess.valueStr(recPacket.name, recPacket.id, recPacket.pw, recPacket.birth, recPacket.phone,
					ctype);
			DBProccess.getInstance().insertData(ETable.ACCOUNT, calum, values);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ScSignInUpAck ack = new ScSignInUpAck(client.uuid, EResult.SUCCESS);
		client.sendPacket(ack);

	}
}
