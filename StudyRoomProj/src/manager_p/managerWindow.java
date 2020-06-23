package manager_p;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsChatSyn;
import client_p.ui_p.LockerMain;
import client_p.ui_p.Seating_Arrangement;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.syn_p.SMChatConnectSyn;
import server_p.packet_p.syn_p.ScChatSyn;
import java.awt.Color;

public class managerWindow extends JFrame implements Receivable {
	private JTable table_1;
	private JScrollPane scrollPane_3;
	private JPanel panel_6;
	private CardLayout cl_panel_6;
	private JTable table_3;
	private JTable table_4;
	private JTextField textField;
	public JTabbedPane tabbedPane;
	
	String text = "";
	CsChatSyn chatSyn;
	private final static String newline = "\n";
	private JTextArea textArea;
	private JTable table;
	private JScrollPane scrollPane_3_1;
	private JPanel panel;
	private JTable table_2;
	
	public static void main(String[] args) {
		managerWindow mww = new managerWindow();
		PacketMap.getInstance().map.put(SMChatConnectSyn.class, mww); // Ã¤ÆÃ ¿¬°á ¿äÃ»¿¡ ´ëÇÑ ÀÀ´ä
		PacketMap.getInstance().map.put(ScChatBroadCast.class, mww);
		PacketMap.getInstance().map.put(CsChatSyn.class, mww);
		ClientNet.getInstance().start();
		
	}

	public managerWindow() {

		System.out.println("»ý¼º!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1280, 800);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setToolTipText("È¸¿ø");
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("\uD68C\uC6D0\uAD00\uB9AC", null, panel_5, null);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 162, 0, 0 };
		gbl_panel_5.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_5.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		panel_5.setLayout(gbl_panel_5);

		JPanel panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_7.anchor = GridBagConstraints.NORTH;
		gbc_panel_7.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 1;
		panel_5.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] { 0, 0 };
		gbl_panel_7.rowHeights = new int[] { 88, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_7.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_7.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_7.setLayout(gbl_panel_7);

		JLabel lblNewLabel = new JLabel("\uBA54\uB274");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		panel_7.add(lblNewLabel, gbc_lblNewLabel);

		JButton btnNewButton = new JButton("\uD604\uC7AC \uC774\uC6A9\uC911 \uACE0\uAC1D");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel_6.show(panel_6, "scrollPane_3");
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 3;
		panel_7.add(btnNewButton, gbc_btnNewButton);

		JButton btnNewButton_1 = new JButton("\uC804\uCCB4 \uACE0\uAC1D");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel_6.show(panel_6, "scrollPane_3_1");
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 5;
		panel_7.add(btnNewButton_1, gbc_btnNewButton_1);

		JButton btnNewButton_2 = new JButton("\uD68C\uC6D0\uAC80\uC0C9");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel_6.show(panel_6, "panel");
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 7;
		panel_7.add(btnNewButton_2, gbc_btnNewButton_2);

		panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 1;
		panel_5.add(panel_6, gbc_panel_6);
		cl_panel_6 = new CardLayout(0, 0);
		panel_6.setLayout(cl_panel_6);

		
		
		//ÇöÀç ÀÌ¿ëÁß °í°´
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_6.add("scrollPane_3", scrollPane_3);

		String header3[] = { "ÀÌ¿ë¼®", "ÀÌ¿ë°´", "±Ý¾×" };
		String contents3[][] = { { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, };
		table_1 = new JTable(contents3, header3);
		table_1.setRowHeight(27);
		table_1.setFillsViewportHeight(true);
		table_1.setFont(new Font("»õ±¼¸²", Font.PLAIN, 25));
		scrollPane_3.setViewportView(table_1);
		
		
		
		//ÀüÃ¼ È¸¿ø¸®½ºÆ®
		scrollPane_3_1 = new JScrollPane();
		scrollPane_3_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_3_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		panel_6.add("scrollPane_3_1",scrollPane_3_1);

		String header4[] = { "ÀÌ¿ë¼®", "ÀÌ¿ë°´", "±Ý¾×" };
		String contents4[][] = { { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, };
		table = new JTable(contents4, header4);
		table.setRowHeight(27);
		table.setFillsViewportHeight(true);
		table.setFont(new Font("»õ±¼¸²", Font.PLAIN, 25));
		scrollPane_3_1.setViewportView(table);
		
		
		
		//È¸¿ø°Ë»ö
		panel = new JPanel();
		panel.setBackground(Color.PINK);
		panel_6.add("panel",panel);
		
		
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\uC88C\uC11D/\uB8F8 \uAD00\uB9AC", null, panel_1, null);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 399, 926, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JPanel panel_7_1 = new JPanel();
		GridBagConstraints gbc_panel_7_1 = new GridBagConstraints();
		gbc_panel_7_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7_1.fill = GridBagConstraints.BOTH;
		gbc_panel_7_1.gridx = 0;
		gbc_panel_7_1.gridy = 0;
		panel_1.add(panel_7_1, gbc_panel_7_1);
		GridBagLayout gbl_panel_7_1 = new GridBagLayout();
		gbl_panel_7_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_7_1.rowHeights = new int[] { 88, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_7_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_7_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_7_1.setLayout(gbl_panel_7_1);

		JLabel lblNewLabel_6 = new JLabel("\uBA54\uB274");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 1;
		panel_7_1.add(lblNewLabel_6, gbc_lblNewLabel_6);

		JButton btnNewButton_3 = new JButton("\uC88C\uC11D/\uB8F8 \uBE44\uD65C\uC131\uD654");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 0;
		gbc_btnNewButton_3.gridy = 3;
		panel_7_1.add(btnNewButton_3, gbc_btnNewButton_3);

		JButton btnNewButton_1_1 = new JButton("\uC88C\uC11D/\uB8F8 \uD65C\uC131\uD654");
		GridBagConstraints gbc_btnNewButton_1_1 = new GridBagConstraints();
		gbc_btnNewButton_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1_1.gridx = 0;
		gbc_btnNewButton_1_1.gridy = 5;
		panel_7_1.add(btnNewButton_1_1, gbc_btnNewButton_1_1);

		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_7 = new GridBagConstraints();
		gbc_scrollPane_7.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_7.gridx = 1;
		gbc_scrollPane_7.gridy = 0;
		panel_1.add(scrollPane_7, gbc_scrollPane_7);

		Dimension size = new Dimension();// »çÀÌÁî¸¦ ÁöÁ¤ÇÏ±â À§ÇÑ °´Ã¼ »ý¼º
		size.setSize(900, 1000);// °´Ã¼ÀÇ »çÀÌÁî¸¦ ÁöÁ¤
		Seating_Arrangement seating_Arrangement = new Seating_Arrangement();
		seating_Arrangement.setPreferredSize(size);// »çÀÌÁî Á¤º¸¸¦ °¡Áö°í ÀÖ´Â
		scrollPane_7.setViewportView(seating_Arrangement);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\uC0AC\uBB3C\uD568 \uAD00\uB9AC", null, panel_2, null);

		JPanel panel_1_1 = new JPanel();
		panel_2.add(panel_1_1);
		GridBagLayout gbl_panel_1_1 = new GridBagLayout();
		gbl_panel_1_1.columnWidths = new int[] { 350, 920, 0 };
		gbl_panel_1_1.rowHeights = new int[] { 717, 0 };
		gbl_panel_1_1.columnWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_1_1.setLayout(gbl_panel_1_1);

		JPanel panel_7_1_1 = new JPanel();
		GridBagConstraints gbc_panel_7_1_1 = new GridBagConstraints();
		gbc_panel_7_1_1.fill = GridBagConstraints.BOTH;
		gbc_panel_7_1_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_7_1_1.gridx = 0;
		gbc_panel_7_1_1.gridy = 0;
		panel_1_1.add(panel_7_1_1, gbc_panel_7_1_1);
		GridBagLayout gbl_panel_7_1_1 = new GridBagLayout();
		gbl_panel_7_1_1.columnWidths = new int[] { 112, 0 };
		gbl_panel_7_1_1.rowHeights = new int[] { 88, 0, 0, 0, 0, 0, 0, 87, 0, 0, 0 };
		gbl_panel_7_1_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_7_1_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_7_1_1.setLayout(gbl_panel_7_1_1);

		JLabel lblNewLabel_6_1 = new JLabel("\uC0AC\uBB3C\uD568 \uC815\uBCF4");
		lblNewLabel_6_1.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.PLAIN, 35));
		GridBagConstraints gbc_lblNewLabel_6_1 = new GridBagConstraints();
		gbc_lblNewLabel_6_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_6_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6_1.gridx = 0;
		gbc_lblNewLabel_6_1.gridy = 1;
		panel_7_1_1.add(lblNewLabel_6_1, gbc_lblNewLabel_6_1);

		JPanel panel_16 = new JPanel();
		GridBagConstraints gbc_panel_16 = new GridBagConstraints();
		gbc_panel_16.insets = new Insets(0, 0, 5, 0);
		gbc_panel_16.fill = GridBagConstraints.BOTH;
		gbc_panel_16.gridx = 0;
		gbc_panel_16.gridy = 4;
		panel_7_1_1.add(panel_16, gbc_panel_16);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[] { 200, 0, 0 };
		gbl_panel_16.rowHeights = new int[] { 0, 0 };
		gbl_panel_16.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_16.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_16.setLayout(gbl_panel_16);

		JLabel lblNewLabel_7 = new JLabel("\uC0AC\uBB3C\uD568 \uC774\uC6A9\uC5EC\uBD80:");
		lblNewLabel_7.setFont(new Font("»õ±¼¸²", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 0;
		panel_16.add(lblNewLabel_7, gbc_lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.gridx = 1;
		gbc_lblNewLabel_8.gridy = 0;
		panel_16.add(lblNewLabel_8, gbc_lblNewLabel_8);

		JPanel panel_16_1 = new JPanel();
		GridBagConstraints gbc_panel_16_1 = new GridBagConstraints();
		gbc_panel_16_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_16_1.fill = GridBagConstraints.BOTH;
		gbc_panel_16_1.gridx = 0;
		gbc_panel_16_1.gridy = 6;
		panel_7_1_1.add(panel_16_1, gbc_panel_16_1);
		GridBagLayout gbl_panel_16_1 = new GridBagLayout();
		gbl_panel_16_1.columnWidths = new int[] { 200, 0, 0 };
		gbl_panel_16_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_16_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_16_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_16_1.setLayout(gbl_panel_16_1);

		JLabel lblNewLabel_7_1 = new JLabel("\uC0AC\uBB3C\uD568 \uBE44\uBC00\uBC88\uD638:");
		lblNewLabel_7_1.setFont(new Font("»õ±¼¸²", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel_7_1 = new GridBagConstraints();
		gbc_lblNewLabel_7_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7_1.gridx = 0;
		gbc_lblNewLabel_7_1.gridy = 0;
		panel_16_1.add(lblNewLabel_7_1, gbc_lblNewLabel_7_1);

		JLabel lblNewLabel_8_1 = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel_8_1 = new GridBagConstraints();
		gbc_lblNewLabel_8_1.gridx = 1;
		gbc_lblNewLabel_8_1.gridy = 0;
		panel_16_1.add(lblNewLabel_8_1, gbc_lblNewLabel_8_1);

		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 1;
		gbc_panel_11.gridy = 0;
		panel_1_1.add(panel_11, gbc_panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[] { 150, 0, 0, 0, 0 };
		gbl_panel_11.rowHeights = new int[] { 150, 0, 0, 0, 0 };
		gbl_panel_11.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_11.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_11.setLayout(gbl_panel_11);

		JButton btnNewButton_4_1_1 = new JButton("\uC0AC\uBB3C\uD568 1\uBC88");
		btnNewButton_4_1_1.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_1_1.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_1_1 = new GridBagConstraints();
		gbc_btnNewButton_4_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_1_1.gridx = 1;
		gbc_btnNewButton_4_1_1.gridy = 1;
		panel_11.add(btnNewButton_4_1_1, gbc_btnNewButton_4_1_1);

		JButton btnNewButton_4_1 = new JButton("\uC0AC\uBB3C\uD568 2\uBC88");
		btnNewButton_4_1.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_1.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_1 = new GridBagConstraints();
		gbc_btnNewButton_4_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_1.gridx = 2;
		gbc_btnNewButton_4_1.gridy = 1;
		panel_11.add(btnNewButton_4_1, gbc_btnNewButton_4_1);

		JButton btnNewButton_4_2 = new JButton("\uC0AC\uBB3C\uD568 3\uBC88");
		btnNewButton_4_2.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_2.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_2 = new GridBagConstraints();
		gbc_btnNewButton_4_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4_2.gridx = 3;
		gbc_btnNewButton_4_2.gridy = 1;
		panel_11.add(btnNewButton_4_2, gbc_btnNewButton_4_2);

		JButton btnNewButton_4_1_2 = new JButton("\uC0AC\uBB3C\uD568 4\uBC88");
		btnNewButton_4_1_2.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_1_2.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_1_2 = new GridBagConstraints();
		gbc_btnNewButton_4_1_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_1_2.gridx = 1;
		gbc_btnNewButton_4_1_2.gridy = 2;
		panel_11.add(btnNewButton_4_1_2, gbc_btnNewButton_4_1_2);

		JButton btnNewButton_4_3 = new JButton("\uC0AC\uBB3C\uD568 5\uBC88");
		btnNewButton_4_3.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_3.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_3 = new GridBagConstraints();
		gbc_btnNewButton_4_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4_3.gridx = 2;
		gbc_btnNewButton_4_3.gridy = 2;
		panel_11.add(btnNewButton_4_3, gbc_btnNewButton_4_3);

		JButton btnNewButton_4_1_3 = new JButton("\uC0AC\uBB3C\uD568 6\uBC88");
		btnNewButton_4_1_3.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_1_3.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_1_3 = new GridBagConstraints();
		gbc_btnNewButton_4_1_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_4_1_3.gridx = 3;
		gbc_btnNewButton_4_1_3.gridy = 2;
		panel_11.add(btnNewButton_4_1_3, gbc_btnNewButton_4_1_3);

		JButton btnNewButton_4_1_4 = new JButton("\uC0AC\uBB3C\uD568 7\uBC88");
		btnNewButton_4_1_4.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_1_4.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_1_4 = new GridBagConstraints();
		gbc_btnNewButton_4_1_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4_1_4.gridx = 1;
		gbc_btnNewButton_4_1_4.gridy = 3;
		panel_11.add(btnNewButton_4_1_4, gbc_btnNewButton_4_1_4);

		JButton btnNewButton_4_1_5 = new JButton("\uC0AC\uBB3C\uD568 8\uBC88");
		btnNewButton_4_1_5.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_1_5.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_1_5 = new GridBagConstraints();
		gbc_btnNewButton_4_1_5.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4_1_5.gridx = 2;
		gbc_btnNewButton_4_1_5.gridy = 3;
		panel_11.add(btnNewButton_4_1_5, gbc_btnNewButton_4_1_5);

		JButton btnNewButton_4_1_6 = new JButton("\uC0AC\uBB3C\uD568 9\uBC88");
		btnNewButton_4_1_6.setPreferredSize(new Dimension(210, 130));
		btnNewButton_4_1_6.setFont(new Font("»õ±¼¸²", Font.BOLD, 30));
		GridBagConstraints gbc_btnNewButton_4_1_6 = new GridBagConstraints();
		gbc_btnNewButton_4_1_6.gridx = 3;
		gbc_btnNewButton_4_1_6.gridy = 3;
		panel_11.add(btnNewButton_4_1_6, gbc_btnNewButton_4_1_6);

		Dimension size1 = new Dimension();// »çÀÌÁî¸¦ ÁöÁ¤ÇÏ±â À§ÇÑ °´Ã¼ »ý¼º
		size1.setSize(900, 1000);// °´Ã¼ÀÇ »çÀÌÁî¸¦ ÁöÁ¤
		LockerMain lockerMain = new LockerMain();
		lockerMain.setPreferredSize(size1);
		
				JPanel panel_3 = new JPanel();
				System.out.println(panel_3.getHeight());
				tabbedPane.addTab("\uC608\uC57D \uAD00\uB9AC", null, panel_3, null);
				panel_3.setLayout(null);
				
				JPanel panel_18 = new JPanel();
				panel_18.setBounds(40, 128, 550, 520);
				panel_3.add(panel_18);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("\uC694\uAE08 \uAD00\uB9AC", null, panel_4, null);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 300, 0, 300, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		JPanel panel_10_2 = new JPanel();
		GridBagConstraints gbc_panel_10_2 = new GridBagConstraints();
		gbc_panel_10_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_10_2.fill = GridBagConstraints.BOTH;
		gbc_panel_10_2.gridx = 1;
		gbc_panel_10_2.gridy = 1;
		panel_4.add(panel_10_2, gbc_panel_10_2);
		GridBagLayout gbl_panel_10_2 = new GridBagLayout();
		gbl_panel_10_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_10_2.rowHeights = new int[] { 0, 510, 0, 0 };
		gbl_panel_10_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_10_2.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panel_10_2.setLayout(gbl_panel_10_2);

		JLabel lblNewLabel_1_2 = new JLabel("\uB2F9\uC77C \uB9E4\uCD9C");
		lblNewLabel_1_2.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 40));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1_2.gridx = 0;
		gbc_lblNewLabel_1_2.gridy = 0;
		panel_10_2.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);

		JScrollPane scrollPane_6_2 = new JScrollPane();
		scrollPane_6_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_6_2 = new GridBagConstraints();
		gbc_scrollPane_6_2.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6_2.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_6_2.gridx = 0;
		gbc_scrollPane_6_2.gridy = 1;
		panel_10_2.add(scrollPane_6_2, gbc_scrollPane_6_2);
		
		
		
		//´çÀÏ¸ÅÃâ
		String header5[] = { "ÀÌ¿ë¼®", "ÀÌ¿ë°´", "±Ý¾×" };
		String contents5[][] = { { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, };
		table_2 = new JTable(contents5, header5);
		table_2.setRowHeight(27);
		table_2.setFillsViewportHeight(true);
		table_2.setFont(new Font("»õ±¼¸²", Font.PLAIN, 25));
		scrollPane_6_2.setViewportView(table_2);
		

		JPanel panel_12_2 = new JPanel();
		GridBagConstraints gbc_panel_12_2 = new GridBagConstraints();
		gbc_panel_12_2.fill = GridBagConstraints.BOTH;
		gbc_panel_12_2.gridx = 0;
		gbc_panel_12_2.gridy = 2;
		panel_10_2.add(panel_12_2, gbc_panel_12_2);
		panel_12_2.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel panel_13_2 = new JPanel();
		panel_12_2.add(panel_13_2);
		GridBagLayout gbl_panel_13_2 = new GridBagLayout();
		gbl_panel_13_2.columnWidths = new int[] { 150, 0 };
		gbl_panel_13_2.rowHeights = new int[] { 0, 56, 0, 0 };
		gbl_panel_13_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_13_2.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_13_2.setLayout(gbl_panel_13_2);

		JLabel lblNewLabel_2_2 = new JLabel("\uB2F9\uC77C \uB9E4\uCD9C");
		lblNewLabel_2_2.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		GridBagConstraints gbc_lblNewLabel_2_2 = new GridBagConstraints();
		gbc_lblNewLabel_2_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2_2.gridx = 0;
		gbc_lblNewLabel_2_2.gridy = 1;
		panel_13_2.add(lblNewLabel_2_2, gbc_lblNewLabel_2_2);

		JPanel panel_14_2 = new JPanel();
		panel_12_2.add(panel_14_2);
		panel_14_2.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_3_2 = new JLabel("\uCD1D \uAE08\uC561");
		lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_2.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		panel_14_2.add(lblNewLabel_3_2);

		JLabel lblNewLabel_4_3 = new JLabel("2000");
		lblNewLabel_4_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_3.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.BOLD, 30));
		panel_14_2.add(lblNewLabel_4_3);

		JPanel panel_15_2 = new JPanel();
		panel_12_2.add(panel_15_2);
		panel_15_2.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_5_2 = new JLabel("\uCD1D \uC774\uC6A9\uAC1D");
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		panel_15_2.add(lblNewLabel_5_2);

		JLabel lblNewLabel_4_1_2 = new JLabel("2000");
		lblNewLabel_4_1_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_2.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.BOLD, 30));
		panel_15_2.add(lblNewLabel_4_1_2);

		JPanel panel_9 = new JPanel();
		tabbedPane.addTab("\uB9E4\uCD9C \uC870\uD68C", null, panel_9, null);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[] { 31, 575, 122, 575, 0, 0 };
		gbl_panel_9.rowHeights = new int[] { 0, 780, 0, 0 };
		gbl_panel_9.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_9.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_9.setLayout(gbl_panel_9);

		JPanel panel_10 = new JPanel();
		GridBagConstraints gbc_panel_10 = new GridBagConstraints();
		gbc_panel_10.insets = new Insets(0, 0, 5, 5);
		gbc_panel_10.fill = GridBagConstraints.BOTH;
		gbc_panel_10.gridx = 1;
		gbc_panel_10.gridy = 1;
		panel_9.add(panel_10, gbc_panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[] { 0, 0 };
		gbl_panel_10.rowHeights = new int[] { 0, 510, 0, 0 };
		gbl_panel_10.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_10.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panel_10.setLayout(gbl_panel_10);

		JLabel lblNewLabel_1 = new JLabel("\uB2F9\uC77C \uB9E4\uCD9C");
		lblNewLabel_1.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 40));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_10.add(lblNewLabel_1, gbc_lblNewLabel_1);

		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_6 = new GridBagConstraints();
		gbc_scrollPane_6.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_6.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6.gridx = 0;
		gbc_scrollPane_6.gridy = 1;
		panel_10.add(scrollPane_6, gbc_scrollPane_6);

		String header[] = { "ÀÌ¿ë¼®", "ÀÌ¿ë°´", "±Ý¾×" };
		String contents[][] = { { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, { "»þ¿ö½Ç", "2", "30000" }, { "ÀÏ¹Ý1", "3", "34003" }, { "¤·¤©¤§¤§", "4", "34534" },
				{ "dfeb", "5", "234767" }, };
		table_3 = new JTable(contents, header);
		table_3.setRowHeight(27);
		table_3.setFillsViewportHeight(true);
		table_3.setFont(new Font("»õ±¼¸²", Font.PLAIN, 25));
		scrollPane_6.setViewportView(table_3);

		JPanel panel_12 = new JPanel();
		GridBagConstraints gbc_panel_12 = new GridBagConstraints();
		gbc_panel_12.fill = GridBagConstraints.BOTH;
		gbc_panel_12.gridx = 0;
		gbc_panel_12.gridy = 2;
		panel_10.add(panel_12, gbc_panel_12);
		panel_12.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel panel_13 = new JPanel();
		panel_12.add(panel_13);
		GridBagLayout gbl_panel_13 = new GridBagLayout();
		gbl_panel_13.columnWidths = new int[] { 150, 0 };
		gbl_panel_13.rowHeights = new int[] { 0, 56, 0, 0 };
		gbl_panel_13.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_13.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_13.setLayout(gbl_panel_13);

		JLabel lblNewLabel_2 = new JLabel("\uB2F9\uC77C \uB9E4\uCD9C");
		lblNewLabel_2.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 1;
		panel_13.add(lblNewLabel_2, gbc_lblNewLabel_2);

		JPanel panel_14 = new JPanel();
		panel_12.add(panel_14);
		panel_14.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_3 = new JLabel("\uCD1D \uAE08\uC561");
		lblNewLabel_3.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_14.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("2000");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.BOLD, 30));
		panel_14.add(lblNewLabel_4);

		JPanel panel_15 = new JPanel();
		panel_12.add(panel_15);
		panel_15.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_5 = new JLabel("\uCD1D \uC774\uC6A9\uAC1D");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		panel_15.add(lblNewLabel_5);

		JLabel lblNewLabel_4_1 = new JLabel("2000");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.BOLD, 30));
		panel_15.add(lblNewLabel_4_1);

		JPanel panel_10_1 = new JPanel();
		GridBagConstraints gbc_panel_10_1 = new GridBagConstraints();
		gbc_panel_10_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_10_1.fill = GridBagConstraints.BOTH;
		gbc_panel_10_1.gridx = 3;
		gbc_panel_10_1.gridy = 1;
		panel_9.add(panel_10_1, gbc_panel_10_1);
		GridBagLayout gbl_panel_10_1 = new GridBagLayout();
		gbl_panel_10_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_10_1.rowHeights = new int[] { 0, 507, 0, 0 };
		gbl_panel_10_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_10_1.rowWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panel_10_1.setLayout(gbl_panel_10_1);

		JLabel lblNewLabel_1_1 = new JLabel("\uC6D4\uAC04 \uB9E4\uCD9C");
		lblNewLabel_1_1.setFont(new Font("ÈÞ¸Õ¸ðÀ½T", Font.BOLD, 40));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1_1.gridx = 0;
		gbc_lblNewLabel_1_1.gridy = 0;
		panel_10_1.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);

		JScrollPane scrollPane_6_1 = new JScrollPane();
		scrollPane_6_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_6_1 = new GridBagConstraints();
		gbc_scrollPane_6_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_6_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_6_1.gridx = 0;
		gbc_scrollPane_6_1.gridy = 1;
		panel_10_1.add(scrollPane_6_1, gbc_scrollPane_6_1);

		table_4 = new JTable();
		scrollPane_6_1.setViewportView(table_4);

		JPanel panel_12_1 = new JPanel();
		GridBagConstraints gbc_panel_12_1 = new GridBagConstraints();
		gbc_panel_12_1.fill = GridBagConstraints.BOTH;
		gbc_panel_12_1.gridx = 0;
		gbc_panel_12_1.gridy = 2;
		panel_10_1.add(panel_12_1, gbc_panel_12_1);
		panel_12_1.setLayout(new GridLayout(1, 3, 0, 0));

		JPanel panel_13_1 = new JPanel();
		panel_12_1.add(panel_13_1);
		GridBagLayout gbl_panel_13_1 = new GridBagLayout();
		gbl_panel_13_1.columnWidths = new int[] { 150, 0 };
		gbl_panel_13_1.rowHeights = new int[] { 0, 56, 0, 0 };
		gbl_panel_13_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_13_1.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_13_1.setLayout(gbl_panel_13_1);

		JLabel lblNewLabel_2_1 = new JLabel("\uC6D4\uAC04 \uB9E4\uCD9C");
		lblNewLabel_2_1.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		GridBagConstraints gbc_lblNewLabel_2_1 = new GridBagConstraints();
		gbc_lblNewLabel_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2_1.gridx = 0;
		gbc_lblNewLabel_2_1.gridy = 1;
		panel_13_1.add(lblNewLabel_2_1, gbc_lblNewLabel_2_1);

		JPanel panel_14_1 = new JPanel();
		panel_12_1.add(panel_14_1);
		panel_14_1.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_3_1 = new JLabel("\uCD1D \uAE08\uC561");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		panel_14_1.add(lblNewLabel_3_1);

		JLabel lblNewLabel_4_2 = new JLabel("2000");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_2.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.BOLD, 30));
		panel_14_1.add(lblNewLabel_4_2);

		JPanel panel_15_1 = new JPanel();
		panel_12_1.add(panel_15_1);
		panel_15_1.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblNewLabel_5_1 = new JLabel("\uCD1D \uC774\uC6A9\uAC1D");
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setFont(new Font("ÈÞ¸Õ¿¢½ºÆ÷", Font.BOLD, 30));
		panel_15_1.add(lblNewLabel_5_1);

		JLabel lblNewLabel_4_1_1 = new JLabel("2000");
		lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4_1_1.setFont(new Font("ÈÞ¸Õ¸ÅÁ÷Ã¼", Font.BOLD, 30));
		panel_15_1.add(lblNewLabel_4_1_1);

		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("1:1\uCC44\uD305", null, panel_8, null);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[] { 0, 388, 79, 678, 131, 0 };
		gbl_panel_8.rowHeights = new int[] { 59, 603, 71, 0 };
		gbl_panel_8.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_8.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_8.setLayout(gbl_panel_8);

		JPanel panel_17 = new JPanel();
		GridBagConstraints gbc_panel_17 = new GridBagConstraints();
		gbc_panel_17.insets = new Insets(0, 0, 5, 5);
		gbc_panel_17.fill = GridBagConstraints.BOTH;
		gbc_panel_17.gridx = 1;
		gbc_panel_17.gridy = 1;
		panel_8.add(panel_17, gbc_panel_17);
		GridBagLayout gbl_panel_17 = new GridBagLayout();
		gbl_panel_17.columnWidths = new int[] { 0, 0 };
		gbl_panel_17.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_17.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_17.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_17.setLayout(gbl_panel_17);

		JLabel lblNewLabel_9 = new JLabel("1:1 \uCC44\uD305 \uBB38\uC758");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 1;
		panel_17.add(lblNewLabel_9, gbc_lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("\uC774\uB984");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 3;
		panel_17.add(lblNewLabel_10, gbc_lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("\uB2D8\uACFC \uCC44\uD305\uC911\uC785\uB2C8\uB2E4");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 4;
		panel_17.add(lblNewLabel_11, gbc_lblNewLabel_11);

		JPanel panel_19 = new JPanel();
		GridBagConstraints gbc_panel_19 = new GridBagConstraints();
		gbc_panel_19.insets = new Insets(0, 0, 5, 5);
		gbc_panel_19.fill = GridBagConstraints.BOTH;
		gbc_panel_19.gridx = 3;
		gbc_panel_19.gridy = 1;
		panel_8.add(panel_19, gbc_panel_19);
		GridBagLayout gbl_panel_19 = new GridBagLayout();
		gbl_panel_19.columnWidths = new int[] { 0, 0 };
		gbl_panel_19.rowHeights = new int[] { 519, 56, 0 };
		gbl_panel_19.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_19.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel_19.setLayout(gbl_panel_19);

		textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		panel_19.add(textArea, gbc_textArea);

		JPanel panel_20 = new JPanel();
		GridBagConstraints gbc_panel_20 = new GridBagConstraints();
		gbc_panel_20.fill = GridBagConstraints.BOTH;
		gbc_panel_20.gridx = 0;
		gbc_panel_20.gridy = 1;
		panel_19.add(panel_20, gbc_panel_20);
		GridBagLayout gbl_panel_20 = new GridBagLayout();
		gbl_panel_20.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_20.rowHeights = new int[] { 0, 0 };
		gbl_panel_20.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_20.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel_20.setLayout(gbl_panel_20);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel_20.add(textField, gbc_textField);
		textField.setColumns(10);
		
		//ÅØ½ºÆ® ÀÔ·Â ¾×¼Ç
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = textField.getText();
				chatSyn.setText(text);
				ClientNet.getInstance().sendPacket(chatSyn);
				textArea.append(text + newline);
				textField.selectAll();
				textArea.setCaretPosition(textArea.getParent().getWidth());
			}
		});

		JButton btnNewButton_4 = new JButton("\uC804\uC1A1");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 0;
		panel_20.add(btnNewButton_4, gbc_btnNewButton_4);

		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!textField.getText().equals("")) {
					
					text = textField.getText();
					chatSyn.setText(text);
					ClientNet.getInstance().sendPacket(chatSyn);
					textArea.append(text + newline);
					textField.selectAll();
					textArea.setCaretPosition(textArea.getParent().getWidth());
					textField.setText("");
				}
			}
		});
		
	}

	protected JPanel getPanel_6() {
		return panel_6;
	}

	@Override
	public void receive(PacketBase packet) {
		System.out.println(packet.getClass());
		if(packet.getClass() == SMChatConnectSyn.class) {
			SMChatConnectSyn sccAck = (SMChatConnectSyn) packet;
			if (sccAck.eResult == EResult.SUCCESS) {
				System.out.println("ÇÏ¾Ì");
				ChatReqDialog dialog = new ChatReqDialog(this,sccAck);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		}
		if(packet.getClass() == ScChatBroadCast.class) {
			ScChatBroadCast scChat = (ScChatBroadCast)packet;
			textArea.setText(textArea.getText()+"\n"+scChat.getText());
		}
		
	}
}

//class CardSwitch implements ActionListener {
//
//	CardLayout card;
//	JPanel jp;
//
//	public CardSwitch(JPanel parentPanel, CardLayout cardLay) {
//		this.card = cardLay;
//		this.jp = parentPanel;
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		String str = e.getActionCommand();
//		switch (str) {
//		case "ÇöÀç ÀÌ¿ëÁß °í°´":
//			card.show(jp, "scrollPane_3");
//			break;
//		case "ÀüÃ¼ °í°´":
//			card.show(jp, "scrollPane_3_1");
//			break;
////		case "È¸¿ø °Ë»ö":
////			card.show(jp, "scrollPane_5");
////			break;
//
//		default:
//			break;
//		}
//	}
//
//}
