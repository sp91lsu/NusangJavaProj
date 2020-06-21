package client_p.ui_p;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
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
	
	private JTextField currentTextField;
	String text = "";

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

		JTextField idTextF = new JTextField();
		idTextF.setText(" or 핸드폰번호 입력( '-' 없이 입력)");
		idTextF.setToolTipText("");
		idTextF.setFont(new Font("맑은 고딕", Font.ITALIC, 14));
		idTextF.setBounds(323, 156, 328, 55);
		idTextF.addMouseListener(new MyAdapter());
		add(idTextF);
		idTextF.setColumns(10);
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		lblPw.setBounds(251, 236, 51, 54);
		add(lblPw);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(323, 236, 328, 54);
		passwordField.addMouseListener(new MyAdapter());
		add(passwordField);

		JButton logInBtn = new JButton("로그인");
		logInBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		logInBtn.setBounds(323, 321, 120, 45);
		add(logInBtn);
		logInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CsLoginSyn packet = new CsLoginSyn(idTextF.getText(), passwordField.getText(), true);
				ClientNet.getInstance().sendPacket(packet);
			}});
		
		JButton signUpBt = new JButton("회원가입");
		signUpBt.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		signUpBt.setBounds(508, 321, 120, 45);
		add(signUpBt);
		signUpBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().signUpFrame.setVisible(true);
			}});

		JPanel keybordPane = new JPanel();
		keybordPane.setBounds(12, 413, 860, 300);
		add(keybordPane);

		/////////////////////////////////// 키보드///////////////////////

		// Individual keyboard rows
		String firstRow[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "BackSpace" };
		String secondRow[] = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" };
		String thirdRow[] = { "blank", "A", "S", "D", "F", "G", "H", "J", "K", "L" };
		String fourthRow[] = { "blank", "blank", "Z", "X", "C", "V", "B", "N", "M" };

		JButton first[];
		JButton second[];
		JButton third[];
		JButton fourth[];

		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpKeyboard = new JPanel(new GridBagLayout());
		JPanel jpNote = new JPanel();
		keybordPane.add(jpNorth, BorderLayout.NORTH);
		keybordPane.add(jpNote);
		keybordPane.add(jpCenter, BorderLayout.CENTER);
		keybordPane.add(jpKeyboard, BorderLayout.SOUTH);

		first = new JButton[firstRow.length];
		second = new JButton[secondRow.length];
		third = new JButton[thirdRow.length];
		fourth = new JButton[fourthRow.length];

		addKeys(jpKeyboard, 0, firstRow, first);
		addKeys(jpKeyboard, 1, secondRow, second);
		addKeys(jpKeyboard, 2, thirdRow, third);
		addKeys(jpKeyboard, 3, fourthRow, fourth);

		keybordPane.add(jpKeyboard);
	}
	
	class MyAdapter extends MouseAdapter // 마우스로 id or pw TextField 클릭시 적용
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			if (currentTextField != (JTextField) e.getSource())
				text = "";
			currentTextField = (JTextField) e.getSource();
		}
	}

	void addKeys(JPanel parent, int row, String[] keys, JButton[] buttons) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = row;
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.BOTH;

		int gap = 0;
		for (int index = 0; index < keys.length; index++) {
			String key = keys[index];
			if ("blank".equalsIgnoreCase(key)) {
				gbc.gridx++;
			} else if ("fill".equalsIgnoreCase(key)) {
				gbc.gridwidth++;
				gap++;
			} else {
				// System.out.println("Add " + key);
				JButton btn = new JButton(key);
				btn.addActionListener(new BtnAct());
				buttons[index] = btn;
				parent.add(btn, gbc);
				gbc.gridx += gap + 1;
				gbc.gridwidth = 1;
				gap = 0;
			}
		}
	}

	class BtnAct implements ActionListener { // 버튼 액션에 대한 이너클래스

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton keyPoint = (JButton) e.getSource();
			if (keyPoint.getText() != "BackSpace") {
				text += keyPoint.getText();
			} else if (keyPoint.getText() == "BackSpace")
				textBack();

			currentTextField.setText(text);
		}

		void textBack() {
			if (text.length() > 0) {
				text = text.substring(0, text.length() - 1);
			}
		}
	}

	@Override
	public void receive(PacketBase packet) {

		ScLoginAck ack = (ScLoginAck) packet;

		if (ack.eResult == EResult.SUCCESS) {
			BaseFrame.getInstance().userData = ack.userdata;
			BaseFrame.getInstance().roomInfoList = ack.roomList;
			// BaseFrame.getInstance().updateInfo(ack.roomList);
			if (BaseFrame.getInstance().loginType == ELoginType.KIOSK) {

				BaseFrame.getInstance().view("MainLayout");
			} else if (BaseFrame.getInstance().loginType == ELoginType.MOBILE) {

				BaseFrame.getInstance().view("Seating_Arrangement");
			}
		}
	}
}