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

public class SignUpMain extends JFrame implements Receivable, MouseListener {

	JTextField nameTextField;
	JTextField idTextField;
	JTextField phoneNumTextField;
	JPasswordField passwordField;
	JPasswordField check_passwordField;

	JDialog jd,jd2;
	String text = "";
	JLabel label_1, label_2, label_3, label_4, label_5;
	JLabel jl, jl2;
	JButton jb, jb2;
	ArrayList<JTextField> textList = new ArrayList<JTextField>();
	ArrayList<JPasswordField> pTextList = new ArrayList<JPasswordField>();

	String korean = "[��-�R]*";
	String engNum = ".*[a-zA-Z].*";
	String engNum1 = ".*[0-9].*";

	String passChk = "[a-zA-Z0-9].{7}";
	String phoneChk = "010.[0-9].{6,7}";
	String name, id, pass, phoneNum, pass2 = null;
	Boolean idchk=false,hpchk=false;

	public SignUpMain() {
		setBounds(100, 100, 900, 1000);
		JPanel mainPane = new JPanel();
		setContentPane(mainPane);
		mainPane.setLayout(null);

		JLabel titleLabel = new JLabel("ȸ������â");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(268, 10, 380, 75);
		mainPane.add(titleLabel);

		JLabel idL = new JLabel("���̵�");
		idL.setBounds(129, 164, 77, 42);
		mainPane.add(idL);

		JLabel nameL = new JLabel("�̸�");
		nameL.setBounds(129, 112, 77, 42);
		mainPane.add(nameL);

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
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!idchk||!hpchk) {
					infoChk();
				}else {
					
					check();
				}
			}
		});

		JButton cancelBtn = new JButton("���");
		cancelBtn.setBounds(494, 368, 140, 42);
		mainPane.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JButton idChkBtn = new JButton("ID �ߺ�Ȯ��");
		idChkBtn.setBounds(477, 169, 105, 33);
		mainPane.add(idChkBtn);
		idChkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				idChk();
			}
		});

		JButton pwChkBtn = new JButton("�ڵ��� �ߺ�üũ");
		pwChkBtn.setBounds(477, 318, 140, 33);
		mainPane.add(pwChkBtn);
		pwChkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				phChk();
			}
		});

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

		label_2 = new JLabel("����,���ڷ� ���յ� 8�ڸ��� �Է��ϼ���");
		label_2.setBounds(477, 216, 318, 33);
		mainPane.add(label_2);

		label_3 = new JLabel("�Է��� ��й�ȣ�� ���� �Է��ϼ���");
		label_3.setBounds(477, 268, 318, 33);
		mainPane.add(label_3);

		label_4 = new JLabel("'-'�� �����ϰ� �Է��ϼ���");
		label_4.setBounds(631, 318, 178, 33);
		mainPane.add(label_4);

		label_5 = new JLabel("����,���ڷ� ���յ� 8�ڸ��� �Է��ϼ���");
		label_5.setBounds(594, 164, 267, 33);
		mainPane.add(label_5);

		setVisible(false);
	}

	@Override
	public void receive(PacketBase packet) {

		if (packet.getClass() == ScSignUpAck.class) {// �α��� Ȯ��
			ScSignUpAck ack = (ScSignUpAck) packet;

			if (ack.eResult == EResult.SUCCESS) {
				jd = new JDialog();
				jd.setBounds(50, 50, 150, 150);
				jd.getContentPane().setLayout(new GridLayout(2, 1));
				jl = new JLabel("ȸ������ �Ϸ�");
				jb = new JButton("Ȯ��");
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton bbb = (JButton) e.getSource();
						if (bbb.getText().equals("Ȯ��")) {
							jd.setVisible(false);
							dispose();
						}
					}
				});

				jd.getContentPane().add(jl);
				jd.getContentPane().add(jb);
				jd.setVisible(true);
			} else if (ack.eResult == EResult.DUPLICATEED_ID) {
				jd = new JDialog();
				jd.setBounds(50, 50, 150, 150);
				jd.getContentPane().setLayout(new GridLayout(2, 1));
				jl = new JLabel("ID �ߺ� Ȯ���� ���ּ���");
				jb = new JButton("Ȯ��");
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton bbb = (JButton) e.getSource();
						if (bbb.getText().equals("Ȯ��")) {
							jd.dispose();
						}
					}
				});

				jd.getContentPane().add(jl);
				jd.getContentPane().add(jb);
				jd.setVisible(true);
			}
		} else if (packet.getClass() == ScDuplicateIDAck.class) {// �ߺ�Ȯ��
			ScDuplicateIDAck ack = (ScDuplicateIDAck) packet;

			if ((ack.eResult == EResult.SUCCESS) && !ack.is_hp) {// id����
				loginChk("��밡���� id �Դϴ�");
			} else if ((ack.eResult == EResult.SUCCESS) && ack.is_hp) {// �ڵ�������
				loginChk("��� ������ �ڵ��� �Դϴ�.");
			} else if ((ack.eResult == EResult.DUPLICATEED_ID) && !ack.is_hp) {// id�ߺ�
				loginChk("�ߺ��� ID �Դϴ�.");
			} else if ((ack.eResult == EResult.DUPLICATEED_ID) && ack.is_hp) {// hp�ߺ�
				loginChk("�ߺ��� �ڵ��� �Դϴ�.");
			}
		} else {

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

		}
	}
	
	public void infoChk() {
		JDialog chkJd = new JDialog();
		chkJd.setBounds(100, 100, 300, 200);
		chkJd.setLayout(new GridLayout(2,1));
		JLabel jll = new JLabel("ID �� �޴��� ��ȣ �ߺ��˻縦 �����ϼ���");
		chkJd.add(jll);
		JButton jbt = new JButton("Ȯ��");
		chkJd.add(jbt);
		jbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				chkJd.dispose();
				
			}
		});
		chkJd.setVisible(true);
	}

	public void textFieldSet() {// ȸ������ �ؽ�Ʈ �ʵ�,�󺧳��� �ʱ�ȭ
		for (JTextField text : textList) {
			text.setText("");
		}

		for (JPasswordField pText : pTextList) {
			pText.setText("");
		}

		label_1.setText("�ѱ۷� �Է��ϼ���");
		label_2.setText("����,���ڷ� ���յ� 8�ڸ��� �Է��ϼ���");
		label_3.setText("�Է��� ��й�ȣ�� ���� �Է��ϼ���");
		label_4.setText("'-'�� �����ϰ� �Է��ϼ���");
		label_5.setText("����,���ڷ� ���յ� 8�ڸ��� �Է��ϼ���");
	}

	void idChk() {
		id = idTextField.getText();
		if (id.matches(engNum) && id.matches(engNum1) && id.length() < 9) {
			CsDuplicateIDSyn packet = new CsDuplicateIDSyn(idTextField.getText(), false);
			ClientNet.getInstance().sendPacket(packet);
			idchk=true;
		} else {
			fail("��Ȯ��ID");
			
		}
	}

	void phChk() {
		phoneNum = phoneNumTextField.getText();
		if (phoneNum.matches(phoneChk)) {
			CsDuplicateIDSyn packet = new CsDuplicateIDSyn(phoneNumTextField.getText(), true);
			ClientNet.getInstance().sendPacket(packet);
			hpchk=true;
		} else {
			fail("��Ȯ�� �ڵ���");
			
		}
	}

	void fail(String type) {
		JDialog chkJd = new JDialog();
		chkJd.setBounds(100, 100, 200, 200);
		chkJd.getContentPane().setLayout(new GridLayout(2, 1));
		JLabel chkLb = new JLabel(type + " ������ �Է��ϼ���");
		chkJd.getContentPane().add(chkLb);
		JButton chkBt = new JButton("Ȯ��");
		chkJd.getContentPane().add(chkBt);
		chkBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkJd.dispose();
			}
		});
		chkJd.setVisible(true);
	}

	void loginChk(String msg) {
		jd2 = new JDialog();
		jd2.setBounds(50, 50, 150, 150);
		jd2.getContentPane().setLayout(new GridLayout(2, 1));
		jl2 = new JLabel(msg);
		jb2 = new JButton("Ȯ��");
		jb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JButton bbb = (JButton) e.getSource();
				if (bbb.getText().equals("Ȯ��")) {
					jd2.setVisible(false);
					setVisible(true);
				}
			}
		});

		jd2.getContentPane().add(jl2);
		jd2.getContentPane().add(jb2);
		jd2.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JTextField jtf = (JTextField) e.getSource();

		if (!jtf.getText().equals("")) {
			jtf.setText("");
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