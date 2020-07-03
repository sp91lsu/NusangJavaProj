package client_p.ui_p;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import client_p.CalCal;
import client_p.ClientNet;
import client_p.packet_p.syn_p.CsBuyRoomSyn;
import data_p.product_p.room_p.RoomProduct;

public class AddTimeDialog extends JDialog implements ActionListener {

	JDialog timeEmpty;
	int startTime, endTime, timeChoice = 0;
	int addPri = (int) BaseFrame.getInstance().getUsingRoom().price;
	Calendar last;

	public AddTimeDialog() {

		int extension = timeChoice();
		if (extension > 0) {

			setModal(true);
			setBounds(725, 350, 450, 380);
			JPanel contentPane = new JPanel();
			setContentPane(contentPane);
			contentPane.setLayout(null);
			getContentPane().setBackground(MyColor.black);

			JLabel titleL = new JLabel("좌석 연장");
			titleL.setHorizontalAlignment(SwingConstants.CENTER);
			titleL.setBounds(94, 10, 273, 54);
			contentPane.add(titleL);
			titleL.setForeground(Color.white);

			JLabel timeInfoL = new JLabel("시간 연장");
			timeInfoL.setBounds(259, 74, 73, 31);
			contentPane.add(timeInfoL);
			timeInfoL.setForeground(Color.white);

			JLabel addPTitleL = new JLabel("추가 결제 금액:");
			addPTitleL.setFont(new Font("굴림", Font.BOLD, 15));
			addPTitleL.setBounds(111, 190, 111, 43);
			contentPane.add(addPTitleL);
			addPTitleL.setForeground(Color.white);

			JLabel priceInfoL = new JLabel(addPri + "원");
			priceInfoL.setFont(new Font("굴림", Font.BOLD, 15));
			priceInfoL.setHorizontalAlignment(SwingConstants.LEFT);
			priceInfoL.setBounds(240, 190, 146, 43);
			contentPane.add(priceInfoL);
			priceInfoL.setForeground(Color.white);

			JLabel roomTitleL = new JLabel("현재 이용중인 좌석:");
			roomTitleL.setFont(new Font("굴림", Font.BOLD, 14));
			roomTitleL.setBounds(105, 127, 146, 43);
			contentPane.add(roomTitleL);
			roomTitleL.setForeground(Color.white);

			JLabel roomNameL = new JLabel(BaseFrame.getInstance().getUsingRoom().name);
			roomNameL.setHorizontalAlignment(SwingConstants.LEFT);
			roomNameL.setFont(new Font("굴림", Font.BOLD, 15));
			roomNameL.setBounds(263, 128, 111, 43);
			contentPane.add(roomNameL);
			roomNameL.setForeground(Color.white);

			JButton payButton = new JButton("결제");
			payButton.setBounds(94, 262, 110, 43);
			contentPane.add(payButton);
			payButton.setBackground(MyColor.w_white);
			payButton.addActionListener(this);

			JButton cancelButton = new JButton("취소");
			cancelButton.setBounds(246, 262, 110, 43);
			cancelButton.setBackground(MyColor.w_white);
			cancelButton.addActionListener(this);
			contentPane.add(cancelButton);

			Vector<Integer> timeCnt = new Vector<Integer>();

			if (extension > 0) {
				timeChoice = 1;
			}

			for (int i = 1; i <= extension; i++) {
				timeCnt.add(i);
			}

			JComboBox timeSelectCom = new JComboBox(timeCnt);
			timeSelectCom.setBounds(174, 74, 73, 31);
			contentPane.add(timeSelectCom);

			timeSelectCom.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (timeSelectCom.getSelectedItem() != null) {
						timeChoice = (int) timeSelectCom.getSelectedItem();
					}
					addPri = (int) timeSelectCom.getSelectedItem() * 
							(int) BaseFrame.getInstance().getUsingRoom().price;
					priceInfoL.setText(addPri + "원");
				}
			});
			setUndecorated(true);
			setVisible(true);
		} else {
			timeEmpty = new JDialog();
			timeEmpty.setBounds(835, 440, 250, 200);
			timeEmpty.setBackground(MyColor.black);
			timeEmpty.setLayout(new GridLayout(0, 1));

			JLabel lb = new JLabel("연장 할 수 있는 시간이 없습니다.");
			lb.setOpaque(true);
			lb.setForeground(MyColor.white);
			lb.setBackground(MyColor.black);
			timeEmpty.add(lb);

			JButton bt = new JButton("확인");
			bt.setBackground(MyColor.w_white);
			timeEmpty.add(bt);
			bt.addActionListener(this);
			timeEmpty.setUndecorated(true);
			timeEmpty.setModal(true);
			timeEmpty.setVisible(true);

		}
	}

	public int timeChoice() {

		RoomProduct room = BaseFrame.getInstance().checkMyReserRoom(null, Calendar.DATE);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();

		if (btn.getText().equals("결제")) {
			sendPacket();
			dispose();
			BaseFrame.getInstance().getMainLayout().is_addTime = false;
		} else if (btn.getText().equals("취소")) {
			dispose();
			BaseFrame.getInstance().getMainLayout().is_addTime = false;
		} else if (btn.getText().equals("확인")) {
			timeEmpty.dispose();
			BaseFrame.getInstance().getMainLayout().is_addTime = false;
		}
	}
}
