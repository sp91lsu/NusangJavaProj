package client_p.ui_p;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class SignUpMain extends JFrame implements Receivable{

	private JTextField nameTextField;
	private JTextField idTextField;
	private JTextField phoneNumTextField;
	private JPasswordField passwordField;
	private JPasswordField check_passwordField;
	
	JDialog jd,jd2;
	JLabel pwChk;
	String text="";
	JLabel jl,jl2;
	JButton jb,jb2;
	JButton signUpBtn;
	ArrayList<JTextField> textList = new ArrayList<JTextField>();
	ArrayList<JPasswordField> pTextList = new ArrayList<JPasswordField>();
	
	public SignUpMain() {
		setBounds(100, 100, 900, 1000);
		JPanel mainPane = new JPanel();
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("회원가입창");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(268, 10, 380, 75);
		mainPane.add(titleLabel);
		
		JLabel idL = new JLabel("아이디");
		idL.setBounds(280, 155, 77, 42);
		mainPane.add(idL);
		
		JLabel nameL = new JLabel("영문이름");
		nameL.setBounds(280, 103, 77, 42);
		mainPane.add(nameL);
		
		JLabel pwL = new JLabel("비밀번호");
		pwL.setBounds(278, 196, 77, 42);
		mainPane.add(pwL);
		
		JLabel pwChkL = new JLabel("비밀번호확인");
		pwChkL.setBounds(280, 248, 89, 42);
		mainPane.add(pwChkL);
		
		JLabel phonNumL = new JLabel("휴대폰 번호");
		phonNumL.setBounds(280, 300, 77, 42);
		mainPane.add(phonNumL);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(381, 112, 191, 33);
		mainPane.add(nameTextField);
		nameTextField.setColumns(10);
		textList.add(nameTextField);
		
		idTextField = new JTextField();
		idTextField.setColumns(10);
		idTextField.setBounds(381, 159, 191, 33);
		mainPane.add(idTextField);
		textList.add(idTextField);
		
		phoneNumTextField = new JTextField();
		phoneNumTextField.setColumns(10);
		phoneNumTextField.setBounds(383, 309, 191, 33);
		mainPane.add(phoneNumTextField);
		textList.add(phoneNumTextField);
		
		signUpBtn = new JButton("회원가입");
		signUpBtn.setBounds(296, 368, 140, 42);
		mainPane.add(signUpBtn);
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < textList.size(); i++) {
					if(textList.get(i).getText().equals("")){
						SignUpPop pop = new SignUpPop();
						return;
					}}
				for (int i = 0; i < pTextList.size(); i++) {
					if(pTextList.get(i).getText().equals("")){
						SignUpPop pop = new SignUpPop();
						return;
					}}
				check();
				}});
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.setBounds(494, 368, 140, 42);
		mainPane.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		JButton idChkBtn = new JButton("ID 중복확인");
		idChkBtn.setBounds(584, 159, 105, 33);
		mainPane.add(idChkBtn);
		idChkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CsDuplicateIDSyn packet = new CsDuplicateIDSyn(idTextField.getText());
				ClientNet.getInstance().sendPacket(packet);
			}});
		
		pwChk = new JLabel("입력한 비밀번호와 동일 하게 입력 하세요");
		pwChk.setBounds(584, 248, 300, 42);
		mainPane.add(pwChk);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(381, 210, 191, 33);
		mainPane.add(passwordField);
		pTextList.add(passwordField);
		
		check_passwordField = new JPasswordField();
		check_passwordField.setBounds(381, 257, 191, 33);
		mainPane.add(check_passwordField);
		pTextList.add(check_passwordField);
		
		JPanel keybordPane = new JPanel();
		keybordPane.setBounds(12, 431, 860, 300);
		mainPane.add(keybordPane);

		setVisible(false);
	}
	

	@Override
	public void receive(PacketBase packet) {
		
		if (packet.getClass() == ScSignUpAck.class) {
			ScSignUpAck ack = (ScSignUpAck) packet;

			if (ack.eResult == EResult.SUCCESS) {
				jd = new JDialog();
				jd.setBounds(50, 50, 150, 150);
				jd.setLayout(new GridLayout(2, 1));
				jl = new JLabel("회원가입 완료");
				jb = new JButton("확인");
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton bbb = (JButton) e.getSource();
						if (bbb.getText().equals("확인")) {
							jd.setVisible(false);
							dispose();
						}
					}
				});

				jd.add(jl);
				jd.add(jb);
				jd.setVisible(true);
			}else if(ack.eResult == EResult.DUPLICATEED_ID) {
				signUpBtn.setEnabled(false);
				jd = new JDialog();
				jd.setBounds(50, 50, 150, 150);
				jd.setLayout(new GridLayout(2, 1));
				jl = new JLabel("ID 중복 확인을 해주세요");
				jb = new JButton("확인");
				jb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton bbb = (JButton) e.getSource();
						if (bbb.getText().equals("확인")) {
							jd.setVisible(false);
							setVisible(true);
							
						}
					}
				});

				jd.add(jl);
				jd.add(jb);
				jd.setVisible(true);
			}
		}else if(packet.getClass() == ScDuplicateIDAck.class) {
			ScDuplicateIDAck ack = (ScDuplicateIDAck) packet;
			
			if(ack.eResult == EResult.SUCCESS) {
				jd2 = new JDialog();
				jd2.setBounds(50, 50, 150, 150);
				jd2.setLayout(new GridLayout(2, 1));
				jl2 = new JLabel("사용 가능한 ID 입니다.");
				jb2 = new JButton("확인");
				jb2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton bbb = (JButton) e.getSource();
						if (bbb.getText().equals("확인")) {
							jd2.setVisible(false);
							setVisible(true);
							signUpBtn.setEnabled(true);
							
						}
					}
				});

				jd2.add(jl2);
				jd2.add(jb2);
				jd2.setVisible(true);
			}else if(ack.eResult == EResult.DUPLICATEED_ID){
				jd2 = new JDialog();
				jd2.setBounds(50, 50, 150, 150);
				jd2.setLayout(new GridLayout(2, 1));
				jl2 = new JLabel("중복된 ID 입니다.");
				jb2 = new JButton("확인");
				jb2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton bbb = (JButton) e.getSource();
						if (bbb.getText().equals("확인")) {
							jd2.setVisible(false);
							setVisible(true);
							signUpBtn.setEnabled(false);
						}
					}
				});

				jd2.add(jl2);
				jd2.add(jb2);
				jd2.setVisible(true);
			}
		}
		
	}

	void check()
	{
		CsSignUpSyn packet =new CsSignUpSyn(nameTextField.getText(), idTextField.getText(), passwordField.getText(), phoneNumTextField.getText(), "", "");
		
		passwordField.getText().trim();
		check_passwordField.getText().trim();
		
		if((check_passwordField.getText().toString().trim().length()>0)) {
			try {
				if(passwordField.getText().equals(check_passwordField.getText())) {
					pwChk.setText("입력한 비밀번호와 일치합니다.");
					
					ClientNet.getInstance().sendPacket(packet);
				}else {
					pwChk.setText("입력한 비밀번호와 동일하게 입력하세요");
					
				}
				
			} catch (Exception e2) {
				
			}
		}
	}
}