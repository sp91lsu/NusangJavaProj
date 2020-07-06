package manager_p;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client_p.ClientNet;
import client_p.PacketMap;
import client_p.Receivable;
import client_p.packet_p.syn_p.CsLoginSyn;
import client_p.ui_p.BaseFrame;
import manager_p.panelDialog_p.ChkDialog;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScLoginAck;

public class managerLogin extends JFrame implements Receivable, MouseListener {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passwordField;
	private ManagerWindow mw;
	JLabel idLabel, logLabel;
	JDialog logDialog;
	private JButton logInBtn;
	String info = "����� ID �Ǵ� �޴��� ��ȣ�� �Է��ϼ���";
	String idInfo = "����� ID�� �Է��ϼ���";
	String phinfo = "����� �޴��� ��ȣ�� �Է��ϼ���";
	private JCheckBox changeBox;

	// �Ŵ��� sp91lsu/33334444/01010041004
	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
		managerLogin frame = new managerLogin();
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
	}

	/**
	 * Create the frame.
	 */
	public managerLogin() {
		PacketMap.getInstance().map.put(ScLoginAck.class, this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 5.0, 1.0, 0.0, 1.0, 1.0, 10.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 5);
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		contentPane.add(panel_4, gbc_panel_4);

		JLabel lblNewLabel_1 = new JLabel("\uAD00\uB9AC\uC790 \uB85C\uADF8\uC778");
		lblNewLabel_1.setFont(new Font("�޸տ�����", Font.BOLD, 40));
		panel_4.add(lblNewLabel_1);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 280, 450, 0 };
		gbl_panel.rowHeights = new int[] { 50, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		idLabel = new JLabel("ID");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("HY�߰��", Font.BOLD, 30));
		GridBagConstraints gbc_lblPw_1 = new GridBagConstraints();
		gbc_lblPw_1.anchor = GridBagConstraints.EAST;
		gbc_lblPw_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblPw_1.gridx = 0;
		gbc_lblPw_1.gridy = 0;
		panel.add(idLabel, gbc_lblPw_1);

		textFieldLogin = new JTextField();
		textFieldLogin.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldLogin.setFont(new Font("����", Font.PLAIN, 30));
		textFieldLogin.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		panel.add(textFieldLogin, gbc_textField_1);

		JPanel panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.insets = new Insets(0, 0, 5, 5);
		gbc_panel_5.fill = GridBagConstraints.BOTH;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 2;
		contentPane.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 280, 0, 0 };
		gbl_panel_5.rowHeights = new int[] { 41, 0 };
		gbl_panel_5.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_5.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_5.setLayout(gbl_panel_5);

		changeBox = new JCheckBox("\uD734\uB300\uD3F0 \uBC88\uD638\uB85C \uB85C\uADF8\uC778\uD558\uAE30");
		changeBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (changeBox.isSelected() == true) {
					idLabel.setText("Phone");// �������� ����ȣ��
					textFieldLogin.setText(phinfo);
				} else {
					idLabel.setText("ID");// �ٽ� ������ �� ���̵��
					textFieldLogin.setText(idInfo);
				}

			}
		});
		GridBagConstraints gbc_changeBox = new GridBagConstraints();
		gbc_changeBox.fill = GridBagConstraints.VERTICAL;
		gbc_changeBox.gridx = 1;
		gbc_changeBox.gridy = 0;
		panel_5.add(changeBox, gbc_changeBox);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 280, 450, 0 };
		gbl_panel_1.rowHeights = new int[] { 50, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblPw = new JLabel("PW");
		GridBagConstraints gbc_lblPw = new GridBagConstraints();
		gbc_lblPw.anchor = GridBagConstraints.EAST;
		gbc_lblPw.insets = new Insets(0, 0, 0, 5);
		gbc_lblPw.gridx = 0;
		gbc_lblPw.gridy = 0;
		panel_1.add(lblPw, gbc_lblPw);
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setFont(new Font("HY�߰��", Font.BOLD, 30));

		passwordField = new JPasswordField();
		passwordField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CsLoginSyn packet = new CsLoginSyn(textFieldLogin.getText(), passwordField.getText(),
						!changeBox.isSelected());
				ClientNet.getInstance().sendPacket(packet);
			}
		});
		passwordField.setHorizontalAlignment(SwingConstants.LEFT);
		passwordField.setFont(new Font("����", Font.PLAIN, 30));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.BOTH;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 0;
		panel_1.add(passwordField, gbc_passwordField);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 4;
		contentPane.add(panel_2, gbc_panel_2);

		logInBtn = new JButton("�α���");
		logInBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CsLoginSyn packet = new CsLoginSyn(textFieldLogin.getText(), passwordField.getText(),
						!changeBox.isSelected());
				ClientNet.getInstance().sendPacket(packet);
			}
		});
		logInBtn.setFont(new Font("����", Font.BOLD, 30));
		panel_2.add(logInBtn);

		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 5;
		contentPane.add(panel_3, gbc_panel_3);

		setVisible(true);

		mw = new ManagerWindow();
	}

	class logChk extends Thread {
		@Override
		public void run() {// ������ 30�� �༭ �α��� ��ư ��Ȱ��ȭ
			try {
				for (int i = 30; i >= 0; i--) {
					logInBtn.setEnabled(false);
				}
				sleep(30000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			logInBtn.setEnabled(true);
		}
	}

	public void logSet() {// �۾����� �� �α��� ȭ�� ���� �� �ؽ�Ʈ set
		textFieldLogin.setText(info);
		passwordField.setText("");
	}

	public void logTextChk() {// ack �� Not Found Data �� �� ����ִ� â
		logDialog = new JDialog();
		logDialog.setBounds(250, 250, 300, 300);
		logDialog.getContentPane().setLayout(new GridLayout(2, 1));
		logLabel = new JLabel("����� ID�� ��й�ȣ�� �Է��ϼ���");
		logDialog.getContentPane().add(logLabel);
		JButton logChkButton = new JButton("Ȯ��");
		logDialog.getContentPane().add(logChkButton);
		logChkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				logDialog.dispose();
				logSet();
			}
		});
		logDialog.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		textFieldLogin = (JTextField) e.getSource();
		String idfield = textFieldLogin.getText();

		if (idfield.equals(info) || idfield.equals(idInfo) || idfield.equals(phinfo)) {
			textFieldLogin.setText("");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void receive(PacketBase packet) {// �α��� ���� �޴� �κ�
		int cnt = 0;
		ScLoginAck ack = (ScLoginAck) packet;
//		BaseFrame.getInstance().userData = ack.userdata;
		if (ack.eResult == EResult.SUCCESS) {// �α��� ������
			if (ack.userdata.cType == 1) {
				BaseFrame.getInstance().roomInfoList = ack.roomList;
				BaseFrame.getInstance().userData = ack.userdata;
				BaseFrame.getInstance().lockerlist = ack.lockerList;
				mw.setVisible(true);
				this.setVisible(false);
			} else {
				new ChkDialog("������ ���̵� �ƴմϴ�");
			}
		} else if (ack.eResult == EResult.NOT_FOUND_DATA) {// �α��� ���н�
			// �α��ν� ID �Ǵ� ��й�ȣ ���Է� ���� �� ����ִ� â
			logTextChk();

			cnt++;
			if (cnt == 3) {
				logLabel.setText("<html>���Է� �α��� 3ȸ �������� 30�� �ڿ� " + "<br>�ٽ� ���� ���ֽñ� �ٶ��ϴ�.<html>");
				logChk log = new logChk();
				log.start();
				cnt = 0;
			}
		}
	}
}
