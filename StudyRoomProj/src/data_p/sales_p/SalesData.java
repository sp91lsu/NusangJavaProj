package data_p.sales_p;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesData implements Serializable {
	ArrayList<SalesRecord> SalesRecordArrL;
	ArrayList<SalesBySeat> SaleBySeatArrL;
	SalesTot SalesTot;
	
	public SalesData(ArrayList<SalesRecord> salesRecordArrL, ArrayList<SalesBySeat> saleBySeatArrL,
			SalesTot SalesTot) {
		SalesRecordArrL = salesRecordArrL;
		SaleBySeatArrL = saleBySeatArrL;
		SalesTot = SalesTot;
	}
	
	
}


