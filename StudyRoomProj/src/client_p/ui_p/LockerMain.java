package client_p.ui_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class LockerMain extends JPanel implements ActionListener {

	ArrayList<JButton> list = new ArrayList<JButton>();
	String lockerNum = "123";

	public LockerMain() {
		setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(40, 30, 450, 330);
		add(mainPanel);

		ButtonGroup bg = new ButtonGroup();
		mainPanel.setLayout(null);

		JButton button_1 = new JButton("사물함 1번");
		button_1.setBounds(0, 0, 150, 100);
		mainPanel.add(button_1);
		list.add(button_1);
		bg.add(button_1);

		JButton button_2 = new JButton("사물함 2번");
		button_2.setBounds(150, 0, 150, 100);
		mainPanel.add(button_2);
		list.add(button_2);
		bg.add(button_2);

		JButton button_3 = new JButton("사물함 3번");
		button_3.setBounds(300, 0, 150, 100);
		mainPanel.add(button_3);
		list.add(button_3);
		bg.add(button_3);

		JButton button_4 = new JButton("사물함 4번");
		button_4.setBounds(0, 100, 150, 100);
		mainPanel.add(button_4);
		list.add(button_4);
		bg.add(button_4);

		JButton button_5 = new JButton("사물함 5번");
		button_5.setBounds(150, 100, 150, 100);
		mainPanel.add(button_5);
		list.add(button_5);
		bg.add(button_5);

		JButton button_6 = new JButton("사물함 6번");
		button_6.setBounds(300, 100, 150, 100);
		mainPanel.add(button_6);
		list.add(button_6);
		bg.add(button_6);

		JButton button_7 = new JButton("사물함 7번");
		button_7.setBounds(0, 200, 150, 100);
		mainPanel.add(button_7);
		list.add(button_7);
		bg.add(button_7);

		JButton button_8 = new JButton("사물함 8번");
		button_8.setBounds(150, 200, 150, 100);
		mainPanel.add(button_8);
		list.add(button_8);
		bg.add(button_8);

		JButton button_9 = new JButton("사물함 9번");
		button_9.setBounds(300, 200, 150, 100);
		mainPanel.add(button_9);
		list.add(button_9);
		bg.add(button_9);

		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(0, 400, 450, 330);
		add(infoPanel);
		infoPanel.setLayout(null);

		JLabel infoLabel = new JLabel(
				"<html>사물함 이용 안내<br>이용 요금 : 1000원<br>" + "대여 시간은 <br>좌석/룸 이용 시간과<br> 동일합니다.<html>");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(65, 5, 128, 90);
		infoPanel.add(infoLabel);

		JButton pwSetting = new JButton("비밀번호 설정");
		pwSetting.setBounds(200, 10, 120, 100);
		infoPanel.add(pwSetting);
		pwSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LockerPWFrame lp = new LockerPWFrame(lockerNum);
			}
		});

		JButton cancelButton = new JButton("취소");
		cancelButton.setBounds(350, 10, 100, 100);
		infoPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().view("MainLayout");
			}
		});

		for (JButton jbt : list) {
			jbt.addActionListener(this);
		}

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		lockerNum = btn.getText();
		for (JButton jbtt : list) {
			if (e.getSource().equals(jbtt)) {
				jbtt.setBackground(Color.red);
			} else {
				jbtt.setBackground(null);
			}
		}
	}
}