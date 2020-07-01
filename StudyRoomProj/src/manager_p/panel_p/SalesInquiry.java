package manager_p.panel_p;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import data_p.sales_p.SalesBySeat;
import data_p.sales_p.SalesRecord;
import data_p.sales_p.SalesTot;
import manager_p.syn_p.MsSalesInquirySyn;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.SmSalesInquiryAck;

public class SalesInquiry extends JPanel implements Receivable {
	JFrame tfram;
	private JScrollPane scrPane_SalesBySeat;
	private JScrollPane scrPane_SalesRecord;
	private JLabel lb_WhatSales;
	private JLabel lb_TotUsers;
	private JLabel lb_TotSales;
	private JComboBox comBox_Year;
	private JComboBox comBox_Month;
	private JComboBox comBox_Day;
	private JCheckBox chBox_Month;
	private JCheckBox chkBox_Day;
	
	public Vector<String> yearList = new Vector<String>();
	public Vector<String> monthList = new Vector<String>();
	public Vector<String> dayList = new Vector<String>();
	public Calendar today = Calendar.getInstance();
	
	String year,month,day;
	
	class ActionLister_SalesInq implements ActionListener{
		String sort;
		
		public ActionLister_SalesInq(String sort) {
			this.sort = sort;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (sort) {
			case "chkBox_Year":
				if(!chkBox_Day.isSelected())
				year = "0";
				break;
				
			case "chkBox_Day":
				if(chkBox_Day.isSelected()) {
					comBox_Day.setEnabled(true);
					comBox_Month.setEnabled(true);
					if(!chBox_Month.isSelected()) {
						chBox_Month.setSelected(true);
						chBox_Month.setEnabled(false);
					}
				}else {
					comBox_Day.setEnabled(false);
					chBox_Month.setEnabled(true);
					day = "0";
				}
				
				
				break;
			case "chBox_Month":
				if(chBox_Month.isSelected()) {
					comBox_Month.setEnabled(true);
				}else {
					comBox_Month.setEnabled(false);
					month = "0";
				}
				break;
				
			case "comBox_Year":
//				monthList.removeAllElements();
//				if((int)comBox_Year.getSelectedItem()!=today.get(Calendar.YEAR)) {
//					System.out.println((int)comBox_Year.getSelectedItem()+"선택됨");
//					for (int i = 1; i <= 12; i++) {
//						monthList.add(i);
//					}
//					comBox_Month = new JComboBox<Integer>(monthList);
//					comBox_Month.revalidate();
//					comBox_Month.repaint();
//				}else {
//					for (int i = 1; i <= today.get(Calendar.MONTH)+1; i++) {
//						monthList.add(i);
//					}
//					comBox_Month = new JComboBox<Integer>(monthList);
//					comBox_Month.revalidate();
//					comBox_Month.repaint();
//				}
//				System.out.println(monthList.size());
				
				year = comBox_Year.getSelectedItem().toString();
				
				break;
				
			case "comBox_Month":
//				dayList.removeAllElements();
//				if((int)comBox_Month.getSelectedItem()!=1) {
//					int year = (int)comBox_Year.getSelectedItem();
//					int mon = (int)comBox_Month.getSelectedItem();
//					int day = 1;
//					today.set(year, mon-1, day);
//					int last = today.getActualMaximum(Calendar.DAY_OF_MONTH);
//					System.out.println(last);
//					today = Calendar.getInstance();
//					dayList = new Vector<Integer>();
//					for (int i = 1; i <= last; i++) {
//						dayList.add(i);
//					}
//					comBox_Day = new JComboBox<Integer>(dayList);
//				}else {
//					today = Calendar.getInstance();
//					for (int i = 1; i <= today.get(Calendar.DATE); i++) {
//						dayList.add(i);
//					}
//					comBox_Day = new JComboBox<Integer>(dayList);
//				}
				
				month = comBox_Month.getSelectedItem().toString();
				break;
				
			case "comBox_Day":
				day = comBox_Day.getSelectedItem().toString();
				break;
				
			case "조회":
				System.out.println(year+"/"+month+"/"+day);
				MsSalesInquirySyn packet = new MsSalesInquirySyn(year, month, day);
				ClientNet.getInstance().sendPacket(packet);
			default:
				break;
			}
		}
		
	}
	
	public void setDateList() {
		//현재 시간 이전의
		//해 리스트
		for (int i = today.get(Calendar.YEAR); i >= 2000; i--) {
			yearList.add(""+i);
		}
		comBox_Year = new JComboBox<String>(yearList);
		
		//달 리스트
//		for (int i = 1; i <= today.get(Calendar.MONTH)+1; i++) {
//			monthList.add(i);
//		}
		for (int i = 1; i <= 12; i++) {
			monthList.add(i<10?"0"+i:""+i);
		}
		comBox_Month = new JComboBox<String>(monthList);
		
		
		//일 리스트
//		for (int i = 1; i <= today.get(Calendar.DATE); i++) {
//			dayList.add(i);
//		}
		for (int i = 1; i <= 31; i++) {
			dayList.add(i<10?"0"+i:""+i);
		}
		comBox_Day = new JComboBox<String>(dayList);
		
		
		year = ""+today.get(Calendar.YEAR);
		month = "01";
		day = "01";
	}
			
	public SalesInquiry() {
		PacketMap.getInstance().map.put(SmSalesInquiryAck.class, this);
		setDateList();
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] { 104, 910, 30};
		gbl_panel_9.rowHeights = new int[] { 144, 423, 43, 30, 0 };
		gbl_panel_9.columnWeights = new double[] { 1.0, 1.0, 0.0 };
		gbl_panel_9.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_9.setLayout(gbl_panel_9);

		String header[] = { "이용석", "이용객", "금액" };
		String contents[][] = { { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, };
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 0;
		panel_9.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 500, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 0, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_2.add(panel_5, gbc_panel_5);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 1;
		gbc_panel_3.gridy = 0;
		panel_2.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 47, 0, 0};
		gbl_panel_3.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(20);
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.BOTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel_3.add(panel_4, gbc_panel_4);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		comBox_Year.addActionListener(new ActionLister_SalesInq("comBox_Year"));
		panel_7.add(comBox_Year);
		
		JLabel lb_Year= new JLabel("년");
		lb_Year.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_7.add(lb_Year);
		
		JPanel panel_7_1 = new JPanel();
		panel_4.add(panel_7_1);
		comBox_Month.addActionListener(new ActionLister_SalesInq("comBox_Month"));
		panel_7_1.add(comBox_Month);
		
		JLabel lb_Month = new JLabel("월");
		lb_Month.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_7_1.add(lb_Month);
		
		chBox_Month = new JCheckBox("");
		chBox_Month.addActionListener(new ActionLister_SalesInq("chBox_Month"));
		panel_7_1.add(chBox_Month);
		
		JPanel panel_7_2 = new JPanel();
		panel_4.add(panel_7_2);
		
		comBox_Day.addActionListener(new ActionLister_SalesInq("comBox_Day"));
		panel_7_2.add(comBox_Day);
		
		JLabel lb_Day = new JLabel("일");
		lb_Day.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_7_2.add(lb_Day);
		
		chkBox_Day = new JCheckBox("");
		chkBox_Day.addActionListener(new ActionLister_SalesInq("chkBox_Day"));
		panel_7_2.add(chkBox_Day);
		
		JPanel panel_6 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_6.getLayout();
		flowLayout_1.setHgap(20);
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 2;
		panel_3.add(panel_6, gbc_panel_6);
		
		JButton btn_Inquiry = new JButton("조회");
		btn_Inquiry.addActionListener(new ActionLister_SalesInq("조회"));
		btn_Inquiry.setFont(new Font("굴림", Font.PLAIN, 32));
		panel_6.add(btn_Inquiry);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		panel_9.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{541, 25, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 0, 5);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 0;
		panel.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0};
		gbl_panel_10.rowHeights = new int[]{30, 404, 0};
		gbl_panel_10.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblNewLabel_1 = new JLabel("\uD310\uB9E4 \uAE30\uB85D");
		lblNewLabel_1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_10.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		scrPane_SalesRecord = new JScrollPane();
		scrPane_SalesRecord.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
		gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6.gridx = 0;
		gbc_scrollPane_6.gridy = 1;
		panel_10.add(scrPane_SalesRecord, gbc_scrollPane_6);
		
		JPanel panel_10_1 = new JPanel();
		GridBagConstraints gbc_panel_10_1 = new GridBagConstraints();
		gbc_panel_10_1.fill = GridBagConstraints.BOTH;
		gbc_panel_10_1.gridx = 2;
		gbc_panel_10_1.gridy = 0;
		panel.add(panel_10_1, gbc_panel_10_1);
		GridBagLayout gbl_panel_10_1 = new GridBagLayout();
		gbl_panel_10_1.columnWidths = new int[]{0, 0};
		gbl_panel_10_1.rowHeights = new int[]{30, 400, 0};
		gbl_panel_10_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_10_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_10_1.setLayout(gbl_panel_10_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uC774\uC6A9\uC11D\uBCC4 \uB9E4\uCD9C");
		lblNewLabel_1_1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 0;
		panel_10_1.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		scrPane_SalesBySeat = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		panel_10_1.add(scrPane_SalesBySeat, gbc_scrollPane);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 2;
		panel_9.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel panel_12_1 = new JPanel();
		GridBagConstraints gbc_panel_12_1 = new GridBagConstraints();
		gbc_panel_12_1.fill = GridBagConstraints.BOTH;
		gbc_panel_12_1.gridx = 1;
		gbc_panel_12_1.gridy = 0;
		panel_1.add(panel_12_1, gbc_panel_12_1);
		panel_12_1.setLayout(new GridLayout(1, 3, 0, 0));
		
		JPanel panel_13_1 = new JPanel();
		panel_12_1.add(panel_13_1);
		GridBagLayout gbl_panel_13_1 = new GridBagLayout();
		gbl_panel_13_1.columnWidths = new int[]{150, 0};
		gbl_panel_13_1.rowHeights = new int[]{21, 49, 18, 0};
		gbl_panel_13_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_13_1.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_13_1.setLayout(gbl_panel_13_1);
		
		lb_WhatSales = new JLabel("매출");
		lb_WhatSales.setFont(new  Font("휴먼엑스포", Font.BOLD, 25));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 1;
		panel_13_1.add(lb_WhatSales, gbc_lblNewLabel_2_1);
		
		JPanel panel_14_1 = new JPanel();
		panel_12_1.add(panel_14_1);
		panel_14_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lb_T = new JLabel("총 이용객 수");
		lb_T.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_T.setFont(new Font("휴먼엑스포", Font.BOLD, 25));
		panel_14_1.add(lb_T);
		
		lb_TotUsers = new JLabel("0");
		lb_TotUsers.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_TotUsers.setFont(new Font("휴먼매직체", Font.BOLD, 30));
		panel_14_1.add(lb_TotUsers);
		
		JPanel panel_15_1 = new JPanel();
		panel_12_1.add(panel_15_1);
		panel_15_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lb_To = new JLabel("합산 금액");
		lb_To.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_To.setFont(new Font("휴먼엑스포", Font.BOLD, 25));
		panel_15_1.add(lb_To);
		
		lb_TotSales = new JLabel("0");
		lb_TotSales.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_TotSales.setFont(new Font("휴먼매직체", Font.BOLD, 30));
		panel_15_1.add(lb_TotSales);
	}

	@Override
	public void receive(PacketBase packet) {
		SmSalesInquiryAck ack = (SmSalesInquiryAck) packet;
		ArrayList<SalesRecord> sr = ack.salesD.salesRecordArrL;
		ArrayList<SalesBySeat> sb = ack.salesD.saleBySeatArrL;
		SalesTot st = ack.salesD.salesTot;
		
		//scrPane_SalesRecord
			String [] header_Record = {"날짜","이용석","금액","이용자","이용자ID","이용시간"};
			String contents_Records[][] = new String [sr.size()][header_Record.length];
				for (int i = 0; i < sr.size(); i++) {
					contents_Records[i][0] = sr.get(i).date;
					contents_Records[i][1] = sr.get(i).room_name;
					contents_Records[i][2] = sr.get(i).room_price_Tot+"";
					contents_Records[i][3] = sr.get(i).user_name;
					contents_Records[i][4] = sr.get(i).user_id;
					String s = "";
					for (String st1 : sr.get(i).hourList) {
						s += st1+" ";
					}
					contents_Records[i][5] = s;
				}
			
			DefaultTableModel tModel_Record = new DefaultTableModel(contents_Records,header_Record);
			JTable table_Record = new JTable(tModel_Record);
			table_Record.setRowHeight(27);
			table_Record.setFillsViewportHeight(true);
			table_Record.setFont(new Font("새굴림", Font.PLAIN, 25));
			scrPane_SalesRecord.setViewportView(table_Record);
		
		//scrPane_SalesRecord
			String [] header_Seat = {"날짜","이용석","합계 금액","이용객 수"};
			String contents_Seat[][] = new String [sr.size()][header_Seat.length];
				for (int i = 0; i < sr.size(); i++) {
					contents_Seat[i][0] = sb.get(i).date;
					contents_Seat[i][1] = sb.get(i).room_name;
					contents_Seat[i][2] = sb.get(i).sum+"";
					contents_Seat[i][3] = sb.get(i).cnt+"";
				}
			DefaultTableModel tModel_Seat = new DefaultTableModel(contents_Seat,header_Seat);
			JTable table_Seat = new JTable(tModel_Seat);
			table_Seat.setRowHeight(27);
			table_Seat.setFillsViewportHeight(true);
			table_Seat.setFont(new Font("새굴림", Font.PLAIN, 25));
			scrPane_SalesBySeat.setViewportView(table_Seat);
			
		//SalesTot
			lb_WhatSales.setText(st.dateSortN==2?"연 매출":st.dateSortN==5?"월 매출":st.dateSortN==8?"당일 매출":"");
			
	}

}
