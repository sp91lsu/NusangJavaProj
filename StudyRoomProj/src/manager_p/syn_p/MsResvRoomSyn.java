package manager_p.syn_p;

import packetBase_p.PacketBase;

//예약 룸 정보 요청
public class MsResvRoomSyn  extends PacketBase {
	public String yyyy;
	public String mm;
	public String dd;

	public MsResvRoomSyn(int yyyy, int mm, String dd) {
		this.yyyy = yyyy+"";
		this.mm = mm<10 ? "0"+mm : ""+mm;
		this.dd = dd.length()==1 ? "0"+dd : dd;
	}

	@Override
	public String toString() {
		return "MsGiveMeResvRoomSyn [yyyy=" + yyyy + ", mm=" + mm + ", dd=" + dd + "]";
	}
	
	
}
