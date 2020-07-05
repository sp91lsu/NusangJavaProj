package manager_p.panelDialog_p;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client_p.ClientNet;
import client_p.ui_p.MyColor;
import manager_p.ManagerWindow;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Color;

public class ChatEndDialog extends JDialog {

	ManagerWindow mw;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChatEndDialog dialog = new ChatEndDialog(new ManagerWindow());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ChatEndDialog(ManagerWindow mw) {
		this.mw = mw;
		setBounds(100, 100, 400, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.DARK_GRAY);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{374, 0};
		gbl_contentPanel.rowHeights = new int[]{168, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lb_Chat_EndChk = new JLabel("채팅을 종료하시겠습니까?");
			lb_Chat_EndChk.setForeground(Color.WHITE);
			lb_Chat_EndChk.setFont(new Font("굴림", Font.PLAIN, 20));
			GridBagConstraints gbc_lb_Chat_EndChk = new GridBagConstraints();
			gbc_lb_Chat_EndChk.gridx = 0;
			gbc_lb_Chat_EndChk.gridy = 0;
			contentPanel.add(lb_Chat_EndChk, gbc_lb_Chat_EndChk);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setBackground(MyColor.w_white);
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						mw.pnl_Chatting.btnTerminate.setEnabled(false);
						mw.pnl_Chatting.amIstopChat = true;
						mw.pnl_Chatting.textArea.setText(mw.pnl_Chatting.textArea.getText()+"\n"+"["+mw.pnl_Chatting.userName+"]"+"님과의 채팅을 종료하였습니다.\n");
						mw.pnl_Chatting.chatSyn.end();
						ClientNet.getInstance().sendPacket(mw.pnl_Chatting.chatSyn);
						mw.pnl_Chatting.lb_Chat_end.setText("님과 채팅이 종료되었습니다.");
						dispose();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBackground(MyColor.w_white);
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
