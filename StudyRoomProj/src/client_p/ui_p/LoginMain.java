package client_p.ui_p;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client_p.ClientNet;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import client_p.packet_p.syn_p.CsLoginSyn;
import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScLoginAck;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Stack;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.DropMode;

public class LoginMain extends JPanel implements MouseListener {

	private JTextField idTextF;
	private JTextField currentTextField;
	private JPasswordField passwordField;
	private final JPanel keybordPane = new JPanel();
	JButton btn;
	String text = "";

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 900, 1000);
		frame.add(new LoginMain());
		frame.setVisible(true);
	}

	public LoginMain() {
		System.out.println("L");
		setLayout(null);
		setBounds(100, 100, 50, 50);
		JLabel idLabel = new JLabel("ID");
		idLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 26));
		idLabel.setBounds(251, 156, 51, 55);
		add(idLabel);

		idTextF = new JTextField();
		idTextF.setText("  or \uD578\uB4DC\uD3F0\uBC88\uD638 \uC785\uB825( '-' \uC5C6\uC774 \uC785\uB825)");
		idTextF.setToolTipText("");
		idTextF.setFont(new Font("¸¼Àº °íµñ", Font.ITALIC, 14));
		idTextF.setBounds(323, 156, 328, 55);
		idTextF.addActionListener(new TextFieldChange());
		add(idTextF);
		idTextF.setColumns(10);
		// txtOr.addActionListener();
		JLabel lblPw = new JLabel("PW");
		lblPw.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 26));
		lblPw.setBounds(251, 236, 51, 54);
		add(lblPw);

		passwordField = new JPasswordField();
		passwordField.setBounds(323, 236, 328, 54);
		passwordField.addActionListener(new TextFieldChange());
		add(passwordField);

		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		btnNewButton.setBounds(323, 321, 120, 45);
		add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("·Î±×ÀÎ¹öÆ°");

				CsLoginSyn loginPacket = new CsLoginSyn(idTextF.getText(), passwordField.getText(), true);
				ClientNet.getInstance().sendPacket(loginPacket);
//				
			}
		});
		JButton button = new JButton("\uD68C\uC6D0\uAC00\uC785");
		button.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		button.setBounds(508, 321, 120, 45);
		add(button);

		JLabel lblNewLabel = new JLabel("\uB85C\uADF8\uC778\uCC3D");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 40));
		lblNewLabel.setBounds(251, 21, 469, 110);
		add(lblNewLabel);
		keybordPane.setBounds(12, 413, 860, 300);
		add(keybordPane);

		/////////////////////////////////// Å°º¸µå////////////////////

		// Individual keyboard rows
		String firstRow[] = { "~", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "-", "+", "fill", "BackSpace" };
		String secondRow[] = { "Tab", "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "[", "]", "\\" };
		String thirdRow[] = { "Caps", "A", "S", "D", "F", "G", "H", "J", "K", "L", ":", "\"", "fill", "fill", "Enter" };
		String fourthRow[] = { "Shift", "Z", "X", "C", "V", "B", "N", "M", ",", ".", "?", "blank", "^" };
		String fifthRow[] = { "blank", "blank", "fill", "fill", "fill", "fill", "fill", "fill", "fill", "fill", "", "<",
				"v", ">" };

		// Jbuttons corresponding to each individual rows
		JButton first[];
		JButton second[];
		JButton third[];
		JButton fourth[];
		JButton fifth[];

		JPanel jpNorth = new JPanel();
		JPanel jpCenter = new JPanel();
		JPanel jpKeyboard = new JPanel(new GridBagLayout());
		JPanel jpNote = new JPanel();
		keybordPane.add(jpNorth, BorderLayout.NORTH);
		keybordPane.add(jpNote);
		keybordPane.add(jpCenter, BorderLayout.CENTER);
		keybordPane.add(jpKeyboard, BorderLayout.SOUTH);

		first = new JButton[firstRow.length];
		second = new JButton[secondRow.length];
		third = new JButton[thirdRow.length];
		fourth = new JButton[fourthRow.length];
		fifth = new JButton[fifthRow.length];

		addKeys(jpKeyboard, 0, firstRow, first);
		addKeys(jpKeyboard, 1, secondRow, second);
		addKeys(jpKeyboard, 2, thirdRow, third);
		addKeys(jpKeyboard, 3, fourthRow, fourth);
		addKeys(jpKeyboard, 4, fifthRow, fifth);

		keybordPane.add(jpKeyboard);
		addMouseListener(this);
//		keybordPane.setVisible(true);
//		setVisible(true);
	}

	void addKeys(JPanel parent, int row, String[] keys, JButton[] buttons) {

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridy = row;
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.BOTH;

		int gap = 0;
		for (int index = 0; index < keys.length; index++) {
			String key = keys[index];
			if ("blank".equalsIgnoreCase(key)) {
				gbc.gridx++;
			} else if ("fill".equalsIgnoreCase(key)) {
				gbc.gridwidth++;
				gap++;
			} else {
				// System.out.println("Add " + key);
				btn = new JButton(key);
				btn.addActionListener(new BtnAct());
				buttons[index] = btn;
				parent.add(btn, gbc);
				gbc.gridx += gap + 1;
				gbc.gridwidth = 1;
				gap = 0;
			}
		}
	}

	class TextFieldChange implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentTextField = (JTextField) e.getSource();
		}

	}

	//////////////////////////////////////////////////////////////
	class BtnAct implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton keyPoint = (JButton) e.getSource();
			if (keyPoint.getText() != "BackSpace") {
				text += keyPoint.getText();
			} else if (keyPoint.getText() == "BackSpace")
				textBack();

			currentTextField.setText(text);
		}

		void textSave(String now) {

		}

		void textBack() {
			if (text.length() > 0)
				text = text.substring(0, text.length() - 1);
		}
	}
	///////////////////////// ¸¶¿ì½º
	///////////////////////// ¸®½º³Êµ¿ÀÛ//////////////////////////////////////////////

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Å¬¸¯");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	

}
