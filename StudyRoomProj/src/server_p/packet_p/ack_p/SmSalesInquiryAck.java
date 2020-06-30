package server_p.packet_p.ack_p;

import data_p.sales_p.SalesData;
import packetBase_p.EResult;
import packetBase_p.ResultPacketBase;

public class SmSalesInquiryAck extends ResultPacketBase  {

	SalesData salesD;
	public SmSalesInquiryAck(EResult eResult,SalesData salesD) {
		super(eResult);
		this.salesD = salesD;
	}

	
}
