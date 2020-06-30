package data_p.sales_p;

import java.io.Serializable;
import java.util.ArrayList;

public class SalesData implements Serializable {
	public ArrayList<SalesRecord> salesRecordArrL;
	public ArrayList<SalesBySeat> saleBySeatArrL;
	public SalesTot salesTot;
	
	public SalesData(ArrayList<SalesRecord> salesRecordArrL, ArrayList<SalesBySeat> saleBySeatArrL,
			SalesTot salesTot) {
		this.salesRecordArrL = salesRecordArrL;
		this.saleBySeatArrL = saleBySeatArrL;
		this.salesTot = salesTot;
	}
	
	
}


