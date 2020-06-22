package client_p.ui_p;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
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
import javax.swing.border.EmptyBorder;

import data_p.product_p.room_p.RoomProduct;

public class ReservationMain extends JPanel {

	public String roomName;
	private final JPanel mapPane = new JPanel();
	int setMonth = 5;
	boolean calViewChk = true;
	public ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
	ArrayList<Button> btnList = new ArrayList<Button>();
	ArrayList<MyCheckBox> checkBoxList = new ArrayList<MyCheckBox>();
	ArrayList<Calendar> timeList = new ArrayList<Calendar>();
	ArrayList<MyJButton> dateList = new ArrayList<MyJButton>();

	class MyCheckBox {
		JCheckBox box;
		int value;

		public MyCheckBox(JCheckBox box, int value) {
			super();

			this.box = box;
			this.value = value;
		}
	}

	class MyJButton {
		JButton dateBtn;

		public MyJButton(JButton button) {
			super();
			this.dateBtn = button;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.add(new ReservationMain());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 900, 1000);
		frame.setVisible(true);
	}

	public ReservationMain() {

		setBackground(new Color(240, 240, 240));
		setForeground(Color.CYAN);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		mapPane.setBounds(12, 10, 860, 488);
		add(mapPane);

		JPanel choicePane = new JPanel();
		choicePane.setBounds(12, 508, 860, 224);
		add(choicePane);
		choicePane.setLayout(null);

		JPanel calendarPane = new JPanel();
		calendarPane.setBounds(0, 0, 283, 224);
		choicePane.add(calendarPane);
		calendarPane.setLayout(null);
		calendarPane.setLayout(null);

		JPanel monthChoicePane = new JPanel();
		monthChoicePane.setBounds(0, 1, 283, 46);
		calendarPane.add(monthChoicePane);
		monthChoicePane.setLayout(null);

		JLabel nowMonthL = new JLabel(setMonth + "");
		nowMonthL.setHorizontalAlignment(SwingConstants.CENTER);
		nowMonthL.setBounds(105, 0, 57, 46);
		monthChoicePane.add(nowMonthL);

		JButton preMonthBtn = new JButton("이전달");
		preMonthBtn.setBounds(5, 12, 97, 23);
		monthChoicePane.add(preMonthBtn);

		JButton nextMonthBtn = new JButton("다음달");
		nextMonthBtn.setBounds(174, 12, 97, 23);
		monthChoicePane.add(nextMonthBtn);
		nextMonthBtn.addActionListener(new MonthChoiceAct());

		JPanel calPaneMain = new JPanel();
		calPaneMain.setBounds(0, 82, 283, 142);
		calPaneMain.setLayout(new GridLayout(6, 7));
		calendarPane.add(calPaneMain);

		JLabel dayLabel = new JLabel("     일         월         화         수         목         금         토");
		dayLabel.setBounds(0, 43, 283, 39);
		calendarPane.add(dayLabel);

		JPanel timeInfoPane = new JPanel();
		timeInfoPane.setBounds(295, 0, 367, 224);
		choicePane.add(timeInfoPane);
		timeInfoPane.setLayout(null);

		JPanel timeChkPane = new JPanel();
		timeChkPane.setBounds(12, 10, 343, 131);
		timeInfoPane.add(timeChkPane);
		timeChkPane.setLayout(new GridLayout(4, 6));

		//////// 시간 선택 버튼/////////
		for (int i = 0; i < 24; i++) {

			DecimalFormat format = new DecimalFormat("00:");
			int text = i + 1;
			int realtime = i - 11;
			MyCheckBox myBox1 = new MyCheckBox(new JCheckBox(format.format(text) + "00"), realtime);
			myBox1.box.addActionListener(new AddTimeActionListener(myBox1.value));
			checkBoxList.add(myBox1);
			timeChkPane.add(myBox1.box);
		}

		JPanel infoPane = new JPanel();
		infoPane.setBounds(12, 151, 343, 63);
		timeInfoPane.add(infoPane);
		infoPane.setLayout(null);

		JLabel roomInfo = new JLabel("취식가능방(5000원)");
		roomInfo.setBounds(0, 0, 158, 63);
		infoPane.add(roomInfo);

		JLabel timeInfo = new JLabel("사용시간 09:00-11:00");
		timeInfo.setBounds(170, 0, 173, 63);
		infoPane.add(timeInfo);

		JPanel paymentPane = new JPanel();
		paymentPane.setBounds(674, 0, 174, 224);
		choicePane.add(paymentPane);
		paymentPane.setLayout(null);

		JButton reservationButton = new JButton("예약하기");
		reservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().roomProduct.setDate(timeList);
				BaseFrame.getInstance().rcalc.setVisible(true);
			}});
		
		reservationButton.setBounds(12, 182, 150, 32);
		paymentPane.add(reservationButton);

		JLabel totPriceLabel = new JLabel("5,000");
		totPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		totPriceLabel.setBounds(23, 126, 87, 32);
		paymentPane.add(totPriceLabel);

		JLabel reservationButton2 = new JLabel("원");
		reservationButton2.setBounds(125, 128, 22, 32);
		paymentPane.add(reservationButton2);

		JLabel reservationButton3 = new JLabel("결제금액");
		reservationButton3.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		reservationButton3.setHorizontalAlignment(SwingConstants.CENTER);
		reservationButton3.setBounds(23, 82, 124, 32);
		paymentPane.add(reservationButton3);

		Vector<Integer> personCnt = new Vector<Integer>();
		for (int i = 0; i <= 10; i++) {
			personCnt.add(i);
		}
		JComboBox personCntChoice = new JComboBox(personCnt);
		personCntChoice.setToolTipText("0");
		personCntChoice.setBounds(52, 38, 50, 21);
		paymentPane.add(personCntChoice);

		JLabel personCntChoice2 = new JLabel("명");
		personCntChoice2.setBounds(109, 33, 22, 30);
		paymentPane.add(personCntChoice2);

		///////////////// 달력입력///////////////////////////////////

		setMonth = 6;

		Calendar today = Calendar.getInstance();
		today.set(Calendar.MONTH, setMonth - 1);
		int nowM = today.get(Calendar.MONTH);
		int nowD = today.get(Calendar.DATE);

		today.set(Calendar.DATE, 1);
		int first = today.get(Calendar.DAY_OF_WEEK);

		int last = today.getActualMaximum(Calendar.DATE);

		for (int i = 2 - first; i < 44 - first; i++) {
			String dateN = i + "";
			if (i < 1)
				dateN = "";
			else if (last < i)
				dateN = "";

			MyJButton datebtn = new MyJButton(new JButton(dateN));
			dateList.add(datebtn);
			calPaneMain.add(datebtn.dateBtn);
		}
	}

	class MonthChoiceAct implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("들어가니??");
			setMonth++;
			System.out.println(setMonth);
		}
	}

	public void init(String name) {
		setVisible(true);
		roomName = name;
		roomInfoUpdate();
	}

	public void roomInfoUpdate() {
		// 서버에 있는 데이터 가져옴
		for (RoomProduct info : BaseFrame.getInstance().roomInfoList) {
			if (info.name == roomName) {
				roomList.add(info);
			}
		}
	}

	public void buttonShow(int date) {
		setVisible(true);

		for (MyCheckBox time24Data : checkBoxList) {
			time24Data.box.setEnabled(true);
		}

		// 6월 3일에 4시
		// 24개의 체크박스 타임 데이터
		for (MyCheckBox time24Data : checkBoxList) {

			// 서버에서 받은 룸의 시간예약데이터
//			for (RoomProduct roomData : roomList) {
//
//				for (TimeData ServerTimeData : roomData.timeList) {
//					if (date == ServerTimeData.date) {
//						if (time24Data.value == ServerTimeData.value) {
//							time24Data.box.setEnabled(false);
//						}
//					}
//				}
//			}
		}
	}

	class AddTimeActionListener implements ActionListener {

		int value;

		AddTimeActionListener(int value) {
			this.value = value;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JCheckBox box = (JCheckBox) e.getSource();

			
			//TimeData time = new TimeData(1, Calendar.getInstance().get(Calendar.DATE), value, 0);
//			if (box.isSelected()) {
//				System.out.println("타임 추가하기");
//				timeList.add(time);
//			} else {
//				System.out.println("타임 제거하기");
//				timeList.remove(time);
//			}
		}
	}
}