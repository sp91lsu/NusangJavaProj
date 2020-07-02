package client_p.ui_p;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data_p.product_p.DataManager;
import data_p.product_p.LockerData;
import java.awt.Font;
import java.awt.GridLayout;

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

public class LockerMain extends JPanel {

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
			btn.addActionListener(new ButtonListener(data));
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
		pwSetting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (currentData != null) {
					lp.openLockerPWFrame(currentData);
				} else {
					JDialog lockerDialog = new JDialog();
					lockerDialog.setLayout(new GridLayout(2, 1));
					lockerDialog.setBounds(850, 400, 200, 200);
					lockerDialog.setUndecorated(true);
					lockerDialog.getContentPane().setBackground(MyColor.black);
					JLabel lockerLabel = new JLabel("구매할 사물함을 선택해 주세요");
					lockerLabel.setHorizontalAlignment(SwingConstants.CENTER);
					lockerDialog.add(lockerLabel);
					lockerLabel.setForeground(Color.white);
					JButton closeButton = new JButton("확인");
					lockerDialog.add(closeButton);
					closeButton.setBackground(MyColor.w_white);
					closeButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							lockerDialog.dispose();

						}
					});
					lockerDialog.setModal(true);
					lockerDialog.setVisible(true);
				}

			}
		});

		JButton cancelButton = new JButton("취소");
		cancelButton.setFont(new Font("굴림", Font.BOLD, 20));
		cancelButton.setBounds(529, 24, 141, 139);
		cancelButton.setBackground(MyColor.w_white);
		infoPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().openMainLayout(null, null, null, null);
				lp.setVisible(false);

			}
		});

		setVisible(true);
	}

	class ButtonListener implements ActionListener {
		LockerData locker;

		public ButtonListener(LockerData locker) {
			this.locker = locker;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();

			lockerNum = btn.getText();
			updateLocker(locker);
			btn.setBackground(Color.red);

		}

	}

	public void updateLocker(LockerData data) {

		currentData = data;
	
		for (LockerBtn lockerbtn : list) {
			lockerbtn.btn.setBackground(Color.white);
			lockerbtn.btn.setEnabled(true);
		}

		for (LockerBtn lockerbtn : list) {
			for (LockerData lockerData : BaseFrame.getInstance().lockerlist) {

				if (lockerbtn.data.id.equals(lockerData.id)) {
					lockerbtn.btn.setBackground(null);
					lockerbtn.btn.setEnabled(false);
					break;
				}
			}
		}
	}
}