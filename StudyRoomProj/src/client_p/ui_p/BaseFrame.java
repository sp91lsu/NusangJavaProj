package client_p.ui_p;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client_p.CalCal;
import client_p.ClientNet;
import client_p.EEnter;
import client_p.PacketMap;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsUpdateRoomSyn;
import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;
import packetBase_p.ELoginType;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScBuyLockerAck;
import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScDuplicateIDAck;
import server_p.packet_p.ack_p.ScExitAck;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScMoveSeatAck;
import server_p.packet_p.ack_p.ScSignUpAck;
import server_p.packet_p.ack_p.ScUnVerifiedAck;
import server_p.packet_p.ack_p.ScUpdateRoomInfoAck;
import server_p.packet_p.broadCast.ScBuyLockerCast;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;

public class BaseFrame extends JFrame implements Receivable {

	boolean is_SeatChange;
	public ArrayList<JPanel> jPanelArrl = new ArrayList<JPanel>();
	public ArrayList<RoomProduct> roomInfoList = new ArrayList<RoomProduct>();
	public ArrayList<LockerData> lockerlist = new ArrayList<LockerData>();
	public ELoginType loginType = ELoginType.KIOSK;
	private static BaseFrame instance;

	public static BaseFrame getInstance() {
		if (instance == null) {
			instance = new BaseFrame();
		}
		return instance;
	}

	public UserData userData = null;
	public SignUpMain signUpFrame = new SignUpMain();
	public PaymentPopFrame paymentPop = new PaymentPopFrame();
	public RCalcFrame rCalcFrame = new RCalcFrame();

	public BaseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void startFrame() {
		addToBaseFrame(new LoginMain());
		addToBaseFrame(new MainLayout());
		addToBaseFrame(new Seating_Arrangement());
		addToBaseFrame(new LockerMain());
		addToBaseFrame(new ClientChatFrame());
		addToBaseFrame(new LockerMain());
		setBackground(Color.GRAY);
		setBounds(510, 140, 900, 800);
		setVisible(true);
		view("LoginMain");
		PacketMap.getInstance().map.put(ScLoginAck.class, (Receivable) jPanelArrl.get(0)); // 로그인
		PacketMap.getInstance().map.put(ScSignUpAck.class, signUpFrame); // 회원가입
		PacketMap.getInstance().map.put(ScDuplicateIDAck.class, signUpFrame);
		PacketMap.getInstance().map.put(ScChatConnectAck.class, (Receivable) jPanelArrl.get(1));
		PacketMap.getInstance().map.put(ScChatBroadCast.class, (Receivable) jPanelArrl.get(4));
		PacketMap.getInstance().map.put(ScMoveSeatAck.class, (Receivable) jPanelArrl.get(2));
		PacketMap.getInstance().map.put(ScExitAck.class, (Receivable) jPanelArrl.get(1));
		PacketMap.getInstance().map.put(ScRoomInfoBroadCast.class, this);
		PacketMap.getInstance().map.put(ScBuyLockerCast.class, this);
		PacketMap.getInstance().map.put(ScBuyLockerAck.class, this);
		PacketMap.getInstance().map.put(ScUpdateRoomInfoAck.class, this);
		PacketMap.getInstance().map.put(ScUnVerifiedAck.class, this);
		PacketMap.getInstance().map.put(ScBuyRoomAck.class, this);// 결제
	}

	void addToBaseFrame(JPanel jp) {
		jPanelArrl.add(jp);
	}

	public void view(String jPanelName) {
		getContentPane().removeAll();

		for (JPanel jp : jPanelArrl) {
			if ((jp.getClass().getSimpleName()).equals(jPanelName)) {
				getContentPane().add(jp);
			}
		}
		getContentPane().revalidate();
		getContentPane().repaint();
	}

	@Override
	public void receive(PacketBase packet) {
		if (packet.getClass() == ScRoomInfoBroadCast.class) {
			ScRoomInfoBroadCast roomInfoCast = (ScRoomInfoBroadCast) packet;
			roomInfoList = roomInfoCast.roomListAll;

			if (getSeatingArrUI().isVisible()) {
				getSeatingArrUI().roomState();
				getSeatingArrUI().checkDate();
			}
		} else if (packet.getClass() == ScBuyLockerCast.class) {
			ScBuyLockerCast packetAck = (ScBuyLockerCast) packet;
			if (packetAck.eResult == EResult.SUCCESS) {
				updateData(null, null, null, packetAck.lockerList);
				getLockerMain().updateLocker(null);
			} else {
				System.out.println("사물함 결제 실패");
			}
		} else if (packet.getClass() == ScBuyLockerAck.class) {
			ScBuyLockerAck packetAck = (ScBuyLockerAck) packet;
			if (packetAck.eResult == EResult.SUCCESS) {
				userData.locker = packetAck.myLocker;
				BaseFrame.getInstance().openMainLayout(null, null, null, packetAck.lockerList);
				AlreadyUsePop pop = new AlreadyUsePop("구매완료되었습니다.");
			} else if (packetAck.eResult == EResult.ALEADY_EXIST_DATA) {
				AlreadyUsePop pop = new AlreadyUsePop("이미 사용 중인 라커입니다.");
			} else {
				System.out.println("사물함 결제 실패");
			}
		} else if (packet.getClass() == ScUpdateRoomInfoAck.class) {
			ScUpdateRoomInfoAck packetAck = (ScUpdateRoomInfoAck) packet;
			if (packetAck.eResult == EResult.SUCCESS) {
				updateData(packetAck.roomListAll, packetAck.myReserList, packetAck.myExitList, packetAck.lockerList);
				if (getMainLayout().isVisible()) {
					openMainLayout(null, null, null, null);
				}
			} else {
				System.out.println("ScUpdateRoomInfoAck에러");
			}
		} else if (packet.getClass() == ScUnVerifiedAck.class) {
			AlreadyUsePop pop = new AlreadyUsePop("<html>가격정보가 변동되었습니다.<br>다시 시도해주세요<html>");
		} else if (packet.getClass() == ScBuyRoomAck.class) {
			ScBuyRoomAck ack = (ScBuyRoomAck) packet;

			if (ack.eResult == EResult.SUCCESS) {
				if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
					BaseFrame.getInstance().view("LoginMain");
				} else {
					BaseFrame.getInstance().openMainLayout(ack.roomList, ack.myReserList, ack.exitList, null);
				}
				AlreadyUsePop pop = new AlreadyUsePop("구매완료되었습니다.");
			} else if (ack.eResult == EResult.ALEADY_EXIST_DATA) {
				AlreadyUsePop pop = new AlreadyUsePop("이미 사용 중인 좌석입니다.");
			} else {
				AlreadyUsePop pop = new AlreadyUsePop("<html>결제에 실패하였습니다.<br>관리자에게 문의해주세요<html>");
			}
		}
	}

	public void updateInfo(ArrayList<RoomProduct> roomList) {
		roomInfoList = roomList;
	}

	public void openSeatingArrUI(EEnter enterType) {
		Seating_Arrangement sa = (Seating_Arrangement) jPanelArrl.get(2);
		view("Seating_Arrangement");
		sa.openPage(enterType);
		if (loginType == ELoginType.MOBILE) {
			sa.north_east.setText("로그아웃");
		} else if (loginType == ELoginType.KIOSK) {
			sa.north_east.setText("뒤로가기");
		}
	}

	public Seating_Arrangement getSeatingArrUI() {
		Seating_Arrangement sa = (Seating_Arrangement) jPanelArrl.get(2);
		return sa;
	}

	public ClientChatFrame getClientChatFrame() {
		return (ClientChatFrame) jPanelArrl.get(4);
	}

	public ClientChatFrame openClientChatFrame() {
		ClientChatFrame claChat = getClientChatFrame();
		view("ClientChatFrame");
		claChat.textArea.setText("");
		return claChat;
	}

	public LoginMain getLoginMain() {
		return (LoginMain) jPanelArrl.get(0);
	}

	public LockerMain openLockerMain() {
		LockerMain lockerMain = getLockerMain();
		view("LockerMain");
		lockerMain.updateLocker(null);

		return lockerMain;
	}

	public LockerMain getLockerMain() {
		LockerMain lockerMain = (LockerMain) jPanelArrl.get(5);
		return lockerMain;
	}

	public MainLayout openMainLayout(ArrayList<RoomProduct> reserAll, ArrayList<RoomProduct> myReser,
			ArrayList<RoomProduct> exitList, ArrayList<LockerData> lockerList) {

		updateData(reserAll, myReser, exitList, lockerList);
		MainLayout layout = (MainLayout) jPanelArrl.get(1);
		BaseFrame.getInstance().view("MainLayout");
		layout.updatePage();
		return layout;
	}

	public MainLayout getMainLayout() {
		return (MainLayout) jPanelArrl.get(1);
	}

	public void updateData(ArrayList<RoomProduct> reserAll, ArrayList<RoomProduct> myReser,
			ArrayList<RoomProduct> exitList, ArrayList<LockerData> lockerList) {

		if (reserAll != null) {
			roomInfoList = reserAll;
		}
		if (myReser != null) {
			System.out.println(userData);
			userData.myReservationList = myReser;
		}
		if (exitList != null) {
			userData.exitList = exitList;
		}
		if (lockerList != null) {
			this.lockerlist = lockerList;
		}

	}

	// 현재 사용하고 있는 룸 정보
	public RoomProduct getUsingRoom() {

		return checkMyReserRoom(null, Calendar.HOUR_OF_DAY);
	}

	public RoomProduct checkMyReserRoom(Calendar compCal, int field) {

		if (compCal == null) {
			compCal = Calendar.getInstance();
		}
		RoomProduct clone = null;
		if (userData != null) {
			for (RoomProduct product : userData.myReservationList) {
				for (int i = 0; i < product.calendarList.size(); i++) {
					Calendar cal = product.calendarList.get(i);
					if (CalCal.isSameTime(field, cal, compCal)) {
						if (clone == null) {
							clone = product.getClone();
						}
						clone.calendarList.add(cal);
					}
				}
			}
		}
		return clone;
	}

	public long getTodayRemainTime() {

		Calendar current = Calendar.getInstance();

		Calendar last = null;
		Calendar start = null;

		RoomProduct room = userData.getTodayRoom();

		if (room != null) {// 오늘 총 예약한 리스트
			for (Calendar cal : room.calendarList) {
				if (CalCal.isSameTime(Calendar.DATE, cal, current)) {
					if (last == null) {
						last = cal;
						start = cal;
					}
					if (last.getTimeInMillis() < cal.getTimeInMillis()) {
						last = cal;
					}
					if (start.getTimeInMillis() > cal.getTimeInMillis()) {
						start = cal;
					}
				}
			}
		}

		if (start != null) {
			// 오늘 예약한 남은시간
			if (start.getTimeInMillis() < current.getTimeInMillis()) {
				return (last.getTimeInMillis() + TimeUnit.HOURS.toMillis(1)) - current.getTimeInMillis();
			} else {
				return (last.getTimeInMillis() + TimeUnit.HOURS.toMillis(1)) - start.getTimeInMillis();
			}
		}
		return 0;
	}
}

class CheckRoomInfo extends Thread {
	@Override
	public void run() {
		try {
			while (true) {
				Calendar cal = Calendar.getInstance();
				if (cal.get(Calendar.MINUTE) == 0 && cal.get(Calendar.SECOND) == 0) {
					CsUpdateRoomSyn packet = new CsUpdateRoomSyn(BaseFrame.getInstance().userData.uuid);
					ClientNet.getInstance().sendPacket(packet);
				}
				sleep(800);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}