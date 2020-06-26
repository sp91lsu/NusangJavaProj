package client_p.ui_p;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
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
import server_p.packet_p.ack_p.ScBuyRoomAck;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScDuplicateIDAck;
import server_p.packet_p.ack_p.ScExitAck;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.ack_p.ScMoveSeatAck;
import server_p.packet_p.ack_p.ScSignUpAck;
import server_p.packet_p.broadCast.ScBuyLockerCast;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;

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
		startFrame();
	}

	void startFrame() {
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
		PacketMap.getInstance().map.put(ScRoomInfoBroadCast.class, (Receivable) this);
		PacketMap.getInstance().map.put(ScChatConnectAck.class, (Receivable) jPanelArrl.get(1));
		PacketMap.getInstance().map.put(ScChatBroadCast.class, (Receivable) jPanelArrl.get(5));
		PacketMap.getInstance().map.put(ScRoomInfoBroadCast.class, this);
		PacketMap.getInstance().map.put(ScMoveSeatAck.class, (Receivable) jPanelArrl.get(2));
		PacketMap.getInstance().map.put(ScExitAck.class, (Receivable) jPanelArrl.get(1));
		PacketMap.getInstance().map.put(ScBuyLockerCast.class, (Receivable) this);
		PacketMap.getInstance().map.put(ScDuplicateIDAck.class, (Receivable) signUpFrame);
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
			roomInfoList = roomInfoCast.roomList;

			for (JButton all : getSeatingArrUI().all) {// 모든버튼
				for (RoomProduct room : getCurrentRoomList()) {
					if (all.getText().equals(room.name)) {
						all.setBackground(Color.red);
					}
				}
			}

			if (payment.isVisible()) {
				payment.updatePayment();
			}
			if (getReservationMain().isVisible()) {
				getReservationMain().updateReservationMain();
			}
		} else if (packet.getClass() == ScBuyLockerCast.class) {
			ScBuyLockerCast packetAck = (ScBuyLockerCast) packet;
			if (packetAck.eResult == EResult.SUCCESS) {
				BaseFrame.getInstance().view("LoginMain");

				for (LockerData data : packetAck.lockerList) {// 구매한 라커 번호
					System.out.println("data" + data);
					for (LockerBtn lockerbtn : getLockerMain().list) {
						if (lockerbtn.data.id.equals(data.id)) {
							lockerbtn.btn.setBackground(null);
							lockerbtn.btn.setEnabled(false);
						}
					}
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

	public Seating_Arrangement getSeatingArrUI() {
		Seating_Arrangement sa = (Seating_Arrangement) jPanelArrl.get(2);
		sa.setBtnColor();
		return sa;
	}

	public ClientChatFrame getClientChatFrame() {
		return (ClientChatFrame) jPanelArrl.get(5);
	}

	public LockerMain getLockerMain() {
		return (LockerMain) jPanelArrl.get(6);
	}

	public MainLayout openMainLayout() {
		BaseFrame.getInstance().view("MainLayout");
		return (MainLayout) jPanelArrl.get(1);
	}

	public ReservationMain getReservationMain() {
		return (ReservationMain) jPanelArrl.get(4);
	}

	// 오늘 총 이용시간
	public long totTodayUseTime() {

		int hour = 0;

		// 현재 시간
		Calendar current = Calendar.getInstance();

		// 마지막 시간
		current.add(Calendar.HOUR, 1);

		// 오늘 예약한 상품
		for (RoomProduct room : userData.myReservationList) {

			for (Calendar time : room.calendarList) {

				if (time.getTimeInMillis() <= current.getTimeInMillis()) {

					hour++;
				}
			}
		}
		// 오늘 누적 시간만 밀리세컨즈로
		long hourMilli = TimeUnit.HOURS.toMillis(hour);

		return hourMilli - getTodayRemainTime();
	}

	// 현재 사용하고 있는 룸 정보
	public RoomProduct getUsingRoom() {
		Calendar current = Calendar.getInstance();
		RoomProduct clone = null;

		for (RoomProduct product : userData.myReservationList) {
			for (int i = 0; i < product.calendarList.size(); i++) {
				Calendar cal = product.calendarList.get(i);

				if (isSameTime(Calendar.HOUR_OF_DAY, cal, current) && !product.isExit) {
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

		// 오늘예약한 리스트만 가지고오기
		Calendar current = Calendar.getInstance();
		current.set(Calendar.HOUR_OF_DAY, -1);
		long remainTime = 0;

		RoomProduct cRoom = getUsingRoom();

		if (cRoom != null) {// 오늘 총 예약한 리스트
			for (Calendar cal : cRoom.calendarList) {

				System.out.println("예약한 시간" + cal.getTimeInMillis());
				System.out.println("현재 시간" + current.getTimeInMillis());
				if (cal.getTimeInMillis() >= current.getTimeInMillis()) {
					remainTime += 1000 * 60 * 60;
				}
			}

			// 오늘 예약한 남은시간
			return remainTime -= TimeUnit.MINUTES.toMillis(current.get(Calendar.MINUTE));
		} else {
			return 0;
		}
	}
}

class CheckRoomInfo extends Thread {
	@Override
	public void run() {
		try {
			while (true) {
				Calendar cal = Calendar.getInstance();
				if (cal.get(Calendar.MINUTE) == 0 && cal.get(Calendar.SECOND) == 0) {
					CsUpdateRoomSyn packet = new CsUpdateRoomSyn(BaseFrame.getInstance().roomProduct,
							BaseFrame.getInstance().userData.uuid);
					ClientNet.getInstance().sendPacket(packet);
				}
				sleep(800);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}