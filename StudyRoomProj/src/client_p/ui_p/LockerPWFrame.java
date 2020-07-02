package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import data_p.product_p.LockerData;

public class LockerPWFrame extends JDialog implements ActionListener {

	JPasswordField passwordField;
	private LockerData lockerData;
	String text = "";

	public LockerPWFrame() {
		setModal(true);
		setBounds(100, 100, 500, 450);
		getContentPane().setBackground(MyColor.black);
		getContentPane().setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("����", Font.BOLD, 40));
		passwordField.setBounds(77, 53, 320, 74);
		getContentPane().add(passwordField);

		ButtonGroup bg = new ButtonGroup();

		JButton btnNewButton = new JButton("1");
		btnNewButton.setBounds(39, 142, 129, 55);
		getContentPane().add(btnNewButton);
		bg.add(btnNewButton);
		btnNewButton.addActionListener(this);
		btnNewButton.setBackground(MyColor.w_white);

		JButton button = new JButton("2");
		button.setBounds(169, 142, 129, 55);
		getContentPane().add(button);
		bg.add(button);
		button.addActionListener(this);
		button.setBackground(MyColor.w_white);

		JButton button_1 = new JButton("3");
		button_1.setBounds(300, 142, 129, 55);
		getContentPane().add(button_1);
		bg.add(button_1);
		button_1.addActionListener(this);
		button_1.setBackground(MyColor.w_white);

		JButton button_2 = new JButton("4");
		button_2.setBounds(300, 200, 129, 55);
		getContentPane().add(button_2);
		bg.add(button_2);
		button_2.addActionListener(this);
		button_2.setBackground(MyColor.w_white);

		JButton button_3 = new JButton("5");
		button_3.setBounds(169, 200, 129, 55);
		getContentPane().add(button_3);
		bg.add(button_3);
		button_3.addActionListener(this);
		button_3.setBackground(MyColor.w_white);

		JButton button_4 = new JButton("6");
		button_4.setBounds(39, 200, 129, 55);
		getContentPane().add(button_4);
		bg.add(button_4);
		button_4.addActionListener(this);
		button_4.setBackground(MyColor.w_white);

		JButton button_5 = new JButton("7");
		button_5.setBounds(300, 257, 129, 55);
		getContentPane().add(button_5);
		bg.add(button_5);
		button_5.addActionListener(this);
		button_5.setBackground(MyColor.w_white);

		JButton button_6 = new JButton("8");
		button_6.setBounds(169, 257, 129, 55);
		getContentPane().add(button_6);
		bg.add(button_6);
		button_6.addActionListener(this);
		button_6.setBackground(MyColor.w_white);

		JButton button_7 = new JButton("9");
		button_7.setBounds(39, 257, 129, 55);
		getContentPane().add(button_7);
		bg.add(button_7);
		button_7.addActionListener(this);
		button_7.setBackground(MyColor.w_white);

		JButton ok_Button = new JButton("Ȯ��");
		ok_Button.setBounds(111, 324, 129, 55);
		getContentPane().add(ok_Button);
		ok_Button.addActionListener(this);
		ok_Button.setBackground(MyColor.w_white);

		JButton button_9 = new JButton("���");
		button_9.setBounds(248, 324, 129, 55);
		getContentPane().add(button_9);
		button_9.addActionListener(this);
		button_9.setBackground(MyColor.w_white);

		JLabel lblNewLabel = new JLabel("��й�ȣ 4�ڸ��� �Է��ϼ���");
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
		if (passwordField.getText().length() < 4) {
			if (keyPoint.getText() != "BackSpace" && keyPoint.getText() != "Ȯ��" && keyPoint.getText() != "���") {
				text += keyPoint.getText();
			} else if (keyPoint.getText() == "BackSpace")
				textBack();

			passwordField.setText(text);
		}

		if (keyPoint.getText().equals("Ȯ��")) {
			lockerData.setPW(passwordField.getText());
			LockerCalcFrame lc = new LockerCalcFrame(lockerData);
			setVisible(false);
			System.out.println("Ȯ��");
		} else if (keyPoint.getText().equals("���")) {
			setVisible(false);
			System.out.println("���");
		}
	}

	void textBack() {
		if (text.length() > 0)
			text = text.substring(0, text.length() - 1);
	}

	public void openLockerPWFrame(LockerData lockerData) {
		text = "";
		passwordField.setText(text);
		this.lockerData = lockerData;
		setVisible(true);
	}
}