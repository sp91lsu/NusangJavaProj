package data_p.sales_p;

import java.io.Serializable;

public class SalesTot implements Serializable {
	public int dateSortN = 0;
	public String cntTot = "";
	public String sumTot = "";
	
	public SalesTot(int dateSortN, String cntTot, String sumTot) {
		this.dateSortN = dateSortN;
		this.cntTot = cntTot;
		this.sumTot = sumTot;
	}
	
	
	
	
}
