package manager_p.panel_p;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import data_p.ExcelRead_PriceExcel;
import data_p.ExcelWriter_UpdateColumn;
import manager_p.ack_p.MsUptRoomPrSyn;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.SmUptRoomPrAck;

public class SetPrice extends JPanel implements Receivable {
	JFrame tfram;
	private String header[] = { "이용석", "시간당 금액", "단위" };
	private String contents[][] = new String [new ExcelRead_PriceExcel().rowDataArr(1,"DataTable/RoomData.xlsx").size()][header.length];
	private DefaultTableModel tableModel;
	private JTable table;
	private JScrollPane scrollPane_6_2;
	String backUpPath = "DataTable/RoomData_BackUp.xlsx";
	String nowPath = "DataTable/RoomData.xlsx";
	
	//테이블 읽어와서 출력 <READ>
	void read(String dataPath) {
		ExcelRead_PriceExcel seatEx = new ExcelRead_PriceExcel();
		for (int i = 0; i < new ExcelRead_PriceExcel().rowDataArr(1,dataPath).size(); i++) {
			contents[i][0] = seatEx.rowDataArr(1,dataPath).get(i);
			contents[i][1] = seatEx.rowDataArr(2,dataPath).get(i);
			contents[i][2] = "원";
			
			//요금 테이블
			tableModel = new DefaultTableModel(contents, header);
			table = new JTable(tableModel);
			table.setRowHeight(27);
			table.setFillsViewportHeight(true);
			table.setFont(new Font("새굴림", Font.PLAIN, 25));
			scrollPane_6_2.setViewportView(table);
			
		}
		System.out.println(this.getClass().getTypeName()+": "+dataPath+" 불러오기 완료");
	}
	
	//입력된 0열 1열을 엑셀 1열 2열에 덮어쓰기 <WRITE>
	void restore(String dataPath) {
		int columCnt = header.length;
		int rowCnt = contents.length;
		for (int i = 0; i < columCnt-1; i++) {
			ArrayList<String> arr = new ArrayList<String>();
			for (int j = 0; j < rowCnt; j++) {
//				System.out.println((String) table.getValueAt(j, i));
				arr.add((String) table.getValueAt(j, i));
			}
//			System.out.println(i+"번쨰 arr 사이즈"+arr.size());
			new ExcelWriter_UpdateColumn(arr, i+1,dataPath);
		}
		System.out.println(dataPath+"에 저장완료");
	}
	
	void uptDB_RoomData(boolean isReset) {
		HashMap<Integer, Integer> map_roomID_Pr = new HashMap<Integer, Integer>();
		ExcelRead_PriceExcel seatEx = new ExcelRead_PriceExcel();
		ArrayList<String> roomIDArrL = seatEx.rowDataArr(0, backUpPath);
		
		if(isReset) {
			ArrayList<String> roomPrArrL = seatEx.rowDataArr(2, backUpPath);
			for (int i = 0; i < roomIDArrL.size(); i++) {
				map_roomID_Pr.put(Integer.parseInt(roomIDArrL.get(i)), Integer.parseInt(roomPrArrL.get(i)));
			}
		}else {
			for (int i = 0; i < roomIDArrL.size(); i++) {
				map_roomID_Pr.put(Integer.parseInt(roomIDArrL.get(i)), (Integer)table.getValueAt(i, 1));
			}
		}
		
		MsUptRoomPrSyn packet = new MsUptRoomPrSyn(map_roomID_Pr);
		ClientNet.getInstance().sendPacket(packet);
	}
	
	
	class ActionLister_SetPr implements ActionListener{
		String sort;
		
		public ActionLister_SetPr(String sort) {
			this.sort = sort;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (sort) {
			case "복원":
				read(backUpPath);
				restore(nowPath); 
				uptDB_RoomData(true);
				break;
			case "새로고침":
				read(nowPath);
				break;
			case "적용":
				restore(nowPath);
				uptDB_RoomData(false);
				break;

			default:
				break;
			}
		}
		
	}
			
	public SetPrice() {
		PacketMap.getInstance().map.put(SmUptRoomPrAck.class, this);
//		public SetPrice(TestFrame tfram) {
//		this.tfram = tfram;
//		this.tfram.tabbedPane.addTab("요금 관리", this);
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 270, 489, 230, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);
		
		JButton btn_Reset = new JButton("복원(백업파일)");
		btn_Reset.addActionListener(new ActionLister_SetPr("복원"));
		btn_Reset.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		GridBagConstraints gbc_btn_SetPrice_1 = new GridBagConstraints();
		gbc_btn_SetPrice_1.anchor = GridBagConstraints.SOUTH;
		gbc_btn_SetPrice_1.insets = new Insets(0, 0, 5, 5);
		gbc_btn_SetPrice_1.gridx = 0;
		gbc_btn_SetPrice_1.gridy = 1;
		panel_4.add(btn_Reset, gbc_btn_SetPrice_1);

		JPanel panel_10_2 = new JPanel();
		GridBagConstraints gbc_panel_10_2 = new GridBagConstraints();
		gbc_panel_10_2.anchor = GridBagConstraints.SOUTH;
		gbc_panel_10_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_10_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_10_2.gridx = 1;
		gbc_panel_10_2.gridy = 1;
		panel_4.add(panel_10_2, gbc_panel_10_2);
		GridBagLayout gbl_panel_10_2 = new GridBagLayout();
		gbl_panel_10_2.columnWidths = new int[] { 0, 0 };
		gbl_panel_10_2.rowHeights = new int[] { 0, 644, 0 };
		gbl_panel_10_2.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_10_2.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		panel_10_2.setLayout(gbl_panel_10_2);

		JLabel lblNewLabel_1_2 = new JLabel("요금 설정");
		lblNewLabel_1_2.setFont(new Font("휴먼모음T", Font.BOLD, 40));
		GridBagConstraints gbc_lblNewLabel_1_2 = new GridBagConstraints();
		gbc_lblNewLabel_1_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1_2.gridx = 0;
		gbc_lblNewLabel_1_2.gridy = 0;
		panel_10_2.add(lblNewLabel_1_2, gbc_lblNewLabel_1_2);

		scrollPane_6_2 = new JScrollPane();
		scrollPane_6_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_scrollPane_6_2 = new GridBagConstraints();
		gbc_scrollPane_6_2.fill = GridBagConstraints.VERTICAL;
		gbc_scrollPane_6_2.gridx = 0;
		gbc_scrollPane_6_2.gridy = 1;
		panel_10_2.add(scrollPane_6_2, gbc_scrollPane_6_2);
		
		//테이블 읽어와서 출력 <READ>
		read("DataTable/RoomData.xlsx");
		
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
		
		JButton btnNewButton = new JButton("새로고침");
		btnNewButton.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		btnNewButton.addActionListener(new ActionLister_SetPr("새로고침"));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		panel_18.add(btnNewButton, gbc_btnNewButton);
		
		//입력된 0열 1열을 엑셀 1열 2열에 덮어쓰기 <WRITE>
		JButton btn_Restore = new JButton("적용");
		btn_Restore.addActionListener(new ActionLister_SetPr("적용"));
		btn_Restore.setFont(new Font("맑은 고딕", Font.PLAIN, 30));
		GridBagConstraints gbc_btn_SetPrice = new GridBagConstraints();
		gbc_btn_SetPrice.gridx = 0;
		gbc_btn_SetPrice.gridy = 4;
		panel_18.add(btn_Restore, gbc_btn_SetPrice);
	}

	@Override
	public void receive(PacketBase packet) {
		JDialog jd = new JDialog();
		
		
		jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		jd.setVisible(true);
		
	}

}
