package data_p.sales_p;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesData implements Serializable {
	ArrayList<SalesRecord> SalesRecordArrL;
	ArrayList<SalesBySeat> SaleBySeatArrL;
	ArrayList<SalesTot> SalesTotArrL;
	
	public SalesData(ArrayList<SalesRecord> salesRecordArrL, ArrayList<SalesBySeat> saleBySeatArrL,
			ArrayList<SalesTot> salesTotArrL) {
		SalesRecordArrL = salesRecordArrL;
		SaleBySeatArrL = saleBySeatArrL;
		SalesTotArrL = salesTotArrL;
	}
	
	
}


