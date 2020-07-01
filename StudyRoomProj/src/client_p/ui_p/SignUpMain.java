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
import java.awt.Font;

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
	String korean = "[°¡-ÆR]*";
	String engNum = ".*[a-zA-Z].*";
	String engNum1 = ".*[0-9].*";
	String passChk = "[a-zA-Z0-9].{7}";
	String phoneChk = "010.[0-9].{6,7}";
	String name, id, pass, phoneNum, pass2 = null;

	boolean idchk,hpchk;

	public SignUpMain() {
		setBounds(510, 140, 900, 800);
		JPanel mainPane = new JPanel();
		setContentPane(mainPane);
		mainPane.setLayout(null);

		JLabel titleLabel = new JLabel("È¸¿ø°¡ÀÔ Ã¢");
		titleLabel.setFont(new Font("±¼¸²", Font.BOLD, 38));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(268, 10, 380, 75);
		mainPane.add(titleLabel);

		JLabel nameL = new JLabel("ÀÌ¸§");
		nameL.setFont(new Font("±¼¸²", Font.BOLD, 20));
		nameL.setHorizontalAlignment(SwingConstants.CENTER);
		nameL.setBounds(30, 161, 139, 75);
		mainPane.add(nameL);

		JLabel idL = new JLabel("¾ÆÀÌµð");
		idL.setFont(new Font("±¼¸²", Font.BOLD, 20));
		idL.setHorizontalAlignment(SwingConstants.CENTER);
		idL.setBounds(30, 248, 139, 66);
		mainPane.add(idL);

		JLabel pwL = new JLabel("ºñ¹Ð¹øÈ£");
		pwL.setFont(new Font("±¼¸²", Font.BOLD, 20));
		pwL.setHorizontalAlignment(SwingConstants.CENTER);
		pwL.setBounds(30, 335, 139, 70);
		mainPane.add(pwL);

		JLabel pwChkL = new JLabel("ºñ¹Ð¹øÈ£ È®ÀÎ");
		pwChkL.setFont(new Font("±¼¸²", Font.BOLD, 20));
		pwChkL.setHorizontalAlignment(SwingConstants.CENTER);
		pwChkL.setBounds(30, 417, 139, 66);
		mainPane.add(pwChkL);

		JLabel phonNumL = new JLabel("ÈÞ´ëÆù ¹øÈ£");
		phonNumL.setFont(new Font("±¼¸²", Font.BOLD, 20));
		phonNumL.setHorizontalAlignment(SwingConstants.CENTER);
		phonNumL.setBounds(30, 495, 139, 66);
		mainPane.add(phonNumL);

		nameTextField = new JTextField();// ÀÌ¸§
		nameTextField.setBounds(181, 169, 329, 57);
		mainPane.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.addMouseListener(this);
		textList.add(nameTextField);

		idTextField = new JTextField();// ¾ÆÀÌµð
		idTextField.setColumns(10);
		idTextField.setBounds(181, 253, 329, 57);
		mainPane.add(idTextField);
		idTextField.addMouseListener(this);
		textList.add(idTextField);

		phoneNumTextField = new JTextField();// Æù¹øÈ£
		phoneNumTextField.setColumns(10);
		phoneNumTextField.setBounds(181, 503, 329, 57);
		mainPane.add(phoneNumTextField);
		textList.add(phoneNumTextField);
		phoneNumTextField.addMouseListener(this);

		JButton signUpBtn = new JButton("È¸¿ø°¡ÀÔ");
		signUpBtn.setFont(new Font("±¼¸²", Font.BOLD, 20));
		signUpBtn.setBounds(194, 624, 206, 75);
		mainPane.add(signUpBtn);
		signUpBtn.addActionListener(this);

		JButton idChkBtn = new JButton("ID Áßº¹Ã¼Å©");
		idChkBtn.setBounds(524, 248, 140, 33);
		mainPane.add(idChkBtn);
		idChkBtn.addActionListener(this);

		JButton pwChkBtn = new JButton("ÈÞ´ëÆù Áßº¹Ã¼Å©");
		pwChkBtn.setBounds(524, 512, 140, 33);
		mainPane.add(pwChkBtn);
		pwChkBtn.addActionListener(this);

		JButton cancelBtn = new JButton("Ãë¼Ò");
		cancelBtn.setFont(new Font("±¼¸²", Font.BOLD, 20));
		cancelBtn.setBounds(496, 624, 206, 75);
		mainPane.add(cancelBtn);
		cancelBtn.addActionListener(this);

		passwordField = new JPasswordField();// ºñ¹Ð¹øÈ£
		passwordField.setBounds(181, 335, 329, 57);
		mainPane.add(passwordField);
		pTextList.add(passwordField);
		passwordField.addMouseListener(this);

		check_passwordField = new JPasswordField();// ºñ¹Ð¹øÈ£ È®ÀÎ
		check_passwordField.setBounds(181, 422, 329, 57);
		mainPane.add(check_passwordField);
		pTextList.add(check_passwordField);
		check_passwordField.addMouseListener(this);

		label_1 = new JLabel("ÇÑ±Û·Î ÀÔ·ÂÇÏ¼¼¿ä");
		label_1.setBounds(528, 182, 318, 33);
		mainPane.add(label_1);

		label_2 = new JLabel("¿µ¹®,¼ýÀÚ·Î Á¶ÇÕµÈ 8ÀÚ¸®ÀÌÇÏ·Î ÀÔ·ÂÇÏ¼¼¿ä");
		label_2.setBounds(524, 293, 322, 33);
		mainPane.add(label_2);

		label_3 = new JLabel("¿µ¹®,¼ýÀÚ·Î Á¶ÇÕµÈ 8ÀÚ¸®¸¦ ÀÔ·ÂÇÏ¼¼¿ä");
		label_3.setBounds(528, 354, 318, 33);
		mainPane.add(label_3);

		label_4 = new JLabel("ÀÔ·ÂÇÑ ºñ¹Ð¹øÈ£¿Í °°°Ô ÀÔ·ÂÇÏ¼¼¿ä");
		label_4.setBounds(528, 434, 318, 33);
		mainPane.add(label_4);

		label_5 = new JLabel("'-'´Â Á¦¿ÜÇÏ°í ÀÔ·ÂÇÏ¼¼¿ä");
		label_5.setBounds(678, 512, 168, 33);
		mainPane.add(label_5);

		setVisible(false);

		chkJd = new JDialog();
		chkJd.setBounds(860, 440, 200, 200);
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

		jd2 = new JDialog();//Áßº¹È®ÀÎÃ¢
		jd2.setBounds(860, 465, 200, 150);
		jd2.getContentPane().setLayout(new GridLayout(2, 1));
		jl2 = new JLabel();
		jd2.getContentPane().add(jl2);
		jb2 = new JButton("È®ÀÎ");
		jd2.getContentPane().add(jb2);

		chkSignUp = new JDialog();//È¸¿ø°¡ÀÔ Å¬¸¯½Ã À¯È¿°Ë»ç Ã¢
		chkSignUp.setBounds(810, 440, 300, 200);
		chkSignUp.getContentPane().setLayout(new GridLayout(2, 1));
		JLabel jll = new JLabel("ID ¿Í ÈÞ´ëÆù ¹øÈ£ Áßº¹°Ë»ç¸¦ ÁøÇàÇÏ¼¼¿ä");
		chkSignUp.getContentPane().add(jll);
		jbt = new JButton("È®ÀÎ");
		chkSignUp.getContentPane().add(jbt);
		chkSignUp.setVisible(false);
		
		jd = new JDialog();//È¸¿ø°¡ÀÔ ¿Ï·áÃ¢
		jd.setBounds(100, 100, 200, 150);
		jd.getContentPane().setLayout(new GridLayout(2, 1));
		jl = new JLabel("È¸¿ø°¡ÀÔ ¿Ï·á");
		jd.getContentPane().add(jl);
		jb = new JButton("È®ÀÎ");
		jd.getContentPane().add(jb);
		jd.setVisible(false);
	}

	@Override
	public void receive(PacketBase packet) {

		if (packet.getClass() == ScSignUpAck.class) {
			ScSignUpAck ack = (ScSignUpAck) packet;

			if (ack.eResult == EResult.SUCCESS) {// È¸¿ø°¡ÀÔ ¼º°ø
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
				System.out.println("È¸¿ø°¡ÀÔ ½ÇÆÐ");
			}
		} 
		else if (packet.getClass() == ScDuplicateIDAck.class) {// Áßº¹È®ÀÎÃ¼Å©
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
			System.out.println("SignUpMain ¿¹¿Ü");
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
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (id.matches(engNum) && id.matches(engNum1)) {
				label_5.setText("ÀÔ·Â È®ÀÎ");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (pass.matches(passChk)) {
				label_2.setText("ÀÔ·Â È®ÀÎ");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (phoneNum.matches(phoneChk)) {
				label_4.setText("ÀÔ·Â È®ÀÎ");
			} else {
				SignUpPop pop = new SignUpPop();
				return;
			}
			if (pass.matches(pass2)) {
				label_3.setText("ÀÔ·Â È®ÀÎ");
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

	public void textFieldSet() {// È¸¿ø°¡ÀÔ ÅØ½ºÆ® ÇÊµå,¶óº§³»¿ë ÃÊ±âÈ­
		for (JTextField text : textList) {
			text.setText("");
		}

		for (JPasswordField pText : pTextList) {
			pText.setText("");
		}

		label_1.setText("ÇÑ±Û·Î ÀÔ·ÂÇÏ¼¼¿ä");
		label_2.setText("¿µ¹®,¼ýÀÚ·Î Á¶ÇÕµÈ 8ÀÚ¸®ÀÌÇÏ·Î ÀÔ·ÂÇÏ¼¼¿ä");
		label_3.setText("¿µ¹®,¼ýÀÚ·Î Á¶ÇÕµÈ 8ÀÚ¸®¸¦ ÀÔ·ÂÇÏ¼¼¿ä");
		label_4.setText("ÀÔ·ÂÇÑ ºñ¹Ð¹øÈ£¿Í °°°Ô ÀÔ·ÂÇÏ¼¼¿ä");
		label_5.setText("'-'´Â Á¦¿ÜÇÏ°í ÀÔ·ÂÇÏ¼¼¿ä");
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
		if (btn.getText().equals("ID Áßº¹È®ÀÎ")) {
			if (!chkJd.isVisible())
				idChk();
		} 
		else if (btn.getText().equals("ÇÚµåÆù Áßº¹Ã¼Å©")) {
			if (!chkJd.isVisible())
				phChk();
		} 
		else if (btn.getText().equals("Ãë¼Ò")) {
			dispose();
		}
		else if (btn.getText().equals("È¸¿ø°¡ÀÔ")) {
			if (!idchk || !hpchk) {
				infoChk();
			} else {
				check();
			}
		}
		else {
			System.out.println("¹öÆ° ÀÔ·Â ¿¡·¯");
		}
	}
}