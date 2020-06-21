package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import packetBase_p.PacketBase;

public class Payment extends JFrame implements Receivable {

	private JPanel MainPane;
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String current_day = date.format((Calendar.getInstance().getTime()));
	JLabel titelLabel;
	public String name_Payment;
	JLabel useInfo;
	ArrayList<MyCheckBox> checkBoxList = new ArrayList<Payment.MyCheckBox>();

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

//		JLabel 
		titelLabel = new JLabel("����â");
		titelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titelLabel.setFont(new Font("���� ���", Font.BOLD, 20));
		titelLabel.setBounds(131, 10, 269, 47);
		infoPane.add(titelLabel);

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

		MyCheckBox myBox1 = new MyCheckBox(new JCheckBox("01:00"), 1);
		checkBoxList.add(myBox1);
		timeChKPane.add(myBox1.box);

		MyCheckBox myBox2 = new MyCheckBox(new JCheckBox("02:00"), 2);
		checkBoxList.add(myBox2);
		timeChKPane.add(myBox2.box);

		MyCheckBox myBox3 = new MyCheckBox(new JCheckBox("03:00"), 3);
		checkBoxList.add(myBox3);
		timeChKPane.add(myBox3.box);

		MyCheckBox myBox4 = new MyCheckBox(new JCheckBox("04:00"), 4);
		checkBoxList.add(myBox4);
		timeChKPane.add(myBox4.box);

		MyCheckBox myBox5 = new MyCheckBox(new JCheckBox("05:00"), 5);
		checkBoxList.add(myBox5);
		timeChKPane.add(myBox5.box);

		MyCheckBox myBox6 = new MyCheckBox(new JCheckBox("06:00"), 6);
		checkBoxList.add(myBox6);
		timeChKPane.add(myBox6.box);

		MyCheckBox myBox7 = new MyCheckBox(new JCheckBox("07:00"), 7);
		checkBoxList.add(myBox7);
		timeChKPane.add(myBox7.box);

		MyCheckBox myBox8 = new MyCheckBox(new JCheckBox("08:00"), 8);
		checkBoxList.add(myBox8);
		timeChKPane.add(myBox8.box);

		MyCheckBox myBox9 = new MyCheckBox(new JCheckBox("09:00"), 9);
		checkBoxList.add(myBox9);
		timeChKPane.add(myBox9.box);

		MyCheckBox myBox10 = new MyCheckBox(new JCheckBox("10:00"), 10);
		checkBoxList.add(myBox10);
		timeChKPane.add(myBox10.box);

		MyCheckBox myBox11 = new MyCheckBox(new JCheckBox("11:00"), 11);
		checkBoxList.add(myBox11);
		timeChKPane.add(myBox11.box);

		MyCheckBox myBox12 = new MyCheckBox(new JCheckBox("12:00"), 12);
		checkBoxList.add(myBox12);
		timeChKPane.add(myBox12.box);

		MyCheckBox myBox13 = new MyCheckBox(new JCheckBox("13:00"), 13);
		checkBoxList.add(myBox13);
		timeChKPane.add(myBox13.box);

		MyCheckBox myBox14 = new MyCheckBox(new JCheckBox("14:00"), 14);
		checkBoxList.add(myBox14);
		timeChKPane.add(myBox14.box);

		MyCheckBox myBox15 = new MyCheckBox(new JCheckBox("15:00"), 15);
		checkBoxList.add(myBox15);
		timeChKPane.add(myBox15.box);

		MyCheckBox myBox16 = new MyCheckBox(new JCheckBox("16:00"), 16);
		checkBoxList.add(myBox16);
		timeChKPane.add(myBox16.box);

		MyCheckBox myBox17 = new MyCheckBox(new JCheckBox("17:00"), 17);
		checkBoxList.add(myBox17);
		timeChKPane.add(myBox17.box);

		MyCheckBox myBox18 = new MyCheckBox(new JCheckBox("18:00"), 18);
		checkBoxList.add(myBox18);
		timeChKPane.add(myBox18.box);

		MyCheckBox myBox19 = new MyCheckBox(new JCheckBox("19:00"), 19);
		checkBoxList.add(myBox19);
		timeChKPane.add(myBox19.box);

		MyCheckBox myBox20 = new MyCheckBox(new JCheckBox("20:00"), 20);
		checkBoxList.add(myBox20);
		timeChKPane.add(myBox20.box);

		MyCheckBox myBox21 = new MyCheckBox(new JCheckBox("21:00"), 21);
		checkBoxList.add(myBox21);
		timeChKPane.add(myBox21.box);

		MyCheckBox myBox22 = new MyCheckBox(new JCheckBox("22:00"), 22);
		checkBoxList.add(myBox22);
		timeChKPane.add(myBox22.box);

		MyCheckBox myBox23 = new MyCheckBox(new JCheckBox("23:00"), 23);
		checkBoxList.add(myBox23);
		timeChKPane.add(myBox23.box);

		MyCheckBox myBox24 = new MyCheckBox(new JCheckBox("24:00"), 24);
		checkBoxList.add(myBox24);
		timeChKPane.add(myBox24.box);

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
				PaymentPopFrame calcInfo = new PaymentPopFrame();
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

	boolean ddd = false;

	public void updateRoomInfo() {
		// �������� ���� ������
		for (RoomProduct roomInfo : BaseFrame.getInstance().roomInfoList) {

			// ���� �������� ������
			if (roomInfo.name == name_Payment) {

				// �������� ���� �������� Ÿ�� üũ
				for (TimeData time : roomInfo.timeList) {
					for (MyCheckBox myCheckBox : checkBoxList) {

						if (time.date == Calendar.getInstance().get(Calendar.DATE)) {
							if (time.value == myCheckBox.value) {
								myCheckBox.box.setEnabled(false);
							}
						}
					}
				}
			}

		}
	}

	public void openPage(String RoomName) {
		setVisible(true);
		name_Payment = RoomName;
		useInfo = new JLabel("<html>�̸�: ȫ�浿<br>" + "�����¼�:  " + name_Payment + "<br>" + "�Խǽð�:" + current_day
				+ "(16:00)<br>" + "��ǿ���:" + current_day + "(18:00)<br><br>" + "*������ 1�ð� ������ �����մϴ�.<html>");
		updateRoomInfo();
	}

	@Override
	public void receive(PacketBase packet) {

		System.out.println("���Ͷ�");
		System.out.println(ddd);
		titelLabel.setText("������");

	}
}