package client_p.ui_p;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ClientChatFrame extends JPanel{

	private final static String newline = "\n";
	String text = "";
	JFrame window;

	public ClientChatFrame() {
		window = new JFrame();
		window.setBounds(100, 100, 900, 1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		window.getContentPane().add(this);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(0, 0, 900, 700);
		add(textArea);
		
		TextField keyChat = new TextField();
		keyChat.setBounds(0, 695, 711, 258);
		add(keyChat);
		keyChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text = keyChat.getText();
				textArea.append(text + newline);
				keyChat.selectAll();
			    textArea.setCaretPosition(textArea.getParent().getWidth());
			}});
		
		keyChat.setColumns(10);
		
		JButton sendButton = new JButton("전 송");
		sendButton.setBounds(716, 699, 152, 125);
		add(sendButton);
			sendButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!keyChat.getText().equals("")) {
					text = keyChat.getText();
					textArea.append(text + newline);
					keyChat.selectAll();
					textArea.setCaretPosition(textArea.getParent().getWidth());
					keyChat.setText("");
					}}});
		
		JButton exitButton = new JButton("종 료");
		exitButton.setBounds(717, 824, 151, 117);
		add(exitButton);
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				BaseFrame.getInstance().view("MainLayout");
			}});
		
		setVisible(true);
	}
}