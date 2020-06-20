package client_p.ui_p;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;

public class ReservationMain extends JPanel {

	public String roomName;
	public ArrayList<RoomProduct> roomList = new ArrayList<RoomProduct>();
	private final JPanel mapPane = new JPanel();
	ArrayList<Button> btnList = new ArrayList<Button>();
	int setMonth = 5;
	boolean calViewChk = true;
	ArrayList<MyCheckBox> timeChoiceList = new ArrayList<MyCheckBox>();

	class MyCheckBox {
		JCheckBox box;
		int value;

		MyCheckBox(String text, int value) {
			this.box = new JCheckBox(text);
			this.value = value;
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.add(new ReservationMain());
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setBounds(10, 10, 900, 1000);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ReservationMain() {

		setVisible(false);

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

//		JPanel calPaneMain2 = new JPanel();
//		calPaneMain2.setBounds(0, 57, 283, 167);
//		calPaneMain2.setLayout(new GridLayout(6, 7));
//		calendarPane.add(calPaneMain2);
//		calPaneMain2.setVisible(true);

		JPanel timeInfoPane = new JPanel();
		timeInfoPane.setBounds(295, 0, 367, 224);
		choicePane.add(timeInfoPane);
		timeInfoPane.setLayout(null);

		JPanel timeChkPane = new JPanel();
		timeChkPane.setBounds(12, 10, 343, 131);
		timeInfoPane.add(timeChkPane);
		timeChkPane.setLayout(new GridLayout(4, 6));

		//////// 시간 선택 버튼/////////
		for (int i = 1; i < 25; i++) {
			if (i < 10) {
				MyCheckBox myc = new MyCheckBox("0" + i + ":00", i);
				timeChkPane.add(myc.box);
				timeChoiceList.add(myc);
			} else {
				MyCheckBox myc = new MyCheckBox(i + ":00", i);
				timeChkPane.add(myc.box);
				timeChoiceList.add(myc);
			}
		}

//		for (MyCheckBox tc : timeChoiceList) {
//			System.out.println(tc.box.getText());
//			tc.box.setEnabled(false);
//
//		}

//		for (JCheckBox tcl : timeChoiceList) {
//			for (RoomProduct rL : DataManager.getInstance().roomList) {
//				for (TimeData td : rL.timeList) {
//					if(tcl.getText() td.value)) {
//						tcl.setEnabled(false);
//					}
//				}
//			}
//			
//		}
//

		JPanel infoPane = new JPanel();
		infoPane.setBounds(12, 151, 343, 63);
		timeInfoPane.add(infoPane);
		infoPane.setLayout(null);

		JLabel roomInfo = new JLabel("\uCDE8\uC2DD\uAC00\uB2A5\uBC29(5000\uC6D0)");
		roomInfo.setBounds(0, 0, 158, 63);
		infoPane.add(roomInfo);

		JLabel timeInfo = new JLabel("\uC0AC\uC6A9\uC2DC\uAC04 09:00-11:00");
		timeInfo.setBounds(170, 0, 173, 63);
		infoPane.add(timeInfo);

		JPanel paymentPane = new JPanel();
		paymentPane.setBounds(674, 0, 174, 224);
		choicePane.add(paymentPane);
		paymentPane.setLayout(null);

		JButton reservationButton = new JButton("\uC608\uC57D\uD558\uAE30");
		reservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		reservationButton.setBounds(12, 182, 150, 32);
		paymentPane.add(reservationButton);

		JLabel totPriceLabel = new JLabel("5,000");
		totPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totPriceLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 17));
		totPriceLabel.setBounds(23, 126, 87, 32);
		paymentPane.add(totPriceLabel);

		JLabel reservationButton2 = new JLabel("\uC6D0");
		reservationButton2.setBounds(125, 128, 22, 32);
		paymentPane.add(reservationButton2);

		JLabel reservationButton3 = new JLabel("\uACB0\uC81C\uAE08\uC561");
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

		JLabel personCntChoice2 = new JLabel("\uBA85");
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

		// System.out.println(first);

		for (int i = 1; i < first; i++) {
			System.out.print("\t");
		}

		int last = today.getActualMaximum(Calendar.DATE);

		for (int i = 2 - first; i < 44 - first; i++) {
			String dateN = i + "";
			if (i < 1)
				dateN = "";
			else if (last < i)
				dateN = "";

			calPaneMain.add(new Button(dateN));
		}

	}

	class MonthChoiceAct implements ActionListener {

		@Override
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

		for (MyCheckBox time24Data : timeChoiceList) {
			time24Data.box.setEnabled(true);
		}

		// 6월 3일에 4시
		// 24개의 체크박스 타임 데이터
		for (MyCheckBox time24Data : timeChoiceList) {

			// 서버에서 받은 룸의 시간예약데이터
			for (RoomProduct roomData : roomList) {

				for (TimeData ServerTimeData : roomData.timeList) {
					if (date == ServerTimeData.date) {
						if (time24Data.value == ServerTimeData.value) {
							time24Data.box.setEnabled(false);
						}
					}
				}

			}
		}

	}

}
