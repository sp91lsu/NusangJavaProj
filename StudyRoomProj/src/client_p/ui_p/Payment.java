package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import data_p.product_p.DataManager;

public class Payment extends JFrame {

	private JPanel MainPane;
	JLabel titelLabel;
	JLabel useInfo;
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String current_day = date.format((Calendar.getInstance().getTime()));
	ArrayList<MyCheckBox> checkBoxList = new ArrayList<Payment.MyCheckBox>();
	ArrayList<Calendar> timeList = new ArrayList<Calendar>();
	JLabel priceLabel;
	JComboBox personCntChoice;
	JButton payButton;
	int totPrice = 0;

	class MyCheckBox {
		JCheckBox box;
		int value;

		public MyCheckBox(JCheckBox box, int value) {
			super();
			this.box = box;
			this.value = value;
		}
	}

	public Payment() {
		setBounds(650, 200, 600, 700);
		MainPane = new JPanel();
		setContentPane(MainPane);
		MainPane.setLayout(null);

		JPanel infoPane = new JPanel();
		infoPane.setBackground(new Color(240, 240, 240));
		infoPane.setBounds(12, 10, 560, 355);
		MainPane.add(infoPane);
		infoPane.setLayout(null);

		titelLabel = new JLabel("����â");
		titelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titelLabel.setFont(new Font("���� ���", Font.BOLD, 20));
		titelLabel.setBounds(142, 10, 269, 47);
		infoPane.add(titelLabel);

		JPanel timeChKPane = new JPanel();
		timeChKPane.setBounds(50, 67, 458, 257);
		infoPane.add(timeChKPane);
		timeChKPane.setForeground(Color.WHITE);
		timeChKPane.setLayout(new GridLayout(4, 6));
		useInfo = new JLabel();
		useInfo.setBackground(new Color(240, 240, 240));
		useInfo.setFont(new Font("����", Font.PLAIN, 14));
		useInfo.setBounds(12, 88, 536, 161);
		// infoPane.add(useInfo);

		JPanel centerPane = new JPanel();
		centerPane.setBounds(12, 382, 560, 130);
		MainPane.add(centerPane);
		centerPane.setLayout(null);
		
		// �ð� ���ùڽ� JCheckBox ����
		for (int i = 0; i < 24; i++) {

			DecimalFormat format = new DecimalFormat("00:");
			int text = i;
			int realtime = i;
			MyCheckBox myBox1 = new MyCheckBox(new JCheckBox(format.format(text) + "00"), realtime);
			myBox1.box.addActionListener(new AddTimeActionListener(myBox1.value));
			checkBoxList.add(myBox1);
			timeChKPane.add(myBox1.box);
		}

		// �ο�����
		Vector<Integer> personCnt = new Vector<Integer>();
		for (int i = 0; i <= 10; i++) {
			personCnt.add(i);
		}
		priceLabel = new JLabel("�����ݾ�");
		priceLabel.setBounds(95, 35, 268, 60);
		centerPane.add(priceLabel);
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setFont(new Font("����", Font.PLAIN, 20));

		JLabel wonLabel = new JLabel("��");
		wonLabel.setBounds(375, 35, 86, 60);
		centerPane.add(wonLabel);

		JPanel payPane = new JPanel();
		payPane.setBounds(12, 522, 560, 130);
		MainPane.add(payPane);
		payPane.setLayout(null);

		payButton = new JButton("��          ��");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().setCurrentRoomInfo(timeList);
				//BaseFrame.getInstance().paymentPop.setVisible(true);
	
				dispose();
				
			}
		});
		payButton.setFont(new Font("���� ���", Font.BOLD, 18));
		payButton.setBounds(165, 25, 179, 83);
		payPane.add(payButton);
		payButton.setEnabled(false);
		personCntChoice = new JComboBox(personCnt);
		personCntChoice.setBounds(24, 40, 74, 45);
		personCntChoice.setSelectedIndex(0);
		payPane.add(personCntChoice);

		JLabel cnt = new JLabel("��");
		cnt.setBounds(110, 40, 34, 50);
		payPane.add(cnt);

		JButton cancelButton = new JButton("���");
		cancelButton.setFont(new Font("���� ���", Font.BOLD, 18));
		cancelButton.setBounds(356, 25, 179, 83);
		payPane.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				priceLabel.setText("�̿��: 0");
				personCntChoice.setSelectedIndex(0);
				payButton.setEnabled(false);
				timeList.clear();
			}
		});
		setVisible(false);
	}

	class AddTimeActionListener implements ActionListener {

		int value;

		AddTimeActionListener(int value) {
			this.value = value;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox cBox = (JCheckBox) e.getSource();

			System.out.println(value);

			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.HOUR_OF_DAY, value);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);

			//�ð� üũ��
			if (cBox.isSelected()) {
				System.out.println("Ÿ�� �߰��ϱ�");
				timeList.add(cal);
				totPrice = timeList.size()
						* (int) DataManager.getInstance().roomMap.get(BaseFrame.getInstance().roomProduct.id).price;
				priceLabel.setText(totPrice + "");
				payButton.setEnabled(true);
				for (int i = 0; i < checkBoxList.size(); i++) {
					MyCheckBox mBox = checkBoxList.get(i);
					if (mBox.box == cBox) {
						mBox.box.setEnabled(true);
						if (i + 1 < checkBoxList.size()) {
							checkBoxList.get(i + 1).box.setEnabled(true);

							// ����ð��밡 ��ĥ������ ��ư ��Ȱ��ȭ
							Calendar cal2 = Calendar.getInstance();
							ArrayList<Integer> checkList = BaseFrame.getInstance()
									.getCheckList(cal2.get(Calendar.MONTH), cal2.get(Calendar.DATE));
							for (Integer j : checkList) {
								if (checkBoxList.get(i + 1).value == j) {
									checkBoxList.get(i + 1).box.setEnabled(false);
								}
							}
						}
						i++;
					} else {
						mBox.box.setEnabled(false);
					}
				}
				
			} else {	//�ð� üũ ����
				System.out.println("Ÿ�� �����ϱ�");
				for (int i = 0; i < timeList.size(); i++) {
					Calendar cal3 = timeList.get(i);
					if (cal3.get(Calendar.HOUR_OF_DAY) == value) {
						timeList.remove(cal3);
						totPrice = timeList.size() * (int) DataManager.getInstance().roomMap
								.get(BaseFrame.getInstance().roomProduct.id).price;
						priceLabel.setText(totPrice + "");
					}
				}
				Calendar cal4 = Calendar.getInstance();
				for (int i = checkBoxList.size() - 1; i > cal4.get(Calendar.HOUR_OF_DAY) + 1; i--) {
					MyCheckBox mBox = checkBoxList.get(i);
					if (mBox.box == cBox) {
						if (i - 1 >= 0) {
							checkBoxList.get(i - 1).box.setEnabled(true);
							// ����ð��밡 ��ĥ������ ��ư ��Ȱ��ȭ
							Calendar cal5 = Calendar.getInstance();
							ArrayList<Integer> checkList = BaseFrame.getInstance()
									.getCheckList(cal5.get(Calendar.MONTH), cal5.get(Calendar.DATE));
							for (Integer j : checkList) {
								if (checkBoxList.get(i - 1).value == j) {
									checkBoxList.get(i - 1).box.setEnabled(false);
								}
							}
							i--;
						}
					} else {
						mBox.box.setEnabled(false);
					}
				}
				if (timeList.isEmpty()) {
					payButton.setEnabled(false);
					resPossibleChk();
				}
			}
		}
	}

	public void resPossibleChk() {
		totPrice = 0;
		for (MyCheckBox myCheckBox : checkBoxList) {
			myCheckBox.box.setSelected(false);
			myCheckBox.box.setEnabled(true);
		}
		Calendar cal = Calendar.getInstance();
		ArrayList<Integer> checkList = BaseFrame.getInstance().getCheckList(cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE));
		for (MyCheckBox myCheckBox : checkBoxList) {
			for (Integer i : checkList) {
				if (myCheckBox.value == i) {
					myCheckBox.box.setEnabled(false);
				}
			}
			if (myCheckBox.value < cal.get(Calendar.HOUR_OF_DAY)) {
				myCheckBox.box.setEnabled(false);
			}
		}
	}

	public void openPage() {
		setVisible(true);
		System.out.println("openPage");
		if(BaseFrame.getInstance().getUsingRoom()==null)
		{
			useInfo = new JLabel("<html>�̸�: ȫ�浿<br>" + "�����¼�:  " + BaseFrame.getInstance().roomProduct.name + "<br>"
					+ "�Խǽð�:" + current_day + "(16:00)<br>" + "��ǿ���:" + current_day + "(18:00)<br><br>"
					+ "*������ 1�ð� ������ �����մϴ�.<html>");
		}
		else if(BaseFrame.getInstance().roomProduct==null)
		{
			useInfo = new JLabel("<html>�̸�: ȫ�浿<br>" + "�����¼�:  " + BaseFrame.getInstance().getUsingRoom().name + "<br>"
					+ "�Խǽð�:" + current_day + "(16:00)<br>" + "��ǿ���:" + current_day + "(18:00)<br><br>"
					+ "*������ 1�ð� ������ �����մϴ�.<html>");
		}
	}
	
	public void updatePayment()
	{
//		JOptionPane.showMessageDialog(null, "�¼���Ȳ�� ������Ʈ�Ǿ����ϴ�.");
		resPossibleChk();
		System.out.println("�߰��� ������");
	}
}