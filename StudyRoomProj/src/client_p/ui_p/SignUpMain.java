package client_p.ui_p;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsDuplicateIDSyn;
import client_p.packet_p.syn_p.CsSignUpSyn;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScDuplicateIDAck;
import server_p.packet_p.ack_p.ScSignUpAck;

public class SignUpMain extends JFrame implements Receivable, MouseListener, ActionListener {

	ArrayList<JTextField> textList = new ArrayList<JTextField>();
	ArrayList<JPasswordField> pTextList = new ArrayList<JPasswordField>();

	JTextField nameTextField;
	JTextField idTextField;
	JTextField phoneNumTextField;
	JPasswordField passwordField;
	JPasswordField check_passwordField;

	JLabel jl, jl2, label_1, label_2, label_3, label_4, label_5;
	JButton jb, jb2, jbt;
	JDialog jd, jd2, chkJd, chkSignUp;

	String text = "";
	String korean = "[��-�R]*";
	String engNum = ".*[a-zA-Z].*";
	String engNum1 = ".*[0-9].*";
	String passChk = "[a-zA-Z0-9].{7}";
	String phoneChk = "010.[0-9].{6,7}";
	String name, id, pass, phoneNum, pass2 = null;

	boolean idchk,hpchk;

	public SignUpMain() {
		setBounds(100, 100, 900, 800);
		JPanel mainPane = new JPanel();
		setContentPane(mainPane);
		mainPane.setLayout(null);

		JLabel titleLabel = new JLabel("ȸ������â");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(268, 10, 380, 75);
		mainPane.add(titleLabel);

		JLabel nameL = new JLabel("�̸�");
		nameL.setBounds(129, 112, 77, 42);
		mainPane.add(nameL);

		JLabel idL = new JLabel("���̵�");
		idL.setBounds(129, 164, 77, 42);
		mainPane.add(idL);

		JLabel pwL = new JLabel("��й�ȣ");
		pwL.setBounds(127, 205, 77, 42);
		mainPane.add(pwL);

		JLabel pwChkL = new JLabel("��й�ȣȮ��");
		pwChkL.setBounds(129, 257, 89, 42);
		mainPane.add(pwChkL);

		JLabel phonNumL = new JLabel("�޴��� ��ȣ");
		phonNumL.setBounds(129, 309, 77, 42);
		mainPane.add(phonNumL);

		nameTextField = new JTextField();// �̸�
		nameTextField.setBounds(269, 121, 191, 33);
		mainPane.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.addMouseListener(this);
		textList.add(nameTextField);

		idTextField = new JTextField();// ���̵�
		idTextField.setColumns(10);
		idTextField.setBounds(269, 168, 191, 33);
		mainPane.add(idTextField);
		idTextField.addMouseListener(this);
		textList.add(idTextField);

		phoneNumTextField = new JTextField();// ����ȣ
		phoneNumTextField.setColumns(10);
		phoneNumTextField.setBounds(271, 318, 191, 33);
		mainPane.add(phoneNumTextField);
		textList.add(phoneNumTextField);
		phoneNumTextField.addMouseListener(this);

		JButton signUpBtn = new JButton("ȸ������");
		signUpBtn.setBounds(192, 368, 140, 42);
		mainPane.add(signUpBtn);
		signUpBtn.addActionListener(this);

		JButton idChkBtn = new JButton("ID �ߺ�Ȯ��");
		idChkBtn.setBounds(477, 169, 105, 33);
		mainPane.add(idChkBtn);
		idChkBtn.addActionListener(this);

		JButton pwChkBtn = new JButton("�ڵ��� �ߺ�üũ");
		pwChkBtn.setBounds(477, 318, 140, 33);
		mainPane.add(pwChkBtn);
		pwChkBtn.addActionListener(this);

		JButton cancelBtn = new JButton("���");
		cancelBtn.setBounds(494, 368, 140, 42);
		mainPane.add(cancelBtn);
		cancelBtn.addActionListener(this);

		passwordField = new JPasswordField();// ��й�ȣ
		passwordField.setBounds(269, 219, 191, 33);
		mainPane.add(passwordField);
		pTextList.add(passwordField);
		passwordField.addMouseListener(this);

		check_passwordField = new JPasswordField();// ��й�ȣ Ȯ��
		check_passwordField.setBounds(269, 266, 191, 33);
		mainPane.add(check_passwordField);
		pTextList.add(check_passwordField);
		check_passwordField.addMouseListener(this);

		JPanel keybordPane = new JPanel();
		keybordPane.setBounds(12, 431, 860, 300);
		mainPane.add(keybordPane);

		label_1 = new JLabel("�ѱ۷� �Է��ϼ���");
		label_1.setBounds(477, 123, 318, 33);
		mainPane.add(label_1);

		label_2 = new JLabel("����,���ڷ� ���յ� 8�ڸ����Ϸ� �Է��ϼ���");
		label_2.setBounds(594, 164, 267, 33);
		mainPane.add(label_2);

		label_3 = new JLabel("����,���ڷ� ���յ� 8�ڸ��� �Է��ϼ���");
		label_3.setBounds(477, 216, 318, 33);
		mainPane.add(label_3);

		label_4 = new JLabel("�Է��� ��й�ȣ�� ���� �Է��ϼ���");
		label_4.setBounds(477, 268, 318, 33);
		mainPane.add(label_4);

		label_5 = new JLabel("'-'�� �����ϰ� �Է��ϼ���");
		label_5.setBounds(632, 318, 318, 33);
		mainPane.add(label_5);

		setVisible(false);

		chkJd = new JDialog();
		chkJd.setBounds(100, 100, 200, 200);
		chkJd.getContentPane().setLayout(new GridLayout(2, 1));

		JLabel chkLb = new JLabel("��Ȯ�� �������� �Է��ϼ���");
		chkJd.getContentPane().add(chkLb);
		JButton chkBt = new JButton("Ȯ��");
		chkJd.getContentPane().add(chkBt);
		chkBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkJd.setVisible(false);
			}
		});
		chkJd.setVisible(false);

		jd2 = new JDialog();//�ߺ�Ȯ��â
		jd2.setBounds(100, 100, 200, 150);
		jd2.getContentPane().setLayout(new GridLayout(2, 1));
		jl2 = new JLabel();
		jd2.getContentPane().add(jl2);
		jb2 = new JButton("Ȯ��");
		jd2.getContentPane().add(jb2);

		chkSignUp = new JDialog();//ȸ������ Ŭ���� ��ȿ�˻� ��
		chkSignUp.setBounds(100, 100, 300, 200);
		chkSignUp.getContentPane().setLayout(new GridLayout(2, 1));
		JLabel jll = new JLabel("ID �� �޴��� ��ȣ �ߺ��˻縦 �����ϼ���");
		chkSignUp.getContentPane().add(jll);
		jbt = new JButton("Ȯ��");
		chkSignUp.getContentPane().add(jbt);
		chkSignUp.setVisible(false);
		
		jd = new JDialog();//ȸ������ �Ϸ�â
		jd.setBounds(100, 100, 200, 150);
		jd.getContentPane().setLayout(new GridLayout(2, 1));
		jl = new JLabel("ȸ������ �Ϸ�");
		jd.getContentPane().add(jl);
		jb = new JButton("Ȯ��");
		jd.getContentPane().add(jb);
		jd.setVisible(false);
	}

	@Override
	public void receive(PacketBase packet) {

		if (packet.getClass() == ScSignUpAck.class) {
			ScSignUpAck ack = (ScSignUpAck) packet;

			if (ack.eResult == EResult.SUCCESS) {// ȸ������ ����
				idchk = false;
				hpchk = false;

				jd.setVisible(true);
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						jd.setVisible(false);
						dispose();
					}
				});
			}
			else {
				System.out.println("ȸ������ ����");
			}
		} 
		else if (packet.getClass() == ScDuplicateIDAck.class) {// �ߺ�Ȯ��üũ
			ScDuplicateIDAck ack = (ScDuplicateIDAck) packet;
			if ((ack.eResult == EResult.SUCCESS) && !ack.is_hp) {// id����
				idchk = true;
				jl2.setText("��밡���� id �Դϴ�");
				loginChk();
			} else if ((ack.eResult == EResult.SUCCESS) && ack.is_hp) {// �ڵ�������
				hpchk = true;
				jl2.setText("��� ������ �ڵ��� �Դϴ�.");
				loginChk();
			} else if ((ack.eResult == EResult.DUPLICATEED_ID) && !ack.is_hp) {// id�ߺ�
				jl2.setText("�ߺ��� ID �Դϴ�.");
				loginChk();
			} else if ((ack.eResult == EResult.DUPLICATEED_ID) && ack.is_hp) {// hp�ߺ�
				jl2.setText("�ߺ��� �ڵ��� �Դϴ�.");
				loginChk();
			}
		} 
		else {
			System.out.println("SignUpMain ����");
		}
	}

	void check() {

		name = nameTextField.getText();
		id = idTextField.getText();
		pass = passwordField.getText();
		phoneNum = phoneNumTextField.getText();
		pass2 = check_passwordField.getText();
		try {
			if (name.matches(korean)) {
				label_1.setText("�Է� Ȯ��");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (id.matches(engNum) && id.matches(engNum1)) {
				label_5.setText("�Է� Ȯ��");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (pass.matches(passChk)) {
				label_2.setText("�Է� Ȯ��");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (phoneNum.matches(phoneChk)) {
				label_4.setText("�Է� Ȯ��");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (pass.matches(pass2)) {
				label_3.setText("�Է� Ȯ��");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}

			if (idchk && hpchk) {
				CsSignUpSyn packet = new CsSignUpSyn(nameTextField.getText(), idTextField.getText(),
						passwordField.getText(), phoneNumTextField.getText(), "", "");
				ClientNet.getInstance().sendPacket(packet);
				idchk = false;
				hpchk = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("���ϸ�ġ ����");
		}
	}

	public void infoChk() {
		chkSignUp.setVisible(true);
		jbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkSignUp.setVisible(false);
			}
		});
	}

	void idChk() {
		id = idTextField.getText();
		if (id.matches(engNum) && id.matches(engNum1) && id.length() < 9 && !chkJd.isVisible())
		{
			CsDuplicateIDSyn packet = new CsDuplicateIDSyn(idTextField.getText(), false);
			ClientNet.getInstance().sendPacket(packet);
		} else {
			chkJd.setVisible(true);
		}
	}

	void phChk() {
		phoneNum = phoneNumTextField.getText();
		if (phoneNum.matches(phoneChk)) {
			CsDuplicateIDSyn packet = new CsDuplicateIDSyn(phoneNumTextField.getText(), true);
			ClientNet.getInstance().sendPacket(packet);
		} else {
			chkJd.setVisible(true);
		}
	}

	void loginChk() {
		jd2.setVisible(true);
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jd2.setVisible(false);
			}
		});
	}

	public void textFieldSet() {// ȸ������ �ؽ�Ʈ �ʵ�,�󺧳��� �ʱ�ȭ
		for (JTextField text : textList) {
			text.setText("");
		}

		for (JPasswordField pText : pTextList) {
			pText.setText("");
		}

		label_1.setText("�ѱ۷� �Է��ϼ���");
		label_2.setText("����,���ڷ� ���յ� 8�ڸ����Ϸ� �Է��ϼ���");
		label_3.setText("����,���ڷ� ���յ� 8�ڸ��� �Է��ϼ���");
		label_4.setText("�Է��� ��й�ȣ�� ���� �Է��ϼ���");
		label_5.setText("'-'�� �����ϰ� �Է��ϼ���");
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField jtf = (JTextField) e.getSource();

		if (!jtf.getText().equals("")) {
			jtf.setText("");
		}
	}
	
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.getText().equals("ID �ߺ�Ȯ��")) {
			if (!chkJd.isVisible())
				idChk();
		} 
		else if (btn.getText().equals("�ڵ��� �ߺ�üũ")) {
			if (!chkJd.isVisible())
				phChk();
		} 
		else if (btn.getText().equals("���")) {
			dispose();
		}
		else if (btn.getText().equals("ȸ������")) {
			if (!idchk || !hpchk) {
				infoChk();
			} else {
				check();
			}
		}
		else {
			System.out.println("��ư �Է� ����");
		}
	}
}