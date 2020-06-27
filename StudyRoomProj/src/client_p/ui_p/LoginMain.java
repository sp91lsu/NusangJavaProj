package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsLoginSyn;
import packetBase_p.ELoginType;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScLoginAck;

public class LoginMain extends JPanel implements Receivable {

	private JTextField idTextF;
	private JPasswordField passwordField;
	CheckRoomInfo chkroominfo;
	JCheckBox changeBox;
	JLabel idLabel;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 1000);
		frame.getContentPane().add(new LoginMain());
		frame.setVisible(true);
	}

	public LoginMain() {
		// �������� ���� �α��� ���� Ŭ������ �׿� �´� �Լ�Ŭ���� ����
//		PacketMap.getInstance().map.put(ScLoginAck.class, new ReceiveLoginAck());
		setLayout(null);

		JLabel lblNewLabel = new JLabel("�α���â");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 40));
		lblNewLabel.setBounds(230, 21, 469, 110);
		add(lblNewLabel);

		idLabel = new JLabel("ID");
		idLabel.setFont(new Font("���� ���", Font.BOLD, 26));
		idLabel.setBounds(150, 156, 100, 55);
		add(idLabel);

		idTextF = new JTextField();
		idTextF.setText("����� ID �Ǵ� �޴��� ��ȣ�� �Է��ϼ���");
		idTextF.setToolTipText("");
		idTextF.setFont(new Font("���� ���", Font.ITALIC, 14));
		idTextF.setBounds(300, 156, 328, 55);
		add(idTextF);
		idTextF.setColumns(10);

		changeBox = new JCheckBox("�޴��� ��ȣ�� �α����ϱ�");
		changeBox.setBounds(300, 220, 300, 70);
		changeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (changeBox.isSelected() == true) {
					idLabel.setText("Phone");
					idTextF.setText("����� �޴��� ��ȣ�� �Է��ϼ���");
				} else {
					idLabel.setText("ID");
					idTextF.setText("����� ID�� �Է��ϼ���");
				}

			}
		});
		add(changeBox);

		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("���� ���", Font.BOLD, 26));
		lblPw.setBounds(150, 300, 51, 54);
		add(lblPw);

		passwordField = new JPasswordField();
		passwordField.setBounds(300, 300, 328, 54);
		add(passwordField);

		JButton logInBtn = new JButton("�α���");
		logInBtn.setFont(new Font("���� ���", Font.BOLD, 20));
		logInBtn.setBounds(300, 400, 120, 45);
		add(logInBtn);
		logInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CsLoginSyn packet = new CsLoginSyn(idTextF.getText(), passwordField.getText(), !changeBox.isSelected());
				ClientNet.getInstance().sendPacket(packet);
			}
		});

		JButton signUpBt = new JButton("ȸ������");
		signUpBt.setFont(new Font("���� ���", Font.BOLD, 20));
		signUpBt.setBounds(480, 400, 120, 45);
		add(signUpBt);
		signUpBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().signUpFrame.setVisible(true);
				BaseFrame.getInstance().signUpFrame.label_1.setText("�ѱ۷� �Է��ϼ���");
				BaseFrame.getInstance().signUpFrame.label_2.setText("����,���ڷ� ���յ� 8�ڸ��� �Է��ϼ���");
				BaseFrame.getInstance().signUpFrame.label_3.setText("�Է��� ��й�ȣ�� ���� �Է��ϼ���");
				BaseFrame.getInstance().signUpFrame.label_4.setText("'-'�� �����ϰ� �Է��ϼ���");
				BaseFrame.getInstance().signUpFrame.label_5.setText("����,���ڷ� ���յ� 8�ڸ��� �Է��ϼ���");
				
			}
		});
	}

	@Override
	public void receive(PacketBase packet) {

		ScLoginAck ack = (ScLoginAck) packet;

		if (ack.eResult == EResult.SUCCESS) {
			BaseFrame.getInstance().userData = ack.userdata;
			System.out.println("���� ������ ����");
			 BaseFrame.getInstance().getSeatingArrUI().btn_state(false);
			if (BaseFrame.getInstance().loginType == ELoginType.KIOSK) {

				idTextF.setText(" or �ڵ�����ȣ �Է�( '-' ���� �Է�)");
				passwordField.setText("");

				BaseFrame.getInstance().openMainLayout(ack.roomList, null, ack.lockerList);

				chkroominfo = new CheckRoomInfo();
				chkroominfo.start();
			} else if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
				BaseFrame.getInstance().getSeatingArrUI().north_east.setEnabled(false);
				BaseFrame.getInstance().view("Seating_Arrangement");
			}
		}
	}
}