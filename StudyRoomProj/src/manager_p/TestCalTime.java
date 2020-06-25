package manager_p;

import java.util.Calendar;

import com.sun.jmx.snmp.Timestamp;

public class TestCalTime {

	public static void main(String[] args) {

		Calendar cal = Calendar.getInstance();
		Timestamp ts = new Timestamp(cal.getTimeInMillis());
		System.out.println(ts);
		cal.setTimeInMillis(ts.getDateTime());
		System.out.println(cal.getTime());
		
		System.out.println(ts.getDate().getHours());
	}

}
