package client_p.ui_p;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import data_p.product_p.DataManager;
import data_p.product_p.room_p.RoomProduct;
import data_p.user_p.UserData;

public class RCalcFrame extends JDialog {

	RoomProduct room;
	UserData userData;
	JLabel contentLabel;

	public void openPage(RoomProduct room) {
		this.room = room;
		userData = BaseFrame.getInstance().userData;
		contentLabel.setText(getDateFormat());
		setModal(true);
		setVisible(true);
	}

	public RCalcFrame() {

		setBounds(710, 100, 500, 500);
		getContentPane().setLayout(null);

		JLabel MainLabel = new JLabel("���� �� ���� ����");
		MainLabel.setHorizontalAlignment(SwingConstants.CENTER);
		MainLabel.setFont(new Font("����", Font.BOLD, 28));
		MainLabel.setBounds(27, 29, 412, 81);
		getContentPane().add(MainLabel);

		contentLabel = new JLabel();
		contentLabel.setFont(new Font("����", Font.PLAIN, 20));
		contentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentLabel.setBounds(37, 125, 409, 220);
		getContentPane().add(contentLabel);

		JButton okButton = new JButton("���� �� ����");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BaseFrame.getInstance().paymentPop.openPage(room);
				BaseFrame.getInstance().getLoginMain().logSet();
				dispose();
			}
		});
		okButton.setBounds(60, 360, 173, 58);
		getContentPane().add(okButton);

		JButton cancleButton = new JButton("���");
		cancleButton.setBounds(250, 360, 173, 58);
		cancleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		getContentPane().add(cancleButton);

		setVisible(false);
		setUndecorated(true);
	}

	String getDateFormat() {
		String text = "<html>���� �¼� :" + room.name + "<br>������ ID :" + userData.id + "<br>���� ���� :"
				+ room.calendarList.get(0).get(Calendar.YEAR) + " " + (room.calendarList.get(0).get(Calendar.MONTH) + 1)
				+ " " + room.calendarList.get(0).get(Calendar.DATE) + "<br>���� �ð� :";
		text += room.calendarList.get(0).get(Calendar.HOUR_OF_DAY) + " ~ "
				+ (room.calendarList.get(room.calendarList.size() - 1).get(Calendar.HOUR_OF_DAY) + 1);
		text += "<br>�޴�����ȣ :" + userData.phone + "<br><html>";
		text += "<br>���� :" + DataManager.getInstance().roomMap.get(room.id).price + "��" + "<br><html>";
		return text;
	}

}