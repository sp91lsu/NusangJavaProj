package manager_p.panelDialog_p;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import client_p.ui_p.MyColor;
import data_p.ExcelRead_PriceExcel;
import data_p.ExcelWriter_UpdateColumn;
import data_p.sales_p.SalesTot;
import manager_p.ack_p.MsUptRoomPrSyn;
import manager_p.panelDialog_p.SalesInquiry.ActionLister_SalesInq;
import manager_p.syn_p.MsSalesInquirySyn;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.SmSalesInquiryAck;
import server_p.packet_p.ack_p.SmUptRoomPrAck;
import server_p.packet_p.broadCast.ScBuyLockerCast;

import java.awt.Color;

public class ParkTong extends JPanel implements Receivable {
	JFrame tfram;
	
	private JComboBox comBox_Year;
	private JComboBox comBox_Month;
	private JComboBox comBox_Day;
	
	public Vector<String> sortList = new Vector<String>();
	public Vector<String> yearList = new Vector<String>();
	public Vector<String> monthList = new Vector<String>();
	public Vector<String> dayList = new Vector<String>();
	public Calendar today = Calendar.getInstance();
	
	String year,month,day;
	private JComboBox<String> comBox_Sort;
	private JLabel lb_date;
	
	
	//���̺� �о�ͼ� ��� <READ>
	void read(String dataPath) {
		
			
	}
	
	//�Էµ� 0�� 1���� ���� 1�� 2���� ����� <WRITE>
	void restore(String dataPath) {
	}
	
	
	class ActionLister_SalesInq implements ActionListener{
		String sort;
		
		public ActionLister_SalesInq(String sort) {
			this.sort = sort;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (sort) {
			
			case "comBox_Sort":
				if(comBox_Sort.getSelectedItem().toString().equals("��")){
					comBox_Month.setEnabled(false);
					comBox_Day.setEnabled(false);
				}
				if(comBox_Sort.getSelectedItem().toString().equals("��")){
					comBox_Month.setEnabled(true);
					comBox_Day.setEnabled(false);
				}
				if(comBox_Sort.getSelectedItem().toString().equals("��")){
					comBox_Month.setEnabled(true);
					comBox_Day.setEnabled(true);
				}
				break;
					
			case "comBox_Year":
				
				year = comBox_Year.getSelectedItem().toString();
				
				break;
				
			case "comBox_Month":
				
				month = comBox_Month.getSelectedItem().toString();
				break;
				
			case "comBox_Day":
				day = comBox_Day.getSelectedItem().toString();
				break;
				
			case "��ȸ":
				if(comBox_Sort.getSelectedItem().toString().equals("��")){
					year = comBox_Year.getSelectedItem().toString();
					month = "0";
					day = "0";
				}
				if(comBox_Sort.getSelectedItem().toString().equals("��")){
					year = comBox_Year.getSelectedItem().toString();
					month = comBox_Month.getSelectedItem().toString();
					day = "0";
				}
				if(comBox_Sort.getSelectedItem().toString().equals("��")){
					year = comBox_Year.getSelectedItem().toString();
					month = comBox_Month.getSelectedItem().toString();
					day = comBox_Day.getSelectedItem().toString();
				}
				
				
				System.out.println(year+"/"+month+"/"+day);
				MsSalesInquirySyn packet = new MsSalesInquirySyn(year, month, day);
				ClientNet.getInstance().sendPacket(packet);
				if (month.equals("0") && day.equals("0")) {
					lb_date.setText(year+"��");
				} else if (!month.equals("0") && day.equals("0")) {
					lb_date.setText(year+"�� "+month+"�� ");
				} else {
					lb_date.setText(year+"�� "+month+"�� "+day+"��");
				}
				
			default:
				break;
			}
		}
		
	}
	
	public void setDateList() {
		//���� �ð� ������
		//�� ����Ʈ
		for (int i = today.get(Calendar.YEAR); i >= 2000; i--) {
			yearList.add(""+i);
		}
		
		//�� ����Ʈ
//		for (int i = 1; i <= today.get(Calendar.MONTH)+1; i++) {
//			monthList.add(i);
//		}
		for (int i = 1; i <= 12; i++) {
			monthList.add(i<10?"0"+i:""+i);
		}
		
		
		//�� ����Ʈ
//		for (int i = 1; i <= today.get(Calendar.DATE); i++) {
//			dayList.add(i);
//		}
		for (int i = 1; i <= 31; i++) {
			dayList.add(i<10?"0"+i:""+i);
		}
		
		
		year = ""+today.get(Calendar.YEAR);
		month = "01";
		day = "01";
	}
	
	public ParkTong() {
		PacketMap.getInstance().map.put(SmSalesInquiryAck.class, this);
//		public SetPrice(TestFrame tfram) {
//		this.tfram = tfram;
//		this.tfram.tabbedPane.addTab("��� ����", this);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.DARK_GRAY);
		add(panel_4);
		
		comBox_Sort = new JComboBox<String>(sortList);
		comBox_Sort.setFont(new Font("����", Font.PLAIN, 20));
		comBox_Sort.setBackground(MyColor.w_white);
		comBox_Sort.addActionListener(new ActionLister_SalesInq("comBox_Sort"));
		comBox_Year = new JComboBox<String>(yearList);
		comBox_Year.setFont(new Font("����", Font.PLAIN, 20));
		comBox_Year.setBackground(MyColor.w_white);
		comBox_Year.addActionListener(new ActionLister_SalesInq("comBox_Year"));
		comBox_Month = new JComboBox<String>(monthList);
		comBox_Month.setFont(new Font("����", Font.PLAIN, 20));
		comBox_Month.setBackground(MyColor.w_white);
		comBox_Month.addActionListener(new ActionLister_SalesInq("comBox_Month"));
		comBox_Day = new JComboBox<String>(dayList);
		comBox_Day.setFont(new Font("����", Font.PLAIN, 20));
		comBox_Day.setBackground(MyColor.w_white);
		comBox_Day.addActionListener(new ActionLister_SalesInq("comBox_Day"));
	}

	@Override
	public void receive(PacketBase packet) {
		SmSalesInquiryAck ack = (SmSalesInquiryAck) packet;
		SalesTot st = ack.salesD.salesTot;
		System.out.println(st.cntTot);
		System.out.println(st.sumTot);
	}

}
