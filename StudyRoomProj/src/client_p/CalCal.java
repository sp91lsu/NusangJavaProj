package client_p;

import java.util.Calendar;

public class CalCal {

	public static Calendar copy(int field, Calendar cal) {
		Calendar copyCal = Calendar.getInstance();

		int last = Calendar.YEAR;

		if (field >= Calendar.YEAR) {
			copyCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		}
		if (field >= Calendar.MONTH) {
			copyCal.set(Calendar.MONTH, cal.get(Calendar.MONTH));
		}
		if (field >= Calendar.DATE) {
			copyCal.set(Calendar.DATE, cal.get(Calendar.DATE));
		}
		if (field >= Calendar.HOUR_OF_DAY) {
			copyCal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY));
		}
		return copyCal;
	}

	public static boolean isSameTime(int field, Calendar cal1, Calendar cal2) {

		int last = Calendar.YEAR;

		boolean pass = false;

		if (field >= Calendar.YEAR) {
			last = Calendar.YEAR;
			pass = cal1.get(last) == cal2.get(last);
		}
		if (pass && field >= Calendar.MONTH) {
			last = Calendar.MONTH;
			pass = cal1.get(last) == cal2.get(last);
		}
		if (pass && field >= Calendar.DATE) {
			last = Calendar.DATE;
			pass = cal1.get(last) == cal2.get(last);
		}
		if (pass && field >= Calendar.HOUR_OF_DAY) {
			last = Calendar.HOUR_OF_DAY;
			pass = cal1.get(last) == cal2.get(last);
		}

		return pass;
	}

}
