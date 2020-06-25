package manager_p;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client_p.ClientNet;
import client_p.packet_p.syn_p.CsChatSyn;
import manager_p.ack_p.MsChatConnectAck;
import server_p.packet_p.syn_p.SMChatConnectSyn;

public class ChatReqDialog extends JDialog {
	managerWindow mw;
	private final JPanel contentPanel = new JPanel();
	SMChatConnectSyn smc;
	String userName=null;
	
	public static void main(String[] args) {
		ChatReqDialog dialog = new ChatReqDialog(new managerWindow(),new SMChatConnectSyn(null));
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public ChatReqDialog(managerWindow mw,SMChatConnectSyn smc) {
		this.mw = mw;
		this.smc = smc;
//		userName = smc.name;
		setBounds(100, 100, 339, 213);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uCC44\uD305\uC744 \uC218\uB77D\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		lblNewLabel.setBounds(42, 85, 221, 15);
		contentPanel.add(lblNewLabel);

		JLabel lbClientName = new JLabel(userName);
		lbClientName.setBounds(42, 35, 129, 15);
		contentPanel.add(lbClientName);

		JLabel lblNewLabel_2 = new JLabel(
				"\uB2D8 \uC73C\uB85C\uBD80\uD130 \uCC44\uD305 \uBB38\uC758 \uC694\uCCAD\uC774 \uC654\uC2B5\uB2C8\uB2E4.");
		lblNewLabel_2.setBounds(42, 60, 221, 15);
		contentPanel.add(lblNewLabel_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\uC218\uB77D");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						MsChatConnectAck packet = new MsChatConnectAck(true);
						packet.setCip(smc.clientIp);
						packet.setManagerIp(smc.managerIp);
						CsChatSyn csc = new CsChatSyn(smc.clientIp,smc.managerIp);
						mw.chatSyn =csc;
						ClientNet.getInstance().sendPacket(packet);
						mw.tabbedPane.setSelectedIndex(7);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\uAC70\uC808");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						MsChatConnectAck packet = new MsChatConnectAck(true);
						ClientNet.getInstance().sendPacket(packet);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
