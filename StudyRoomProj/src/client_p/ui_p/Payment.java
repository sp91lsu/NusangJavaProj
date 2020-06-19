package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Payment extends JFrame 
{
	private JPanel MainPane;
	SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	String current_day=date.format((Calendar.getInstance().getTime()));
	
	public Payment(String name)
	{
		setBounds(650, 200, 600, 700);
		MainPane = new JPanel();
		setContentPane(MainPane);
		MainPane.setLayout(null);
		
		JPanel infoPane = new JPanel();
		infoPane.setBackground(new Color(240, 240, 240));
		infoPane.setBounds(12, 10, 560, 286);
		MainPane.add(infoPane);
		infoPane.setLayout(null);
		
		JLabel titelLabel = new JLabel("결제창");
		titelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titelLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		titelLabel.setBounds(131, 10, 269, 47);
		infoPane.add(titelLabel);
		
		JLabel useInfo = new JLabel("<html>이름: 홍길동<br>"
				+ "선택좌석:  "+ name+"<br>" + 
				"입실시간:"+current_day+"(16:00)<br>"
				+ "퇴실예정:"+current_day+"(18:00)<br><br>" + 
				"*예약은 1시간 단위로 가능합니다.<html>"				
				);
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
			//시간 선택박스
				JCheckBox timeChkBox_01 = new JCheckBox("01:00");
				timeChKPane.add(timeChkBox_01);
				
				JCheckBox timeChkBox_02 = new JCheckBox("02:00");
				timeChKPane.add(timeChkBox_02);
				
				JCheckBox timeChkBox_03 = new JCheckBox("03:00");
				timeChKPane.add(timeChkBox_03);
				
				JCheckBox timeChkBox_04 = new JCheckBox("04:00");
				timeChKPane.add(timeChkBox_04);
				
				JCheckBox timeChkBox_05 = new JCheckBox("05:00");
				timeChKPane.add(timeChkBox_05);
				
				JCheckBox timeChkBox_06 = new JCheckBox("06:00");
				timeChKPane.add(timeChkBox_06);
				
				JCheckBox timeChkBox_07 = new JCheckBox("07:00");
				timeChKPane.add(timeChkBox_07);
				
				JCheckBox timeChkBox_08 = new JCheckBox("08:00");
				timeChKPane.add(timeChkBox_08);
				
				JCheckBox timeChkBox_09 = new JCheckBox("09:00");
				timeChKPane.add(timeChkBox_09);
				
				JCheckBox timeChkBox_10 = new JCheckBox("10:00");
				timeChKPane.add(timeChkBox_10);
				
				JCheckBox timeChkBox_11 = new JCheckBox("11:00");
				timeChKPane.add(timeChkBox_11);
				
				JCheckBox timeChkBox_12 = new JCheckBox("12:00");
				timeChKPane.add(timeChkBox_12);
				
				JCheckBox timeChkBox_13 = new JCheckBox("13:00");
				timeChKPane.add(timeChkBox_13);
				
				JCheckBox timeChkBox_14 = new JCheckBox("14:00");
				timeChKPane.add(timeChkBox_14);
				
				JCheckBox timeChkBox_15 = new JCheckBox("15:00");
				timeChKPane.add(timeChkBox_15);
				
				JCheckBox timeChkBox_16 = new JCheckBox("16:00");
				timeChKPane.add(timeChkBox_16);
				
				JCheckBox timeChkBox_17 = new JCheckBox("17:00");
				timeChKPane.add(timeChkBox_17);
				
				JCheckBox timeChkBox_18 = new JCheckBox("18:00");
				timeChKPane.add(timeChkBox_18);
				
				JCheckBox timeChkBox_19 = new JCheckBox("19:00");
				timeChKPane.add(timeChkBox_19);
				
				JCheckBox timeChkBox_20 = new JCheckBox("20:00");
				timeChKPane.add(timeChkBox_20);
				
				JCheckBox timeChkBox_21 = new JCheckBox("21:00");
				timeChKPane.add(timeChkBox_21);
				
				JCheckBox timeChkBox_22 = new JCheckBox("22:00");
				timeChKPane.add(timeChkBox_22);
				
				JCheckBox timeChkBox_23 = new JCheckBox("23:00");
				timeChKPane.add(timeChkBox_23);
				
				JCheckBox timeChkBox_24 = new JCheckBox("24:00");
				timeChKPane.add(timeChkBox_24);
		
		//인원선택
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
				
			}});
		payButton.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		payButton.setBounds(356, 30, 179, 60);
		payPane.add(payButton);
		
			JLabel priceLabel = new JLabel("결제금액     5,000 \uC6D0");
			priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
			priceLabel.setFont(new Font("굴림", Font.PLAIN, 15));
			priceLabel.setBounds(37, 30, 265, 60);
			payPane.add(priceLabel);
			
		setVisible(true);
	}
}