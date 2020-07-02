//serverpacketmeth

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
import client_p.packet_p.syn_p.CsGetRoomDataSyn;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.packet_p.syn_p.CsMoveSeatSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
import client_p.packet_p.syn_p.CsUpdateRoomSyn;
import data_p.product_p.DataManager;
import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import dbOracle_p.AccountDao;
import dbOracle_p.LockerDao;
import dbOracle_p.RoomDao;
import dbOracle_p.RoomNowDao;
import manager_p.ack_p.MsChatConnectAck;
import manager_p.ack_p.MsUptRoomPrSyn;
import manager_p.syn_p.MsAllMemListSyn;
import manager_p.syn_p.MsCurrMemListSyn;
import manager_p.syn_p.MsResvRoomSyn;
import manager_p.syn_p.MsMemSearchSyn;
import manager_p.syn_p.MsSalesInquirySyn;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScBuyLockerAck;
import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScDuplicateIDAck;
import server_p.packet_p.ack_p.ScExitAck;
import server_p.packet_p.ack_p.ScGetRoomDataAck;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScMoveSeatAck;
import server_p.packet_p.ack_p.ScSignUpAck;
import server_p.packet_p.ack_p.ScUpdateRoomInfoAck;
import server_p.packet_p.ack_p.SmAllMemListAck;
import server_p.packet_p.ack_p.SmCurrMemListAck;
import server_p.packet_p.ack_p.SmMemSearchAck;
import server_p.packet_p.ack_p.SmResvRoomAck;
import server_p.packet_p.ack_p.SmSalesInquiryAck;
import server_p.packet_p.ack_p.SmUptRoomPrAck;
import server_p.packet_p.broadCast.ScBuyLockerCast;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.broadCast.ScGetRoomDataCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;
import server_p.packet_p.syn_p.SMChatConnectSyn;

public interface ServerPacketMethod {

	void receive(SocketClient client, PacketBase packet);
}

class MethLoginSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsLoginSyn recPacket = (CsLoginSyn) packet;

		String idOrPhone = recPacket.isID == true ? "id" : "phone";

		UserData userData = null;
		ScLoginAck ack = null;
		try {
			userData = new AccountDao().loginUser(idOrPhone, recPacket.id, recPacket.pw);

			if (userData != null) {

				new AccountDao().ipCheck(userData.uuid, client.socket.getInetAddress().toString());
				userData.setReserRoom(new RoomDao().findUserRoom(userData.uuid, false));
				userData.setExitRoom(new RoomDao().findUserRoom(userData.uuid, true));
				userData.locker = new LockerDao().findUserLocker(userData.uuid);
				ack = new ScLoginAck(EResult.SUCCESS, userData, new RoomDao().getReservationListAll(),
						new LockerDao().getLockerIDList());

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

			if (new AccountDao().duplicateIDChk("id", recPacket.id)) {
				ack = new ScSignUpAck(EResult.DUPLICATEED_ID, "");
			} else {

				UserData userData = new UserData(UUID.randomUUID().toString(), recPacket.name, recPacket.id,
						recPacket.pw, recPacket.phone, recPacket.birth, recPacket.cType);

				new AccountDao().createAccount(userData);

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

		ArrayList<String> managerIpList = new AccountDao().findManagersIp();
		SocketClient mc = null;
		for (String ip : managerIpList) {

			mc = MyServer.getInstance().findClient(ip);
			if (mc != null) {
				break;
			}
		}

		SMChatConnectSyn toMchatSyn = new SMChatConnectSyn(EResult.SUCCESS, resPacket.userData);
		toMchatSyn.setCIP(client.socket.getInetAddress().toString());

		if (mc != null) {// && !sc.isChat
			toMchatSyn.setManagerIp(mc.socket.getInetAddress().toString());
			toMchatSyn.setCIP(client.socket.getInetAddress().toString());
			mc.sendPacket(toMchatSyn);
		}
	}
}

//관리자가 서버로 연결 응답
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
				scConnectAck = new ScChatConnectAck(EResult.NEGATIVE_CHAT, null, null);
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

		MyServer.getInstance().broadCastP2P(chatClient, chatManager, chatBroadCast);
	}
}

class MethBuyRoomSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsBuyRoomSyn recPacket = (CsBuyRoomSyn) packet;

		System.out.println(recPacket.RoomProduct.calendarList.size());
		ScBuyRoomAck ack = null;

		// 타임별로 룸 구매
		try {
			if (new RoomDao().availableBuyRoom(recPacket.RoomProduct)) {

				new RoomDao().insertRoomInfo(recPacket.uuid, recPacket.RoomProduct);
				// roomDao.reset();
				ArrayList<RoomProduct> roomList = new RoomDao().getReservationListAll();
				// roomDao.reset();

				ack = new ScBuyRoomAck(EResult.SUCCESS, roomList, new RoomDao().findUserRoom(recPacket.uuid, false),
						new RoomDao().findUserRoom(recPacket.uuid, true));
				ScRoomInfoBroadCast roomCast = new ScRoomInfoBroadCast(EResult.SUCCESS, roomList);

				MyServer.getInstance().broadCast(client, roomCast);

			} else {
				ack = new ScBuyRoomAck(EResult.ALEADY_EXIST_DATA, null, null, null);
			}
			client.sendPacket(ack);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// roomDao.close();
	}
}

class MethMoveSeatSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsMoveSeatSyn recPacket = (CsMoveSeatSyn) packet;

		System.out.println("자리이동 ");
		ScMoveSeatAck ack = null;

		// 타임별로 룸 구매
		// RoomDao roomDao = new RoomDao();
		if (new RoomDao().availableMove(recPacket.moveSeatID)) {

			new RoomDao().moveSeat(recPacket.userUUID, recPacket.originRoom, recPacket.moveSeatID);

			// roomDao.reset();
			ArrayList<RoomProduct> reserListAll = new RoomDao().getReservationListAll();
			// roomDao.reset();
			ArrayList<RoomProduct> myReserList = new RoomDao().findUserRoom(recPacket.userUUID, false);
			ArrayList<RoomProduct> myExitList = new RoomDao().findUserRoom(recPacket.userUUID, true);
			ack = new ScMoveSeatAck(EResult.SUCCESS, reserListAll, myReserList, myExitList);

			ScRoomInfoBroadCast roomCast = new ScRoomInfoBroadCast(EResult.SUCCESS, reserListAll);

			MyServer.getInstance().broadCast(client, roomCast);
		} else {
			ack = new ScMoveSeatAck(EResult.ALEADY_EXIST_DATA, null, null, null);
		}
		client.sendPacket(ack);

		// roomDao.close();
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

		new RoomDao().exitRoom(respacket.room);

		// roomDao.reset();
		ArrayList<RoomProduct> reserListAll = new RoomDao().getReservationListAll();
		// roomDao.reset();
		ArrayList<RoomProduct> myReserList = new RoomDao().findUserRoom(respacket.room.userUUID, false);
		ArrayList<RoomProduct> myExitList = new RoomDao().findUserRoom(respacket.room.userUUID, true);

		ScExitAck ack;

		new LockerDao().exitLocker(respacket.room.userUUID);

		ArrayList<LockerData> lockerList = new LockerDao().getLockerIDList();

		ScRoomInfoBroadCast roomCast = new ScRoomInfoBroadCast(EResult.SUCCESS, reserListAll);
		roomCast = new ScRoomInfoBroadCast(EResult.SUCCESS, reserListAll);

		ScBuyLockerCast locakerCast = new ScBuyLockerCast(EResult.SUCCESS, lockerList);

		MyServer.getInstance().broadCast(client, roomCast);
		MyServer.getInstance().broadCast(client, locakerCast);

		ack = new ScExitAck(EResult.SUCCESS, reserListAll, myReserList, myExitList, lockerList);
		client.sendPacket(ack);
	}
}

class MethUpdateRoomSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		CsUpdateRoomSyn resPacket = (CsUpdateRoomSyn) packet;

		ArrayList<RoomProduct> myReserList = new RoomDao().findUserRoom(resPacket.uuid, false);
		ArrayList<RoomProduct> exitList = new RoomDao().findUserRoom(resPacket.uuid, true);
		ArrayList<RoomProduct> reserAll = new RoomDao().getReservationListAll();
		ArrayList<LockerData> lockerList = new LockerDao().getLockerIDList();
		try {
			ScUpdateRoomInfoAck roomAck = new ScUpdateRoomInfoAck(EResult.SUCCESS, reserAll, myReserList, exitList,
					lockerList);

			client.sendPacket(roomAck);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

//예약 룸정보 관리자로
class MethMsResvRoomSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {
		System.out.println("MethResvRoomSyn 실행");
		MsResvRoomSyn resPacket = (MsResvRoomSyn) packet;
		SmResvRoomAck ack = null;
		try {
			System.out.println(resPacket.yyyy);
			System.out.println(resPacket.yyyy.substring(2));
			String yy = resPacket.yyyy.substring(2);
			ack = new SmResvRoomAck(EResult.SUCCESS,
					new RoomDao().salesData(yy, resPacket.mm, resPacket.dd).salesRecordArrL);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("MethResvRoomSyn catch");
		}
		client.sendPacket(ack);

	}
}

//현재 이용중 고객 조회
class MethMsCurrMemListSyn implements ServerPacketMethod {

	@Override
	public void receive(SocketClient client, PacketBase packet) {
		MsCurrMemListSyn resPacket = (MsCurrMemListSyn) packet;

		SmCurrMemListAck toMcurrMLAck = null;
		try {
			toMcurrMLAck = new SmCurrMemListAck(EResult.SUCCESS, new AccountDao().getCurrentUserList());
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
		try {
//			if (sc != null) {
			ack = new SmAllMemListAck(EResult.SUCCESS, new AccountDao().getAllUserList());
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

		SmMemSearchAck ack = null;
		try {
			ack = new SmMemSearchAck(EResult.SUCCESS, new AccountDao().getAllUserList());
		} catch (Exception e) {
			e.printStackTrace();
		}

		client.sendPacket(ack);

	}
}

class MethBuyLockerSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsBuyLockerSyn resPacket = (CsBuyLockerSyn) packet;

		ScBuyLockerAck ack = null;

		if (new LockerDao().availableBuy(resPacket.locker.id)) {

			new LockerDao().insertLocker(resPacket.uuid, resPacket.locker);

			ArrayList<LockerData> lockerList = new LockerDao().getLockerIDList();

			LockerData locker = new LockerDao().findUserLocker(resPacket.uuid);

			ScBuyLockerCast lockerCast = new ScBuyLockerCast(EResult.SUCCESS, lockerList);

			ack = new ScBuyLockerAck(EResult.SUCCESS, lockerList, locker);

			MyServer.getInstance().broadCast(client, lockerCast);

		} else {
			ack = new ScBuyLockerAck(EResult.ALEADY_EXIST_DATA, null, null);
		}

		client.sendPacket(ack);

	}
}

class MethDuplicateIDSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsDuplicateIDSyn resPacket = (CsDuplicateIDSyn) packet;

		ScDuplicateIDAck ack;

		String idOrPhone = resPacket.is_hp ? "phone" : "id";
		try {
			if (new AccountDao().duplicateIDChk(idOrPhone, resPacket.id)) {
				ack = new ScDuplicateIDAck(EResult.DUPLICATEED_ID, resPacket.is_hp);
			} else {
				ack = new ScDuplicateIDAck(EResult.SUCCESS, resPacket.is_hp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			ack = new ScDuplicateIDAck(EResult.FAIL, resPacket.is_hp);
		}

		client.sendPacket(ack);
	}

}

class MethMsSalesInquirySyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		MsSalesInquirySyn resPacket = (MsSalesInquirySyn) packet;

		try {
			SmSalesInquiryAck ack = new SmSalesInquiryAck(EResult.SUCCESS,
					new RoomDao().salesData(resPacket.year, resPacket.month, resPacket.day));
//	         String managerIp = "/192.168.100.27";
			client.sendPacket(ack);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class MethCsGetRoomDataSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		CsGetRoomDataSyn resPacket = (CsGetRoomDataSyn) packet;

		// db에서 룸 모델 불러와서
		new RoomNowDao().settingRoomData();

		ScGetRoomDataAck ack = new ScGetRoomDataAck(EResult.SUCCESS, DataManager.getInstance().roomMap);

		client.sendPacket(ack);
	}
}

class MethMsUptRoomPrSyn implements ServerPacketMethod {

	public void receive(SocketClient client, PacketBase packet) {

		MsUptRoomPrSyn resPacket = (MsUptRoomPrSyn) packet;

		SmUptRoomPrAck ack = null;
		try {
			for (int key : resPacket.map_roomID_Pr.keySet()) {
				new RoomNowDao().upt_NowRoomData(key, resPacket.map_roomID_Pr.get(key));
				ack = new SmUptRoomPrAck(EResult.SUCCESS);
			}
			new RoomNowDao().settingRoomData();

			ScGetRoomDataCast roomCast = new ScGetRoomDataCast(EResult.SUCCESS, DataManager.getInstance().roomMap);
			MyServer.getInstance().broadCast(client, roomCast);
		} catch (Exception e) {
			ack = new SmUptRoomPrAck(EResult.FAIL);
			e.printStackTrace();
		}

		client.sendPacket(ack);

	}
}
