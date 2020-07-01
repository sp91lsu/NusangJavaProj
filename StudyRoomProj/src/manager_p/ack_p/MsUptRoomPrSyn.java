package manager_p.ack_p;

import java.sql.SQLException;
import java.util.HashMap;

import packetBase_p.PacketBase;

public class MsUptRoomPrSyn extends PacketBase {

	public HashMap<Integer, Integer> map_roomID_Pr;

	public MsUptRoomPrSyn(HashMap<Integer, Integer> map_roomID_Pr) {
		super();
		this.map_roomID_Pr = map_roomID_Pr;
	}
}

