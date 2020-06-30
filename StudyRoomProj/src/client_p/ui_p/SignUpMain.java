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
	String korean = "[°¡-ÆR]*";
	String engNum = ".*[a-zA-Z].*";
	String engNum1 = ".*[0-9].*";
	String passChk = "[a-zA-Z0-9].{7}";
	String phoneChk = "010.[0-9].{6,7}";
	String name, id, pass, phoneNum, pass2 = null;

	Boolean idchk = false, hpchk = false;

	public SignUpMain() {
		setBounds(100, 100, 900, 1000);
		JPanel mainPane = new JPanel();
		setContentPane(mainPane);
		mainPane.setLayout(null);

		JLabel titleLabel = new JLabel("È¸¿ø°¡ÀÔÃ¢");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(268, 10, 380, 75);
		mainPane.add(titleLabel);

		JLabel nameL = new JLabel("ÀÌ¸§");
		nameL.setBounds(129, 112, 77, 42);
		mainPane.add(nameL);

		JLabel idL = new JLabel("¾ÆÀÌµð");
		idL.setBounds(129, 164, 77, 42);
		mainPane.add(idL);

		JLabel pwL = new JLabel("ºñ¹Ð¹øÈ£");
		pwL.setBounds(127, 205, 77, 42);
		mainPane.add(pwL);

		JLabel pwChkL = new JLabel("ºñ¹Ð¹øÈ£È®ÀÎ");
		pwChkL.setBounds(129, 257, 89, 42);
		mainPane.add(pwChkL);

		JLabel phonNumL = new JLabel("ÈÞ´ëÆù ¹øÈ£");
		phonNumL.setBounds(129, 309, 77, 42);
		mainPane.add(phonNumL);

		nameTextField = new JTextField();// ÀÌ¸§
		nameTextField.setBounds(269, 121, 191, 33);
		mainPane.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.addMouseListener(this);
		textList.add(nameTextField);

		idTextField = new JTextField();// ¾ÆÀÌµð
		idTextField.setColumns(10);
		idTextField.setBounds(269, 168, 191, 33);
		mainPane.add(idTextField);
		idTextField.addMouseListener(this);
		textList.add(idTextField);

		phoneNumTextField = new JTextField();// Æù¹øÈ£
		phoneNumTextField.setColumns(10);
		phoneNumTextField.setBounds(271, 318, 191, 33);
		mainPane.add(phoneNumTextField);
		textList.add(phoneNumTextField);
		phoneNumTextField.addMouseListener(this);

		JButton signUpBtn = new JButton("È¸¿ø°¡ÀÔ");
		signUpBtn.setBounds(192, 368, 140, 42);
		mainPane.add(signUpBtn);
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!idchk || !hpchk) {
					infoChk();
				} else {
					check();
				}
			}
		});

		JButton cancelBtn = new JButton("Ãë¼Ò");
		cancelBtn.setBounds(494, 368, 140, 42);
		mainPane.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});

		JButton idChkBtn = new JButton("ID Áßº¹È®ÀÎ");
		idChkBtn.setBounds(477, 169, 105, 33);
		mainPane.add(idChkBtn);
		idChkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!chkJd.isVisible())
					idChk();
			}
		});

		JButton pwChkBtn = new JButton("ÇÚµåÆù Áßº¹Ã¼Å©");
		pwChkBtn.setBounds(477, 318, 140, 33);
		mainPane.add(pwChkBtn);
		pwChkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!chkJd.isVisible())
					phChk();
			}
		});

		passwordField = new JPasswordField();// ºñ¹Ð¹øÈ£
		passwordField.setBounds(269, 219, 191, 33);
		mainPane.add(passwordField);
		pTextList.add(passwordField);
		passwordField.addMouseListener(this);

		check_passwordField = new JPasswordField();// ºñ¹Ð¹øÈ£ È®ÀÎ
		check_passwordField.setBounds(269, 266, 191, 33);
		mainPane.add(check_passwordField);
		pTextList.add(check_passwordField);
		check_passwordField.addMouseListener(this);

		JPanel keybordPane = new JPanel();
		keybordPane.setBounds(12, 431, 860, 300);
		mainPane.add(keybordPane);

		label_1 = new JLabel("ÇÑ±Û·Î ÀÔ·ÂÇÏ¼¼¿ä");
		label_1.setBounds(477, 123, 318, 33);
		mainPane.add(label_1);

		label_2 = new JLabel("¿µ¹®,¼ýÀÚ·Î Á¶ÇÕµÈ 8ÀÚ¸®ÀÌÇÏ·Î ÀÔ·ÂÇÏ¼¼¿ä");
		label_2.setBounds(594, 164, 267, 33);
		mainPane.add(label_2);

		label_3 = new JLabel("¿µ¹®,¼ýÀÚ·Î Á¶ÇÕµÈ 8ÀÚ¸®¸¦ ÀÔ·ÂÇÏ¼¼¿ä");
		label_3.setBounds(477, 216, 318, 33);
		mainPane.add(label_3);

		label_4 = new JLabel("ÀÔ·ÂÇÑ ºñ¹Ð¹øÈ£¿Í °°°Ô ÀÔ·ÂÇÏ¼¼¿ä");
		label_4.setBounds(477, 268, 318, 33);
		mainPane.add(label_4);

		label_5 = new JLabel("'-'´Â Á¦¿ÜÇÏ°í ÀÔ·ÂÇÏ¼¼¿ä");
		label_5.setBounds(632, 318, 318, 33);
		mainPane.add(label_5);

		setVisible(false);

		chkJd = new JDialog();
		chkJd.setBounds(100, 100, 200, 200);
		chkJd.getContentPane().setLayout(new GridLayout(2, 1));

		JLabel chkLb = new JLabel("Á¤È®ÇÑ Çü½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä");
		chkJd.getContentPane().add(chkLb);
		JButton chkBt = new JButton("È®ÀÎ");
		chkJd.getContentPane().add(chkBt);
		chkBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chkJd.setVisible(false);
			}
		});
		chkJd.setVisible(false);

		jd2 = new JDialog();
		jd2.setBounds(100, 100, 200, 150);
		jd2.getContentPane().setLayout(new GridLayout(2, 1));
		jl2 = new JLabel();
		jb2 = new JButton("È®ÀÎ");
		jd2.getContentPane().add(jl2);
		jd2.getContentPane().add(jb2);

		chkSignUp = new JDialog();
		chkSignUp.setBounds(100, 100, 300, 200);
		chkSignUp.getContentPane().setLayout(new GridLayout(2, 1));
		JLabel jll = new JLabel("ID ¿Í ÈÞ´ëÆù ¹øÈ£ Áßº¹°Ë»ç¸¦ ÁøÇàÇÏ¼¼¿ä");
		chkSignUp.getContentPane().add(jll);
		jbt = new JButton("È®ÀÎ");
		chkSignUp.getContentPane().add(jbt);
		chkSignUp.setVisible(false);
	}

	@Override
	public void receive(PacketBase packet) {

		if (packet.getClass() == ScSignUpAck.class) {
			ScSignUpAck ack = (ScSignUpAck) packet;

			if (ack.eResult == EResult.SUCCESS) {// È¸¿ø°¡ÀÔ ¼º°ø
				idchk = false;
				hpchk = false;

				jd = new JDialog();
				jd.setBounds(100, 100, 200, 150);
				jd.getContentPane().setLayout(new GridLayout(2, 1));
				jl = new JLabel("È¸¿ø°¡ÀÔ ¿Ï·á");
				jb = new JButton("È®ÀÎ");
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton bbb = (JButton) e.getSource();
						if (bbb.getText().equals("È®ÀÎ")) {
							jd.setVisible(false);
							dispose();
						}
					}
				});

				jd.getContentPane().add(jl);
				jd.getContentPane().add(jb);
				jd.setVisible(true);
			}
		} 
		else if (packet.getClass() == ScDuplicateIDAck.class) {// Áßº¹È®ÀÎ
			ScDuplicateIDAck ack = (ScDuplicateIDAck) packet;

			if ((ack.eResult == EResult.SUCCESS) && !ack.is_hp) {// id¼º°ø
				idchk = true;
				jl2.setText("»ç¿ë°¡´ÉÇÑ id ÀÔ´Ï´Ù");
				loginChk();
			} else if ((ack.eResult == EResult.SUCCESS) && ack.is_hp) {// ÇÚµåÆù¼º°ø
				hpchk = true;
				jl2.setText("»ç¿ë °¡´ÉÇÑ ÇÚµåÆù ÀÔ´Ï´Ù.");
				loginChk();
			} else if ((ack.eResult == EResult.DUPLICATEED_ID) && !ack.is_hp) {// idÁßº¹
				jl2.setText("Áßº¹µÈ ID ÀÔ´Ï´Ù.");
				loginChk();
			} else if ((ack.eResult == EResult.DUPLICATEED_ID) && ack.is_hp) {// hpÁßº¹
				jl2.setText("Áßº¹µÈ ÇÚµåÆù ÀÔ´Ï´Ù.");
				loginChk();
			}
		} 
		else {
			System.out.println("È¸¿ø°¡ÀÔ ¿¹¿Ü¿¡·¯");
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
				label_1.setText("ÀÔ·Â È®ÀÎ");
				System.out.println("l1");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (id.matches(engNum) && id.matches(engNum1)) {
				label_5.setText("ÀÔ·Â È®ÀÎ");
				System.out.println("l1");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (pass.matches(passChk)) {
				label_2.setText("ÀÔ·Â È®ÀÎ");
				System.out.println("l1");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (phoneNum.matches(phoneChk)) {
				label_4.setText("ÀÔ·Â È®ÀÎ");
				System.out.println("l1");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (pass.matches(pass2)) {
				label_3.setText("ÀÔ·Â È®ÀÎ");
				System.out.println("l1");
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
			System.out.println("ÆÐÅÏ¸ÅÄ¡ ¿¡·¯");
		}
	}

	public void infoChk() {
		chkSignUp.setVisible(true);
		jbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				chkSignUp.setVisible(false);
			}
		});
	}

	public void textFieldSet() {// È¸¿ø°¡ÀÔ ÅØ½ºÆ® ÇÊµå,¶óº§³»¿ë ÃÊ±âÈ­
		for (JTextField text : textList) {
			text.setText("");
		}

		for (JPasswordField pText : pTextList) {
			pText.setText("");
		}

		label_1.setText("ÇÑ±Û·Î ÀÔ·ÂÇÏ¼¼¿ä");
		label_2.setText("¿µ¹®,¼ýÀÚ·Î Á¶ÇÕµÈ 8ÀÚ¸®ÀÌÇÏ·Î ÀÔ·ÂÇÏ¼¼¿ä");
		label_3.setText("ÀÔ·ÂÇÑ ºñ¹Ð¹øÈ£¿Í °°°Ô ÀÔ·ÂÇÏ¼¼¿ä");
		label_4.setText("¿µ¹®,¼ýÀÚ·Î Á¶ÇÕµÈ 8ÀÚ¸®¸¦ ÀÔ·ÂÇÏ¼¼¿ä");
		label_5.setText("'-'´Â Á¦¿ÜÇÏ°í ÀÔ·ÂÇÏ¼¼¿ä");
	}

	void idChk() {
		id = idTextField.getText();
		if (id.matches(engNum) && id.matches(engNum1) && id.length() < 9) {
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
				JButton bbb = (JButton) e.getSource();
				if (bbb.getText().equals("È®ÀÎ")) {
					jd2.setVisible(false);
				}
			}
		});
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