package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
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

public class LoginMain extends JPanel implements Receivable, MouseListener, ActionListener {

	JTextField idTextF;
	JPasswordField passwordField;
	CheckRoomInfo chkroominfo;
	JCheckBox changeBox;
	JLabel idLabel,logLabel;
	JButton logInBtn, logChkButton;
	JDialog logDialog;
	String info = "����� ID �Ǵ� �޴��� ��ȣ�� �Է��ϼ���";
	String idInfo = "����� ID�� �Է��ϼ���";
	String phinfo = "����� �޴��� ��ȣ�� �Է��ϼ���";
	int cnt = 0;
	boolean isLogin=false;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 1000);
		frame.getContentPane().add(new LoginMain());
		frame.setVisible(true);
	}

	public LoginMain() {
		// �������� ���� �α��� ���� Ŭ������ �׿� �´� �Լ�Ŭ���� ����
		setLayout(null);
		setBackground(MyColor.black);

		JLabel lblNewLabel = new JLabel("�α���");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("���� ���", Font.BOLD, 40));
		lblNewLabel.setBounds(230, 21, 469, 110);
		add(lblNewLabel);

		idLabel = new JLabel("ID");
		idLabel.setForeground(Color.WHITE);
		idLabel.setFont(new Font("���� ���", Font.BOLD, 26));
		idLabel.setBounds(150, 156, 100, 55);
		add(idLabel);

		idTextF = new JTextField();//ID �Է��ʵ�
		idTextF.setBackground(MyColor.w_white);
		idTextF.setText(info);
		idTextF.setToolTipText("");
		idTextF.setFont(new Font("���� ���", Font.ITALIC, 14));
		idTextF.setBounds(300, 156, 328, 55);
		add(idTextF);
		idTextF.setColumns(10);
		idTextF.addMouseListener(this);

		changeBox = new JCheckBox("�޴��� ��ȣ�� �α����ϱ�");//�޴�����ȣ�� �α��� ��ȯ ��ư
		changeBox.setForeground(Color.WHITE);
		changeBox.setBounds(300, 220, 300, 70);
		changeBox.setBackground(null);
		changeBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (changeBox.isSelected() == true) {
					idLabel.setText("Phone");//�������� ����ȣ��
					idTextF.setText(phinfo);
				} else {
					idLabel.setText("ID");// �ٽ� ������ �� ���̵��
					idTextF.setText(idInfo);
				}
			}
		});
		add(changeBox);

		JLabel lblPw = new JLabel("PW");
		lblPw.setForeground(Color.WHITE);
		lblPw.setFont(new Font("���� ���", Font.BOLD, 26));
		lblPw.setBounds(150, 300, 51, 54);
		add(lblPw);

		passwordField = new JPasswordField();//��й�ȣ �Է� �ʵ�
		passwordField.setBackground(MyColor.w_white);
		passwordField.setBounds(300, 300, 328, 54);
		add(passwordField);

		logInBtn = new JButton("�α���");//�α��� ��ư
		logInBtn.setBackground(MyColor.w_white);
		logInBtn.setFont(new Font("���� ���", Font.BOLD, 20));
		logInBtn.setBounds(300, 400, 120, 45);
		add(logInBtn);
		logInBtn.addActionListener(this);

		JButton signUpBt = new JButton("ȸ������");//ȸ������ ��ư
		signUpBt.setBackground(MyColor.w_white);
		signUpBt.setFont(new Font("���� ���", Font.BOLD, 20));
		signUpBt.setBounds(480, 400, 120, 45);
		add(signUpBt);
		signUpBt.addActionListener(this);
		
		logDialog = new JDialog();
		logDialog.setBounds(810, 390, 300, 300);
		logDialog.getContentPane().setLayout(new GridLayout(2,1));
		logDialog.setUndecorated(true);
		logLabel = new JLabel();
		logLabel.setOpaque(true);
		logLabel.setBackground(MyColor.black);
		logLabel.setForeground(MyColor.white);
		logDialog.getContentPane().add(logLabel);
		logChkButton = new JButton("Ȯ��");
		logChkButton.setBackground(MyColor.w_white);
		logDialog.getContentPane().add(logChkButton);
		logDialog.setVisible(false);
	}

	@Override
	public void receive(PacketBase packet) {//�α��� ���� �޴� �κ�

		ScLoginAck ack = (ScLoginAck) packet;
		BaseFrame.getInstance().userData = ack.userdata;
		if (ack.eResult == EResult.SUCCESS) {//�α��� ������
			if (BaseFrame.getInstance().loginType == ELoginType.KIOSK) {

				BaseFrame.getInstance().openMainLayout(ack.roomList, null, null, ack.lockerList);

				chkroominfo = new CheckRoomInfo();
				chkroominfo.start();
				
			} else if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {
				BaseFrame.getInstance().updateData(ack.roomList, null, null, ack.lockerList);
				
				ReservationInfoMain rif = new ReservationInfoMain();
				
			}
			isLogin=false;
		}
		else if (ack.eResult == EResult.NOT_FOUND_DATA) {// �α��� ���н�
			// �α��ν� ID �Ǵ� ��й�ȣ ���Է� ���� �� ����ִ� â
			cnt++;
			if (cnt == 3) {
				logLabel.setText("<html>���Է� �α��� 3ȸ �������� 30�� �ڿ� " 
						+ "<br>�ٽ� ���� ���ֽñ� �ٶ��ϴ�.<html>");
				logChk log = new logChk();
				log.start();
				cnt = 0;
			} else if (cnt == 1) {
				logLabel.setText("����� ID�� ��й�ȣ�� �Է��ϼ���");
			}
			logTextChk();
			isLogin = false;
		}
		else {
			System.out.println("�α����� �˼����� ����");
		}
	}
	
	class logChk extends Thread {
		@Override
		public void run() {// ������ 30�� �༭ �α��� ��ư ��Ȱ��ȭ
			try {
				for (int i = 30; i >= 0; i--) {
					logInBtn.setEnabled(false);
				}
				sleep(3000);
			} 
			catch (Exception e) {
			}
			logInBtn.setEnabled(true);
		}
	}
	
	public void logSet() {//�۾����� �� �α��� ȭ�� ���� �� �ؽ�Ʈ set
		idTextF.setText(info);
		passwordField.setText("");
	}
	
	public void logTextChk() {// ack �� Not Found Data �� �� ����ִ� â
		logChkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logDialog.dispose();
				logSet();
			}
		});
		logDialog.setModal(true);
		logDialog.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
		idTextF = (JTextField) e.getSource();
		String idfield = idTextF.getText();

		if (idfield.equals(info) || idfield.equals(idInfo) || idfield.equals(phinfo)) {
			idTextF.setText("");
		}
	}

	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.getText().equals("�α���")) {
			if (!isLogin) {
				isLogin = true;
				CsLoginSyn packet = new CsLoginSyn(idTextF.getText(), passwordField.getText(), !changeBox.isSelected());
				ClientNet.getInstance().sendPacket(packet);
			}
		}
		else if(btn.getText().equals("ȸ������")) {
			BaseFrame.getInstance().signUpFrame.setVisible(true);
			BaseFrame.getInstance().signUpFrame.textFieldSet();
		}
	}
}