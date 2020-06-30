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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client_p.Receivable;
import packetBase_p.EResult;
import packetBase_p.PacketBase;
import server_p.packet_p.ack_p.ScLoginAck;

public class managerLogin extends JFrame implements Receivable,MouseListener{

	private JPanel contentPane;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private ManagerWindow mw;
	JLabel idLabel,logLabel;
	JDialog logDialog;
	private JButton logInBtn;
	String info = "등록한 ID 또는 휴대폰 번호를 입력하세요";
	String idInfo = "등록한 ID를 입력하세요";
	String phinfo = "등록한 휴대폰 번호를 입력하세요";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					managerLogin frame = new managerLogin();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public managerLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 100, 1280, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{5.0, 1.0, 1.0, 1.0, 10.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JPanel panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.insets = new Insets(0, 0, 5, 0);
		gbc_panel_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 0;
		contentPane.add(panel_4, gbc_panel_4);
		
		JLabel lblNewLabel_1 = new JLabel("\uAD00\uB9AC\uC790 \uB85C\uADF8\uC778");
		lblNewLabel_1.setFont(new Font("휴먼엑스포", Font.BOLD, 40));
		panel_4.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{445, 390, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblPw_1 = new JLabel("ID");
		lblPw_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw_1.setFont(new Font("HY견고딕", Font.BOLD, 30));
		GridBagConstraints gbc_lblPw_1 = new GridBagConstraints();
		gbc_lblPw_1.anchor = GridBagConstraints.EAST;
		gbc_lblPw_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblPw_1.gridx = 0;
		gbc_lblPw_1.gridy = 0;
		panel.add(lblPw_1, gbc_lblPw_1);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.TRAILING);
		textField_1.setFont(new Font("굴림", Font.PLAIN, 30));
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 0;
		panel.add(textField_1, gbc_textField_1);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {445, 390, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblPw = new JLabel("PW");
		GridBagConstraints gbc_lblPw = new GridBagConstraints();
		gbc_lblPw.anchor = GridBagConstraints.EAST;
		gbc_lblPw.insets = new Insets(0, 0, 0, 5);
		gbc_lblPw.gridx = 0;
		gbc_lblPw.gridy = 0;
		panel_1.add(lblPw, gbc_lblPw);
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setFont(new Font("HY견고딕", Font.BOLD, 30));
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("굴림", Font.PLAIN, 30));
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 0;
		panel_1.add(passwordField, gbc_passwordField);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 3;
		contentPane.add(panel_2, gbc_panel_2);
		
		logInBtn = new JButton("로그인");
		logInBtn.setFont(new Font("굴림", Font.BOLD, 30));
		panel_2.add(logInBtn);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 4;
		contentPane.add(panel_3, gbc_panel_3);
		
		setVisible(true);
		
		mw = new ManagerWindow();
	}
	
	class logChk extends Thread{
		@Override
		public void run() {//쓰레드 30초 줘서 로그인 버튼 비활성화
			try {
				for (int i = 30; i >= 0 ; i--) {
					logInBtn.setEnabled(false);
				}
				sleep(30000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			logInBtn.setEnabled(true);
		}
	}
	
	public void logSet() {//작업진행 후 로그인 화면 왔을 때 텍스트 set
		textField_1.setText(info);
		passwordField.setText("");
	}
	
	public void logTextChk() {//ack 가 Not Found Data 일 때 띄워주는 창
		logDialog = new JDialog();
		logDialog.setBounds(250, 250, 300, 300);
		logDialog.setLayout(new GridLayout(2,1));
		logLabel = new JLabel("등록한 ID와 비밀번호를 입력하세요");
		logDialog.add(logLabel);
		JButton logChkButton = new JButton("확인");
		logDialog.add(logChkButton);
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
		textField_1 = (JTextField) e.getSource();
		String idfield = textField_1.getText();

		if (idfield.equals(info) || idfield.equals(idInfo) || idfield.equals(phinfo)) {
			textField_1.setText("");
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
	public void receive(PacketBase packet) {//로그인 응답 받는 부분
		int cnt = 0;
		ScLoginAck ack = (ScLoginAck) packet;
//		BaseFrame.getInstance().userData = ack.userdata;
		if (ack.eResult == EResult.SUCCESS) {//로그인 성공시
			mw.setVisible(true);
		}else if(ack.eResult == EResult.NOT_FOUND_DATA) {//로그인 실패시
			//로그인시 ID 또는 비밀번호 미입력 했을 때 띄워주는 창
			logTextChk();
			
			cnt++;
			if(cnt==3) {
				logLabel.setText("<html>미입력 로그인 3회 진행으로 30초 뒤에 "
						+ "<br>다시 실행 해주시기 바랍니다.<html>");
				logChk log = new logChk();
				log.start();
				cnt = 0;
			}
		}
	}
}
