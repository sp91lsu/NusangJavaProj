package client_p.ui_p;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsMoveSeatSyn;
import data_p.product_p.DataManager;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.ELoginType;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScMoveSeatAck;

public class Seating_Arrangement extends JPanel implements Receivable {

	public boolean seatChange = false;
	static JLabel north_west;
	int moveSeatId;
	JLabel lblNewLabel_7;
	JLabel lblNewLabel_8;

	ArrayList<TimeData> timeList = new ArrayList<TimeData>();
	ArrayList<JButton> group = new ArrayList<JButton>();// 단체석
	ArrayList<JButton> solo = new ArrayList<JButton>();// 개인석
	ArrayList<JButton> all = new ArrayList<JButton>();// 전체

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(500, 30, 900, 900);
		frame.getContentPane().add(new Seating_Arrangement());
		frame.setVisible(true);
	}

	public Seating_Arrangement() {
		setLayout(new BorderLayout(0, 0));
		// 상단 패널
		JPanel panel_north = new JPanel();
		add(panel_north, BorderLayout.NORTH);
		panel_north.setLayout(new BorderLayout(0, 0));

		this.north_west = new JLabel("2020-06-18  21:24");// 날자,시간
		north_west.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_north.add(north_west, BorderLayout.WEST);

		JButton north_east = new JButton("뒤로가기");
		north_east.setFont(new Font("굴림", Font.PLAIN, 17));
		panel_north.add(north_east, BorderLayout.EAST);
		north_east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seatChange = false;
				System.out.println("좌석이동중이냐?" + seatChange);
				BaseFrame.getInstance().view("MainLayout");
				BaseFrame.getInstance().getSeatingArrUI().group_state(true);
				BaseFrame.getInstance().getSeatingArrUI().solo_state(true);
			}
		});

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
		JPanel panel_center = new JPanel();
		add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(null);

		// 여기서부터 룸 버튼
		JButton roomBtn1 = new JButton("샤워실");
		roomBtn1.addActionListener(new BtnAct(roomBtn1));
		roomBtn1.setBounds(0, 0, 300, 150);
		panel_center.add(roomBtn1);
		group.add(roomBtn1);

		JButton roomBtn2 = new JButton("8인실");
		roomBtn2.addActionListener(new BtnAct(roomBtn2));
		roomBtn2.setBounds(300, 0, 250, 150);
		panel_center.add(roomBtn2);
		group.add(roomBtn2);

		JButton roomBtn3 = new JButton("6인실-1");
		roomBtn3.addActionListener(new BtnAct(roomBtn3));
		roomBtn3.setBounds(550, 0, 160, 150);
		panel_center.add(roomBtn3);
		group.add(roomBtn3);

		JButton roomBtn4 = new JButton("6인실-2");
		roomBtn4.addActionListener(new BtnAct(roomBtn4));
		roomBtn4.setBounds(710, 0, 160, 150);
		panel_center.add(roomBtn4);
		group.add(roomBtn4);

		JButton roomBtn5 = new JButton("4인실-1");
		roomBtn5.addActionListener(new BtnAct(roomBtn5));
		roomBtn5.setBounds(710, 220, 160, 150);
		panel_center.add(roomBtn5);
		group.add(roomBtn5);

		JButton roomBtn6 = new JButton("4인실-2");
		roomBtn6.addActionListener(new BtnAct(roomBtn6));
		roomBtn6.setBounds(710, 370, 160, 150);
		panel_center.add(roomBtn6);
		group.add(roomBtn6);

		JButton roomBtn7 = new JButton("2인실-1");
		roomBtn7.addActionListener(new BtnAct(roomBtn7));
		roomBtn7.setBounds(710, 520, 160, 100);
		panel_center.add(roomBtn7);
		group.add(roomBtn7);

		JButton roomBtn8 = new JButton("2인실-2");
		roomBtn8.addActionListener(new BtnAct(roomBtn8));
		roomBtn8.setBounds(710, 620, 160, 100);
		panel_center.add(roomBtn8);
		group.add(roomBtn8);

		JButton roomBtn9 = new JButton("2인실-3");
		roomBtn9.addActionListener(new BtnAct(roomBtn9));
		roomBtn9.setBounds(710, 720, 160, 100);
		panel_center.add(roomBtn9);
		group.add(roomBtn9);

		JButton roomBtn10 = new JButton("노래방");
		roomBtn10.addActionListener(new BtnAct(roomBtn10));
		roomBtn10.setBounds(0, 150, 160, 210);
		panel_center.add(roomBtn10);
		group.add(roomBtn10);

		JButton roomBtn11 = new JButton("파티룸");
		roomBtn11.addActionListener(new BtnAct(roomBtn11));
		roomBtn11.setBounds(0, 360, 160, 210);
		panel_center.add(roomBtn11);
		group.add(roomBtn11);

		// 매너존 패널
		JPanel mannerzone_panel = new JPanel();
		mannerzone_panel.setBounds(0, 570, 300, 250);
		panel_center.add(mannerzone_panel);
		mannerzone_panel.setLayout(null);

		// 매너존 라벨,버튼
		JLabel label_manner = new JLabel("매너존");
		label_manner.setFont(new Font("굴림", Font.PLAIN, 20));
		label_manner.setBounds(120, 115, 70, 20);
		mannerzone_panel.add(label_manner);

		JButton btnM_1 = new JButton("매너존-1");
		btnM_1.addActionListener(new BtnAct(btnM_1));
		btnM_1.setBounds(0, 0, 100, 90);
		mannerzone_panel.add(btnM_1);
		solo.add(btnM_1);

		JButton btnM_2 = new JButton("매너존-2");
		btnM_2.addActionListener(new BtnAct(btnM_2));
		btnM_2.setBounds(100, 0, 100, 90);
		mannerzone_panel.add(btnM_2);
		solo.add(btnM_2);

		JButton btnM_3 = new JButton("매너존-3");
		btnM_3.addActionListener(new BtnAct(btnM_3));
		btnM_3.setBounds(0, 160, 100, 90);
		mannerzone_panel.add(btnM_3);
		solo.add(btnM_3);

		JButton btnM_4 = new JButton("매너존-4");
		btnM_4.addActionListener(new BtnAct(btnM_4));
		btnM_4.setBounds(100, 160, 100, 90);
		mannerzone_panel.add(btnM_4);
		solo.add(btnM_4);

		JButton btnM_5 = new JButton("매너존-5");
		btnM_5.addActionListener(new BtnAct(btnM_5));
		btnM_5.setBounds(200, 160, 100, 90);
		mannerzone_panel.add(btnM_5);
		solo.add(btnM_5);

		// 일반석 패널
		JPanel normalzone_panel = new JPanel();
		normalzone_panel.setBounds(300, 250, 300, 250);
		panel_center.add(normalzone_panel);
		normalzone_panel.setLayout(null);
		// 일반실 라벨,버튼
		JLabel label_normal = new JLabel("일반실");
		label_normal.setFont(new Font("굴림", Font.PLAIN, 20));
		label_normal.setBounds(120, 115, 70, 20);
		normalzone_panel.add(label_normal);

		JButton btnN_1 = new JButton("개인석-1");
		btnN_1.addActionListener(new BtnAct(btnN_1));
		btnN_1.setBounds(0, 0, 100, 90);
		normalzone_panel.add(btnN_1);
		solo.add(btnN_1);

		JButton btnN_2 = new JButton("개인석-2");
		btnN_2.addActionListener(new BtnAct(btnN_2));
		btnN_2.setBounds(100, 0, 100, 90);
		normalzone_panel.add(btnN_2);
		solo.add(btnN_2);

		JButton btnN_3 = new JButton("개인석-3");
		btnN_3.addActionListener(new BtnAct(btnN_3));
		btnN_3.setBounds(200, 0, 100, 90);
		normalzone_panel.add(btnN_3);
		solo.add(btnN_3);

		JButton btnN_4 = new JButton("개인석-4");
		btnN_4.addActionListener(new BtnAct(btnN_4));
		btnN_4.setBounds(0, 160, 100, 90);
		normalzone_panel.add(btnN_4);
		solo.add(btnN_4);

		JButton btnN_5 = new JButton("개인석-5");
		btnN_5.addActionListener(new BtnAct(btnN_5));
		btnN_5.setBounds(100, 160, 100, 90);
		normalzone_panel.add(btnN_5);
		solo.add(btnN_5);

		JButton btnN_6 = new JButton("개인석-6");
		btnN_6.addActionListener(new BtnAct(btnN_6));
		btnN_6.setBounds(200, 160, 100, 90);
		normalzone_panel.add(btnN_6);
		solo.add(btnN_6);

		// 휴게실
		JPanel restzone_panel = new JPanel();
		restzone_panel.setBackground(Color.ORANGE);
		restzone_panel.setBounds(300, 620, 260, 200);
		panel_center.add(restzone_panel);
		restzone_panel.setLayout(null);
		// 휴게실 안에 들어갈것은 여기서
		JLabel label_rest = new JLabel("휴게실");
		label_rest.setFont(new Font("굴림", Font.PLAIN, 20));
		label_rest.setBounds(100, 100, 70, 20);
		restzone_panel.add(label_rest);

		JPanel locker = new JPanel();
		locker.setBackground(Color.LIGHT_GRAY);
		locker.setBounds(560, 620, 30, 200);
		panel_center.add(locker);
		locker.setLayout(null);

		JLabel lblNewLabel = new JLabel("<html>사<br>물<br>함<html>");
		lblNewLabel.setBounds(0, 60, 32, 79);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 20));
		locker.add(lblNewLabel);

		SetNowTime now_time = new SetNowTime(north_west);
		now_time.start();
		setVisible(true);
		all.addAll(solo);
		all.addAll(group);
		for (JButton buttonColor : all) {
			buttonColor.setBackground(Color.green);
			buttonColor.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					for (JButton jbtt : all) {
						if (e.getSource().equals(jbtt)) {
							jbtt.setBackground(Color.cyan);
						} else {
							jbtt.setBackground(Color.green);
						}
					}
					
				}
			});
		}
	}

	public void group_state(boolean state) {// 그룹버튼 활성/비활성
		for (JButton button : group) {
			button.setEnabled(state);
		}
	}

	public void solo_state(boolean state) {// 그룹버튼 활성/비활성
		for (JButton button : solo) {
			button.setEnabled(state);
		}
	}

	public void openPage() {
		RoomProduct roomProduct = BaseFrame.getInstance().getUsingRoom();
		if (roomProduct != null) {
			for (JButton jButton : all) {
				if (roomProduct.name.equals(jButton.getText())) {
					jButton.setBackground(Color.blue);
					lblNewLabel_7.setVisible(true);
					lblNewLabel_8.setVisible(true);
					
				}
			}
		}
		
	}

	public void setBtnColor() {

		for (RoomProduct room : BaseFrame.getInstance().roomInfoList) {

			for (Calendar cal : room.calendarList) {

				if (BaseFrame.getInstance().isSameTime(cal, Calendar.getInstance())) {

					for (JButton jButton2 : all) {
						if (jButton2.getText().equals(room.name)) {
							jButton2.setBackground(Color.red);
							jButton2.setEnabled(false);
						}
					}
				}
			}
		}

		RoomProduct roomProduct = BaseFrame.getInstance().getUsingRoom();
		if (roomProduct != null) {
			for (JButton jButton : all) {
				if (roomProduct.name.equals(jButton.getText())) {
					jButton.setBackground(Color.cyan);
					jButton.setBackground(Color.green);
				}
			}

		}
	}
	
	@Override
	public void receive(PacketBase packet) {
		ScMoveSeatAck ack = (ScMoveSeatAck) packet;
		if (ack.eResult == EResult.SUCCESS) {
			String roomName = DataManager.getInstance().roomMap.get(moveSeatId).name;
			BaseFrame.getInstance().view("LoginMain");
			setBtnColor();
		} else {

		}
	}

	class BtnAct implements ActionListener {
		JButton bt;

		public BtnAct(JButton bt) {
			this.bt = bt;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			for (RoomProduct roomData : DataManager.getInstance().roomMap.values()) {

				if (roomData.name.equals(bt.getText())) {
					if (!BaseFrame.getInstance().getSeatingArrUI().seatChange)// 좌석이동중이 아닐때
					{
						// 페이지 여는 순간 현재 상품 복사
						BaseFrame.getInstance().roomProduct = roomData;

						if (BaseFrame.getInstance().loginType == ELoginType.KIOSK) {
							System.out.println("KIOSK");
							BaseFrame.getInstance().payment.openPage();
						} else if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
							System.out.println("MOBILE");
							BaseFrame.getInstance().view("ReservationMain");
							BaseFrame.getInstance().getReservationMain().init(roomData.name);
						}

						BaseFrame.getInstance().payment.resPossibleChk();
					} else// 좌석이동중일때
					{
						for (RoomProduct room : DataManager.getInstance().roomMap.values()) {
							if (room.name.equals(bt.getText())) {
								moveSeatId = room.id;
							}
						}
						SeatChangeOkPop frame = new SeatChangeOkPop();
					}
				}
			}
		}
	}
}
