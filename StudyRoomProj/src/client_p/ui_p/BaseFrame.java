package client_p.ui_p;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
import server_p.packet_p.ack_p.ScSignUpAck;
import server_p.packet_p.ack_p.ScUpdateRoomInfoAck;
import server_p.packet_p.broadCast.ScBuyLockerCast;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;

enum EEnter {
	MOBILE, SEATCHANGE, PRIVROOM, GROUPROOM
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
		PacketMap.getInstance().map.put(ScSignUpAck.class, (Receivable) signUpFrame); // 회원가입
		PacketMap.getInstance().map.put(ScBuyRoomAck.class, paymentPop);// 결제
		PacketMap.getInstance().map.put(ScChatConnectAck.class, (Receivable) jPanelArrl.get(1));
		PacketMap.getInstance().map.put(ScChatBroadCast.class, (Receivable) jPanelArrl.get(4));
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

			if (rCalcFrame.isVisible()) {
				JDialog jd = new JDialog();
				jd.setBounds(500, 500, 150, 150);
				jd.getContentPane().setLayout(new GridLayout(2, 1));
				JLabel jl = new JLabel("좌석현황이 변경되었습니다.");
				JButton jb = new JButton("확인");
				jd.getContentPane().add(jl);
				jd.getContentPane().add(jb);
				jd.setVisible(true);
				rCalcFrame.setVisible(false);
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jd.dispose();
					}
				});
			}

		} else if (packet.getClass() == ScBuyLockerCast.class) {
			ScBuyLockerCast packetAck = (ScBuyLockerCast) packet;
			if (packetAck.eResult == EResult.SUCCESS) {
				updateData(null, null, null, packetAck.lockerList);
				getLockerMain().updateLocker();
			} else {
				System.out.println("사물함 결제 실패");
			}
		} else if (packet.getClass() == ScBuyLockerAck.class) {
			ScBuyLockerAck packetAck = (ScBuyLockerAck) packet;
			if (packetAck.eResult == EResult.SUCCESS) {

				userData.locker = packetAck.myLocker;
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
		lockerMain.updateLocker();

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

//	public ReservationMain getReservationMain() {
//		return (ReservationMain) jPanelArrl.get(4);
//	}

	// 현재 사용하고 있는 룸 정보
	public RoomProduct getUsingRoom() {

		return checkMyReserRoom(Calendar.HOUR_OF_DAY);
	}

	public RoomProduct checkMyReserRoom(int field) {
		Calendar current = Calendar.getInstance();
		RoomProduct clone = null;
		if (userData != null) {
			for (RoomProduct product : userData.myReservationList) {
				for (int i = 0; i < product.calendarList.size(); i++) {
					Calendar cal = product.calendarList.get(i);
					if (isSameTime(field, cal, current)) {
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

	public boolean isSameTime(int field, Calendar cal1, Calendar cal2) {

		int last = Calendar.YEAR;

		if (field >= Calendar.YEAR) {
			last = Calendar.YEAR;
		}
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