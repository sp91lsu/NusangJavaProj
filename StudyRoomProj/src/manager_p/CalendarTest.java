package manager_p;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.apache.poi.hssf.util.HSSFColor.BLUE;

import client_p.ClientNet;
import manager_p.syn_p.MsResvRoomSyn;

public class CalendarTest extends JPanel {

   private final JPanel mapPane = new JPanel();
   int setdate;
   int setMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
   int nowMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
   int nowYear = Calendar.getInstance().get(Calendar.YEAR);
   int setYear = Calendar.getInstance().get(Calendar.YEAR);

   ArrayList<MyJButton> dateList = new ArrayList<MyJButton>();
   JLabel nowMonthL;
   JPanel calPaneMain;
   JLabel yearInfoL;
   
   ManagerWindow mw;
private JButton bf;

   // ���� �ð��� ���� ��ư�� ������ �ִ� Ŭ����
   class MyCheckBox {
      JCheckBox box;
      int value;

      public MyCheckBox(JCheckBox box, int value) {
         super();

         this.box = box;
         this.value = value;
      }
   }

   // �޷��� date ��ư�� �������ִ� Ŭ����
   class MyJButton {
      JButton dateBtn;

      public MyJButton(JButton dateBtn) {
         super();
         this.dateBtn = dateBtn;
      }
   }

   public static void main(String[] args) {
      JFrame frame = new JFrame();
      frame.getContentPane().add(new CalendarTest(new ManagerWindow()));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setBounds(10, 10, 900, 800);
      frame.setVisible(true);
   }

   public CalendarTest(ManagerWindow mw) {
	  this.mw = mw;
	   
      System.out.println(Calendar.getInstance().getTime());

      setBackground(new Color(240, 240, 240));
      setForeground(Color.CYAN);
      setBorder(new EmptyBorder(5, 5, 5, 5));
      setLayout(null);
      mapPane.setBounds(12, 10, 800, 400);
      add(mapPane);
      mapPane.setLayout(null);

      yearInfoL = new JLabel(setYear + "��");
      yearInfoL.setFont(new Font("���� ���", Font.BOLD, 15));
      yearInfoL.setHorizontalAlignment(SwingConstants.CENTER);
      yearInfoL.setBounds(175, 0, 90, 30);
      mapPane.add(yearInfoL);

      JPanel calendarPane = new JPanel();
      calendarPane.setBounds(12, 20, 440, 340);
      mapPane.add(calendarPane);
      calendarPane.setLayout(null);
      calendarPane.setLayout(null);

      JPanel monthChoicePane = new JPanel();
      monthChoicePane.setBounds(0, 1, 432, 46);
      calendarPane.add(monthChoicePane);
      monthChoicePane.setLayout(  new GridLayout(1, 3));

      JButton preMonthBtn = new JButton("������");
      monthChoicePane.add(preMonthBtn);
      preMonthBtn.addActionListener(new PreMonthAct());

      nowMonthL = new JLabel(setMonth + "");
      nowMonthL.setHorizontalAlignment(SwingConstants.CENTER);
      monthChoicePane.add(nowMonthL);

      JButton nextMonthBtn = new JButton("������");
      monthChoicePane.add(nextMonthBtn);
      nextMonthBtn.addActionListener(new NextMonthAct());

      calPaneMain = new JPanel();
      calPaneMain.setBounds(0, 82, 432, 217);
      calPaneMain.setLayout(new GridLayout(6, 7));
      calendarPane.add(calPaneMain);

      JLabel dayLabel = new JLabel(
            "           ��               ��                ȭ                ��                ��                ��                ��");
      dayLabel.setBounds(0, 43, 432, 39);
      calendarPane.add(dayLabel);

      makeCalendar();
   }

   // �� �����ϴ� ��ư(������)
   class NextMonthAct implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         resetResInfo();
         if (setMonth > 0) {
            setMonth++;
            if (setMonth == 13) {
               setMonth = 1;
               setYear++;
               yearInfoL.setText(setYear + "��");
            }
         }
         nowMonthL.setText(setMonth + "");
         System.out.println(setMonth);
         calPaneMain.removeAll();
         makeCalendar();
      }
   }

   // �� �����ϴ� ��ư(������)
   class PreMonthAct implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent e) {
         resetResInfo();
         if (setMonth > 0) {
            setMonth--;
            if (setMonth == 0) {
               setMonth = 12;
               setYear--;
               yearInfoL.setText(setYear + "��");
            }
         } 
         nowMonthL.setText(setMonth + "");
         System.out.println(setMonth);
         calPaneMain.removeAll();
         makeCalendar();
      }
   }

   // �޷� �����
   public void makeCalendar() {

      Calendar today = Calendar.getInstance();
      today.set(Calendar.YEAR, setYear);
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

         // ��ư�� ���೯¥ �� ���
         MyJButton datebtn = new MyJButton(new JButton(dateN));
         dateList.add(datebtn);
         calPaneMain.add(datebtn.dateBtn);
         for (MyJButton myBtn : dateList) { // ���� date�� ��ư ��Ȱ��ȭ
            if (myBtn.dateBtn.getText() == "") {
               myBtn.dateBtn.setEnabled(false);
            }

         }
         bf = new JButton();
         datebtn.dateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	String date = datebtn.dateBtn.getText();
            	bf.setBackground(null);
            	datebtn.dateBtn.setBackground(Color.blue);
            	MsResvRoomSyn packet = new MsResvRoomSyn(setYear, setMonth, date);
            	ClientNet.getInstance().sendPacket(packet);
            	bf = datebtn.dateBtn;
            }
         });
      }

   }

   // �ٸ� ��ư Ŭ���� �������� ����
   public void resetResInfo() {
      nowMonthL.setText(setMonth + "");
      for (MyJButton btn : dateList) {
         btn.dateBtn.setBackground(null);
      }
   }
}