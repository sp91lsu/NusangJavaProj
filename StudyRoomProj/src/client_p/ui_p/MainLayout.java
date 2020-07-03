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
import client_p.EEnter;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsChatConnectSyn;
import client_p.packet_p.syn_p.CsChatSyn;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.ack_p.ScExitAck;

public class MainLayout extends JPanel implements Receivable, ActionListener {

	private JButton button_1;
	private JButton button_2;
	private JButton button_6;
	private JButton button_9;
	private JButton button_5;
	private JButton button_3;
	private JButton button_7;
	long todayRemainTime;

	boolean is_Info, is_useData, is_addTime, is_Exit, is_LogOut;

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
		setBackground(MyColor.black);
		JPanel panel = new JPanel();
		panel.setBounds(83, 127, 691, 486);
		panel.setBackground(MyColor.black);
		add(panel);

		JLabel lblNewLabel = new JLabel("자바 스터디룸");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		lblNewLabel.setForeground(MyColor.white);
		lblNewLabel.setBounds(261, 10, 396, 107);
		add(lblNewLabel);
		panel.setLayout(new GridLayout(0, 3, 10, 10));

		button_1 = new JButton("개인석 이용");
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_1.setBackground(MyColor.w_white);
		panel.add(button_1);
		button_1.addActionListener(this);

		button_2 = new JButton("단체룸 이용");
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_2.setBackground(MyColor.w_white);
		panel.add(button_2);
		button_2.addActionListener(this);

		button_3 = new JButton("사물함 대여");
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_3.setBackground(MyColor.w_white);
		panel.add(button_3);
		button_3.addActionListener(this);

		JButton button_4 = new JButton("1:1 고객문의");
		button_4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_4.setBackground(MyColor.w_white);
		panel.add(button_4);
		button_4.addActionListener(this);

		button_5 = new JButton("개인석 이동");
		button_5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_5.setBackground(MyColor.w_white);
		panel.add(button_5);
		button_5.addActionListener(this);

		button_6 = new JButton("좌석 연장");
		button_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_6.setBackground(MyColor.w_white);
		panel.add(button_6);
		button_6.addActionListener(this);

		button_7 = new JButton("당일 구매 정보");
		button_7.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_7.setBackground(MyColor.w_white);
		panel.add(button_7);
		button_7.addActionListener(this);

		JButton button_8 = new JButton("이용 내역 조회");
		button_8.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_8.setBackground(MyColor.w_white);
		panel.add(button_8);
		button_8.addActionListener(this);

		button_9 = new JButton("퇴실");
		button_9.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_9.setBackground(MyColor.w_white);
		panel.add(button_9);
		button_9.addActionListener(this);

		JButton button_10 = new JButton("로그아웃");
		button_10.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_10.setBackground(MyColor.w_white);
		button_10.setBounds(325, 648, 238, 68);
		button_10.addActionListener(this);
		add(button_10);
	}

	public void updatePage() {
		todayRemainTime = BaseFrame.getInstance().getTodayRemainTime();
		RoomProduct reserRoom = BaseFrame.getInstance().checkMyReserRoom(null, Calendar.DATE); // 오늘 이용할 방 리스트
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

				BaseFrame.getInstance().getClientChatFrame().setChatPacket(new CsChatSyn());
				BaseFrame.getInstance().openClientChatFrame();

			} else if (ack.eResult == EResult.NEGATIVE_CHAT) {
				BaseFrame.getInstance().openClientChatFrame().chatNegative();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn.getText().equals("개인석 이용")) {
			System.out.println("개인석이용");
			BaseFrame.getInstance().openSeatingArrUI(EEnter.PRIVROOM);
			BaseFrame.getInstance().getSeatingArrUI().startTime_state();
		} else if (btn.getText().equals("단체룸 이용")) {
			BaseFrame.getInstance().openSeatingArrUI(EEnter.GROUPROOM);
			BaseFrame.getInstance().getSeatingArrUI().startTime_state();
		} else if (btn.getText().equals("사물함 대여")) {
			BaseFrame.getInstance().openLockerMain();
		} else if (btn.getText().equals("1:1 고객문의")) {
			BaseFrame.getInstance().openClientChatFrame();
			CsChatConnectSyn packet = new CsChatConnectSyn(BaseFrame.getInstance().userData);
			ClientNet.getInstance().sendPacket(packet);
		} else if (btn.getText().equals("개인석 이동")) {
			BaseFrame.getInstance().openSeatingArrUI(EEnter.SEATCHANGE);
			BaseFrame.getInstance().view("Seating_Arrangement");
			SeatChangePop frame = new SeatChangePop();
		} else if (btn.getText().equals("좌석 연장")) {
			if (!is_addTime) {
				is_addTime = true;
				AddTimeDialog frame = new AddTimeDialog();
			}
		} else if (btn.getText().equals("당일 구매 정보")) {
			if (BaseFrame.getInstance().getTodayRemainTime() > 0) {
				if (!is_useData) {
					is_useData = true;
					TimeFrame time = new TimeFrame();
				}
			} else {
				System.out.println("현재 이용중인 방이 없음.");
			}
		} else if (btn.getText().equals("이용 내역 조회")) {
			if (!is_Info) {
				is_Info = true;
				InfoFrame info = new InfoFrame();
			}
		} else if (btn.getText().equals("퇴실")) {
			if (!is_Exit) {
				is_Exit = true;
				ExitFrame exitframe = new ExitFrame(button_9.getText());
			}
		} else if (btn.getText().equals("로그아웃")) {
			if (!is_LogOut) {
				is_LogOut = true;
				LogoutPopFrame logout = new LogoutPopFrame();
				logout.setVisible(true);
			}
		}
	}
}