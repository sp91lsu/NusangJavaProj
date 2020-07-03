package data_p.sales_p;

import java.io.Serializable;

public class SalesBySeat implements Serializable {
	public String room_name = "";
	public int sum = 0;
	public  int cnt = 0;
   
   public SalesBySeat(String room_name, int sum, int cnt) {
      super();
      this.room_name = room_name;
      this.sum = sum;
      this.cnt = cnt;
   }

   
   
}