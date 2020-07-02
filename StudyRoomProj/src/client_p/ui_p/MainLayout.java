package client_p.ui_p;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address;

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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class MainLayout extends JPanel implements Receivable, ActionListener {

	private JButton button_1;
	private JButton button_2;
	private JButton button_6;
	private JButton button_9;
	private JButton button_5;
	private JButton button_3;
	private JButton button_7;
	long todayRemainTime;

	boolean is_Info, is_useData, is_addTime, is_Exit;
	boolean is_LogOut;
	private JLabel button_1_label;
	private JLabel button_2_label;
	private JLabel button_3_label;
	private JLabel button_4_label;
	private JLabel button_5_label;
	private JLabel button_6_label;
	private JLabel button_7_label;
	private JLabel button_8_label;
	private JLabel button_9_label;

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
		panel.setBounds(83, 127, 691, 486);
		add(panel);

		JLabel lblNewLabel = new JLabel("로그인 후 화면");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		lblNewLabel.setBounds(261, 10, 396, 107);
		add(lblNewLabel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 228, 228, 228, 0 };
		gbl_panel.rowHeights = new int[] { 113, 45, 113, 45, 113, 45 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panel.setLayout(gbl_panel);

		button_1 = new JButton("개인석 이용");
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.fill = GridBagConstraints.BOTH;
		gbc_button_1.insets = new Insets(0, 0, 5, 5);
		gbc_button_1.gridx = 0;
		gbc_button_1.gridy = 0;
		panel.add(button_1, gbc_button_1);
		button_1.addActionListener(this);
		
		button_1_label = new JLabel("개인석 이용");
		GridBagConstraints gbc_button_1_label = new GridBagConstraints();
		gbc_button_1_label.insets = new Insets(0, 0, 5, 5);
		gbc_button_1_label.gridx = 0;
		gbc_button_1_label.gridy = 1;
		panel.add(button_1_label, gbc_button_1_label);

		button_2 = new JButton("단체룸 이용");
		button_2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.fill = GridBagConstraints.BOTH;
		gbc_button_2.insets = new Insets(0, 0, 5, 5);
		gbc_button_2.gridx = 1;
		gbc_button_2.gridy = 0;
		panel.add(button_2, gbc_button_2);
		button_2.addActionListener(this);
		
		button_2_label = new JLabel("단체룸 이용");
		GridBagConstraints gbc_button_2_label = new GridBagConstraints();
		gbc_button_2_label.insets = new Insets(0, 0, 5, 5);
		gbc_button_2_label.gridx = 1;
		gbc_button_2_label.gridy = 1;
		panel.add(button_2_label, gbc_button_2_label);

		button_3 = new JButton("사물함 대여");
		button_3.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.fill = GridBagConstraints.BOTH;
		gbc_button_3.insets = new Insets(0, 0, 5, 0);
		gbc_button_3.gridx = 2;
		gbc_button_3.gridy = 0;
		panel.add(button_3, gbc_button_3);
		button_3.addActionListener(this);

		button_3_label = new JLabel("사물함 대여");
		GridBagConstraints gbc_button_3_label = new GridBagConstraints();
		gbc_button_3_label.insets = new Insets(0, 0, 5, 0);
		gbc_button_3_label.gridx = 2;
		gbc_button_3_label.gridy = 1;
		panel.add(button_3_label, gbc_button_3_label);
		
		JButton button_4 = new JButton();
		ImageIcon button_4Img = new ImageIcon("img/문의.png");
		button_4.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_4 = new GridBagConstraints();
		gbc_button_4.fill = GridBagConstraints.BOTH;
		gbc_button_4.insets = new Insets(0, 0, 5, 5);
		gbc_button_4.gridx = 0;
		gbc_button_4.gridy = 2;
		panel.add(button_4, gbc_button_4);
		button_4.addActionListener(this);
		button_4.setIcon(resizeIcon(button_4Img));
		
		button_4_label = new JLabel("1:1 문의");
		GridBagConstraints gbc_button_4_label = new GridBagConstraints();
		gbc_button_4_label.insets = new Insets(0, 0, 5, 5);
		gbc_button_4_label.gridx = 0;
		gbc_button_4_label.gridy = 3;
		panel.add(button_4_label, gbc_button_4_label);


		button_5 = new JButton("개인석 이동");
		button_5.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_5 = new GridBagConstraints();
		gbc_button_5.fill = GridBagConstraints.BOTH;
		gbc_button_5.insets = new Insets(0, 0, 5, 5);
		gbc_button_5.gridx = 1;
		gbc_button_5.gridy = 2;
		panel.add(button_5, gbc_button_5);
		button_5.addActionListener(this);
		
		button_5_label = new JLabel("개인석 이동");
		GridBagConstraints gbc_button_5_label = new GridBagConstraints();
		gbc_button_5_label.insets = new Insets(0, 0, 5, 5);
		gbc_button_5_label.gridx = 1;
		gbc_button_5_label.gridy = 3;
		panel.add(button_5_label, gbc_button_5_label);


		button_6 = new JButton("좌석 연장");
		button_6.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_6 = new GridBagConstraints();
		gbc_button_6.fill = GridBagConstraints.BOTH;
		gbc_button_6.insets = new Insets(0, 0, 5, 0);
		gbc_button_6.gridx = 2;
		gbc_button_6.gridy = 2;
		panel.add(button_6, gbc_button_6);
		button_6.addActionListener(this);

		button_6_label = new JLabel("좌석 연장");
		GridBagConstraints gbc_button_6_label = new GridBagConstraints();
		gbc_button_6_label.insets = new Insets(0, 0, 5, 0);
		gbc_button_6_label.gridx = 2;
		gbc_button_6_label.gridy = 3;
		panel.add(button_6_label, gbc_button_6_label);

		button_7 = new JButton("당일 구매 정보");
		button_7.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_7 = new GridBagConstraints();
		gbc_button_7.fill = GridBagConstraints.BOTH;
		gbc_button_7.insets = new Insets(0, 0, 5, 5);
		gbc_button_7.gridx = 0;
		gbc_button_7.gridy = 4;
		panel.add(button_7, gbc_button_7);
		button_7.addActionListener(this);
		
		
		button_7_label = new JLabel("당일 구매 정보");
		GridBagConstraints gbc_button_7_label = new GridBagConstraints();
		gbc_button_7_label.gridheight = 2;
		gbc_button_7_label.insets = new Insets(0, 0, 0, 5);
		gbc_button_7_label.gridx = 0;
		gbc_button_7_label.gridy = 5;
		panel.add(button_7_label, gbc_button_7_label);

		JButton button_8 = new JButton("이용 내역 조회");
		button_8.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_8 = new GridBagConstraints();
		gbc_button_8.fill = GridBagConstraints.BOTH;
		gbc_button_8.insets = new Insets(0, 0, 5, 5);
		gbc_button_8.gridx = 1;
		gbc_button_8.gridy = 4;
		panel.add(button_8, gbc_button_8);
		button_8.addActionListener(this);

		button_8_label = new JLabel("이용 내역 조회");
		GridBagConstraints gbc_button_8_label = new GridBagConstraints();
		gbc_button_8_label.gridheight = 2;
		gbc_button_8_label.insets = new Insets(0, 0, 0, 5);
		gbc_button_8_label.gridx = 1;
		gbc_button_8_label.gridy = 5;
		panel.add(button_8_label, gbc_button_8_label);


		button_9 = new JButton("퇴실");
		button_9.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		GridBagConstraints gbc_button_9 = new GridBagConstraints();
		gbc_button_9.insets = new Insets(0, 0, 5, 0);
		gbc_button_9.fill = GridBagConstraints.BOTH;
		gbc_button_9.gridx = 2;
		gbc_button_9.gridy = 4;
		panel.add(button_9, gbc_button_9);

		button_9_label = new JLabel("퇴실");
		GridBagConstraints gbc_button_9_label = new GridBagConstraints();
		gbc_button_9_label.gridheight = 2;
		gbc_button_9_label.gridx = 2;
		gbc_button_9_label.gridy = 5;
		panel.add(button_9_label, gbc_button_9_label);
		button_9.addActionListener(this);

		JButton button_10 = new JButton("로그아웃");
		button_10.setFont(new Font("맑은 고딕", Font.BOLD, 20));
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

				BaseFrame.getInstance().getClientChatFrame().setChatPacket(new CsChatSyn(ack.cip, ack.mip));
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
				AddTimeFrame frame = new AddTimeFrame();
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

	static Icon resizeIcon(ImageIcon icon) {
		Image img = icon.getImage();
		Image resizedImage = img.getScaledInstance(180, 100, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(resizedImage);
	}
}