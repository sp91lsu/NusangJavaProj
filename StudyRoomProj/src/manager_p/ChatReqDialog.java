package manager_p;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client_p.ClientNet;
import client_p.packet_p.ack_p.CsChatConnectAck;
import client_p.packet_p.ack_p.MsChatConnectAck;
import client_p.packet_p.syn_p.CsChatConnectSyn;
import packetBase_p.EResult;
import server_p.packet_p.ack_p.ScChatConnectAck;
import server_p.packet_p.syn_p.ScChatSyn;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatReqDialog extends JDialog {
	// managerWindow mw;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		try {
		ChatReqDialog dialog = new ChatReqDialog(new managerWindow());
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	/**
	 * Create the dialog.
	 */
	public ChatReqDialog(managerWindow mw) {
		// this.mw = mw;
		setBounds(100, 100, 339, 213);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\uCC44\uD305\uC744 \uC218\uB77D\uD558\uC2DC\uACA0\uC2B5\uB2C8\uAE4C?");
		lblNewLabel.setBounds(42, 85, 221, 15);
		contentPanel.add(lblNewLabel);

		JLabel lbClientName = new JLabel("\uD074\uB77C\uC774\uC5B8\uD2B8");
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
						ScChatSyn packet = new ScChatSyn();
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
