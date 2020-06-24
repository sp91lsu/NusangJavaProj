package client_p.ui_p;

import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
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

import client_p.ui_p.Payment.MyCheckBox;
import data_p.product_p.TimeData;
import data_p.product_p.room_p.RoomProduct;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class ReservationMain extends JPanel {

	private final JPanel mapPane = new JPanel();
	int setMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
	int nowMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
	int dateChk;
	boolean calViewChk = true;
	
	ArrayList<Button> btnList = new ArrayList<Button>();
	ArrayList<MyCheckBox> checkBoxList = new ArrayList<MyCheckBox>();
	ArrayList<Calendar> timeList = new ArrayList<Calendar>();
	ArrayList<MyJButton> dateList = new ArrayList<MyJButton>();
	JLabel roomInfo = new JLabel();
	JLabel timeInfo;
	JLabel nowMonthL;
	JPanel calPaneMain;
	
	class MyCheckBox {
		JCheckBox box;
		int value;

		public MyCheckBox(JCheckBox box, int value) {
			super();

			this.box = box;
			this.value = value;
		}
	}

	//�޷��� date ��ư�� �������ִ� Ŭ����
	class MyJButton {
		JButton dateBtn;

		public MyJButton(JButton dateBtn) {
			super();
			this.dateBtn = dateBtn;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new ReservationMain());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(10, 10, 900, 1000);
		frame.setVisible(true);
	}

	public ReservationMain() {

		setBackground(new Color(240, 240, 240));
		setForeground(Color.CYAN);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		mapPane.setBounds(12, 10, 860, 400);
		add(mapPane);

		JPanel choicePane = new JPanel();
		choicePane.setBounds(12, 420, 860, 300);
		add(choicePane);
		choicePane.setLayout(null);
		mapPane.setLayout(null);
		
		JButton backBtn = new JButton("�ڷΰ���");
		backBtn.setBounds(750, 10, 90, 30);
		mapPane.add(backBtn);
		backBtn.addActionListener(new ActionListener() {
			//�ڷΰ��鼭 �޷� ����޷� �޷� �ʱ�ȭ
			@Override
			public void actionPerformed(ActionEvent e) {
				setMonth = Calendar.getInstance().get(Calendar.MONTH)+1;
				BaseFrame.getInstance().view("Seating_Arrangement");
				calPaneMain.removeAll();
				makeCalendar();
				nowMonthL.setText(setMonth+"");
			}
		});

		JPanel calendarPane = new JPanel();
		calendarPane.setBounds(220, 29, 440, 340);
		mapPane.add(calendarPane);
		calendarPane.setLayout(null);
		calendarPane.setLayout(null);

		JPanel monthChoicePane = new JPanel();
		monthChoicePane.setBounds(0, 1, 432, 46);
		calendarPane.add(monthChoicePane);
		monthChoicePane.setLayout(new GridLayout(1,3));

		JButton preMonthBtn = new JButton("������");
		monthChoicePane.add(preMonthBtn);
		preMonthBtn.addActionListener(new PreMonthAct());
		
		nowMonthL = new JLabel(setMonth+"");
		nowMonthL.setHorizontalAlignment(SwingConstants.CENTER);
		monthChoicePane.add(nowMonthL);

		JButton nextMonthBtn = new JButton("������");
		monthChoicePane.add(nextMonthBtn);
		nextMonthBtn.addActionListener(new NextMonthAct());

		calPaneMain = new JPanel();
		calPaneMain.setBounds(0, 82, 432, 217);
		calPaneMain.setLayout(new GridLayout(6, 7));
		calendarPane.add(calPaneMain);

		JLabel dayLabel = new JLabel("           ��               ��                ȭ                ��                ��                ��                ��");
		dayLabel.setBounds(0, 43, 432, 39);
		calendarPane.add(dayLabel);

		JPanel timeInfoPane = new JPanel();
		timeInfoPane.setBounds(180, 0, 367, 224);
		choicePane.add(timeInfoPane);
		timeInfoPane.setLayout(null);

		JPanel timeChkPane = new JPanel();
		timeChkPane.setBounds(12, 10, 350, 140);
		timeInfoPane.add(timeChkPane);
		timeChkPane.setLayout(new GridLayout(4, 6));

		//�ð����� ��ư ����
		for (int i = 0; i < 24; i++) {

			DecimalFormat format = new DecimalFormat("00:");
			int text = i + 1;
			int realtime = i + 1;
			MyCheckBox myBox1 = new MyCheckBox(new JCheckBox(format.format(text) + "00"), realtime);
			myBox1.box.addActionListener(new AddTimeActionListener(myBox1.value));
			checkBoxList.add(myBox1);
			timeChkPane.add(myBox1.box);
		}

		JPanel infoPane = new JPanel();
		infoPane.setBounds(12, 151, 343, 63);
		timeInfoPane.add(infoPane);
		infoPane.setLayout(null);

		roomInfo.setBounds(0, 0, 158, 63);
		infoPane.add(roomInfo);

		timeInfo = new JLabel("���ð� 09:00-11:00");
		timeInfo.setBounds(170, 0, 173, 63);
		infoPane.add(timeInfo);

		JPanel paymentPane = new JPanel();
		paymentPane.setBounds(580, 0, 174, 224);
		choicePane.add(paymentPane);
		paymentPane.setLayout(null);

		JButton reservationButton = new JButton("�����ϱ�");
		reservationButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				BaseFrame.getInstance().setCurrentRoomInfo(timeList);
				BaseFrame.getInstance().rcalc.setVisible(true);
			}
		});
		reservationButton.setBounds(12, 182, 150, 32);
		paymentPane.add(reservationButton);

		JLabel totPriceLabel = new JLabel("5,000");
		totPriceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totPriceLabel.setFont(new Font("���� ���", Font.PLAIN, 17));
		totPriceLabel.setBounds(23, 126, 87, 32);
		paymentPane.add(totPriceLabel);

		JLabel reservationButton2 = new JLabel("��");
		reservationButton2.setBounds(125, 128, 22, 32);
		paymentPane.add(reservationButton2);

		JLabel reservationButton3 = new JLabel("�����ݾ�");
		reservationButton3.setFont(new Font("���� ���", Font.BOLD, 14));
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

		JLabel personCntChoice2 = new JLabel("��");
		personCntChoice2.setBounds(109, 33, 22, 30);
		paymentPane.add(personCntChoice2);
		
		makeCalendar();
	}
	

	//�ð���ư -> ���������Ϳ� ���Ͽ� ��Ȱ��ȭ
	public void resPossibleChk(int date) {

		for (MyCheckBox myCheckBox : checkBoxList) {
			myCheckBox.box.setSelected(false);
			myCheckBox.box.setEnabled(true);
		}

		Calendar cal = Calendar.getInstance();
		ArrayList<Integer> checkList = BaseFrame.getInstance().getCheckList(cal.get(Calendar.MONTH),
				date);

		for (MyCheckBox myCheckBox : checkBoxList) {
			for (Integer i : checkList) {
				if (myCheckBox.value == i) {
					myCheckBox.box.setEnabled(false);
				}
			}
		}
	}

	//�� �����ϴ� ��ư(������)
	class NextMonthAct implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println();
			
			if(setMonth>0) {
				setMonth++;
				if(setMonth == 13) {
					setMonth = 1;
				}
			}
			nowMonthL.setText(setMonth+"");
			System.out.println(setMonth);
			calPaneMain.removeAll();
			makeCalendar();
		}
	}
	//�� �����ϴ� ��ư(������)
	class PreMonthAct implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println();
			
			if(setMonth > nowMonth) {
				setMonth--;
				if(setMonth == 13) {
					setMonth = 1;
				}
			}else {
				return;
			}
			nowMonthL.setText(setMonth+"");
			System.out.println(setMonth);
			calPaneMain.removeAll();
			makeCalendar();
		}
	}

	public void init(String name) {
		setVisible(true);
		roomInfo.setText(name);
	}
	
	//�޷� �����
	public void makeCalendar() {
		
		System.out.println("�޷»����ض�~~~~~~~!!!");
		Calendar today = Calendar.getInstance();
		today.set(Calendar.MONTH, setMonth-1);
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
	
			//��ư�� ���೯¥ �� ���
			MyJButton datebtn = new MyJButton(new JButton(dateN));
			dateList.add(datebtn);
			calPaneMain.add(datebtn.dateBtn);
			for (MyJButton myBtn : dateList) {		//���� date�� ��ư ��Ȱ��ȭ
				if(myBtn.dateBtn.getText()==""){
					myBtn.dateBtn.setEnabled(false);
				}
	
			}
			datebtn.dateBtn.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					resPossibleChk(Integer.parseInt(datebtn.dateBtn.getText()));
					dateChk = Integer.parseInt(datebtn.dateBtn.getText());
				}
			});
	
		}
		
	}
	
	//�ð��� �����ϴ� ���ÿ� �����ϴ� �����Ͱ��� ������ ���� ����
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
			
			cal.set(Calendar.MONTH, setMonth-1);
			cal.set(Calendar.DATE, dateChk);
			cal.set(Calendar.HOUR_OF_DAY, value);

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
}