package manager_p.syn_p;

import packetBase_p.PacketBase;

//예약 룸 정보 요청
public class MsGiveMeResvRoomSyn  extends PacketBase {
	public String yyyy;
	public String mm;
	public String dd;

	public MsGiveMeResvRoomSyn(int yyyy, int mm, String dd) {
		this.yyyy = yyyy+"";
		this.mm = mm<10 ? "0"+mm : ""+mm;
		this.dd = dd.length()==1 ? "0"+dd : dd;
	}
	
	
}
