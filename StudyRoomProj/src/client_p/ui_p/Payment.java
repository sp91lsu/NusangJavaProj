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

	public RoomProduct roomProduct;
	private JPanel MainPane;
	JLabel titelLabel;
	JLabel useInfo;
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String current_day = date.format((Calendar.getInstance().getTime()));
	ArrayList<MyCheckBox> checkBoxList = new ArrayList<Payment.MyCheckBox>();
	ArrayList<TimeData> timeList = new ArrayList<TimeData>();

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
		titelLabel = new JLabel("결제창");
		titelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titelLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titelLabel.setBounds(131, 10, 269, 47);
		infoPane.add(titelLabel);
		useInfo = new JLabel();
		useInfo.setBackground(new Color(240, 240, 240));
		useInfo.setFont(new Font("굴림", Font.PLAIN, 14));
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
		// 시간 선택박스
//				JCheckBox 

		for (int i = 0; i < 24; i++) {

			DecimalFormat format = new DecimalFormat("00:00");
			int text = i + 1;
			int realtime = i - 11;
			MyCheckBox myBox1 = new MyCheckBox(new JCheckBox(format.format(text)), realtime);
			myBox1.box.addActionListener(new AddTimeActionListener(myBox1.value));
			checkBoxList.add(myBox1);
			timeChKPane.add(myBox1.box);
		}

		// 인원선택
		Vector<Integer> personCnt = new Vector<Integer>();
		for (int i = 0; i <= 10; i++) {
			personCnt.add(i);
		}
		JComboBox personCntChoice = new JComboBox(personCnt);
		personCntChoice.setBounds(470, 92, 40, 21);
		centerPane.add(personCntChoice);

		JLabel cnt = new JLabel("명");
		cnt.setBounds(522, 86, 26, 32);
		centerPane.add(cnt);

		JPanel payPane = new JPanel();
		payPane.setBounds(12, 522, 560, 130);
		MainPane.add(payPane);
		payPane.setLayout(null);

		JButton payButton = new JButton("결          제");
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				roomProduct.setDate(Calendar.getInstance().get(Calendar.DATE), timeList);
				BaseFrame.getInstance().paymentPop.setVisible(true);
				;
			}
		});
		payButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		payButton.setBounds(356, 30, 179, 60);
		payPane.add(payButton);

		JLabel priceLabel = new JLabel("결제금액     5,000 \uC6D0");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setFont(new Font("굴림", Font.PLAIN, 15));
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

			TimeData time = new TimeData(1, Calendar.getInstance().get(Calendar.DATE), value, 0);
			if (box.isSelected()) {
				System.out.println("타임 추가하기");
				timeList.add(time);
			} else {
				System.out.println("타임 제거하기");
				timeList.remove(time);
			}
		}
	}

	boolean ddd = false;

	public void updateRoomInfo() {
		// 서버에서 받은 룸정보
		for (RoomProduct roomInfo : BaseFrame.getInstance().roomInfoList) {

			// 현제 페이지의 룸정보
			System.out.println(roomInfo);
			System.out.println(roomProduct);
			if (roomInfo.name == roomProduct.name) {
				// 서버에서 받은 룸정보의 타임 체크
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

	public void openPage(RoomProduct product) {
		setVisible(true);
		System.out.println("openPage");
		System.out.println(product);
		roomProduct = product;
		useInfo = new JLabel("<html>이름: 홍길동<br>" + "선택좌석:  " + roomProduct.name + "<br>" + "입실시간:" + current_day
				+ "(16:00)<br>" + "퇴실예정:" + current_day + "(18:00)<br><br>" + "*예약은 1시간 단위로 가능합니다.<html>");
		updateRoomInfo();
	}
}