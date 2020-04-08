package test.TestDate;

import com.iteso.calendar.Millis;

public class TestMillis {
	public static void main (String[] args) {

		Millis m1 = new Millis();  
//		System.out.println(m1);  		// [19:37:42.798] 08/10/18  
		m1.setFormat(1);  
//		System.out.println(m1);   		// [07:37:42 PM] 8-Oct-2018 (1539045462798)  
		m1.setFormat(2);  
//		System.out.println(m1);   		// [07:37:42 PM] 8 de octubre de 2018 (1539045462798)

		Millis m2 = new Millis(15, 12, 2018);    				// [00:00:00.000] 15/12/18  
		Millis m3 = new Millis(21, 45, 52, 15, 12, 2018);  		// [21:45:52.000] 15/12/18  
		Millis m4 = new Millis(21, 45, 52, 785, 15, 12, 2018);  // [21:45:52.785] 15/12/18  
		int    ms = m4.getMilliseconds();      					// ms = 785 
		long   ts = m4.getTimestamp();      					// ts = 1544931952785 
		
		 Millis m5 = new Millis(1_307_169_163_000L);    // [01:32:43.000] 04/06/11 
		 m5.setTimestamp(0);      						// [18:00:00.000] 31/12/69  
		 m5.setMilliseconds(90);      					// [18:00:00.090] 31/12/69 
		 System.out.println(m5);
	}
}
