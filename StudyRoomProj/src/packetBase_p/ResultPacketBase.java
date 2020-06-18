package packetBase_p;

import java.util.UUID;

public class ResultPacketBase extends PacketBase {

	public EResult eResult;

	public ResultPacketBase(EResult eResult) {
		super();
		this.eResult = eResult;
	}
}