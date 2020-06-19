package client_p.ui_p;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class SignUpMain extends JFrame {

	private JPanel mainPane;
	private JTextField nameTextField;
	private JTextField idTextField;
	private JTextField phoneNumTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JButton btn;
	String text="";
	private JTextField currentTextField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpMain frame = new SignUpMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	class MyAdapter extends MouseAdapter		// 마우스로 id or pw TextField 클릭시 적용
	{
		@Override
		public void mouseClicked(MouseEvent e) {
			
		if(	currentTextField != (JTextField) e.getSource())
			text = "";
			currentTextField = (JTextField) e.getSource();
		}
	}


	public SignUpMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 1000);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
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
		nameTextField.addMouseListener(new MyAdapter());
		
		idTextField = new JTextField();
		idTextField.setColumns(10);
		idTextField.setBounds(381, 159, 191, 33);
		mainPane.add(idTextField);
		idTextField.addMouseListener(new MyAdapter());
		
		phoneNumTextField = new JTextField();
		phoneNumTextField.setColumns(10);
		phoneNumTextField.setBounds(383, 309, 191, 33);
		mainPane.add(phoneNumTextField);
		phoneNumTextField.addMouseListener(new MyAdapter());
		
		JButton signUpBtn = new JButton("회원가입");
		signUpBtn.setBounds(296, 368, 140, 42);
		mainPane.add(signUpBtn);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.setBounds(494, 368, 140, 42);
		mainPane.add(cancelBtn);
		
		JButton phoneNumChkBtn = new JButton("인증하기");
		phoneNumChkBtn.setBounds(584, 309, 105, 33);
		mainPane.add(phoneNumChkBtn);
		
		JButton idChkBtn = new JButton("중복확인");
		idChkBtn.setBounds(584, 159, 105, 33);
		mainPane.add(idChkBtn);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(381, 210, 191, 33);
		mainPane.add(passwordField);
		passwordField.addMouseListener(new MyAdapter());
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(381, 257, 191, 33);
		mainPane.add(passwordField_1);
		passwordField_1.addMouseListener(new MyAdapter());
		
		JPanel keybordPane = new JPanel();
		keybordPane.setBounds(12, 431, 860, 300);
		mainPane.add(keybordPane);
		
		
		String firstRow[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",  "BackSpace" };
		String secondRow[] = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P" };
		String thirdRow[] = {  "blank","A", "S", "D", "F", "G", "H", "J", "K", "L" };
		String fourthRow[] = { "blank","blank","Z", "X", "C", "V", "B", "N", "M" };
//		String fifthRow[] = { "blank", "blank", "fill", "fill", "fill", "fill", "fill", "fill", "fill", "fill", "", "<",
//				"v", ">" };

		// Jbuttons corresponding to each individual rows
		JButton first[];
		JButton second[];
		JButton third[];
		JButton fourth[];
		JButton fifth[];

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
//		fifth = new JButton[fifthRow.length];

		addKeys(jpKeyboard, 0, firstRow, first);
		addKeys(jpKeyboard, 1, secondRow, second);
		addKeys(jpKeyboard, 2, thirdRow, third);
		addKeys(jpKeyboard, 3, fourthRow, fourth);
//		addKeys(jpKeyboard, 4, fifthRow, fifth);

		keybordPane.add(jpKeyboard);
		
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
				//System.out.println("Add " + key);
				btn = new JButton(key);
				btn.addActionListener(new BtnAct());
				buttons[index] = btn;
				parent.add(btn, gbc);
				gbc.gridx += gap + 1;
				gbc.gridwidth = 1;
				gap = 0;
			}
		}		
	}
	
	class BtnAct implements ActionListener{		//버튼 액션에 대한 이너클래스
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton keyPoint = (JButton)e.getSource();
			if(keyPoint.getText() !="BackSpace") {
				text += keyPoint.getText();
			}
			else if(keyPoint.getText()=="BackSpace")
				textBack();
		
			currentTextField.setText(text);	
		}
		
		void textBack()
		{
			if(text.length() > 0)
			text =  text.substring(0,text.length()-1);
		}
	}

}
