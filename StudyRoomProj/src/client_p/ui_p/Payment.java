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
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client_p.Receivable;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;
import javafx.scene.control.CheckBox;
import packetBase_p.PacketBase;

public class Payment extends JFrame {

	private JPanel MainPane;
	JLabel titelLabel;
	JLabel useInfo;
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String current_day = date.format((Calendar.getInstance().getTime()));
	ArrayList<MyCheckBox> checkBoxList = new ArrayList<Payment.MyCheckBox>();
	ArrayList<Calendar> timeList = new ArrayList<Calendar>();

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
		infoPane.setBounds(12, 10, 560, 286);
		MainPane.add(infoPane);
		infoPane.setLayout(null);

		titelLabel = new JLabel("����â");
		titelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titelLabel.setFont(new Font("���� ���", Font.BOLD, 20));
		titelLabel.setBounds(131, 10, 269, 47);
		infoPane.add(titelLabel);
		useInfo = new JLabel();
		useInfo.setBackground(new Color(240, 240, 240));
		useInfo.setFont(new Font("����", Font.PLAIN, 14));
		useInfo.setBounds(12, 88, 536, 161);
		infoPane.add(useInfo);

		JPanel centerPane = new JPanel();
		centerPane.setBounds(12, 306, 560, 206);
		MainPane.add(centerPane);
		centerPane.setLayout(null);

		JPanel timeChKPane = new JPanel();
		timeChKPane.setForeground(Color.WHITE);
		timeChKPane.setBounds(0, 0, 458, 206);
		centerPane.add(timeChKPane);
		timeChKPane.setLayout(new GridLayout(4, 6));
		// �ð� ���ùڽ�
//				JCheckBox 

		for (int i = 0; i < 24; i++) {

			DecimalFormat format = new DecimalFormat("00:");
			int text = i + 1;
			int realtime = i + 1;
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
		JComboBox personCntChoice = new JComboBox(personCnt);
		personCntChoice.setBounds(470, 92, 40, 21);
		centerPane.add(personCntChoice);

		JLabel cnt = new JLabel("��");
		cnt.setBounds(522, 86, 26, 32);
		centerPane.add(cnt);

		JPanel payPane = new JPanel();
		payPane.setBounds(12, 522, 560, 130);
		MainPane.add(payPane);
		payPane.setLayout(null);

		JButton payButton = new JButton("��          ��");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().roomProduct.setDate(timeList);
				BaseFrame.getInstance().paymentPop.setVisible(true);
				;
			}
		});
		payButton.setFont(new Font("���� ���", Font.BOLD, 18));
		payButton.setBounds(356, 30, 179, 60);
		payPane.add(payButton);

		JLabel priceLabel = new JLabel("�����ݾ�     5,000 \uC6D0");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setFont(new Font("����", Font.PLAIN, 15));
		priceLabel.setBounds(37, 30, 265, 60);
		payPane.add(priceLabel);

		setVisible(false);
	}

	class AddTimeActionListener implements ActionListener {

		int value;

		AddTimeActionListener(int value) {
			this.value = value;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox box = (JCheckBox) e.getSource();

			System.out.println(value);

			Calendar cal = Calendar.getInstance();

			cal.set(Calendar.HOUR_OF_DAY, value);
			// TimeData time = new TimeData(1, Calendar.getInstance().get(Calendar.DATE),
			// value, 0);

			if (box.isSelected()) {
				System.out.println("Ÿ�� �߰��ϱ�");
				timeList.add(cal);
			} else {
				System.out.println("Ÿ�� �����ϱ�");
				for (Calendar cal1 : timeList) {
					if (cal1.get(Calendar.HOUR_OF_DAY) == value) {
						timeList.remove(cal1);
					}
				}

			}
		}
	}

	boolean ddd = false;

	public void updateRoomInfo() {
		// �������� ���� ������

	}

	public void resPossibleChk() {
		for (RoomProduct roomInfo : BaseFrame.getInstance().roomInfoList) {
			for (Calendar a : roomInfo.calendarList) {
				System.out.println("����Ʈ����ִ�?>>>" + a);
			}

			// ���� �������� ������
			System.out.println(roomInfo);
//			System.out.println("������>>>>>"+roomInfo.name);
//			System.out.println("��������>>"+BaseFrame.getInstance().roomProduct.name);
			if (roomInfo.name.equals(BaseFrame.getInstance().roomProduct.name)) {
//				 �������� ���� �������� Ÿ�� üũ
				for (Calendar time : roomInfo.calendarList) {
					for (MyCheckBox myCheckBox : checkBoxList) {

						if (time.get(Calendar.DATE) == Calendar.getInstance().get(Calendar.DATE)
								&& time.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)) {
		
							if (time.get(Calendar.HOUR_OF_DAY) == myCheckBox.value) {
								myCheckBox.box.setEnabled(false);
							}
						}
					}
				}
			}
		}

	}

	public void openPage() {
		setVisible(true);
		System.out.println("openPage");
		useInfo = new JLabel("<html>�̸�: ȫ�浿<br>" + "�����¼�:  " + BaseFrame.getInstance().roomProduct.name + "<br>"
				+ "�Խǽð�:" + current_day + "(16:00)<br>" + "��ǿ���:" + current_day + "(18:00)<br><br>"
				+ "*������ 1�ð� ������ �����մϴ�.<html>");
		updateRoomInfo();
	}
}