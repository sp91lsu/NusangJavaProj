package client_p.ui_p;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsChatSyn;
import packetBase_p.PacketBase;
import server_p.packet_p.broadCast.ScChatBroadCast;

public class ClientChatFrame extends JPanel implements Receivable {

	private final static String newline = "\n";
	String text = "";
	JFrame window;
	CsChatSyn chatSyn = null;
	private TextField keyChat;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setBounds(100, 100, 900, 1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.getContentPane().add(new ClientChatFrame());
		window.setVisible(true);
	}

	public ClientChatFrame() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 454, 0 };
		gridBagLayout.rowHeights = new int[] { 570, 126, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 336, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		keyChat = new TextField();
		keyChat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text = "[" + BaseFrame.getInstance().userData.name + "]: " + keyChat.getText() + "\n";
				// textArea.append(text);
				chatSyn.setText(text);
				ClientNet.getInstance().sendPacket(chatSyn);

				keyChat.setText("");

				keyChat.selectAll();
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());

			}
		});
		keyChat.setColumns(10);
		GridBagConstraints gbc_keyChat = new GridBagConstraints();
		gbc_keyChat.fill = GridBagConstraints.BOTH;
		gbc_keyChat.insets = new Insets(0, 0, 0, 5);
		gbc_keyChat.gridx = 0;
		gbc_keyChat.gridy = 0;
		panel.add(keyChat, gbc_keyChat);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));

		JButton sendButton = new JButton("전송");
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				text = "[" + BaseFrame.getInstance().userData.name + "]: " + keyChat.getText() + "\n";
				// textArea.append(text);
				chatSyn.setText(text);
				ClientNet.getInstance().sendPacket(chatSyn);

				keyChat.setText("");

				keyChat.selectAll();
				scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
			}
		});
		panel_1.add(sendButton);

		JButton exitButton = new JButton("종료");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().openMainLayout(null, null, null, null);
			}
		});
		panel_1.add(exitButton);

		setVisible(true);
	}

	public void setChatPacket(CsChatSyn chatSyn) {
		this.chatSyn = chatSyn;
	}

	@Override
	public void receive(PacketBase packet) {

		ScChatBroadCast scChat = (ScChatBroadCast) packet;
		textArea.setText(textArea.getText() + newline + scChat.getText() + newline);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
}