package client_p.ui_p;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import data_p.product_p.LockerData;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScExitAck;

public class MainLayout extends JPanel implements Receivable {

	private JButton button_1;
	private JButton button_2;
	private JButton button_6;
	private JButton button_9;
	private JButton button_5;
	private JButton button_3;
	private JButton button_7;
	long todayRemainTime;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ClientNet.getInstance().start();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 800);
		frame.getContentPane().add(new MainLayout());
		frame.setVisible(true);
	}

	public MainLayout() {

		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(82, 168, 725, 430);
		add(panel);
		panel.setLayout(new GridLayout(3, 3, 5, 5));

		button_1 = new JButton("개인석 이용");
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().openSeatingArrUI(EEnter.PRIVROOM);
				BaseFrame.getInstance().getSeatingArrUI().startTime_state();
			}
		});

		button_2 = new JButton("단체룸 이용");
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().openSeatingArrUI(EEnter.GROUPROOM);
				// BaseFrame.getInstance().getSeatingArrUI().solo_state(false);
				// BaseFrame.getInstance().getSeatingArrUI().combo_state(false);
				BaseFrame.getInstance().getSeatingArrUI().startTime_state();
			}
		});

		button_3 = new JButton("사물함 대여");
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().openLockerMain();

			}
		});

		JButton button_4 = new JButton("1:1 고객문의");
		button_4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_4);
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("ClientChatFrame");
				CsChatConnectSyn packet = new CsChatConnectSyn(BaseFrame.getInstance().userData);
				ClientNet.getInstance().sendPacket(packet);
				System.out.println("패킷이 다시 올때까지 기다려야 함");// 다이얼로그 창 설정하기
			}
		});

		button_5 = new JButton("개인석 이동");
		button_5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_5);
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// BaseFrame.getInstance().getSeatingArrUI().group_state(false);
				BaseFrame.getInstance().openSeatingArrUI(EEnter.SEATCHANGE);
				BaseFrame.getInstance().view("Seating_Arrangement");
				SeatChangePop frame = new SeatChangePop();
			}
		});

		button_6 = new JButton("좌석 연장");
		button_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_6);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTimeFrame frame = new AddTimeFrame();
			}
		});

		button_7 = new JButton("당일 구매 정보");
		button_7.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_7);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (BaseFrame.getInstance().getTodayRemainTime() > 0) {
					TimeFrame time = new TimeFrame();
				} else {
					System.out.println("현재 이용중인 방이 없음.");
				}
			}
		});

		JButton button_8 = new JButton("이용 내역 조회");
		button_8.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_8);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoFrame info = new InfoFrame();
			}
		});

		button_9 = new JButton("퇴실");
		button_9.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_9);

		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitFrame exitframe = new ExitFrame(button_9.getText());
			}
		});

		JButton button_10 = new JButton("로그아웃");
		button_10.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_10.setBounds(325, 622, 238, 94);
		button_10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				LogoutPopFrame logout = new LogoutPopFrame();
				logout.setVisible(true);
			}
		});
		add(button_10);

		JLabel lblNewLabel = new JLabel("로그인 후 화면");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		lblNewLabel.setBounds(261, 10, 396, 107);
		add(lblNewLabel);

	}

	public void updatePage() {
		todayRemainTime = BaseFrame.getInstance().getTodayRemainTime();
		RoomProduct reserRoom = BaseFrame.getInstance().checkMyReserRoom(Calendar.DATE); // 오늘 이용할 방 리스트
		RoomProduct usingRoom = BaseFrame.getInstance().getUsingRoom(); // 지금 이용중인 방
		if (usingRoom != null) {

			button_9.setText("퇴실");
			button_3.setEnabled(true);
			button_7.setEnabled(true);
			button_5.setEnabled(usingRoom.personNum == 1);
			button_6.setEnabled(true);// 좌석 연장 버튼 활성화
			
		} else if (reserRoom != null) {
			button_5.setEnabled(false);
			button_3.setEnabled(false);
			button_7.setEnabled(true);
			button_9.setText("예약 취소");
			button_6.setEnabled(false);// 좌석 연장 버튼 비활성화
		} else {
			button_9.setText("퇴실");
			button_3.setEnabled(false);
			button_5.setEnabled(false);
			button_7.setEnabled(false);
			button_9.setEnabled(false);
			button_6.setEnabled(false);// 좌석 연장 버튼 비활성화
		}
		System.out.println("오늘 남은 시간" + todayRemainTime);

		System.out.println("남은시간");
		if (todayRemainTime > 0)// 예약했으면
		{
			button_1.setEnabled(false);// 개인룸 이용 버튼 비활성화
			button_2.setEnabled(false);// 단체석 이용버튼 비활성화
			button_9.setEnabled(true);
		} else {
			button_1.setEnabled(true);// 개인룸 이용 버튼 활성화
			button_2.setEnabled(true);// 단체룸 이용 버튼 활성화
			button_9.setEnabled(false);
		}

		if (BaseFrame.getInstance().userData.locker != null) {
			button_3.setEnabled(false);
		}
	}

	public void is_reservation() {
		button_1.setEnabled(false);// 개인룸 이용 버튼 비활성화
		button_2.setEnabled(false);// 단체석 이용버튼 비활성화
	}

	@Override
	public void receive(PacketBase packet) {
		if (packet.getClass() == ScChatConnectAck.class) {
			ScChatConnectAck ack = (ScChatConnectAck) packet;
			if (ack.eResult == EResult.SUCCESS) {

				BaseFrame.getInstance().getClientChatFrame().setChatPacket(new CsChatSyn(ack.cip, ack.mip));
				BaseFrame.getInstance().view("ClientChatFrame");
				
				
				}else if(ack.eResult == EResult.NEGATIVE_CHAT) {
					System.out.println("ㄴㄴㄴㄴ");
					BaseFrame.getInstance().getClientChat().chatNegative();

			} else {
				System.out.println("거절당함");
			}
		} else if (packet.getClass() == ScExitAck.class) {
			ScExitAck resPacket = (ScExitAck) packet;
			if (resPacket.eResult == EResult.SUCCESS) {
				BaseFrame.getInstance().openMainLayout(resPacket.reserListAll, resPacket.myReserList,
						resPacket.myExitList, resPacket.lockerList);
				BaseFrame.getInstance().userData.locker = null;
				updatePage();
			} else if (resPacket.eResult == EResult.FAIL) {
				System.out.println("퇴실 실패");
			}
		}
	}
}