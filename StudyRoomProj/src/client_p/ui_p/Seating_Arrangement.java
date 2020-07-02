package client_p.ui_p;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.org.apache.bcel.internal.generic.IADD;

import client_p.CalCal;
import client_p.EEnter;
import client_p.Receivable;
import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.ELoginType;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScMoveSeatAck;

enum EState {
	INIT, EMPTY, USE, MY, DIM, DISABLE
}

public class Seating_Arrangement extends JPanel implements Receivable {

	class RoomObj {

		JButton btn;
		RoomProduct room;
		EState state;

		public RoomObj(int id, JPanel panel) {
			super();
			room = DataManager.getInstance().roomMap.get(id);
			this.btn = new JButton(room.name);
			panel.add(btn);
			btn.addActionListener(new BtnAct(this));
			all.add(this);
			setState(EState.EMPTY);
		}

		void setState(EState state) {

			this.state = state;
			switch (state) {
			case EMPTY:
				btn.setBackground(new Color(50, 205, 50));
				btn.setEnabled(true);
				break;
			case INIT:
				btn.setBackground(new Color(50, 205, 50));
				btn.setEnabled(false);
				break;
			case MY:
				btn.setBackground(new Color(65, 105, 225));
				btn.setEnabled(false);
				break;
			case USE:
				btn.setBackground(new Color(255, 54, 54));
				btn.setEnabled(false);
				break;
			case DIM:
				btn.setBackground(new Color(125, 125, 125));
				btn.setEnabled(false);
				break;
			case DISABLE:
				btn.setEnabled(false);
				break;
			}
		}
	}

	static JLabel north_west;

	JPanel panel_center;
	JPanel panel_center_east;

	int starttime = 0;
	int endtime = 1;
	JPanel timeSelectPane;
	JLabel lblNewLabel_7;
	JLabel lblNewLabel_8;
	JButton north_east;
	EEnter enterType = EEnter.MOBILE;
	JComboBox dateCBox;
	JComboBox monthCBox;
	JComboBox yearCBox;
	JComboBox timeStartCBox;
	JComboBox timeEndCbox;
	ReserInfoPane reserInfoPane;
	int setMonth;
	int nowMonth;
	int setYear;
	int nowYear;
	int setDate;
	int nowDate;
	int nowMaxDate;
	int nowHour;
	ArrayList<RoomObj> group = new ArrayList<RoomObj>();// 단체석
	ArrayList<RoomObj> solo = new ArrayList<RoomObj>();// 개인석
	ArrayList<RoomObj> all = new ArrayList<RoomObj>();// 전체
	ArrayList<JComboBox> comboList = new ArrayList<JComboBox>();
	JButton searchButton;
	JButton selectBtn;
	JLabel iconLabel1, iconLabel2, iconLabel3, iconLabel4;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(200, 30, 900, 800);
		frame.getContentPane().add(new Seating_Arrangement());
		frame.setVisible(true);
	}

	public Seating_Arrangement() {

		setLayout(null);

		// 상단 패널
		JPanel panel_north = new JPanel();
		panel_north.setBounds(10, 5, 860, 30);
		add(panel_north);
		panel_north.setLayout(new BorderLayout(0, 0));

		this.north_west = new JLabel("2020-06-18  21:24");// 날자,시간
		north_west.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_north.add(north_west, BorderLayout.WEST);

		if (BaseFrame.getInstance().loginType != ELoginType.MANAGER) {
			north_east = new JButton("뒤로가기");
			north_east.setFont(new Font("굴림", Font.PLAIN, 17));
			panel_north.add(north_east, BorderLayout.EAST);
			north_east.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
						BaseFrame.getInstance().view("LoginMain");
						BaseFrame.getInstance().getLoginMain().logSet();
					} else {
						BaseFrame.getInstance().view("MainLayout");
					}
					group_state(EState.EMPTY);
					solo_state(EState.EMPTY);
				}
			});
		}
		
		JPanel north_center = new JPanel();
		panel_north.add(north_center, BorderLayout.CENTER);
		north_center.setLayout(new BorderLayout(0, 0));

		JPanel north_center_center = new JPanel();
		north_center.add(north_center_center, BorderLayout.CENTER);
//            north_center_center.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_5 = new JPanel();
		north_center_center.add(panel_5);

		JPanel panel_6 = new JPanel();
		north_center_center.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_1 = new JLabel("이용가능");
		panel_6.add(lblNewLabel_1);

		ImageIcon umgIc = new ImageIcon("img/green.png");
		JLabel lblNewLabel_2 = new JLabel(umgIc);
		panel_6.add(lblNewLabel_2);

		JPanel panel_7 = new JPanel();
		north_center_center.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_3 = new JLabel("사용중");
		panel_7.add(lblNewLabel_3);

		umgIc = new ImageIcon("img/red.png");
		JLabel lblNewLabel_4 = new JLabel(umgIc);
		panel_7.add(lblNewLabel_4);

		JPanel panel_8 = new JPanel();
		north_center_center.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel_5 = new JLabel("선택 좌석");
		panel_8.add(lblNewLabel_5);

		umgIc = new ImageIcon("img/cyan.png");
		JLabel lblNewLabel_6 = new JLabel(umgIc);
		panel_8.add(lblNewLabel_6);

		JPanel panel_9 = new JPanel();
		north_center_center.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));

		lblNewLabel_7 = new JLabel("현재 좌석");
		panel_9.add(lblNewLabel_7);
		lblNewLabel_7.setVisible(false);

		umgIc = new ImageIcon("img/blue.png");
		lblNewLabel_8 = new JLabel(umgIc);
		panel_9.add(lblNewLabel_8);
		lblNewLabel_8.setVisible(false);

		JPanel north_center_west = new JPanel();
		north_center.add(north_center_west, BorderLayout.WEST);

		JPanel north_center_east = new JPanel();
		north_center.add(north_center_east, BorderLayout.EAST);

		// 중앙패널
		panel_center = new JPanel();
		panel_center.setBounds(10, 40, 890, 650);
		add(panel_center);
		panel_center.setLayout(null);

		if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
			setDate++;
			// 중앙오른쪽패널(예약현황창)
			reserInfoPane = new ReserInfoPane();
			panel_center.add(reserInfoPane, BorderLayout.EAST);
		}
		
		iconLabel1 = new JLabel();
		iconLabel1.setBounds(0, 0, 50, 50);
		ImageIcon imageIcon1 = new ImageIcon(new ImageIcon("img/shower.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		iconLabel1.setIcon(imageIcon1);
		panel_center.add(iconLabel1);
		
		iconLabel2 = new JLabel();
		iconLabel2.setBounds(0, 110, 50, 50);
		ImageIcon imageIcon2 = new ImageIcon(new ImageIcon("img/sing.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		iconLabel2.setIcon(imageIcon2);
		panel_center.add(iconLabel2);
		
		iconLabel3 = new JLabel();
		iconLabel3.setBounds(0, 260, 50, 50);
		ImageIcon imageIcon3 = new ImageIcon(new ImageIcon("img/party.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		iconLabel3.setIcon(imageIcon3);
		panel_center.add(iconLabel3);
		
		iconLabel4 = new JLabel();
		iconLabel4.setBounds(120, 510, 50, 50);
		ImageIcon imageIcon4 = new ImageIcon(new ImageIcon("img/manner.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
		iconLabel4.setIcon(imageIcon4);
		panel_center.add(iconLabel4);
		
		// 여기서부터 룸 버튼
		RoomObj roomBtn1 = new RoomObj(1019, panel_center);
		roomBtn1.btn.setBounds(0, 0, 220, 110);
		group.add(roomBtn1);
		
		RoomObj roomBtn2 = new RoomObj(1018, panel_center);
		roomBtn2.btn.setBounds(220, 0, 170, 110);
		group.add(roomBtn2);

		RoomObj roomBtn3 = new RoomObj(1016, panel_center);
		roomBtn3.btn.setBounds(390, 0, 120, 110);
		group.add(roomBtn3);

		RoomObj roomBtn4 = new RoomObj(1017, panel_center);
		roomBtn4.btn.setBounds(510, 0, 120, 110);
		group.add(roomBtn4);

		RoomObj roomBtn5 = new RoomObj(1014, panel_center);
		roomBtn5.btn.setBounds(515, 190, 120, 90);
		group.add(roomBtn5);

		RoomObj roomBtn6 = new RoomObj(1015, panel_center);
		roomBtn6.btn.setBounds(515, 280, 120, 90);
		group.add(roomBtn6);

		RoomObj roomBtn7 = new RoomObj(1011, panel_center);
		roomBtn7.btn.setBounds(515, 370, 120, 70);
		group.add(roomBtn7);

		RoomObj roomBtn8 = new RoomObj(1012, panel_center);
		roomBtn8.btn.setBounds(515, 440, 120, 70);
		group.add(roomBtn8);

		RoomObj roomBtn9 = new RoomObj(1013, panel_center);
		roomBtn9.btn.setBounds(515, 510, 120, 70);
		group.add(roomBtn9);

		RoomObj roomBtn10 = new RoomObj(1020, panel_center);
		roomBtn10.btn.setBounds(0, 110, 100, 150);
		group.add(roomBtn10);

		RoomObj roomBtn11 = new RoomObj(1021, panel_center);
		roomBtn11.btn.setBounds(0, 260, 100, 155);
		group.add(roomBtn11);

		// 매너존 패널
		JPanel mannerzone_panel = new JPanel();
		mannerzone_panel.setBounds(0, 415, 240, 250);
		panel_center.add(mannerzone_panel);
		mannerzone_panel.setLayout(null);

		// 매너존 라벨,버튼
		JLabel label_manner = new JLabel("매너존");
		label_manner.setFont(new Font("굴림", Font.PLAIN, 20));
		label_manner.setBounds(50, 110, 70, 20);
		mannerzone_panel.add(label_manner);

		RoomObj btnM_1 = new RoomObj(1006, mannerzone_panel);
		btnM_1.btn.setBounds(0, 0, 80, 70);
		solo.add(btnM_1);

		RoomObj btnM_2 = new RoomObj(1007, mannerzone_panel);
		btnM_2.btn.setBounds(80, 0, 80, 70);
		solo.add(btnM_2);

		RoomObj btnM_3 = new RoomObj(1008, mannerzone_panel);
		btnM_3.btn.setBounds(0, 160, 80, 70);
		solo.add(btnM_3);

		RoomObj btnM_4 = new RoomObj(1009, mannerzone_panel);
		btnM_4.btn.setBounds(80, 160, 80, 70);
		solo.add(btnM_4);

		RoomObj btnM_5 = new RoomObj(1010, mannerzone_panel);
		btnM_5.btn.setBounds(160, 160, 80, 70);
		solo.add(btnM_5);

		// 일반석 패널
		JPanel normalzone_panel = new JPanel();
		normalzone_panel.setBounds(210, 160, 240, 250);
		panel_center.add(normalzone_panel);
		normalzone_panel.setLayout(null);
		// 일반실 라벨,버튼
		JLabel label_normal = new JLabel("일반실");
		label_normal.setFont(new Font("굴림", Font.PLAIN, 20));
		label_normal.setBounds(80, 105, 70, 20);
		normalzone_panel.add(label_normal);

		RoomObj btnN_1 = new RoomObj(1000, normalzone_panel);
		btnN_1.btn.setBounds(0, 0, 80, 70);
		solo.add(btnN_1);

		RoomObj btnN_2 = new RoomObj(1001, normalzone_panel);
		btnN_2.btn.setBounds(80, 0, 80, 70);
		solo.add(btnN_2);

		RoomObj btnN_3 = new RoomObj(1002, normalzone_panel);
		btnN_3.btn.setBounds(160, 0, 80, 70);
		solo.add(btnN_3);

		RoomObj btnN_4 = new RoomObj(1003, normalzone_panel);
		btnN_4.btn.setBounds(0, 160, 80, 70);
		solo.add(btnN_4);

		RoomObj btnN_5 = new RoomObj(1004, normalzone_panel);
		btnN_5.btn.setBounds(80, 160, 80, 70);
		solo.add(btnN_5);

		RoomObj btnN_6 = new RoomObj(1005, normalzone_panel);
		btnN_6.btn.setBounds(160, 160, 80, 70);
		solo.add(btnN_6);

		for (RoomObj soloSeat : solo) {
			soloSeat.btn.setFont(new Font("고딕", Font.BOLD, 11));
		}

		// 휴게실
		JPanel restzone_panel = new JPanel();
		restzone_panel.setBackground(Color.ORANGE);
		restzone_panel.setBounds(240, 495, 150, 150);
		panel_center.add(restzone_panel);
		restzone_panel.setLayout(null);
		// 휴게실 안에 들어갈것은 여기서
		JLabel label_rest = new JLabel("휴게실");
		label_rest.setFont(new Font("굴림", Font.PLAIN, 20));
		label_rest.setBounds(55, 70, 70, 20);
		restzone_panel.add(label_rest);

		JPanel locker = new JPanel();
		locker.setBackground(Color.LIGHT_GRAY);
		locker.setBounds(390, 495, 30, 150);
		panel_center.add(locker);
		locker.setLayout(null);

		JLabel lblNewLabel = new JLabel("<html>사<br>물<br>함<html>");
		lblNewLabel.setBounds(5, 30, 32, 79);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		locker.add(lblNewLabel);

		SetNowTime now_time = new SetNowTime(north_west);
		now_time.start();
		setVisible(true);

//////////////////////////////  예약날짜선택 버튼		

		timeSelectPane = new JPanel();
		timeSelectPane.setBounds(10, 680, 860, 70);
		add(timeSelectPane);
		timeSelectPane.setLayout(null);

		// 년도 선택
		Vector<Integer> yearCnt = new Vector<Integer>();
		for (int i = 2020; i <= 2021; i++) {
			yearCnt.add(i);
		}
		yearCBox = new JComboBox(yearCnt);
		yearCBox.setBounds(12, 28, 83, 33);
		yearCBox.setSelectedItem(setYear);
		timeSelectPane.add(yearCBox);
		yearCBox.addActionListener(new yearAct());

		// 월 선택 + 일자 생성
		Vector<Integer> monthCnt = new Vector<Integer>();
		for (int i = nowMonth; i <= 12; i++) {
			monthCnt.add(i);
		}
		monthCBox = new JComboBox(monthCnt);
		monthCBox.setBounds(136, 28, 41, 33);
		monthCBox.setSelectedItem(setMonth);
		timeSelectPane.add(monthCBox);
		monthCBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dateCBox.removeAllItems();
				if (monthCBox.getSelectedItem() != null)
					setMonth = (int) monthCBox.getSelectedItem();
				Calendar selectMonth = Calendar.getInstance();
				selectMonth.set(Calendar.YEAR, setYear);
				selectMonth.set(Calendar.MONTH, setMonth - 1);
				selectMonth.set(Calendar.DATE, 1);
				System.out.println(">>>셋몬쓰 뭐야??>>>" + setMonth);
				int last = selectMonth.getActualMaximum(Calendar.DATE);
				if (BaseFrame.getInstance().loginType != ELoginType.KIOSK) {
					if (nowYear == setYear && nowMonth == setMonth) {
						for (int i = nowMaxDate; i <= last; i++) {
							dateCBox.addItem(i);
						}
					} else {
						System.out.println("여기로 가야 맞지!!!뭐야??>>" + selectMonth.getTime());
						for (int i = 1; i <= last; i++) {
							dateCBox.addItem(i);
						}
					}
				}
				btn_state(EState.INIT);
			}
		});

		// 일자 선택
		Vector<Integer> dateCnt = new Vector<Integer>();
		for (int i = setDate; i <= 31; i++) {
			dateCnt.add(i);
		}
		dateCBox = new JComboBox(dateCnt);
		dateCBox.setBounds(219, 28, 41, 33);
		timeSelectPane.add(dateCBox);
		dateCBox.setSelectedItem(setDate);
		dateCBox.setEnabled(BaseFrame.getInstance().loginType == ELoginType.MOBILE);
		dateCBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (dateCBox.getSelectedItem() != null) {
					setDate = (int) dateCBox.getSelectedItem();
				}
				btn_state(EState.INIT);
				System.out.println("일자선택(셋데이트는??)>>" + setDate);
			}
		});

		// 시작 시간 선택
		timeStartCBox = new JComboBox();
		timeStartCBox.setBounds(313, 28, 83, 33);
		timeSelectPane.add(timeStartCBox);
		for (int i = 0; i <= 23; i++) {
			timeStartCBox.addItem(i);
		}
		timeStartCBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeStartCBox.getSelectedItem() != null) {
					starttime = (int) timeStartCBox.getSelectedItem();
				}
				timeEndCbox.removeAllItems();
				for (int i = starttime + 1; i <= 24; i++) {
					timeEndCbox.addItem(i);
				}
				btn_state(EState.INIT);
			}
		});

		timeEndCbox = new JComboBox();
		timeEndCbox.setBounds(428, 28, 83, 33);
		timeSelectPane.add(timeEndCbox);
		for (int i = 1; i <= 24; i++) {
			timeEndCbox.addItem(i);
		}
		timeEndCbox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeEndCbox.getSelectedItem() != null) {
					endtime = (int) timeEndCbox.getSelectedItem();
				}
				btn_state(EState.INIT);
			}
		});

		searchButton = new JButton("검색");
		searchButton.setBounds(544, 30, 91, 28);
		timeSelectPane.add(searchButton);
		searchButton.addActionListener(new SearchBtnAct());

		JLabel yearL = new JLabel("년");
		yearL.setBounds(107, 28, 29, 33);
		timeSelectPane.add(yearL);

		JLabel monthL = new JLabel("월");
		monthL.setBounds(189, 28, 29, 33);
		timeSelectPane.add(monthL);

		JLabel dateL = new JLabel("일");
		dateL.setBounds(272, 28, 29, 33);
		timeSelectPane.add(dateL);

		JLabel timeSelecL = new JLabel("~");
		timeSelecL.setBounds(408, 28, 29, 33);
		timeSelectPane.add(timeSelecL);

//		JButton payButton = new JButton("결제");
//		payButton.setBounds(669, 21, 141, 40);
//		timeSelectPane.add(payButton);

		comboList.add(monthCBox);
		comboList.add(yearCBox);
		comboList.add(dateCBox);

	}

	public void monthCom_state() {
		monthCBox.removeAllItems();
		for (int i = nowHour; i <= 12; i++) {
			monthCBox.addItem(i);
		}
	}

	public void startTime_state() {
		timeStartCBox.removeAllItems();
		for (int i = nowHour; i <= 23; i++) {
			timeStartCBox.addItem(i);
		}
	}

//	public void combo_setNowD() {
//		System.out.println("작동하니???");
//		yearCBox.setSelectedItem(setYear);
//		monthCBox.setSelectedItem(setMonth);
//		dateCBox.setSelectedItem(setDate);
//	}
	

	public void combo_state(boolean state) {
		for (JComboBox box : comboList) {
			box.setEnabled(state);
		}
	}

	public void group_state(EState state) {// 그룹버튼 활성/비활성
		for (RoomObj button : group) {
			button.setState(state);
		}
	}

	public void solo_state(EState state) {// 그룹버튼 활성/비활성
		for (RoomObj button : solo) {
			button.setState(state);
		}
	}

	public void monthCBoxSetting() {

		monthCBox.removeAllItems();

		if (setYear == nowYear) {
			if (nowDate == Calendar.getInstance().getActualMaximum(Calendar.DATE)) {
				setMonth = nowMonth + 1;
				for (int i = setMonth; i <= 12; i++) {
					monthCBox.addItem(i);
				}
			} else {
				for (int i = nowMonth; i <= 12; i++) {
					monthCBox.addItem(i);
				}
			}
		} else {
			for (int i = 1; i <= 12; i++) {
				monthCBox.addItem(i);
			}
		}
	}

	public void openPage(EEnter enterType) {

		System.out.println("openPage " + enterType);
		timeSetting();
		System.out.println("입장 타입" + enterType);
		this.enterType = enterType;

		boolean isVisibleCheckBox = BaseFrame.getInstance().loginType != ELoginType.KIOSK;
		combo_state(isVisibleCheckBox);

		switch (enterType) {
		case SEATCHANGE:
			roomState();
			checkDate();
			group_state(EState.DIM);
			break;
		case MOBILE:
			btn_state(EState.INIT);
			yearCBox.setSelectedItem(nowYear);
			monthCBoxSetting();
			if (reserInfoPane != null) {
				reserInfoPane.OpenPage();
			}
			break;

		default:
			roomState();
			break;
		}

	}

	void mySeatCheck(RoomObj roomObj, Calendar pivotCal) {

		for (RoomProduct room : BaseFrame.getInstance().userData.myReservationList) {
			if (roomObj.room.id.equals(room.id)) {
				for (Calendar cal : room.calendarList) {
					if (CalCal.isSameTime(Calendar.HOUR_OF_DAY, pivotCal, cal)) {
						roomObj.setState(EState.MY);
						lblNewLabel_7.setVisible(true);
						lblNewLabel_8.setVisible(true);
					}
				}
			}
		}
	}

	@Override
	public void receive(PacketBase packet) {
		ScMoveSeatAck ack = (ScMoveSeatAck) packet;
		if (ack.eResult == EResult.SUCCESS) {
			BaseFrame.getInstance().openMainLayout(ack.reserListAll, ack.myReserList, ack.myExitList, null);
		} else {

		}
	}

	class BtnAct implements ActionListener {

		RoomObj roomObj;

		BtnAct(RoomObj roomObj) {
			this.roomObj = roomObj;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			roomState();
			checkDate();
			JButton select = (JButton) e.getSource();
			select.setBackground(new Color(135, 206, 250));

			if (enterType != EEnter.SEATCHANGE)// 좌석이동중이 아닐때
			{
				// 업데이트 된 가격 새로 복사
				// 페이지 여는 순간 현재 상품 복사
				roomObj.room.setInfo(BaseFrame.getInstance().userData.uuid, createBuyData());

				BaseFrame.getInstance().rCalcFrame.openPage(roomObj.room);

				// BaseFrame.getInstance().payment.resPossibleChk();
			} else// 좌석이동중일때
			{
				SeatChangeOkPop frame = new SeatChangeOkPop(roomObj.room.id);
			}
		}
	}

	public ArrayList<Calendar> createBuyData() {
		ArrayList<Calendar> calList = new ArrayList<Calendar>();

		for (int i = starttime; i < endtime; i++) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, setYear);
			cal.set(Calendar.MONTH, setMonth - 1);
			cal.set(Calendar.DATE, setDate);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.HOUR_OF_DAY, i);
			calList.add(cal);
		}
		return calList;
	}

	public void btn_state(EState state) {
		for (RoomObj allbtn : all) {
			allbtn.setState(state);
		}
	}

	// 검색버튼 눌렀을 때 실행 작업(예약 데이터와 비교하여 좌석버튼 활성화)
	class SearchBtnAct implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			roomState();
			checkDate();
			if (BaseFrame.getInstance().checkMyReserRoom(getSetCalendar(), Calendar.DATE) != null) {
				btn_state(EState.DISABLE);
				System.out.println("그날 내가 예약했음");
			}
		}

	}

	void checkDate() {

		Calendar start = getSetCalendar();
		// 시간이 맞물리면 빨간색 처리
		ArrayList<RoomProduct> roomList = BaseFrame.getInstance().roomInfoList;
		for (RoomProduct roomInfo : roomList) {
			for (Calendar cal : roomInfo.calendarList) {
				System.out.println("cal" + cal.getTime());
				System.out.println("start" + start.getTime());
				// 시간비교 => 년/월/일 비교
				if (CalCal.isSameTime(Calendar.YEAR, cal, start) && CalCal.isSameTime(Calendar.MONTH, cal, start)
						&& CalCal.isSameTime(Calendar.DATE, cal, start)) {

					for (RoomObj roomObj : all) {
						if (cal.get(Calendar.HOUR_OF_DAY) >= starttime && cal.get(Calendar.HOUR_OF_DAY) < endtime) {
							// System.out.println(cal.get(Calendar.HOUR_OF_DAY));
							if (roomInfo.id.equals(roomObj.room.id)) {
								start.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
								roomObj.setState(EState.USE);
								mySeatCheck(roomObj, start);
							}
						}
					}
				}
			}
		}

	}

	Calendar getSetCalendar() {
		Calendar start = Calendar.getInstance();
		start.set(Calendar.YEAR, setYear);
		start.set(Calendar.MONTH, setMonth - 1);
		start.set(Calendar.DATE, setDate);

		return start;
	}

	void roomState() {

		switch (enterType) {
		case GROUPROOM:
			group_state(EState.EMPTY);
			solo_state(EState.INIT);
			timeSelectPane.setVisible(true);
			break;
		case MOBILE:
			if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
				btn_state(EState.EMPTY);
			} else {
				btn_state(EState.INIT);
			}
			timeSelectPane.setVisible(true);
			break;
		case PRIVROOM:
			group_state(EState.INIT);
			solo_state(EState.EMPTY);
			timeSelectPane.setVisible(true);
			break;
		case SEATCHANGE:
			Calendar cal = Calendar.getInstance();
			starttime = cal.get(Calendar.HOUR_OF_DAY);
			endtime = cal.get(Calendar.HOUR_OF_DAY) + 1;
			timeSelectPane.setVisible(false);
			solo_state(EState.EMPTY);
			break;
		default:
			break;
		}
	}

	void timeSetting() {
		setMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		nowMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		setYear = Calendar.getInstance().get(Calendar.YEAR);
		nowYear = Calendar.getInstance().get(Calendar.YEAR);
		setDate = Calendar.getInstance().get(Calendar.DATE);
		nowDate = Calendar.getInstance().get(Calendar.DATE);
		nowMaxDate = Calendar.getInstance().get(Calendar.DATE) + 1;
		nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	public void setMyTimeShow(Calendar cal, int hour) {
		setYear = cal.get(Calendar.YEAR);
		setMonth = cal.get(Calendar.MONTH) + 1;
		setDate = cal.get(Calendar.DATE);
		starttime = hour;
		endtime = starttime + 1;
		searchButton.doClick();
	}

	class yearAct implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setYear = (int) yearCBox.getSelectedItem();
			monthCBox.removeAllItems();
			if (yearCBox.getSelectedItem() != null) {
				monthCBoxSetting();
			}

			btn_state(EState.INIT);
		}

	}
}
