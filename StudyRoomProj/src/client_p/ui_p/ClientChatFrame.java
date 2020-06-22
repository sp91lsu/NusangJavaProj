package client_p.ui_p;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class ClientChatFrame extends JPanel{

	String text = "";
	TextArea textArea;
	JFrame window;
	
	private final static String newline = "\n";
	
	public static void main(String[] args) {
		new ClientChatFrame();
		
	}

	public ClientChatFrame() {
		
		window = new JFrame();
		window.setBounds(100, 100, 900, 1000);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		setLayout(null);
		window.add(this);
		
		textArea = new TextArea();
		textArea.setBounds(0, 0, 900, 700);
		add(textArea);
		
		TextField keyChat = new TextField();
		keyChat.setBounds(0, 350, 800, 300);
		add(keyChat);
		keyChat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				text = keyChat.getText();
				textArea.append(text + newline);
				keyChat.selectAll();
			    textArea.setCaretPosition(textArea.getParent().getWidth());
				
			}
		});
		keyChat.setColumns(10);
		
		JButton sendButton = new JButton("전 송");
		sendButton.setBounds(386, 350, 100, 45);
		sendButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				text = keyChat.getText();
				textArea.append(text + newline);
				keyChat.selectAll();
			    textArea.setCaretPosition(textArea.getParent().getWidth());
				
			}
		});
		add(sendButton);
		
		JButton exitButton = new JButton("종 료");
		exitButton.setBounds(386, 395, 100, 45);
		exitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				BaseFrame.getInstance().view("MainLayout");
				
			}
		});
		add(exitButton);
		
		setVisible(true);
	}

}
