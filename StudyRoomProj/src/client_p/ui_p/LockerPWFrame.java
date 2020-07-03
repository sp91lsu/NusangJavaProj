package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import data_p.product_p.LockerData;

public class LockerPWFrame extends JDialog implements ActionListener {

	JLabel passwordField;
	private LockerData lockerData;
	String text = "";
	String pw = "";

	public LockerPWFrame() {
		setModal(true);
		setBounds(710, 315, 500, 450);
		getContentPane().setBackground(MyColor.black);
		getContentPane().setLayout(null);

		passwordField = new JLabel();
		passwordField.setForeground(Color.WHITE);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("굴림", Font.BOLD, 40));
		passwordField.setBounds(77, 53, 273, 74);
		getContentPane().add(passwordField);

		ButtonGroup bg = new ButtonGroup();

		JButton button1 = new JButton("1");
		button1.setBounds(39, 142, 129, 55);
		getContentPane().add(button1);
		bg.add(button1);
		button1.addActionListener(this);
		button1.setBackground(MyColor.w_white);

		JButton button2 = new JButton("2");
		button2.setBounds(169, 142, 129, 55);
		getContentPane().add(button2);
		bg.add(button2);
		button2.addActionListener(this);
		button2.setBackground(MyColor.w_white);

		JButton button3 = new JButton("3");
		button3.setBounds(300, 142, 129, 55);
		getContentPane().add(button3);
		bg.add(button3);
		button3.addActionListener(this);
		button3.setBackground(MyColor.w_white);

		JButton button4 = new JButton("4");
		button4.setBounds(300, 200, 129, 55);
		getContentPane().add(button4);
		bg.add(button4);
		button4.addActionListener(this);
		button4.setBackground(MyColor.w_white);

		JButton button5 = new JButton("5");
		button5.setBounds(169, 200, 129, 55);
		getContentPane().add(button5);
		bg.add(button5);
		button5.addActionListener(this);
		button5.setBackground(MyColor.w_white);

		JButton button6 = new JButton("6");
		button6.setBounds(39, 200, 129, 55);
		getContentPane().add(button6);
		bg.add(button6);
		button6.addActionListener(this);
		button6.setBackground(MyColor.w_white);

		JButton button7 = new JButton("7");
		button7.setBounds(300, 257, 129, 55);
		getContentPane().add(button7);
		bg.add(button7);
		button7.addActionListener(this);
		button7.setBackground(MyColor.w_white);

		JButton button8 = new JButton("8");
		button8.setBounds(169, 257, 129, 55);
		getContentPane().add(button8);
		bg.add(button8);
		button8.addActionListener(this);
		button8.setBackground(MyColor.w_white);

		JButton button9 = new JButton("9");
		button9.setBounds(39, 257, 129, 55);
		getContentPane().add(button9);
		bg.add(button9);
		button9.addActionListener(this);
		button9.setBackground(MyColor.w_white);

		JButton back = new JButton("<");
		back.setBounds(364, 61, 65, 66);
		getContentPane().add(back);
		bg.add(back);
		back.addActionListener(this);
		back.setBackground(MyColor.w_white);

		JButton ok_Button = new JButton("확인");
		ok_Button.setBounds(111, 324, 129, 55);
		getContentPane().add(ok_Button);
		ok_Button.addActionListener(this);
		ok_Button.setBackground(MyColor.w_white);

		JButton cancel_Button = new JButton("취소");
		cancel_Button.setBounds(248, 324, 129, 55);
		getContentPane().add(cancel_Button);
		cancel_Button.addActionListener(this);
		cancel_Button.setBackground(MyColor.w_white);

		JLabel lblNewLabel = new JLabel("비밀번호 4자리를 입력하세요");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(28, 0, 421, 49);
		lblNewLabel.setForeground(Color.white);
		getContentPane().add(lblNewLabel);

		setVisible(false);
		setUndecorated(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton keyPoint = (JButton) e.getSource();
		if (passwordField.getText().length() < 4 && pw.length() < 4) {
			if (!keyPoint.getText().equals("확인") && !keyPoint.getText().equals("취소")
					&& !keyPoint.getText().equals("<")) {
				text += "*";
				pw += keyPoint.getText();
			}
		}

		if (keyPoint.getText().equals("확인") && passwordField.getText().length() == 4) {
			lockerData.setPW(pw);
			LockerCalcFrame lc = new LockerCalcFrame(lockerData);
			setVisible(false);
			text_Set();
		} else if (keyPoint.getText().equals("취소")) {
			setVisible(false);
			text_Set();
		}
		if (keyPoint.getText().equals("<")) {
			textBack();
		}
		passwordField.setText(text);
	}

	void textBack() {
		if (text.length() > 0 && pw.length() > 0) {
			text = text.substring(0, text.length() - 1);
			pw = pw.substring(0, pw.length() - 1);
		}
	}

	void text_Set()
	{
		text="";
		pw="";
	}
	public void openLockerPWFrame(LockerData lockerData) {
		text = "";
		passwordField.setText(text);
		this.lockerData = lockerData;
		setVisible(true);
	}
}