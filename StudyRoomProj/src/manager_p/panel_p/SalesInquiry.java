package manager_p.panel_p;

import java.awt.BorderLayout;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class SalesInquiry extends JPanel {
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
	public Vector<Integer> yearList = new Vector<Integer>();
	public Vector<Integer> monthList = new Vector<Integer>();
	public Vector<Integer> dayList = new Vector<Integer>();
	public Calendar today = Calendar.getInstance();
	
	class ActionLister_SalesInq implements ActionListener{
		String sort;
		
		public ActionLister_SalesInq(String sort) {
			this.sort = sort;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (sort) {
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
				}
				
				break;
			case "chBox_Month":
				if(chBox_Month.isSelected()) {
					comBox_Month.setEnabled(true);
				}else {
					comBox_Month.setEnabled(false);
				}
				break;
			case "comBox_Year":
				if((int)comBox_Year.getSelectedItem()!=today.get(Calendar.YEAR)) {
					for (int i = 1; i <= 12; i++) {
						monthList.add(i);
					}
				}else {
					for (int i = 1; i <= today.get(Calendar.MONTH)+1; i++) {
						monthList.add(i);
					}
				}
				comBox_Month = new JComboBox<Integer>(monthList);
				break;
				
			case "comBox_Month":
				if((int)comBox_Month.getSelectedItem()!=today.get(Calendar.MONTH)+1) {
					int year = today.get(Calendar.YEAR);
					int mon = today.get(Calendar.MONTH)+1;
					int day = 1;
					today.set(year,mon,1);
					int last = today.getActualMaximum(Calendar.DAY_OF_MONTH);
					for (int i = 1; i <= last; i++) {
						dayList.add(i);
					}
				}else {
					for (int i = 1; i <= today.get(Calendar.DATE); i++) {
						dayList.add(i);
					}
				}
				comBox_Day = new JComboBox<Integer>(dayList);
				break;

			default:
				break;
			}
		}
		
	}
	
	public void setDateList() {
		//ÇöÀç ½Ã°£ ÀÌÀüÀÇ
		//ÇØ ¸®½ºÆ®
		for (int i = today.get(Calendar.YEAR); i >= 2000; i--) {
			yearList.add(i);
		}
		comBox_Year = new JComboBox<Integer>(yearList);
		yearList.removeAllElements();
		//´Þ ¸®½ºÆ®
		for (int i = 1; i <= today.get(Calendar.MONTH)+1; i++) {
			monthList.add(i);
		}
		comBox_Month = new JComboBox<Integer>(monthList);
		monthList.removeAllElements();
		//ÀÏ ¸®½ºÆ®
		for (int i = 1; i <= today.get(Calendar.DATE); i++) {
			dayList.add(i);
		}
		comBox_Day = new JComboBox<Integer>(dayList);
		dayList.removeAllElements();
	}
			
	public SalesInquiry() {
		setDateList();
//		public SetPrice(TestFrame tfram) {
//		this.tfram = tfram;
//		this.tfram.tabbedPane.addTab("¿ä±Ý °ü¸®", this);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_9 = new JPanel();
		add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] { 104, 910, 30};
		gbl_panel_9.rowHeights = new int[] { 144, 423, 43, 30, 0 };
		gbl_panel_9.columnWeights = new double[] { 1.0, 1.0, 0.0 };
		gbl_panel_9.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_9.setLayout(gbl_panel_9);

		String header[] = { "ÀÌ¿ë¼®", "ÀÌ¿ë°´", "±Ý¾×" };
		String contents[][] = { { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
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
		
		panel_7.add(comBox_Year);
		
		JLabel lb_Year= new JLabel("³â");
		lb_Year.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		panel_7.add(lb_Year);
		
		JPanel panel_7_1 = new JPanel();
		panel_4.add(panel_7_1);
		
		comBox_Month.addActionListener(new ActionLister_SalesInq("comBox_Month"));
		panel_7_1.add(comBox_Month);
		
		JLabel lb_Month = new JLabel("¿ù");
		lb_Month.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		panel_7_1.add(lb_Month);
		
		chBox_Month = new JCheckBox("");
		chBox_Month.addActionListener(new ActionLister_SalesInq("chBox_Month"));
		panel_7_1.add(chBox_Month);
		
		JPanel panel_7_2 = new JPanel();
		panel_4.add(panel_7_2);
		
		comBox_Day.addActionListener(new ActionLister_SalesInq("comBox_Day"));
		panel_7_2.add(comBox_Day);
		
		JLabel lb_Day = new JLabel("ÀÏ");
		lb_Day.setFont(new Font("±¼¸²", Font.PLAIN, 20));
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
		
		JButton btn_Inquiry = new JButton("Á¶È¸");
		btn_Inquiry.setFont(new Font("±¼¸²", Font.PLAIN, 32));
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
		lblNewLabel_1.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 30));
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
		lblNewLabel_1_1.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.PLAIN, 30));
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
		
		lb_WhatSales = new JLabel("¸ÅÃâ");
		lb_WhatSales.setFont(new  Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 25));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 1;
		panel_13_1.add(lb_WhatSales, gbc_lblNewLabel_2_1);
		
		JPanel panel_14_1 = new JPanel();
		panel_12_1.add(panel_14_1);
		panel_14_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lb_T = new JLabel("ÃÑ ÀÌ¿ë°´ ¼ö");
		lb_T.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_T.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 25));
		panel_14_1.add(lb_T);
		
		lb_TotUsers = new JLabel("0");
		lb_TotUsers.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_TotUsers.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.BOLD, 30));
		panel_14_1.add(lb_TotUsers);
		
		JPanel panel_15_1 = new JPanel();
		panel_12_1.add(panel_15_1);
		panel_15_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel lb_To = new JLabel("ÇÕ»ê ±Ý¾×");
		lb_To.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_To.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 25));
		panel_15_1.add(lb_To);
		
		lb_TotSales = new JLabel("0");
		lb_TotSales.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_TotSales.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.BOLD, 30));
		panel_15_1.add(lb_TotSales);
	}

}
