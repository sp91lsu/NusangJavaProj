package server_p;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

import client_p.packet_p.syn_p.CsBuyLockerSyn;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import client_p.packet_p.syn_p.CsDuplicateIDSyn;
import client_p.packet_p.syn_p.CsExitSyn;
import client_p.packet_p.syn_p.CsGetExtensionValueSyn;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.packet_p.syn_p.CsMoveSeatSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
import data_p.product_p.DataManager;
import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;
import data_p.product_p.room_p.RoomTimeData;
import data_p.user_p.UserData;
import dbOracle_p.AccountDao;
import dbOracle_p.LockerDao;
import dbOracle_p.RoomDao;
import manager_p.ack_p.MsChatConnectAck;
import manager_p.syn_p.MsAllMemListSyn;
import manager_p.syn_p.MsCurrMemListSyn;
import manager_p.syn_p.MsGiveMeResvRoomSyn;
import manager_p.syn_p.MsMemSearchSyn;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScBuyLockerAck;
import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScDuplicateIDAck;
import server_p.packet_p.ack_p.ScExitAck;
import server_p.packet_p.ack_p.ScGetExtexsionAck;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScMoveSeatAck;
import server_p.packet_p.ack_p.ScSignUpAck;
import server_p.packet_p.ack_p.SmAllMemListAck;
import server_p.packet_p.ack_p.SmCurrMemListAck;
import server_p.packet_p.ack_p.SmGiveMeResvRoomAck;
import server_p.packet_p.ack_p.SmMemSearchAck;
import server_p.packet_p.broadCast.ScBuyLockerCast;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;
import server_p.packet_p.syn_p.SMChatConnectSyn;

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
			userData = accountDao.loginUser(idOrPhone, recPacket.id, recPacket.pw);

			if (userData != null) {

				RoomDao roomDao = new RoomDao();
				LockerDao lockerDao = new LockerDao();

				userData.setMyRoom(roomDao.findUserRoom(userData.uuid));

				ack = new ScLoginAck(EResult.SUCCESS, userData, roomDao.getReservationListAll(),
						lockerDao.getLockerIDList());
			} else {
				ack = new ScLoginAck(EResult.NOT_FOUND_DATA, null, null, null);

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

			AccountDao ad = new AccountDao();

			if (ad.duplicateIDChk(recPacket.id)) {
				ack = new ScSignUpAck(EResult.DUPLICATEED_ID, "");
			} else {
				ad.reset();

				UserData userData = new UserData(UUID.randomUUID().toString(), recPacket.name, recPacket.id,
						recPacket.pw, recPacket.phone, recPacket.birth, recPacket.cType);

				ad.createAccount(userData);

				ack = new ScSignUpAck(EResult.SUCCESS, userData.name);
			}
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

		SocketClient mc = MyServer.getInstance().findClient(MyServer.getInstance().managerIp);

		SMChatConnectSyn toMchatSyn = new SMChatConnectSyn(EResult.SUCCESS, resPacket.userData);
		toMchatSyn.setCIP(client.socket.getInetAddress().toString());

		if (mc != null) {// && !sc.isChat
			toMchatSyn.setManagerIp(mc.socket.getInetAddress().toString());
			toMchatSyn.setCIP(client.socket.getInetAddress().toString());
			mc.sendPacket(toMchatSyn);
		}
	}
}

// 관리자가 서버로 연결 응답
class MethMSChatConnectAck implements ServerPacketMethod {

	@Override
	public void receive(SocketClient client, PacketBase packet) {
		MsChatConnectAck resPacket = (MsChatConnectAck) packet;

		SocketClient sc = MyServer.getInstance().findClient(resPacket.cIp);

		ScChatConnectAck scConnectAck = null;

		if (sc != null) {
			if (resPacket.isConnect) {
				scConnectAck = new ScChatConnectAck(EResult.SUCCESS, resPacket.cIp, resPacket.managerIp);
			} else {
				scConnectAck = new ScChatConnectAck(EResult.FAIL, null, null);
			}
			sc.sendPacket(scConnectAck);
		} else {
			System.out.println("client ip is null");
		}
	}
}

//클라이언트로부터 채팅 데이터 전송 
class MethCsChatSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsChatSyn csChatSyn = (CsChatSyn) packet;

		System.out.println(csChatSyn.cip);
		System.out.println(csChatSyn.mip);
		System.out.println(csChatSyn.text);

		ScChatBroadCast chatBroadCast = new ScChatBroadCast(EResult.SUCCESS, csChatSyn.text);

		SocketClient chatClient = MyServer.getInstance().findClient(csChatSyn.cip);
		SocketClient chatManager = MyServer.getInstance().findClient(csChatSyn.mip);

		if (chatClient != null) {
			chatClient.sendPacket(chatBroadCast);
		}
		if (chatManager != null) {
			chatManager.sendPacket(chatBroadCast);
		}
	}
}

class MethBuyRoomSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsBuyRoomSyn recPacket = (CsBuyRoomSyn) packet;

		System.out.println(recPacket.RoomProduct.calendarList.size());
		ScBuyRoomAck ack = null;

		AccountDao ad = new AccountDao();

		ad.exitUser(recPacket.uuid, 0);

		// 타임별로 룸 구매
		RoomDao roomDao = new RoomDao();
		try {
			if (DataManager.getInstance().roomMap.containsKey(recPacket.RoomProduct.id)) {
				roomDao.insertRoomInfo(recPacket.uuid, recPacket.RoomProduct);
				roomDao.reset();
				ArrayList<RoomProduct> roomList = roomDao.getReservationListAll();
				roomDao.reset();

				ack = new ScBuyRoomAck(EResult.SUCCESS, roomList, roomDao.findUserRoom(recPacket.uuid));
				ScRoomInfoBroadCast roomCast = new ScRoomInfoBroadCast(EResult.SUCCESS, roomList);

				MyServer.getInstance().broadCast(client, roomCast);

			} else {
				ack = new ScBuyRoomAck(EResult.NOT_FOUND_DATA, null, null);
			}
			client.sendPacket(ack);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class MethMoveSeatSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsMoveSeatSyn recPacket = (CsMoveSeatSyn) packet;

		System.out.println("자리이동 ");
		ScMoveSeatAck ack = null;

		// 타임별로 룸 구매
		RoomDao roomDao = new RoomDao();

		roomDao.moveSeat(recPacket.userUUID, recPacket.originRoom, recPacket.moveSeatID);
		roomDao.reset();
		ArrayList<RoomProduct> reserListAll = roomDao.getReservationListAll();
		roomDao.reset();
		ArrayList<RoomProduct> myReserList = roomDao.getReservationListAll();
		ack = new ScMoveSeatAck(EResult.SUCCESS, reserListAll, myReserList);
		client.sendPacket(ack);
	}
}

class MethCloseSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		client.close();
	}
}

class MethExitSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsExitSyn respacket = (CsExitSyn) packet;

		AccountDao ad = new AccountDao();

		ad.exitUser(respacket.room.userUUID, 1);

		RoomDao roomDao = new RoomDao();
		roomDao.exitRoom(respacket.room);

		roomDao.reset();
		ArrayList<RoomProduct> reserListAll = roomDao.getReservationListAll();
		roomDao.reset();
		ArrayList<RoomProduct> myReserList = roomDao.getReservationListAll();

		LockerDao lockerDao = new LockerDao();

		ScExitAck ack;

		if (lockerDao.findUserLocker(respacket.room.userUUID)) {
			lockerDao.reset();
			lockerDao.exitLocker(respacket.room.userUUID);
		}

		ScRoomInfoBroadCast roomCast = new ScRoomInfoBroadCast(EResult.SUCCESS, reserListAll);
		MyServer.getInstance().broadCast(client, roomCast);
		ack = new ScExitAck(EResult.SUCCESS, reserListAll, myReserList);
		client.sendPacket(ack);
	}
}

class MethUpdateRoomSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		RoomDao roomDao = new RoomDao();

		try {
			ScRoomInfoBroadCast roomCast = new ScRoomInfoBroadCast(EResult.SUCCESS, roomDao.getReservationListAll());
			client.sendPacket(roomCast);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

//예약 룸정보 관리자로
class MethMsGiveMeResvRoomSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		MsGiveMeResvRoomSyn resPacket = (MsGiveMeResvRoomSyn) packet;
		RoomDao roomDao = new RoomDao();
		try {
			SmGiveMeResvRoomAck ack = new SmGiveMeResvRoomAck(EResult.SUCCESS,
					roomDao.rTimeDataList(resPacket.yyyy, resPacket.mm, resPacket.dd));
//			String managerIp = "/192.168.100.27";
			SocketClient mc = MyServer.getInstance().findClient(MyServer.getInstance().managerIp);
			client.sendPacket(ack);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class MethGetExtensionValueSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsGetExtensionValueSyn resPacket = (CsGetExtensionValueSyn) packet;
		RoomDao roomDao = new RoomDao();
		int value = 0;
		try {
			value = roomDao.getNextTime(resPacket.uuid, resPacket.pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ScGetExtexsionAck ack = new ScGetExtexsionAck(EResult.SUCCESS, value);
		client.sendPacket(ack);
	}
}

//현재 이용중 고객 조회
class MethMsCurrMemListSyn implements ServerPacketMethod {

	@Override
	public void receive(SocketClient client, PacketBase packet) {
		MsCurrMemListSyn resPacket = (MsCurrMemListSyn) packet;

		SmCurrMemListAck toMcurrMLAck = null;
		AccountDao accountDao = new AccountDao();
		try {
			toMcurrMLAck = new SmCurrMemListAck(EResult.SUCCESS, accountDao.getCurrentUserList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.sendPacket(toMcurrMLAck);
	}
}

//전체 회원 조회
class MethMsAllMemListSyn implements ServerPacketMethod {

	@Override
	public void receive(SocketClient client, PacketBase packet) {
		MsAllMemListSyn resPacket = (MsAllMemListSyn) packet;

		SmAllMemListAck ack = null;
		AccountDao accountDao = new AccountDao();
		try {
//			if (sc != null) {
			ack = new SmAllMemListAck(EResult.SUCCESS, accountDao.getAllUserList());
//			} else {
//				toMcurrMLAck = new SMCurrMemListAck(EResult.FAIL, accountDao.getCurrentUserList());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.sendPacket(ack);
	}
}

//회원 검색
class MethMsMemSearchSyn implements ServerPacketMethod {

	@Override
	public void receive(SocketClient client, PacketBase packet) {
		MsMemSearchSyn resPacket = (MsMemSearchSyn) packet;

		SocketClient mc = MyServer.getInstance().findClient(MyServer.getInstance().managerIp);

		SmMemSearchAck ack = null;
		AccountDao accountDao = new AccountDao();
		try {
			if (mc != null) {
				ack = new SmMemSearchAck(EResult.SUCCESS, accountDao.getAllUserList());
			} else {
				ack = new SmMemSearchAck(EResult.FAIL, accountDao.getAllUserList());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		mc.sendPacket(ack);

//		client.sendPacket(ack);
	}
}

class MethBuyLockerSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsBuyLockerSyn resPacket = (CsBuyLockerSyn) packet;

		LockerDao lockerDao = new LockerDao();

		ScBuyLockerAck ack = null;

		if (lockerDao.insertLocker(resPacket.uuid, resPacket.locker)) {

			lockerDao = new LockerDao();

			ArrayList<LockerData> lockerList = lockerDao.getLockerIDList();

			ScBuyLockerCast lockerCast = new ScBuyLockerCast(EResult.SUCCESS, lockerList);

			ack = new ScBuyLockerAck(EResult.SUCCESS, lockerList);

			MyServer.getInstance().broadCast(client, lockerCast);

		} else {
			ack = new ScBuyLockerAck(EResult.FAIL, null);
		}

		client.sendPacket(ack);

	}
}

class MethDuplicateIDSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsDuplicateIDSyn resPacket = (CsDuplicateIDSyn) packet;

		AccountDao ad = new AccountDao();

		ScDuplicateIDAck ack;
		try {
			if (ad.duplicateIDChk(resPacket.id)) {
				ack = new ScDuplicateIDAck(EResult.DUPLICATEED_ID);
			} else {
				ack = new ScDuplicateIDAck(EResult.SUCCESS);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			ack = new ScDuplicateIDAck(EResult.FAIL);
		}

		client.sendPacket(ack);
	}
}
