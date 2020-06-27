package manager_p.panel_p;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import data_p.ExcelRead_SeatExcel;

public class SetPrice extends JPanel {
	JFrame tfram;
	private String header[] = { "이용석", "시간당 금액", "단위" };
	private String contents[][] = new String [new ExcelRead_SeatExcel().rowDataArr(1).size()][header.length];
	private DefaultTableModel tableModel;
	private JTable table;
	
	public SetPrice() {
//		public SetPrice(TestFrame tfram) {
//		this.tfram = tfram;
//		this.tfram.tabbedPane.addTab("요금 관리", this);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 226, 489, 256, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);
		
		JPanel panel_22 = new JPanel();
		GridBagConstraints gbc_panel_22 = new GridBagConstraints();
		gbc_panel_22.insets = new Insets(0, 0, 5, 5);
		gbc_panel_22.fill = GridBagConstraints.BOTH;
		gbc_panel_22.gridx = 0;
		gbc_panel_22.gridy = 1;
		panel_4.add(panel_22, gbc_panel_22);

		JPanel panel_10_2 = new JPanel();
		GridBagConstraints gbc_panel_10_2 = new GridBagConstraints();
		gbc_panel_10_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_10_2.fill = GridBagConstraints.BOTH;
		gbc_panel_10_2.gridx = 1;
		gbc_panel_10_2.gridy = 1;
		panel_4.add(panel_10_2, gbc_panel_10_2);
		GridBagLayout gbl_panel_10_2 = new GridBagLayout();
		gbl_panel_10_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_10_2.rowHeights = new int[] { 0, 510, 0 };
		gbl_panel_10_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_10_2.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_10_2.setLayout(gbl_panel_10_2);

		JLabel lblNewLabel_1_2 = new JLabel("\uC694\uAE08 \uC124\uC815");
		lblNewLabel_1_2.setFont(new Font("휴먼모음T", Font.BOLD, 40));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1_2.gridx = 0;
		gbc_lblNewLabel_1_2.gridy = 0;
		panel_10_2.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);

		JScrollPane scrollPane_6_2 = new JScrollPane();
		scrollPane_6_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_6_2 = new GridBagConstraints();
		gbc_scrollPane_6_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6_2.gridx = 0;
		gbc_scrollPane_6_2.gridy = 1;
		panel_10_2.add(scrollPane_6_2, gbc_scrollPane_6_2);
		
		ExcelRead_SeatExcel seatEx = new ExcelRead_SeatExcel();
		for (int i = 0; i < new ExcelRead_SeatExcel().rowDataArr(1).size(); i++) {
			contents[i][0] = seatEx.rowDataArr(1).get(i);
			contents[i][1] = seatEx.rowDataArr(2).get(i);
			contents[i][2] = "원";
		}
		System.out.println(contents[2][1]);
		
		//요금 테이블
		tableModel = new DefaultTableModel(contents, header);
		table = new JTable(tableModel);
		table.setRowHeight(27);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("새굴림", Font.PLAIN, 25));
		scrollPane_6_2.setViewportView(table);
		
		JPanel panel_18 = new JPanel();
		GridBagConstraints gbc_panel_18 = new GridBagConstraints();
		gbc_panel_18.anchor = GridBagConstraints.SOUTH;
		gbc_panel_18.insets = new Insets(0, 0, 5, 0);
		gbc_panel_18.gridx = 2;
		gbc_panel_18.gridy = 1;
		panel_4.add(panel_18, gbc_panel_18);
		GridBagLayout gbl_panel_18 = new GridBagLayout();
		gbl_panel_18.columnWidths = new int[]{0, 0};
		gbl_panel_18.rowHeights = new int[]{0, 0, 5, 5, 0, 0};
		gbl_panel_18.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_18.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_18.setLayout(gbl_panel_18);
		
		
		JButton btn_SetPrice = new JButton("적용");
		btn_SetPrice.addActionListener(new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				int columCnt = table.getColumnCount();
				int rowCnt = table.getRowCount();
				for (int i = 0; i < columCnt; i++) {
					
				}
			}
		});
		btn_SetPrice.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		GridBagConstraints gbc_btn_SetPrice = new GridBagConstraints();
		gbc_btn_SetPrice.gridx = 0;
		gbc_btn_SetPrice.gridy = 4;
		panel_18.add(btn_SetPrice, gbc_btn_SetPrice);
	}

}
