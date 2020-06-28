package client_p.ui_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data_p.product_p.DataManager;
import data_p.product_p.LockerData;

class LockerBtn {
	LockerData data;
	JButton btn;

	public LockerBtn(LockerData data, JButton btn) {
		super();

		this.data = data;
		this.btn = btn;

		btn.setText(data.name);
	}
}

public class LockerMain extends JPanel implements ActionListener {

	ArrayList<LockerBtn> list = new ArrayList<LockerBtn>();
	LockerData currentData = null;

	String lockerNum = "123";

	public LockerMain() {
		setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(40, 30, 450, 330);
		add(mainPanel);

		ButtonGroup bg = new ButtonGroup();
		mainPanel.setLayout(null);

		int y = 0;
		int x = 0;
		for (LockerData data : DataManager.getInstance().lockerList) {

			if (x > 2) {
				x = 0;
				y++;
			}
			JButton btn = new JButton();
			btn.setBounds(x * 150, y * 100, 150, 100);
			mainPanel.add(btn);
			bg.add(btn);
			btn.addActionListener(this);
			LockerBtn lBtn = new LockerBtn(data, btn);
			list.add(lBtn);

			x++;
		}

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
				LockerPWFrame lp = new LockerPWFrame(currentData);
			}
		});

		JButton cancelButton = new JButton("취소");
		cancelButton.setBounds(350, 10, 100, 100);
		infoPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().openMainLayout(null, null, null, null);
			}
		});

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		lockerNum = btn.getText();

		for (int i = 0; i < list.size(); i++) {

			JButton listBtn = list.get(i).btn;

			if (e.getSource().equals(listBtn)) {
				listBtn.setBackground(Color.red);
				currentData = list.get(i).data;
			} else {
				listBtn.setBackground(null);
			}

		}
	}

	public void updateLocker(ArrayList<LockerData> lockerList) {

		for (LockerData lockerData : lockerList) {
			for (LockerBtn lockerbtn : list) {
				if (lockerbtn.data.id.equals(lockerData.id)) {
					lockerbtn.btn.setBackground(null);
					lockerbtn.btn.setEnabled(false);
				} else {
					lockerbtn.btn.setBackground(new Color(150, 150, 150));
					lockerbtn.btn.setEnabled(true);
				}
			}
		}
	}
}