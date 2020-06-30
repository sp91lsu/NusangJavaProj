package data_p.sales_p;

import java.io.Serializable;

public class SalesTot implements Serializable {
	public int dateSortN = 0;
	public int sumTot = 0;
	public int cntTot = 0;
	
	public SalesTot(int dateSortN, int sumTot, int cntTot) {
		this.dateSortN = dateSortN;
		this.sumTot = sumTot;
		this.cntTot = cntTot;
	}
	
	
}
