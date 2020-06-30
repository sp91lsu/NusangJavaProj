package client_p.ui_p;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client_p.CalCal;
import client_p.ClientNet;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import data_p.product_p.room_p.RoomProduct;

public class AddTimeFrame extends JFrame {

	private JPanel contentPane;

	int startTime;
	int endTime;
	int timeChoice = 0;
	Calendar last;

	public AddTimeFrame() {
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titleL = new JLabel("좌석 연장");
		titleL.setHorizontalAlignment(SwingConstants.CENTER);
		titleL.setBounds(94, 10, 273, 54);
		contentPane.add(titleL);

		JLabel timeInfoL = new JLabel("시간 연장");
		timeInfoL.setBounds(270, 112, 73, 31);
		contentPane.add(timeInfoL);

		JButton payButton = new JButton("결제");
		payButton.setBounds(91, 188, 110, 43);
		contentPane.add(payButton);
		payButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendPacket();
				dispose();
			}
		});

		JButton cancelButton = new JButton("취소");
		cancelButton.setBounds(243, 188, 110, 43);
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		contentPane.add(cancelButton);

		Vector<Integer> timeCnt = new Vector<Integer>();
		int extension = timeChoice();

		if (extension > 0) {
			timeChoice = 1;
		}

		for (int i = 1; i <= extension; i++) {
			timeCnt.add(i);
		}
		
		JComboBox timeSelectCom = new JComboBox(timeCnt);
		timeSelectCom.setBounds(185, 112, 73, 31);
		contentPane.add(timeSelectCom);
		timeSelectCom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeSelectCom.getSelectedItem() != null)
					timeChoice = (int) timeSelectCom.getSelectedItem();
			}
		});
		setVisible(true);
	}

	public int timeChoice() {

		RoomProduct room = BaseFrame.getInstance().checkMyReserRoom(Calendar.DATE);
		ArrayList<Calendar> myCalList = new ArrayList<Calendar>();

		for (Calendar reserCal : room.calendarList) {
			Calendar buf = Calendar.getInstance();
			buf.setTimeInMillis(reserCal.getTimeInMillis());
			myCalList.add(buf);
		}

		last = myCalList.get(0);
		for (Calendar cal : myCalList) {
			if (last.getTimeInMillis() < cal.getTimeInMillis()) {
				last = cal;
			}
		}
		System.out.println("내 마지막 시간 ");
		int lastIdx = last.get(Calendar.HOUR_OF_DAY);
		System.out.println(lastIdx);
		System.out.println("바로 다음에 올 가장 짧은 시간 ");
		int extension = 0;
		Calendar next = null;

		for (RoomProduct rp : BaseFrame.getInstance().roomInfoList) {
			if (rp.id.equals(room.id)) {
				for (Calendar calMe : rp.calendarList) {
					int reserIdx = calMe.get(Calendar.HOUR_OF_DAY);

					System.out.println("다음예약" + reserIdx);
					if (CalCal.isSameTime(Calendar.DATE, last, calMe)) {

						if (lastIdx < reserIdx) {
							if (next == null) {
								next = calMe;
							}
							if (next.get(Calendar.HOUR_OF_DAY) > calMe.get(Calendar.HOUR_OF_DAY)) {
								next = calMe;
							}
						}
					}
				}
			}
		}

		if (next != null) {
			System.out.println(next.getTime());
			int between = next.get(Calendar.HOUR_OF_DAY) - last.get(Calendar.HOUR_OF_DAY);
			extension = between - 1;
		} else {
			extension = 23 - last.get(Calendar.HOUR_OF_DAY);
		}
		System.out.println("연장할 수 있는 시간 " + extension);

		return extension;
	}

	public void sendPacket() {
		RoomProduct room = BaseFrame.getInstance().getUsingRoom();
		ArrayList<Calendar> myCalList = room.calendarList;
		RoomProduct roomProduct = room.getClone();
		myCalList = new ArrayList<Calendar>();
		for (int i = 1; i <= timeChoice; i++) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.set(Calendar.HOUR_OF_DAY, last.get(Calendar.HOUR_OF_DAY) + i);
			myCalList.add(cal);
		}
		roomProduct.calendarList = myCalList;
		CsBuyRoomSyn packet = new CsBuyRoomSyn(roomProduct, BaseFrame.getInstance().userData.uuid);
		ClientNet.getInstance().sendPacket(packet);
	}
}
