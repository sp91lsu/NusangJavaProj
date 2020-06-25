package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import data_p.product_p.LockerData;

public class LockerPWFrame extends JFrame implements ActionListener{

	private JPasswordField passwordField;
	private LockerData lockerData;
	public LockerPWFrame(LockerData lockerData) {
		this.lockerData = lockerData;
		setBounds(100, 100, 500, 450);
		getContentPane().setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("굴림", Font.BOLD, 40));
		passwordField.setBounds(77, 53, 320, 74);
		getContentPane().add(passwordField);
		
		ButtonGroup bg = new ButtonGroup();
		
		JButton btnNewButton = new JButton("1");
		btnNewButton.setBounds(39, 142, 129, 55);
		getContentPane().add(btnNewButton);
		bg.add(btnNewButton);
		btnNewButton.addActionListener(this);
		
		
		JButton button = new JButton("2");
		button.setBounds(169, 142, 129, 55);
		getContentPane().add(button);
		bg.add(button);
		button.addActionListener(this);
		
		JButton button_1 = new JButton("3");
		button_1.setBounds(300, 142, 129, 55);
		getContentPane().add(button_1);
		bg.add(button_1);
		button_1.addActionListener(this);
		
		JButton button_2 = new JButton("4");
		button_2.setBounds(300, 200, 129, 55);
		getContentPane().add(button_2);
		bg.add(button_2);
		button_2.addActionListener(this);
		
		JButton button_3 = new JButton("5");
		button_3.setBounds(169, 200, 129, 55);
		getContentPane().add(button_3);
		bg.add(button_3);
		button_3.addActionListener(this);
		
		JButton button_4 = new JButton("6");
		button_4.setBounds(39, 200, 129, 55);
		getContentPane().add(button_4);
		bg.add(button_4);
		button_4.addActionListener(this);
		
		JButton button_5 = new JButton("7");
		button_5.setBounds(300, 257, 129, 55);
		getContentPane().add(button_5);
		bg.add(button_5);
		button_5.addActionListener(this);
		
		JButton button_6 = new JButton("8");
		button_6.setBounds(169, 257, 129, 55);
		getContentPane().add(button_6);
		bg.add(button_6);
		button_6.addActionListener(this);
		
		JButton button_7 = new JButton("9");
		button_7.setBounds(39, 257, 129, 55);
		getContentPane().add(button_7);
		bg.add(button_7);
		button_7.addActionListener(this);
		
		JButton button_8 = new JButton("확인");
		button_8.setBounds(111, 324, 129, 55);
		getContentPane().add(button_8);
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LockerCalcFrame lc = new LockerCalcFrame(lockerData);
				System.out.println(passwordField.getText());
				dispose();
			}});
		
		JButton button_9 = new JButton("취소");
		button_9.setBounds(248, 324, 129, 55);
		getContentPane().add(button_9);
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}});
		
		JLabel lblNewLabel = new JLabel("비밀번호 4자리를 입력하세요");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 0, 421, 49);
		getContentPane().add(lblNewLabel);
		
		setVisible(true);
	}
	
	String text = "";

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton keyPoint = (JButton) e.getSource();
		if (passwordField.getText().length() < 4) {

			if (keyPoint.getText() != "BackSpace" && keyPoint.getText() != "확인" 
					&& keyPoint.getText() != "취소") {
				text += keyPoint.getText();
			} else if (keyPoint.getText() == "BackSpace")
				textBack();

			passwordField.setText(text);
		}
	}
      
      void textBack()
      {
         if(text.length() > 0)
         text =  text.substring(0,text.length()-1);
      }
}