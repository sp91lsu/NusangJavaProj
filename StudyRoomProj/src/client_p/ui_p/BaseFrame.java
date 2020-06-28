package client_p.ui_p;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client_p.ClientNet;
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
import server_p.packet_p.ack_p.ScUpdateRoomInfoAck;
import server_p.packet_p.ack_p.ScSignUpAck;
import server_p.packet_p.broadCast.ScBuyLockerCast;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;

enum EEnter {
	NONE, SEATCHANGE, PRIVROOM, GROUPROOM
}

public class BaseFrame extends JFrame implements Receivable {

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

	UserData userData = null;
	public Payment payment = new Payment();
	public SignUpMain signUpFrame = new SignUpMain();
	public PaymentPopFrame paymentPop = new PaymentPopFrame();
	public RoomProduct roomProduct;

	public BaseFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void startFrame() {
		payment.setVisible(false);
		addToBaseFrame(new LoginMain());
		addToBaseFrame(new MainLayout());
		addToBaseFrame(new Seating_Arrangement());
		addToBaseFrame(new LockerMain());
		addToBaseFrame(new ReservationMain());
		addToBaseFrame(new ClientChatFrame());
		addToBaseFrame(new LockerMain());
		setBackground(Color.GRAY);
		setBounds(510, 140, 900, 800);
		setVisible(true);
		view("LoginMain");
		PacketMap.getInstance().map.put(ScLoginAck.class, (Receivable) jPanelArrl.get(0)); // 로그인
		PacketMap.getInstance().map.put(ScSignUpAck.class, (Receivable) signUpFrame); // 회원가입
		PacketMap.getInstance().map.put(ScBuyRoomAck.class, paymentPop);// 결제
		PacketMap.getInstance().map.put(ScChatConnectAck.class, (Receivable) jPanelArrl.get(1));
		PacketMap.getInstance().map.put(ScChatBroadCast.class, (Receivable) jPanelArrl.get(5));
		PacketMap.getInstance().map.put(ScRoomInfoBroadCast.class, this);
		PacketMap.getInstance().map.put(ScMoveSeatAck.class, (Receivable) jPanelArrl.get(2));
		PacketMap.getInstance().map.put(ScExitAck.class, (Receivable) jPanelArrl.get(1));
		PacketMap.getInstance().map.put(ScBuyLockerCast.class, (Receivable) this);
		PacketMap.getInstance().map.put(ScDuplicateIDAck.class, (Receivable) signUpFrame);
		PacketMap.getInstance().map.put(ScBuyLockerAck.class, (Receivable) this);
		PacketMap.getInstance().map.put(ScUpdateRoomInfoAck.class, (Receivable) this);
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

//			if (payment.isVisible()) {
//				payment.updatePayment();
//			}
		} else if (packet.getClass() == ScBuyLockerCast.class) {
			ScBuyLockerCast packetAck = (ScBuyLockerCast) packet;
			if (packetAck.eResult == EResult.SUCCESS) {
				updateData(null, null, null, packetAck.lockerList);
				openLockerMain().updateLocker();
			} else {
				System.out.println("사물함 결제 실패");
			}
		} else if (packet.getClass() == ScBuyLockerAck.class) {
			ScBuyLockerAck packetAck = (ScBuyLockerAck) packet;
			if (packetAck.eResult == EResult.SUCCESS) {
				BaseFrame.getInstance().openMainLayout(null, null, null, packetAck.lockerList);

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
				System.out.println("사물함 결제 실패");
			}
		}
		// 예약페이지
		// 당일결제페이지
	}

	public void updateInfo(ArrayList<RoomProduct> roomList) {
		roomInfoList = roomList;
	}

	// 현재 룸 정보 결제를 위해 시간 넣기
	public void setCurrentRoomInfo(ArrayList<Calendar> calendarList) {
		if (roomProduct != null) {
			roomProduct.setDate(userData.uuid, calendarList);
		} else {
			System.out.println("현재 결제하려고하는 룸 정보가 null임");
		}
	}

	// 예약이 이미 되어있는 int 값 리스트 (payment, reservationMain) 에서 사용하는 함수
	public ArrayList<Integer> getCheckList(int month, int date) {

		ArrayList<Integer> valueList = new ArrayList<Integer>();

		System.out.println("서버에서 받은 룸정보 ");
		// 서버에서 계속 갱신되는 정보 돌려서
		for (RoomProduct roomInfo : roomInfoList) {

			// 현재 구매하려는 룸 정보와 같으면
			if (roomInfo.name.equals(roomProduct.name)) {

				// 그 룸정보의 캘린더를 구해서
				for (Calendar cal : roomInfo.calendarList) {

					if (cal.get(Calendar.MONTH) == month && cal.get(Calendar.DATE) == date)
						for (int i = 1; i <= 24; i++) {
							if (!valueList.contains(i) && cal.get(Calendar.HOUR_OF_DAY) == i) {

								System.out.println(i + "시");
								valueList.add(i);
							}
						}
				}
			}
		}
		return valueList;
	}

	// 현재 이용중인 룸 리스트 (payment, reservationMain) 에서 사용하는 함수
	public ArrayList<RoomProduct> getCurrentRoomList() {

		ArrayList<RoomProduct> cRoomList = new ArrayList<RoomProduct>();

		Calendar current = Calendar.getInstance();
		System.out.println("서버에서 받은 룸정보 ");
		// 서버에서 계속 갱신되는 정보 돌려서
		for (RoomProduct roomInfo : roomInfoList) {

			// 그 룸정보의 캘린더를 구해서
			for (Calendar cal : roomInfo.calendarList) {

				if (isSameTime(Calendar.HOUR_OF_DAY, cal, current)) {

					cRoomList.add(roomInfo);
				}
			}
		}
		return cRoomList;
	}

	public void openSeatingArrUI(EEnter enterType) {
		Seating_Arrangement sa = (Seating_Arrangement) jPanelArrl.get(2);
		view("Seating_Arrangement");
		sa.openPage(enterType);
	}

	public Seating_Arrangement getSeatingArrUI() {
		Seating_Arrangement sa = (Seating_Arrangement) jPanelArrl.get(2);
		return sa;
	}

	public ClientChatFrame getClientChatFrame() {
		return (ClientChatFrame) jPanelArrl.get(5);
	}

	public LockerMain openLockerMain() {
		LockerMain lockerMain = getLockerMain();
		view("LockerMain");
		lockerMain.updateLocker();

		return lockerMain;
	}

	public LockerMain getLockerMain() {
		LockerMain lockerMain = (LockerMain) jPanelArrl.get(6);
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

	public ReservationMain getReservationMain() {
		return (ReservationMain) jPanelArrl.get(4);
	}

	// 현재 사용하고 있는 룸 정보
	public RoomProduct getUsingRoom() {

		return checkMyReserRoom(Calendar.HOUR_OF_DAY);
	}

	public RoomProduct checkMyReserRoom(int field) {
		Calendar current = Calendar.getInstance();
		RoomProduct clone = null;

		for (RoomProduct product : userData.myReservationList) {
			for (int i = 0; i < product.calendarList.size(); i++) {
				Calendar cal = product.calendarList.get(i);
				if (isSameTime(field, cal, current)) {
					clone = product.getClone();
					clone.calendarList.add(cal);
				}
			}
		}
		return clone;
	}

	public boolean isSameTime(int field, Calendar cal1, Calendar cal2) {

		int last = Calendar.MONTH;

		if (field >= Calendar.MONTH) {
			last = Calendar.MONTH;
		}
		if (field >= Calendar.DATE) {
			last = Calendar.DATE;
		}
		if (field >= Calendar.HOUR_OF_DAY) {
			last = Calendar.HOUR_OF_DAY;
		}

		return cal1.get(last) == cal2.get(last);
	}

	public long getTodayRemainTime() {

		Calendar current = Calendar.getInstance();

		Calendar last = null;
		Calendar start = null;

		RoomProduct room = userData.getTodayRoom();

		System.out.println("유저 오늘 방 정보 " + room);
		if (room != null) {// 오늘 총 예약한 리스트
			for (Calendar cal : room.calendarList) {
				if (isSameTime(Calendar.DATE, cal, current)) {
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
			System.out.println("마지막 끝나는 시간" + last.getTime());
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