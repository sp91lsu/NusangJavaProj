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

	public static void main(String[] args) {
		JFrame frame = new JFrame();
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
				BaseFrame.getInstance().view("Seating_Arrangement");
				BaseFrame.getInstance().getSeatingArrUI().group_state(false);
                BaseFrame.getInstance().getSeatingArrUI().combo_state(false);

			}
		});

		button_2 = new JButton("단체룸 이용");
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("Seating_Arrangement");
				BaseFrame.getInstance().getSeatingArrUI().solo_state(false);
                BaseFrame.getInstance().getSeatingArrUI().combo_state(false);
			}
		});

		JButton button_3 = new JButton("사물함 대여");
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_3);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("LockerMain");
				for (LockerData data : BaseFrame.getInstance().lockerlist) {
					for (LockerBtn btn : BaseFrame.getInstance().getLockerMain().list) {
						if (data.name.equals(btn.data.name)) {
							btn.btn.setEnabled(false);
						}
					}
				}
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
				BaseFrame.getInstance().getSeatingArrUI().seatChange = true;
				BaseFrame.getInstance().getSeatingArrUI().group_state(false);
				BaseFrame.getInstance().getSeatingArrUI().openPage();
				BaseFrame.getInstance().view("Seating_Arrangement");
				SeatChangePop frame = new SeatChangePop();
			}
		});

		button_6 = new JButton("좌석 연장");
		button_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_6);
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().payment.openPage();
			}
		});

		JButton button_7 = new JButton("잔여 시간");
		button_7.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(button_7);
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (BaseFrame.getInstance().getUsingRoom() != null) {
					TimeFrame time = new TimeFrame();
				} else {
					System.out.println("현재 이용중인 방이 없음.");
				}
			}
		});

		JButton button_8 = new JButton("내 이용내역");
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

	public void openPage() {
		long remain = BaseFrame.getInstance().getTodayRemainTime();
		RoomProduct reserRoom = BaseFrame.getInstance().checkMyReserRoom(Calendar.DATE);

		if (BaseFrame.getInstance().getUsingRoom() != null) {

			button_9.setText("퇴실");
		} else if (reserRoom != null) {
			BaseFrame.getInstance().roomProduct = reserRoom;
			button_5.setEnabled(false);
			button_9.setText("예약 취소");
		} else {
			button_9.setText("퇴실");
			button_5.setEnabled(false);
			button_9.setEnabled(false);
		}
		System.out.println("오늘 남은 시간" + remain);

		if (remain > 0)// 예약했으면
		{
			button_1.setEnabled(false);// 개인룸 이용 버튼 비활성화
			button_2.setEnabled(false);// 단체석 이용버튼 비활성화
			button_6.setEnabled(true);// 좌석 연장 버튼 활성화
			button_9.setEnabled(true);
		} else {
			button_1.setEnabled(true);// 개인룸 이용 버튼 활성화
			button_2.setEnabled(true);// 단체룸 이용 버튼 활성화
			button_6.setEnabled(false);// 좌석 연장 버튼 비활성화
			button_9.setEnabled(false);
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

			} else {
				System.out.println("거절당함");
			}
		} else if (packet.getClass() == ScExitAck.class) {
			ScExitAck resPacket = (ScExitAck) packet;
			if (resPacket.eResult == EResult.SUCCESS) {
				BaseFrame.getInstance().view("LoginMain");
			} else if (resPacket.eResult == EResult.FAIL) {
				System.out.println("퇴실 실패");
			}
		}
	}
}