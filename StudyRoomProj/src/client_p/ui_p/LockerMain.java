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
import java.awt.Font;

class LockerBtn {
	LockerData data;
	JButton btn;

	public LockerBtn(LockerData data, JButton btn) {
		super();

		this.data = data;
		this.btn = btn;
		btn.setBackground(Color.white);
		btn.setText(data.name);
	}
}

public class LockerMain extends JPanel implements ActionListener {

	ArrayList<LockerBtn> list = new ArrayList<LockerBtn>();
	LockerData currentData = null;
	LockerPWFrame lp = new LockerPWFrame();
	String lockerNum = "123";

	public LockerMain() {
		setLayout(null);
		setBackground(MyColor.black);
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(60, 30, 800, 500);
		mainPanel.setBackground(MyColor.black);
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
			btn.setBounds(x * 250, y * 150, 250, 133);
			mainPanel.add(btn);
			bg.add(btn);
			btn.addActionListener(this);
			LockerBtn lBtn = new LockerBtn(data, btn);
			list.add(lBtn);

			x++;
		}

		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(80, 542, 700, 186);
		add(infoPanel);
		infoPanel.setLayout(null);
		infoPanel.setBackground(MyColor.black);

		JLabel infoLabel = new JLabel(
				"<html>사물함 이용 안내<br>이용 요금 : 1000원<br>" + "대여 시간은 <br>좌석/룸 이용 시간과<br> 동일합니다.<html>");
		infoLabel.setFont(new Font("굴림", Font.BOLD, 18));
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(28, 24, 216, 139);
		infoLabel.setForeground(Color.white);
		infoPanel.add(infoLabel);

		JButton pwSetting = new JButton("비밀번호 설정");
		pwSetting.setFont(new Font("굴림", Font.BOLD, 20));
		pwSetting.setBounds(307, 24, 191, 139);
		pwSetting.setBackground(MyColor.w_white);
		infoPanel.add(pwSetting);
		pwSetting.addActionListener(this);

		JButton cancelButton = new JButton("취소");
		cancelButton.setFont(new Font("굴림", Font.BOLD, 20));
		cancelButton.setBounds(529, 24, 141, 139);
		cancelButton.setBackground(MyColor.w_white);
		infoPanel.add(cancelButton);
		cancelButton.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn.getText().equals("비밀번호 설정") && currentData != null) {
			lp.openLockerPWFrame(currentData);
		} 
		else if (btn.getText().equals("취소")) {
			BaseFrame.getInstance().openMainLayout(null, null, null, null);
			lp.setVisible(false);
		}
		
		lockerNum = btn.getText();

		for (int i = 0; i < list.size(); i++) {

			JButton listBtn = list.get(i).btn;

			if (e.getSource().equals(listBtn)) {
				listBtn.setBackground(Color.red);
				currentData = list.get(i).data;
			} else {
				listBtn.setBackground(Color.white);
			}

		}
	}

	public void updateLocker() {
		for (LockerBtn lockerbtn : list) {
			for (LockerData lockerData : BaseFrame.getInstance().lockerlist) {

				if (lockerbtn.data.id.equals(lockerData.id)) {
					lockerbtn.btn.setBackground(null);
					lockerbtn.btn.setEnabled(false);
					break;
				} else {
					lockerbtn.btn.setBackground(new Color(139, 69, 19));
					lockerbtn.btn.setEnabled(true);
				}
			}
		}
	}
}