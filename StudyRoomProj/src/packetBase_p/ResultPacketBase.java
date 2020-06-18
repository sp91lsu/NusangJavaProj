package packetBase_p;

import java.util.UUID;

public class ResultPacketBase extends PacketBase {

	public EResult eResult;

	public ResultPacketBase(UUID clientUUID, EResult eResult) {
		super();
		this.clientUUID = clientUUID;
		this.eResult = eResult;
	}
}