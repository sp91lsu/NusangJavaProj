package manager_p.syn_p;

import packetBase_p.PacketBase;

public class MsSalesInquirySyn extends PacketBase{
	public String year,month,day;

	public MsSalesInquirySyn(String year, String month, String day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}
	
}
