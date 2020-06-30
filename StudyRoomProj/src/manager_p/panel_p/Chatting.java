package manager_p.panel_p;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsChatSyn;
import manager_p.ChatReqDialog;
import manager_p.ManagerWindow;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.broadCast.ScChatBroadCast;
import server_p.packet_p.syn_p.SMChatConnectSyn;

public class Chatting extends JPanel implements Receivable {
	ManagerWindow mw;
	
	private JTable table_3;
	private JTable table_4;
	private JLabel lbChatName;
	private JTextArea textArea;
	private JTextField textField;
	private JScrollPane scrollPane_Chat;
	public CsChatSyn chatSyn;
	String text = "";

	public Object tabbedPane;
	
	
	class ActionLister_Chatting implements ActionListener{
		String sort;
		
		public ActionLister_Chatting(String sort) {
			this.sort = sort;
		}
		
		void send() {
			text = "[관리자]: "+textField.getText() + "\n";
			chatSyn.setText(text);
			ClientNet.getInstance().sendPacket(chatSyn);
			
			textField.setText("");
			
			textField.selectAll();
			scrollPane_Chat.getVerticalScrollBar().setValue(scrollPane_Chat.getVerticalScrollBar().getMaximum());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (sort) {
			case "전송":
				if (!textField.getText().equals("")) {
					send();
				}
				break;
			case "엔터":
				send();
				break;

			default:
				break;
			}
		}
		
	}
			
	
	
	public Chatting(ManagerWindow mw) {
		this.mw = mw;
		PacketMap.getInstance().map.put(SMChatConnectSyn.class, this); // 채팅 연결 요청에 대한 응답
		PacketMap.getInstance().map.put(ScChatBroadCast.class, this);		
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_8 = new JPanel();
		add(panel_8);
		
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
		gbl_panel_17.rowHeights = new int[] { 30, 0, 30, 0, 0, 0, 0, 0, 0 };
		gbl_panel_17.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_17.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		panel_17.setLayout(gbl_panel_17);

		JLabel lblNewLabel_9 = new JLabel("1:1 \uCC44\uD305 \uBB38\uC758");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 1;
		panel_17.add(lblNewLabel_9, gbc_lblNewLabel_9);

		lbChatName = new JLabel("문의자 이름");
		GridBagConstraints gbc_lbChatName = new GridBagConstraints();
		gbc_lbChatName.insets = new Insets(0, 0, 5, 0);
		gbc_lbChatName.gridx = 0;
		gbc_lbChatName.gridy = 3;
		panel_17.add(lbChatName, gbc_lbChatName);

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

		
		//채팅 에어리어
		textArea = new JTextArea();
		scrollPane_Chat = new JScrollPane(textArea);
		scrollPane_Chat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		panel_19.add(scrollPane_Chat, gbc_textArea);

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

		//채팅
		textField = new JTextField();
		//텍스트 입력 액션
		textField.addActionListener(new ActionLister_Chatting("엔터"));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 0;
		panel_20.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton_4 = new JButton("전송");
		btnNewButton_4.addActionListener(new ActionLister_Chatting("전송"));
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.fill = GridBagConstraints.VERTICAL;
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 0;
		panel_20.add(btnNewButton_4, gbc_btnNewButton_4);

	}



	@Override
	public void receive(PacketBase packet) {
		//채팅연결
		if(packet.getClass() == SMChatConnectSyn.class) {
			SMChatConnectSyn sccAck = (SMChatConnectSyn) packet;
			if (sccAck.eResult == EResult.SUCCESS) {
				System.out.println("채팅 연결 성공 하앍");
//						System.out.println(BaseFrame.getInstance().userData.name);
				ChatReqDialog dialog = new ChatReqDialog(mw,sccAck);
				lbChatName.setText(sccAck.userdata.name);
				dialog.lbClientName.setText(sccAck.userdata.name);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		}
		
		//채팅
		if(packet.getClass() == ScChatBroadCast.class) {
			ScChatBroadCast scChat = (ScChatBroadCast)packet;
			textArea.setText(textArea.getText()+"\n"+scChat.getText());
			scrollPane_Chat.getVerticalScrollBar().setValue(scrollPane_Chat.getVerticalScrollBar().getMaximum());
		}
		
	}

}
