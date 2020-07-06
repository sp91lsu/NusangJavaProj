package manager_p.panelDialog_p;

import java.awt.BorderLayout;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import data_p.sales_p.SalesBySeat;
import data_p.sales_p.SalesRecord;
import data_p.sales_p.SalesTot;
import manager_p.ManagerWindow;
import manager_p.syn_p.MsSalesInquirySyn;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.SmResvRoomAck;
import server_p.packet_p.ack_p.SmSalesInquiryAck;
import java.awt.Color;
import java.awt.SystemColor;

public class ResvInquiry extends JPanel implements Receivable {
	JFrame tfram;
	ManagerWindow mw;
	
	private ArrayList<SalesRecord> salesRecordList;
	private DefaultTableModel dTable5;
	private String headerResvs[] = new String[] { "이용석", "예약자", "예약시간" };
	private String contentsResvs[][];
	private JTable table;
	private JScrollPane scrollPane_12;
	
	
	//생성자
	public ResvInquiry(ManagerWindow mw) {
		this.mw = mw;
		PacketMap.getInstance().map.put(SmResvRoomAck.class, this);
		// 예약
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.DARK_GRAY);
		add(panel_3);
		
		panel_3.setLayout(null);

		CalendarTest cal = new CalendarTest(mw);
		cal.setBackground(Color.WHITE);
		cal.setBounds(197, 24, 502, 362);
		panel_3.add(cal);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(-28, 368, 887, 305);
		cal.add(scrollPane);

		scrollPane_12 = new JScrollPane();
		scrollPane_12.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_12.setBounds(44, 408, 842, 311);
		panel_3.add(scrollPane_12);

		contentsResvs = new String[0][headerResvs.length];
		dTable5 = new DefaultTableModel(contentsResvs, headerResvs);
		table = new JTable(dTable5);
		table.setForeground(Color.DARK_GRAY);
		table.setBackground(Color.WHITE);
		table.getColumn("이용석").setPreferredWidth(60);
		table.getColumn("예약자").setPreferredWidth(100);
		table.getColumn("예약시간").setPreferredWidth(400);
		table.setRowHeight(27);
		table.setFont(new Font("새굴림", Font.PLAIN, 25));
		JTableHeader tableHeader2 = table.getTableHeader();
	      Font headerFont2 = new Font("맑은 고딕", Font.BOLD, 17);
	      tableHeader2.setFont(headerFont2);
		table.setFillsViewportHeight(true);
		scrollPane_12.setViewportView(table);
		
	}

	@Override
	public void receive(PacketBase packet) {
		if (packet.getClass() == SmResvRoomAck.class) {
			System.out.println("매니저가 받았다 액크");
			SmResvRoomAck ack = (SmResvRoomAck) packet;
			salesRecordList = ack.rtd;

			contentsResvs = new String[salesRecordList.size()][headerResvs.length];
			for (int i = 0; i < salesRecordList.size(); i++) {
				contentsResvs[i][0] = salesRecordList.get(i).room_name;
				contentsResvs[i][1] = salesRecordList.get(i).user_name;
				String [] time = salesRecordList.get(i).hourListStr.split(",");
				String ttt = "";
				for (String t : time) {
					ttt += t + "시 ";
				}
				contentsResvs[i][2] = ttt;
			}

			dTable5 = new DefaultTableModel(contentsResvs, headerResvs);
			table = new JTable(dTable5);
			table.setForeground(Color.DARK_GRAY);
			table.setBackground(Color.WHITE);
			table.getColumn("이용석").setPreferredWidth(60);
			table.getColumn("예약자").setPreferredWidth(100);
			table.getColumn("예약시간").setPreferredWidth(400);
			table.setRowHeight(27);
			table.setFont(new Font("새굴림", Font.PLAIN, 25));
			JTableHeader tableHeader2 = table.getTableHeader();
		      Font headerFont2 = new Font("맑은 고딕", Font.BOLD, 17);
		      tableHeader2.setFont(headerFont2);
			table.setFillsViewportHeight(true);
			scrollPane_12.setViewportView(table);

		}
	}
}
