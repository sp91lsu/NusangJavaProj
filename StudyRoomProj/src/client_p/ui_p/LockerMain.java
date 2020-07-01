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
		btn.setBackground(new Color(139, 69, 19));
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
				"<html>�繰�� �̿� �ȳ�<br>�̿� ��� : 1000��<br>" + "�뿩 �ð��� <br>�¼�/�� �̿� �ð���<br> �����մϴ�.<html>");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(65, 5, 128, 90);
		infoPanel.add(infoLabel);

		JButton pwSetting = new JButton("��й�ȣ ����");
		pwSetting.setBounds(200, 10, 120, 100);
		infoPanel.add(pwSetting);
		pwSetting.addActionListener(this);

		JButton cancelButton = new JButton("���");
		cancelButton.setBounds(350, 10, 100, 100);
		infoPanel.add(cancelButton);
		cancelButton.addActionListener(this);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn.getText().equals("��й�ȣ ����") && currentData != null) {
			lp.openLockerPWFrame(currentData);
		} 
		else if (btn.getText().equals("���")) {
			BaseFrame.getInstance().openMainLayout(null, null, null, null);
			lp.setVisible(false);
		}
		
		lockerNum = btn.getText();

		for (int i = 0; i < list.size(); i++) {

			JButton listBtn = list.get(i).btn;

			if (e.getSource().equals(listBtn)) {
				listBtn.setBackground(new Color(255, 54, 54));
				currentData = list.get(i).data;
			} else {
				listBtn.setBackground(new Color(139, 69, 19));
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