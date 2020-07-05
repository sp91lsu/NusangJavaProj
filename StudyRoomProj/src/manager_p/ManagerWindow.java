package manager_p;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import client_p.ClientNet;
import client_p.EEnter;
import client_p.PacketMap;
import client_p.Receivable;
import client_p.ui_p.BaseFrame;
import client_p.ui_p.MyColor;
import client_p.ui_p.Seating_Arrangement;
import data_p.product_p.DataManager;
import manager_p.panelDialog_p.Chatting;
import manager_p.panelDialog_p.MemberList;
import manager_p.panelDialog_p.ResvInquiry;
import manager_p.panelDialog_p.SalesInquiry;
import manager_p.panelDialog_p.SetPrice;
import packetBase_p.ELoginType;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScGetRoomDataAck;
import server_p.packet_p.ack_p.SmAllMemListAck;
import server_p.packet_p.ack_p.SmCurrMemListAck;
import server_p.packet_p.ack_p.SmMemSearchAck;
import server_p.packet_p.broadCast.ScBuyLockerCast;
import server_p.packet_p.broadCast.ScRoomInfoBroadCast;
import java.awt.Color;

public class ManagerWindow extends JFrame implements Receivable {
	
	public JTabbedPane tabbedPane;

	ArrayList<ArrayList<String>> tableSPArr = new ArrayList<ArrayList<String>>();

	Seating_Arrangement pnl_SeatArrange;
	SetPrice pnl_SetPrice = new SetPrice();
	SalesInquiry pnl_SalesInquiry = new SalesInquiry();
	public Chatting pnl_Chatting = new Chatting(this);
	public ResvInquiry pnl_ResvInquiry = new ResvInquiry(this);
	public MemberList pnl_Member = new MemberList(this);

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerWindow mww = new ManagerWindow();
					ClientNet.getInstance().start();
					mww.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ManagerWindow() {
		System.out.println("ManagerWindow 실행");

		PacketMap.getInstance().map.put(ScRoomInfoBroadCast.class, this);
		PacketMap.getInstance().map.put(ScBuyLockerCast.class, this);
		PacketMap.getInstance().map.put(ScGetRoomDataAck.class, this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(40, 100, 1000, 800);
		setBackground(MyColor.black);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setForeground(Color.BLUE);
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				JTabbedPane pane = (JTabbedPane) evt.getSource();
				int sel = pane.getSelectedIndex();
				if (sel == 2) {
					pnl_SalesInquiry.today = Calendar.getInstance();
//              pnl_SalesInquiry.setDateList();
				}
			}
		});

		tabbedPane.addTab("회원조회", pnl_Member);
		
		tabbedPane.addTab("1:1 문의 채팅", pnl_Chatting);
		
		tabbedPane.addTab("예약 조회", pnl_ResvInquiry);

		tabbedPane.addTab("요금 관리", pnl_SetPrice);

		tabbedPane.addTab("매출 조회", pnl_SalesInquiry);

		
		

//      JPanel panel_1 = new JPanel();
//      tabbedPane.addTab("\uC88C\uC11D/\uB8F8 \uAD00\uB9AC", null, panel_1, null);
//      GridBagLayout gbl_panel_1 = new GridBagLayout();
//      gbl_panel_1.columnWidths = new int[] { 107, 889, 0 };
//      gbl_panel_1.rowHeights = new int[] { 0, 0 };
//      gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
//      gbl_panel_1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
//      panel_1.setLayout(gbl_panel_1);
//
//      JPanel panel_7_1 = new JPanel();
//      GridBagConstraints gbc_panel_7_1 = new GridBagConstraints();
//      gbc_panel_7_1.insets = new Insets(0, 0, 0, 5);
//      gbc_panel_7_1.fill = GridBagConstraints.BOTH;
//      gbc_panel_7_1.gridx = 0;
//      gbc_panel_7_1.gridy = 0;
//      panel_1.add(panel_7_1, gbc_panel_7_1);
//      GridBagLayout gbl_panel_7_1 = new GridBagLayout();
//      gbl_panel_7_1.columnWidths = new int[] { 0, 0 };
//      gbl_panel_7_1.rowHeights = new int[] { 88, 0, 0, 0, 0, 0, 0, 0, 0 };
//      gbl_panel_7_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
//      gbl_panel_7_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
//      panel_7_1.setLayout(gbl_panel_7_1);
//
//      JLabel lblNewLabel_6 = new JLabel("\uC88C\uC11D/\uB8F8 \uC635\uC158");
//      GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
//      gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
//      gbc_lblNewLabel_6.gridx = 0;
//      gbc_lblNewLabel_6.gridy = 1;
//      panel_7_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
//
//      JButton btnNewButton_3 = new JButton("ON");
//      btnNewButton_3.addActionListener(new ActionListener() {
//         public void actionPerformed(ActionEvent e) {
//         }
//      });
//      GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
//      gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
//      gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
//      gbc_btnNewButton_3.gridx = 0;
//      gbc_btnNewButton_3.gridy = 3;
//      panel_7_1.add(btnNewButton_3, gbc_btnNewButton_3);
//
//      JButton btnNewButton_1_1 = new JButton("OFF");
//      GridBagConstraints gbc_btnNewButton_1_1 = new GridBagConstraints();
//      gbc_btnNewButton_1_1.fill = GridBagConstraints.HORIZONTAL;
//      gbc_btnNewButton_1_1.insets = new Insets(0, 0, 5, 0);
//      gbc_btnNewButton_1_1.gridx = 0;
//      gbc_btnNewButton_1_1.gridy = 5;
//      panel_7_1.add(btnNewButton_1_1, gbc_btnNewButton_1_1);
//
//      JScrollPane scrollPane_7 = new JScrollPane();
//      scrollPane_7.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//      GridBagConstraints gbc_scrollPane_7 = new GridBagConstraints();
//      gbc_scrollPane_7.fill = GridBagConstraints.BOTH;
//      gbc_scrollPane_7.gridx = 1;
//      gbc_scrollPane_7.gridy = 0;
//      panel_1.add(scrollPane_7, gbc_scrollPane_7);
//
//      Dimension size = new Dimension();// 사이즈를 지정하기 위한 객체 생성
//      size.setSize(900, 1000);// 객체의 사이즈를 지정
//      Seating_Arrangement seating_Arrangement = new Seating_Arrangement();
//      seating_Arrangement.setPreferredSize(size);// 사이즈 정보를 가지고 있는
//      scrollPane_7.setViewportView(seating_Arrangement);
		

		System.out.println("ManagerWindow 준비완료.");
	}

	

	@Override
	public void receive(PacketBase packet) {
		System.out.println(packet.getClass());


//		if (packet.getClass() == ScGetRoomDataAck.class) {
//			ScGetRoomDataAck ack = (ScGetRoomDataAck) packet;
//			DataManager.getInstance().roomMap = ack.roomMap;
//			BaseFrame.getInstance().loginType = ELoginType.MANAGER;
//			pnl_SeatArrange = new Seating_Arrangement();
//			pnl_SeatArrange.openPage(EEnter.MOBILE);
////			tabbedPane.add("좌석/룸 조회", pnl_SeatArrange);
//		}

		if (packet.getClass() == ScRoomInfoBroadCast.class) {
			ScRoomInfoBroadCast roomInfoCast = (ScRoomInfoBroadCast) packet;
			BaseFrame.getInstance().roomInfoList = roomInfoCast.roomListAll;

			if (pnl_SeatArrange.isVisible()) {
				pnl_SeatArrange.roomState();
				pnl_SeatArrange.checkDate();
			}
		}
	}
}