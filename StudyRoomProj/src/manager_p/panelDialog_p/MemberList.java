package manager_p.panelDialog_p;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import client_p.ui_p.MyColor;
import data_p.user_p.UserData;
import manager_p.ManagerWindow;
import manager_p.syn_p.MsAllMemListSyn;
import manager_p.syn_p.MsCurrMemListSyn;
import manager_p.syn_p.MsMemSearchSyn;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.SmAllMemListAck;
import server_p.packet_p.ack_p.SmCurrMemListAck;
import server_p.packet_p.ack_p.SmMemSearchAck;

public class MemberList extends JPanel implements Receivable {
	ManagerWindow mw;
	
	private JPanel panel_6;
	private CardLayout cl_panel_6;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JLabel lbSearch;
	private JScrollPane scrollPane_3;
	private String contentsCurrMem[][];
	private DefaultTableModel dTable;
	private DefaultTableModel dTable2;
	private JScrollPane scrollPane_3_1;
	private JScrollPane scrollPane_3_2;
	private String idxNameMemS;
	private String contentsMemS;
	private String searchList[] = new String[] { "¿Ã∏ß", "ID", "»ﬁ¥Î∆˘ π¯»£" };
	private String[] headerMem = new String[] { "¿Ã∏ß", "ID", "»ﬁ¥Î∆˘π¯»£", "ª˝≥‚ø˘¿œ" };
	private String contentsAllMem[][];
	private String[][] contentsMemSearch = new String[1][headerMem.length];
	private JTable table_1;
	private JTable table_5;
	private JTable table_6;
	private DefaultTableModel dTable3;
	private ArrayList<UserData> searchedUD_ArrL;
	
	
	class ActionLister_MemberList implements ActionListener{
		String sort;
		
		public ActionLister_MemberList(String sort) {
			this.sort = sort;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (sort) {
			case "¿¸º€":
				break;
			case "ø£≈Õ":
				break;
				
			case "¡æ∑·":
				break;

			default:
				break;
			}
		}
		
	}
			
	
	
	public MemberList(ManagerWindow mw) {
		this.mw = mw;
		PacketMap.getInstance().map.put(SmCurrMemListAck.class, this);
		PacketMap.getInstance().map.put(SmAllMemListAck.class, this);
		PacketMap.getInstance().map.put(SmMemSearchAck.class, this);	
		
		setLayout(new BorderLayout(0, 0));
		setBackground(MyColor.black);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.DARK_GRAY);
		add(panel_5);
		
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 197, 0, 30, 0 };
		gbl_panel_5.rowHeights = new int[] { 30, 0, 30, 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_5.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_5.setLayout(gbl_panel_5);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.fill = GridBagConstraints.BOTH;
		gbc_panel_7.insets = new Insets(0, 0, 5, 5);
		gbc_panel_7.gridx = 0;
		gbc_panel_7.gridy = 1;
		panel_5.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[] { 23, 100, 16, 0 };
		gbl_panel_7.rowHeights = new int[] { 88, 30, 30, 30, 30, 30, 30, 30, 0, 30, 62, 0, 0 };
		gbl_panel_7.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_7.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		panel_7.setLayout(gbl_panel_7);

		JLabel lblNewLabel = new JLabel("\uBA54\uB274");
		lblNewLabel.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel_7.add(lblNewLabel, gbc_lblNewLabel);

		// ∞¸∏Æ - «ˆ¿Á ¿ÃøÎ¡ﬂ ∞Ì∞¥ πˆ∆∞
		JButton btnNewButton = new JButton("\uD604\uC7AC \uC774\uC6A9\uC911 \uACE0\uAC1D");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(MyColor.w_white);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel_6.show(panel_6, "scrollPane_3");
				MsCurrMemListSyn packet = new MsCurrMemListSyn();
				ClientNet.getInstance().sendPacket(packet);
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 3;
		panel_7.add(btnNewButton, gbc_btnNewButton);

		// ∞¸∏Æ - ¿¸√º πˆ∆∞
		JButton btnNewButton_1 = new JButton("¿¸√º »∏ø¯");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(MyColor.w_white);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cl_panel_6.show(panel_6, "scrollPane_3_1");
				MsAllMemListSyn packet = new MsAllMemListSyn();
				ClientNet.getInstance().sendPacket(packet);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 5;
		panel_7.add(btnNewButton_1, gbc_btnNewButton_1);

		// ∞¸∏Æ - ∞Àªˆ πˆ∆∞
		JButton btnNewButton_2 = new JButton("\uD68C\uC6D0\uAC80\uC0C9");
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBackground(MyColor.w_white);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_1.getText().equals("")) {
					lbSearch.setText("≥ªøÎ¿ª ¿‘∑¬«ÿ¡÷ººø‰.");
				} else {
					lbSearch.setText("");
					idxNameMemS = comboBox.getSelectedItem().toString();
					contentsMemS = textField_1.getText();
					textField_1.setText("");
					MsMemSearchSyn packet = new MsMemSearchSyn();
					ClientNet.getInstance().sendPacket(packet);
					cl_panel_6.show(panel_6, "scrollPane_3_2");
				}

			}
		});

		comboBox = new JComboBox(searchList);
		comboBox.setBackground(MyColor.w_white);
		comboBox.setForeground(Color.BLACK);
//      comboBox.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//            idxNameMemS = comboBox.getSelectedItem().toString();
//         }
//      });
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 7;
		panel_7.add(comboBox, gbc_comboBox);

		// ∞¸∏Æ - ∞Àªˆ ¿‘∑¬ ≈ÿΩ∫∆Æ« µÂ
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 8;
		panel_7.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 9;
		panel_7.add(btnNewButton_2, gbc_btnNewButton_2);

		JPanel panel_21 = new JPanel();
		panel_21.setBackground(Color.DARK_GRAY);
		GridBagConstraints gbc_panel_21 = new GridBagConstraints();
		gbc_panel_21.insets = new Insets(0, 0, 5, 5);
		gbc_panel_21.fill = GridBagConstraints.BOTH;
		gbc_panel_21.gridx = 1;
		gbc_panel_21.gridy = 10;
		panel_7.add(panel_21, gbc_panel_21);
		panel_21.setLayout(new GridLayout(0, 1, 0, 0));

		lbSearch = new JLabel("");
		lbSearch.setBackground(Color.DARK_GRAY);
		lbSearch.setForeground(Color.WHITE);
		panel_21.add(lbSearch);

		panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.insets = new Insets(0, 0, 5, 5);
		gbc_panel_6.fill = GridBagConstraints.BOTH;
		gbc_panel_6.gridx = 1;
		gbc_panel_6.gridy = 1;
		panel_5.add(panel_6, gbc_panel_6);
		cl_panel_6 = new CardLayout(0, 0);
		panel_6.setLayout(cl_panel_6);

		// «ˆ¿Á ¿ÃøÎ¡ﬂ ∞Ì∞¥
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_6.add("scrollPane_3", scrollPane_3);

		contentsCurrMem = new String[][] {};
		dTable = new DefaultTableModel(contentsCurrMem, headerMem);
		table_1 = new JTable(dTable);
		table_1.setRowHeight(27);
		table_1.setFillsViewportHeight(true);
		table_1.setFont(new Font("ªı±º∏≤", Font.PLAIN, 25));
		JTableHeader tableHeader = table_1.getTableHeader();
	      Font headerFont = new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 17);
	      tableHeader.setFont(headerFont);
		scrollPane_3.setViewportView(table_1);

		// ¿¸√º ∏ÆΩ∫∆Æ
		scrollPane_3_1 = new JScrollPane();
		scrollPane_3_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_6.add("scrollPane_3_1", scrollPane_3_1);

		contentsAllMem = new String[][] { { "ª˛øˆΩ«", "2", "30000", "ef" }, { "¿œπ›1", "3", "34003", "ef" },
				{ "§∑§©§ß§ß", "4", "34534", "ef" }, { "dfeb", "5", "234767", "ef" } };
		dTable2 = new DefaultTableModel(contentsAllMem, headerMem);
		table_5 = new JTable(dTable2);
		table_5.setRowHeight(27);
		table_5.setFillsViewportHeight(true);
		table_5.setFont(new Font("ªı±º∏≤", Font.PLAIN, 25));
		JTableHeader tableHeader1 = table_5.getTableHeader();
	      Font headerFont1 = new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 17);
	      tableHeader1.setFont(headerFont1);
		scrollPane_3_1.setViewportView(table_5);

		scrollPane_3_2 = new JScrollPane();
		scrollPane_3_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_6.add("scrollPane_3_2", scrollPane_3_2);

		contentsMemSearch = new String[][] { { "ª˛øˆΩ«", "2", "30000", "ef" }, { "¿œπ›1", "3", "34003", "ef" },
				{ "§∑§©§ß§ß", "4", "34534", "ef" }, { "dfeb", "5", "234767", "ef" } };
		dTable3 = new DefaultTableModel(contentsMemSearch, headerMem);
		table_6 = new JTable(dTable3);
		table_6.setRowHeight(27);
		table_6.setFillsViewportHeight(true);
		table_6.setFont(new Font("ªı±º∏≤", Font.PLAIN, 25));
		JTableHeader tableHeader2 = table_6.getTableHeader();
	      Font headerFont2 = new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 17);
	      tableHeader2.setFont(headerFont2);
		scrollPane_3_2.setViewportView(table_6);

	}



	@Override
	public void receive(PacketBase packet) {
		System.out.println(packet.getClass());

		// «ˆ¿Á ¿ÃøÎ¡ﬂ ∞Ì∞¥
		if (packet.getClass() == SmCurrMemListAck.class) {
			SmCurrMemListAck currAck = (SmCurrMemListAck) packet;
			contentsCurrMem = new String[currAck.userList.size()][headerMem.length];
			for (int i = 0; i < currAck.userList.size(); i++) {
				contentsCurrMem[i][0] = currAck.userList.get(i).name;
				contentsCurrMem[i][1] = currAck.userList.get(i).id;
				contentsCurrMem[i][2] = currAck.userList.get(i).phone;
				contentsCurrMem[i][3] = currAck.userList.get(i).birth;
			}
			dTable = new DefaultTableModel(contentsCurrMem, headerMem);
			table_1 = new JTable(dTable);
			table_1.setRowHeight(27);
			table_1.setFillsViewportHeight(true);
			table_1.setFont(new Font("ªı±º∏≤", Font.PLAIN, 25));
			 JTableHeader tableHeader = table_1.getTableHeader();
		      Font headerFont = new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 17);
		      tableHeader.setFont(headerFont);
			scrollPane_3.setViewportView(table_1);
		}

		// ¿¸√º ∞Ì∞¥
		if (packet.getClass() == SmAllMemListAck.class) {
			SmAllMemListAck ack = (SmAllMemListAck) packet;
			contentsAllMem = new String[ack.userList.size()][headerMem.length];
			System.out.println("ack ªÁ¿Ã¡Ó:" + ack.userList.size() + " contentsAllMem ªÁ¿Ã¡Ó:" + contentsAllMem.length);
			System.out.println("fefef" + ack.userList.get(0).name);

			for (int i = 0; i < ack.userList.size(); i++) {
				contentsAllMem[i][0] = ack.userList.get(i).name;
				contentsAllMem[i][1] = ack.userList.get(i).id;
				contentsAllMem[i][2] = ack.userList.get(i).phone;
				contentsAllMem[i][3] = ack.userList.get(i).birth;
			}
			dTable2 = new DefaultTableModel(contentsAllMem, headerMem);
			table_5 = new JTable(dTable2);
			table_5.setRowHeight(27);
			table_5.setFillsViewportHeight(true);
			table_5.setFont(new Font("ªı±º∏≤", Font.PLAIN, 25));
			 JTableHeader tableHeader = table_5.getTableHeader();
		      Font headerFont = new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 17);
		      tableHeader.setFont(headerFont);
			scrollPane_3_1.setViewportView(table_5);
		}

//      contentsMemS ∞° §°º∫§∑ ¿œ∂ß [∞°-Éâ§°]º∫[æ∆-üÁ§∑] ¿∏∑Œ πŸ≤„º≠ 
//      pp=[∞°-Éâ§°]º∫[æ∆-üÁ§∑]  if(Pattern.matches(pp, ud.name/id/phone)) searchedUDs.add(ud);
		// ∞Àªˆ
		if (packet.getClass() == SmMemSearchAck.class) {
			SmMemSearchAck ack = (SmMemSearchAck) packet;
			searchedUD_ArrL = new ArrayList<UserData>();

			// √ º∫∞Àªˆ
			String[] cho = { "§°", "§¢", "§§", "§ß", "§®", "§©", "§±", "§≤", "§≥", "§µ", "§∂", "§∑", "§∏", "§π", "§∫", "§ª", "§º", "§Ω",
					"§æ" };
			String[] patt = { "[∞°-Éâ§°]", "[±Ó-Ö˘§¢]", "[≥™-à¢§§]", "[¥Ÿ-ãL§ß]", "[µ˚-çÍ§®]", "[∂Û-ê°§©]", "[∏∂-ìJ§±]", "[πŸ-ïΩ§≤]", "[∫¸-ò™§≥]",
					"[ªÁ-öÔ§µ]", "[ΩŒ-ùœ§∂]", "[æ∆-üÁ§∑]", "[¿⁄-£†§∏]", "[¬•-¬ˆ§π]", "[¬˜-ØÜ§∫]", "[ƒ´-µi§ª]", "[≈∏-ªM§º]", "[∆ƒ-¿ò§Ω]",
					"[«œ-∆R§æ]" };
			String contentsMemSArr[] = contentsMemS.split("");
			for (int i = 0; i < contentsMemS.length(); i++) {
				for (int j = 0; j < cho.length; j++) {
					if (contentsMemSArr[i].equals(cho[j])) {
						contentsMemSArr[i] = patt[j];
					}
				}
			}
			String pattern = ".*";
			for (String s : contentsMemSArr) {
				pattern += s;
			}
			pattern += ".*";

			// ≈◊¿Ã∫Ìø° √‚∑¬«“ æÓ∑π¿Ã∏ÆΩ∫∆Æ ª˝º∫
			if (idxNameMemS.equals(searchList[0])) {
				for (UserData ud : ack.userList) {
					if (ud.name == null)
						continue;
					if (java.util.regex.Pattern.matches(pattern, ud.name)) {
						searchedUD_ArrL.add(ud);
					}
				}
			} else if (idxNameMemS.equals(searchList[1])) {
				for (UserData ud : ack.userList) {
					if (ud.id == null)
						continue;
					if (ud.id.contains(contentsMemS)) {
						searchedUD_ArrL.add(ud);
					}
				}
			} else if (idxNameMemS.equals(searchList[2])) {
				for (UserData ud : ack.userList) {
					if (ud.phone == null)
						continue;
					if (ud.phone.contains(contentsMemS)) {
						searchedUD_ArrL.add(ud);
					}
				}
			}
			System.out.println(searchedUD_ArrL.size());
			contentsMemSearch = new String[searchedUD_ArrL.size()][headerMem.length];
			for (int i = 0; i < searchedUD_ArrL.size(); i++) {
				contentsMemSearch[i][0] = searchedUD_ArrL.get(i).name;
				contentsMemSearch[i][1] = searchedUD_ArrL.get(i).id;
				contentsMemSearch[i][2] = searchedUD_ArrL.get(i).phone;
				contentsMemSearch[i][3] = searchedUD_ArrL.get(i).birth;
			}

			dTable3 = new DefaultTableModel(contentsMemSearch, headerMem);
			table_6 = new JTable(dTable3);
			table_6.setRowHeight(27);
			table_6.setFillsViewportHeight(true);
			table_6.setFont(new Font("ªı±º∏≤", Font.PLAIN, 25));
			JTableHeader tableHeader = table_6.getTableHeader();
		      Font headerFont = new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 17);
		      tableHeader.setFont(headerFont);
			scrollPane_3_2.setViewportView(table_6);
		}
		
	}

}
