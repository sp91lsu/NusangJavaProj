package client_p.ui_p;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class ReservationMain extends JPanel {

	private final JPanel mapPane = new JPanel();
	ArrayList<Button> btnList = new ArrayList<Button>();
	int setMonth =5;
	boolean calViewChk = true;

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

			JCheckBox timeChkBox_01 = new JCheckBox("01:00");
			timeChkPane.add(timeChkBox_01);
	
			JCheckBox timeChkBox_02 = new JCheckBox("02:00");
			timeChkPane.add(timeChkBox_02);
	
			JCheckBox timeChkBox_03 = new JCheckBox("03:00");
			timeChkPane.add(timeChkBox_03);
	
			JCheckBox timeChkBox_04 = new JCheckBox("04:00");
			timeChkPane.add(timeChkBox_04);
	
			JCheckBox timeChkBox_05 = new JCheckBox("05:00");
			timeChkPane.add(timeChkBox_05);
	
			JCheckBox timeChkBox_06 = new JCheckBox("06:00");
			timeChkPane.add(timeChkBox_06);
	
			JCheckBox timeChkBox_07 = new JCheckBox("07:00");
			timeChkPane.add(timeChkBox_07);
	
			JCheckBox timeChkBox_08 = new JCheckBox("08:00");
			timeChkPane.add(timeChkBox_08);
	
			JCheckBox timeChkBox_09 = new JCheckBox("09:00");
			timeChkPane.add(timeChkBox_09);
	
			JCheckBox timeChkBox_10 = new JCheckBox("10:00");
			timeChkPane.add(timeChkBox_10);
	
			JCheckBox timeChkBox_11 = new JCheckBox("11:00");
			timeChkPane.add(timeChkBox_11);
	
			JCheckBox timeChkBox_12 = new JCheckBox("12:00");
			timeChkPane.add(timeChkBox_12);
	
			JCheckBox timeChkBox_13 = new JCheckBox("13:00");
			timeChkPane.add(timeChkBox_13);
	
			JCheckBox timeChkBox_14 = new JCheckBox("14:00");
			timeChkPane.add(timeChkBox_14);
	
			JCheckBox timeChkBox_15 = new JCheckBox("15:00");
			timeChkPane.add(timeChkBox_15);
	
			JCheckBox timeChkBox_16 = new JCheckBox("16:00");
			timeChkPane.add(timeChkBox_16);
	
			JCheckBox timeChkBox_17 = new JCheckBox("17:00");
			timeChkPane.add(timeChkBox_17);
	
			JCheckBox timeChkBox_18 = new JCheckBox("18:00");
			timeChkPane.add(timeChkBox_18);
	
			JCheckBox timeChkBox_19 = new JCheckBox("19:00");
			timeChkPane.add(timeChkBox_19);
	
			JCheckBox timeChkBox_20 = new JCheckBox("20:00");
			timeChkPane.add(timeChkBox_20);
	
			JCheckBox timeChkBox_21 = new JCheckBox("21:00");
			timeChkPane.add(timeChkBox_21);
	
			JCheckBox timeChkBox_22 = new JCheckBox("22:00");
			timeChkPane.add(timeChkBox_22);
	
			JCheckBox timeChkBox_23 = new JCheckBox("23:00");
			timeChkPane.add(timeChkBox_23);
	
			JCheckBox timeChkBox_24 = new JCheckBox("24:00");
			timeChkPane.add(timeChkBox_24);

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



		

		setMonth = 5;

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
			String dateN = i+"";
			if(i<1)
				dateN = "";
			else if(last < i)
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
}

