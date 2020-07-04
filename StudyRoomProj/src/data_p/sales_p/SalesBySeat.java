package data_p.sales_p;

import java.io.Serializable;

public class SalesBySeat implements Serializable {
	public String room_name = "";
	public String sum = "";
	public String cnt = "";
   
   public SalesBySeat(String room_name, String sum, String cnt) {
      super();
      this.room_name = room_name;
      this.sum = sum;
      this.cnt = cnt;
   }

   
   
}