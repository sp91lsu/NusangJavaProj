package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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

public class LoginMain extends JPanel implements Receivable, MouseListener {

	JTextField idTextF;
	JPasswordField passwordField;
	CheckRoomInfo chkroominfo;
	JCheckBox changeBox;
	JLabel idLabel;

	String info = "����� ID �Ǵ� �޴��� ��ȣ�� �Է��ϼ���";
	String idInfo = "����� ID�� �Է��ϼ���";
	String phinfo = "����� �޴��� ��ȣ�� �Է��ϼ���";

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
		idTextF.setText(info);
		idTextF.setToolTipText("");
		idTextF.setFont(new Font("���� ���", Font.ITALIC, 14));
		idTextF.setBounds(300, 156, 328, 55);
		add(idTextF);
		idTextF.setColumns(10);
		idTextF.addMouseListener(this);

		changeBox = new JCheckBox("�޴��� ��ȣ�� �α����ϱ�");
		changeBox.setBounds(300, 220, 300, 70);
		changeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (changeBox.isSelected() == true) {
					idLabel.setText("Phone");
					idTextF.setText(phinfo);
				} else {
					idLabel.setText("ID");
					idTextF.setText(idInfo);
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
				BaseFrame.getInstance().signUpFrame.textFieldSet();

			}
		});
	}

	@Override
	public void receive(PacketBase packet) {

		ScLoginAck ack = (ScLoginAck) packet;
		BaseFrame.getInstance().userData = ack.userdata;
		if (ack.eResult == EResult.SUCCESS) {
			System.out.println("���� ������ ����");
			// BaseFrame.getInstance().getSeatingArrUI().btn_state(false);
			if (BaseFrame.getInstance().loginType == ELoginType.KIOSK) {

				BaseFrame.getInstance().openMainLayout(ack.roomList, null, null, ack.lockerList);

				chkroominfo = new CheckRoomInfo();
				chkroominfo.start();
			} else if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
				BaseFrame.getInstance().updateData(ack.roomList, null, null, ack.lockerList);
				
				ReservationInfoMain rif = new ReservationInfoMain();
				idTextF.setText(info);
				passwordField.setText("");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		idTextF = (JTextField) e.getSource();
		String idfield = idTextF.getText();

		if (idfield.equals(info) || idfield.equals(idInfo) || idfield.equals(phinfo)) {
			idTextF.setText("");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}