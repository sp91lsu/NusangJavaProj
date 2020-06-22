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
import client_p.packet_p.syn_p.CsSignUpSyn;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScSignUpAck;

public class SignUpMain extends JFrame implements Receivable{

	private JTextField nameTextField;
	private JTextField idTextField;
	private JTextField phoneNumTextField;
	private JPasswordField passwordField;
	private JPasswordField check_passwordField;
	private JTextField currentTextField;
	
	JDialog jd;
	JLabel pwChk;
	String text="";
	
	ArrayList<JTextField> textList = new ArrayList<JTextField>();
	ArrayList<JPasswordField> pTextList = new ArrayList<JPasswordField>();
	
//	public static void main(String[] args) {
//		SignUpMain frame = new SignUpMain();
//	}
	
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
		idL.setBounds(280, 155, 77, 42);
		mainPane.add(idL);
		
		JLabel nameL = new JLabel("�����̸�");
		nameL.setBounds(280, 103, 77, 42);
		mainPane.add(nameL);
		
		JLabel pwL = new JLabel("��й�ȣ");
		pwL.setBounds(278, 196, 77, 42);
		mainPane.add(pwL);
		
		JLabel pwChkL = new JLabel("��й�ȣȮ��");
		pwChkL.setBounds(280, 248, 89, 42);
		mainPane.add(pwChkL);
		
		JLabel phonNumL = new JLabel("�޴��� ��ȣ");
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
		
		JButton signUpBtn = new JButton("ȸ������");
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
		
		JButton cancelBtn = new JButton("���");
		cancelBtn.setBounds(494, 368, 140, 42);
		mainPane.add(cancelBtn);
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		JButton idChkBtn = new JButton("ID �ߺ�Ȯ��");
		idChkBtn.setBounds(584, 159, 105, 33);
		mainPane.add(idChkBtn);
		
		pwChk = new JLabel("�Է��� ��й�ȣ�� ���� �ϰ� �Է� �ϼ���");
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
		
		ScSignUpAck ack = (ScSignUpAck) packet;

		
		if (ack.eResult == EResult.SUCCESS) {
			jd = new JDialog();
			jd.setBounds(50, 50, 150, 150);
			jd.setLayout(new GridLayout(2,1));
			JLabel jl = new JLabel("ȸ������ �Ϸ�");
			JButton jb = new JButton("Ȯ��");
			jb.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JButton bbb = (JButton)e.getSource();
					if(bbb.getText().equals("Ȯ��")) {
						jd.setVisible(false);
						setVisible(false);
					}}});
			
			jd.add(jl);
			jd.add(jb);
			jd.setVisible(true);
		}
	}

	void check()
	{
		CsSignUpSyn packet =new CsSignUpSyn(nameTextField.getText(), idTextField.getText(), passwordField.getText(), check_passwordField.getText(), " ", "");
		
		passwordField.getText().trim();
		check_passwordField.getText().trim();
		
		if((check_passwordField.getText().toString().trim().length()>0)) {
			try {
				if(passwordField.getText().equals(check_passwordField.getText())) {
					pwChk.setText("�Է��� ��й�ȣ�� ��ġ�մϴ�.");
					
					ClientNet.getInstance().sendPacket(packet);
				}else {
					pwChk.setText("�Է��� ��й�ȣ�� �����ϰ� �Է��ϼ���");
					
				}
				
			} catch (Exception e2) {
				
			}
		}
	}
}