package manager_p.panelDialog_p;

import java.awt.Component;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

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
	private JScrollPane scrPane_SalesRecord;
	private JComboBox comBox_Year;
	private JComboBox comBox_Month;
	private JComboBox comBox_Day;
	
	public Vector<String> sortList = new Vector<String>();
	public Vector<String> yearList = new Vector<String>();
	public Vector<String> monthList = new Vector<String>();
	public Vector<String> dayList = new Vector<String>();
	public Calendar today = Calendar.getInstance();
	
	String year,month,day;
	private JScrollPane scrPane_SalesBySeat;
	private JComboBox<String> comBox_Sort;
	private JLabel lb_date;
	private JLabel lb_TotUsers;
	private JLabel lb_TotSales;
	private JLabel lb_WhatSales;
	
	class ActionLister_SalesInq implements ActionListener{
		String sort;
		
		public ActionLister_SalesInq(String sort) {
			this.sort = sort;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (sort) {
			
			case "comBox_Sort":
				if(comBox_Sort.getSelectedItem().toString().equals("연")){
					comBox_Month.setEnabled(false);
					comBox_Day.setEnabled(false);
					year = today.get(Calendar.YEAR)+"";
					month = "0";
					day = "0";
				}
				if(comBox_Sort.getSelectedItem().toString().equals("월")){
					comBox_Month.setEnabled(true);
					comBox_Day.setEnabled(false);
					year = today.get(Calendar.YEAR)+"";
					month = comBox_Month.getSelectedItem().toString();
					day = "0";
				}
				if(comBox_Sort.getSelectedItem().toString().equals("일")){
					comBox_Month.setEnabled(true);
					comBox_Day.setEnabled(true);
					year = today.get(Calendar.YEAR)+"";
					month = comBox_Month.getSelectedItem().toString();
					day = comBox_Day.getSelectedItem().toString();
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
				if (month.equals("0") && day.equals("0")) {
					lb_date.setText(year+"년");
				} else if (!month.equals("0") && day.equals("0")) {
					lb_date.setText(year+"년 "+month+"월 ");
				} else {
					lb_date.setText(year+"년 "+month+"월 "+day+"일");
				}
				
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
		
		//달 리스트
//		for (int i = 1; i <= today.get(Calendar.MONTH)+1; i++) {
//			monthList.add(i);
//		}
		for (int i = 1; i <= 12; i++) {
			monthList.add(i<10?"0"+i:""+i);
		}
		
		
		//일 리스트
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
			
	public SalesInquiry() {
		PacketMap.getInstance().map.put(SmSalesInquiryAck.class, this);
		setDateList();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{1000, 39, 0};
		gridBagLayout.rowHeights = new int[]{641, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		String header[] = { "이용석", "이용객", "금액" };
		String contents[][] = { { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "샤워실", "2", "30000" }, { "일반1", "3", "34003" }, { "ㅇㄹㄷㄷ", "4", "34534" },
				{ "dfeb", "5", "234767" }, };
		comBox_Year = new JComboBox<String>(yearList);
		comBox_Month = new JComboBox<String>(monthList);
		comBox_Day = new JComboBox<String>(dayList);
		
		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.insets = new Insets(0, 0, 0, 5);
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 0;
		gbc_panel_9.gridy = 0;
		add(panel_9, gbc_panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] { 104, 900, 22};
		gbl_panel_9.rowHeights = new int[] { 144, 447, 0 };
		gbl_panel_9.columnWeights = new double[] { 1.0, 1.0, 0.0 };
		gbl_panel_9.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_9.setLayout(gbl_panel_9);
		
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
		
		JPanel panel_7_3 = new JPanel();
		panel_4.add(panel_7_3);
		
		sortList.add("일");
		sortList.add("월");
		sortList.add("연");
		comBox_Sort = new JComboBox<String>(sortList);
		comBox_Sort.addActionListener(new ActionLister_SalesInq("comBox_Sort"));
		panel_7_3.add(comBox_Sort);
		
		JLabel lb_Year_1 = new JLabel("\uB9E4\uCD9C");
		lb_Year_1.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_7_3.add(lb_Year_1);
		
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
		
		JPanel panel_7_2 = new JPanel();
		panel_4.add(panel_7_2);
		
		comBox_Day.addActionListener(new ActionLister_SalesInq("comBox_Day"));
		panel_7_2.add(comBox_Day);
		
		JLabel lb_Day = new JLabel("일");
		lb_Day.setFont(new Font("굴림", Font.PLAIN, 20));
		panel_7_2.add(lb_Day);
		
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
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{178, 0};
		gbl_panel_8.rowHeights = new int[]{15, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		lb_date = new JLabel("");
		lb_date.setFont(new Font("굴림", Font.PLAIN, 20));
		GridBagConstraints gbc_lb_date = new GridBagConstraints();
		gbc_lb_date.fill = GridBagConstraints.VERTICAL;
		gbc_lb_date.anchor = GridBagConstraints.WEST;
		gbc_lb_date.gridx = 0;
		gbc_lb_date.gridy = 0;
		panel_8.add(lb_date, gbc_lb_date);
		btn_Inquiry.setFont(new Font("굴림", Font.PLAIN, 32));
		panel_6.add(btn_Inquiry);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		panel_9.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{678, 0};
		gbl_panel.rowHeights = new int[]{377, 206, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 0);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 0;
		gbc_panel_10.gridy = 0;
		panel.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{842, 0};
		gbl_panel_10.rowHeights = new int[]{30, 221, 0};
		gbl_panel_10.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JLabel lblNewLabel_1 = new JLabel("일별 판매 기록");
		lblNewLabel_1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_10.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		scrPane_SalesRecord = new JScrollPane();
		GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
		gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6.gridx = 0;
		gbc_scrollPane_6.gridy = 1;
		panel_10.add(scrPane_SalesRecord, gbc_scrollPane_6);
		
		JPanel panel_10_1 = new JPanel();
		GridBagConstraints gbc_panel_10_1 = new GridBagConstraints();
		gbc_panel_10_1.fill = GridBagConstraints.BOTH;
		gbc_panel_10_1.gridx = 0;
		gbc_panel_10_1.gridy = 1;
		panel.add(panel_10_1, gbc_panel_10_1);
		GridBagLayout gbl_panel_10_1 = new GridBagLayout();
		gbl_panel_10_1.columnWidths = new int[]{353, 0, 0};
		gbl_panel_10_1.rowHeights = new int[]{30, 161, 0};
		gbl_panel_10_1.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_10_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_10_1.setLayout(gbl_panel_10_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("이용석별 기록");
		lblNewLabel_1_1.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 0;
		panel_10_1.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		JLabel lb_Inquiry = new JLabel("조회 기간 합산집계");
		lb_Inquiry.setFont(new Font("휴먼엑스포", Font.BOLD, 25));
		GridBagConstraints gbc_lb_WhatSales_1 = new GridBagConstraints();
		gbc_lb_WhatSales_1.insets = new Insets(0, 0, 5, 0);
		gbc_lb_WhatSales_1.gridx = 1;
		gbc_lb_WhatSales_1.gridy = 0;
		panel_10_1.add(lb_Inquiry, gbc_lb_WhatSales_1);
		
		scrPane_SalesBySeat = new JScrollPane();
		GridBagConstraints gbc_scrPane_SalesBySeat = new GridBagConstraints();
		gbc_scrPane_SalesBySeat.insets = new Insets(0, 0, 0, 5);
		gbc_scrPane_SalesBySeat.fill = GridBagConstraints.BOTH;
		gbc_scrPane_SalesBySeat.gridx = 0;
		gbc_scrPane_SalesBySeat.gridy = 1;
		panel_10_1.add(scrPane_SalesBySeat, gbc_scrPane_SalesBySeat);
		
		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 1;
		gbc_panel_12.gridy = 1;
		panel_10_1.add(panel_12, gbc_panel_12);
		panel_12.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_12.add(panel_13);
		GridBagLayout gbl_panel_13 = new GridBagLayout();
		gbl_panel_13.columnWidths = new int[]{210, 45, 0};
		gbl_panel_13.rowHeights = new int[]{80, 0};
		gbl_panel_13.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_13.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_13.setLayout(gbl_panel_13);
		
		JLabel lb_T = new JLabel("\uCD1D \uC774\uC6A9\uAC1D \uC218  ");
		lb_T.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_T.setFont(new Font("휴먼엑스포", Font.BOLD, 25));
		GridBagConstraints gbc_lb_T = new GridBagConstraints();
		gbc_lb_T.fill = GridBagConstraints.VERTICAL;
		gbc_lb_T.anchor = GridBagConstraints.EAST;
		gbc_lb_T.insets = new Insets(0, 0, 0, 5);
		gbc_lb_T.gridx = 0;
		gbc_lb_T.gridy = 0;
		panel_13.add(lb_T, gbc_lb_T);
		
		lb_TotUsers = new JLabel("0");
		lb_TotUsers.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_TotUsers.setFont(new Font("휴먼매직체", Font.BOLD, 30));
		GridBagConstraints gbc_lb_TotUsers = new GridBagConstraints();
		gbc_lb_TotUsers.fill = GridBagConstraints.VERTICAL;
		gbc_lb_TotUsers.anchor = GridBagConstraints.EAST;
		gbc_lb_TotUsers.gridx = 1;
		gbc_lb_TotUsers.gridy = 0;
		panel_13.add(lb_TotUsers, gbc_lb_TotUsers);
		
		JPanel panel_14 = new JPanel();
		panel_12.add(panel_14);
		GridBagLayout gbl_panel_14 = new GridBagLayout();
		gbl_panel_14.columnWidths = new int[]{210, 70, 0};
		gbl_panel_14.rowHeights = new int[]{80, 0};
		gbl_panel_14.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_14.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_14.setLayout(gbl_panel_14);
		
		lb_WhatSales = new JLabel("매출");
		lb_WhatSales.setFont(new Font("휴먼엑스포", Font.BOLD, 25));
		GridBagConstraints gbc_lb_WhatSales = new GridBagConstraints();
		gbc_lb_WhatSales.insets = new Insets(0, 0, 0, 5);
		gbc_lb_WhatSales.gridx = 0;
		gbc_lb_WhatSales.gridy = 0;
		panel_14.add(lb_WhatSales, gbc_lb_WhatSales);
		
		lb_TotSales = new JLabel("0");
		lb_TotSales.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_TotSales.setFont(new Font("휴먼매직체", Font.BOLD, 30));
		GridBagConstraints gbc_lb_TotSales = new GridBagConstraints();
		gbc_lb_TotSales.anchor = GridBagConstraints.EAST;
		gbc_lb_TotSales.gridx = 1;
		gbc_lb_TotSales.gridy = 0;
		panel_14.add(lb_TotSales, gbc_lb_TotSales);
	}

	@Override
	public void receive(PacketBase packet) {
		SmSalesInquiryAck ack = (SmSalesInquiryAck) packet;
		ArrayList<SalesRecord> sr = ack.salesD.salesRecordArrL;
//System.out.println(ack.salesD.salesRecordArrL.get(0).dateStr);
		ArrayList<SalesBySeat> sb = ack.salesD.saleBySeatArrL;
		SalesTot st = ack.salesD.salesTot;
		
		//scrPane_SalesRecord
			String [] header_Record = {"날짜","이용석","이용자","이용자ID","금액","구매기록"};
			String contents_Records[][] = new String [sr.size()][header_Record.length];
				for (int i = 0; i < sr.size(); i++) {
					contents_Records[i][0] = sr.get(i).dateStr;
					contents_Records[i][1] = sr.get(i).room_name;
					contents_Records[i][2] = sr.get(i).user_name;
					contents_Records[i][3] = sr.get(i).user_id;
					contents_Records[i][4] = sr.get(i).room_price_Tot;
					String [] time = sr.get(i).hourListStr.split(",");
					String ttt = "";
					for (String t : time) {
						ttt += t + "시 ";
					}
					contents_Records[i][5] = ttt;
				}
			
			DefaultTableModel tModel_Record = new DefaultTableModel(contents_Records,header_Record);
			JTable table_Record = new JTable(tModel_Record);
			resizeColumnWidth(table_Record);
			table_Record.setRowHeight(27);
			table_Record.setFillsViewportHeight(true);
			table_Record.setFont(new Font("새굴림", Font.PLAIN, 25));
			scrPane_SalesRecord.setViewportView(table_Record);
		
		//scrPane_SalesBySeat
			String [] header_Seat = {"이용석","이용객 수","합계 금액"};
			String contents_Seat[][] = new String [sb.size()][header_Seat.length];
				for (int i = 0; i < sb.size(); i++) {
					contents_Seat[i][0] = sb.get(i).room_name;
					contents_Seat[i][1] = sb.get(i).cnt+"";
					contents_Seat[i][2] = sb.get(i).sum+"";
					
				}
			DefaultTableModel tModel_Seat = new DefaultTableModel(contents_Seat,header_Seat);
			JTable table_Seat = new JTable(tModel_Seat);
			resizeColumnWidth(table_Seat);
			table_Seat.setRowHeight(27);
			table_Seat.setFillsViewportHeight(true);
			table_Seat.setFont(new Font("새굴림", Font.PLAIN, 25));
			scrPane_SalesBySeat.setViewportView(table_Seat);
			
		//SalesTot
			String ttt = st.dateSortN==2?"연 매출":st.dateSortN==5?"월 매출":st.dateSortN==8?"당일 매출":"";
			lb_WhatSales.setText(ttt);
			lb_TotUsers.setText(st.cntTot+" 명");
			lb_TotSales.setText("  "+st.sumTot+" 원");
	}
	
	public void resizeColumnWidth(JTable table) { 
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumnModel columnModel = table.getColumnModel(); 
		for (int column = 0; column < table.getColumnCount(); column++) { 
			int width = 40; // Min width 
				for (int row = 0; row < table.getRowCount(); row++) { 
				TableCellRenderer renderer = table.getCellRenderer(row, column); 
				Component comp = table.prepareRenderer(renderer, row, column); 
				int wi = comp.getPreferredSize().width +1;
				width = Math.max( wi, width); 
				} 
		columnModel.getColumn(column).setPreferredWidth(width*2+20); 
		} 
	}
}
