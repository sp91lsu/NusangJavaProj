package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsChatSyn;
import client_p.packet_p.syn_p.CsLoginSyn;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.ELoginType;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScLoginAck;
import server_p.packet_p.syn_p.ScChatSyn;

public class LoginMain extends JPanel implements Receivable {

	private JTextField idTextF;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 1000);
		frame.add(new LoginMain());
		frame.setVisible(true);
	}

	public LoginMain() {
		// 서버에서 받은 로그인 응답 클래스와 그에 맞는 함수클래스 연결
//		PacketMap.getInstance().map.put(ScLoginAck.class, new ReceiveLoginAck());
		setLayout(null);

		JLabel lblNewLabel = new JLabel("로그인창");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
		lblNewLabel.setBounds(251, 21, 469, 110);
		add(lblNewLabel);

		JLabel idLabel = new JLabel("ID");
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		idLabel.setBounds(251, 156, 51, 55);
		add(idLabel);

		idTextF = new JTextField();
		idTextF.setText(" or 핸드폰번호 입력( '-' 없이 입력)");
		idTextF.setToolTipText("");
		idTextF.setFont(new Font("맑은 고딕", Font.ITALIC, 14));
		idTextF.setBounds(323, 156, 328, 55);
		add(idTextF);
		idTextF.setColumns(10);

		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		lblPw.setBounds(251, 236, 51, 54);
		add(lblPw);

		passwordField = new JPasswordField();
		passwordField.setBounds(323, 236, 328, 54);
		add(passwordField);

		JButton logInBtn = new JButton("로그인");
		logInBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		logInBtn.setBounds(323, 321, 120, 45);
		add(logInBtn);
		logInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CsLoginSyn packet = new CsLoginSyn(idTextF.getText(), passwordField.getText(), true);
				ClientNet.getInstance().sendPacket(packet);
			}
		});

		JButton signUpBt = new JButton("회원가입");
		signUpBt.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		signUpBt.setBounds(508, 321, 120, 45);
		add(signUpBt);
		signUpBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().signUpFrame.setVisible(true);
			}
		});
	}

	@Override
	public void receive(PacketBase packet) {

		ScLoginAck ack = (ScLoginAck) packet;

		if (ack.eResult == EResult.SUCCESS) {
			BaseFrame.getInstance().userData = ack.userdata;
			System.out.println("내가 예약한 내용");
			for (RoomProduct room : BaseFrame.getInstance().userData.myReservationList) {
				System.out.println(room);
			}
			BaseFrame.getInstance().roomInfoList = ack.roomList;
			// BaseFrame.getInstance().updateInfo(ack.roomList);
			if (BaseFrame.getInstance().loginType == ELoginType.KIOSK) {

				idTextF.setText(" or 핸드폰번호 입력( '-' 없이 입력)");
				passwordField.setText("");
				BaseFrame.getInstance().view("MainLayout");
			} else if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {

				BaseFrame.getInstance().view("Seating_Arrangement");
			}
		}
	}
}