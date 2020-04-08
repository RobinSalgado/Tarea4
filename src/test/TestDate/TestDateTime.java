package test.TestDate;
import com.iteso.calendar.*;

public class TestDateTime {
	public static void main ( String[] args) {


		DateTime time1 = new DateTime();
		DateTime time2 = new DateTime(0,26,3);
		
		DateTime time3 = new DateTime(23,59,59,31,12,2017,0);
			time3.next(); // siguiente segundo de time3		
			
		DateTime time4 = new DateTime(time3.getHours(),time3.getMinutes(),time3.getSeconds(),
				time3.getDay(),time3.getMonth(),time3.getYear(),0);
		
		DateTime date5 = new DateTime(31,12,2017);
		date5.setFormat(0);
		System.out.println(date5.toString());
//		time3.nextPadre();
		date5.setFormat(0);
		System.out.println(date5.getYear());
//		date5.next();// Siguiente día de time3 y time 5
		
		DateTime time6 = new DateTime(time3.getHours(),time3.getMinutes(),time3.getSeconds(),
							date5.getDay(),date5.getMonth(),date5.getYear(),0);

		DateTime clonTime2 = time2.clone();
		
		DateTime myArrOfTimes[] = new DateTime[6];
		myArrOfTimes[0] = time1;
		myArrOfTimes[1] = time2;
		myArrOfTimes[2] = time3;
		myArrOfTimes[3] = time4;
		myArrOfTimes[4] = date5;
		myArrOfTimes[5] = time6;
		
	
		
//		System.out.println(time2.equals(clonTime2));
//		System.out.println(time3.equals(date5));
//		System.out.println(date5.equals(time3));
//		
//		DateTime arrTimes[] = new DateTime[6];
//
//		arrTimes[0] = time1;
//		arrTimes[1] = time2;
//		arrTimes[2] = time3;
//		arrTimes[3] = time4;
//		arrTimes[4] = (DateTime)date5;
//		arrTimes[5] = time6;
//		
//		
//		for ( int i = 0; i < 3; i++) {
//			arrTimes[0].setFormat(i);
//			System.out.println(arrTimes[0].toString());
//		}
//	
		
//		int cont = 1;
//		for ( String i: times ) {
//			
//			if (cont == 1) { time1.setFormat(0); i = time1.toString(); System.out.println(i);
//							 time1.setFormat(1); i = time1.toString(); System.out.println(i);
//							 time1.setFormat(2); i = time1.toString(); System.out.println(i);
//							 System.out.println(""); cont++;}
//			
//			if (cont == 2) { time2.setFormat(0); i = time1.toString(); System.out.println(i);
//							 time2.setFormat(1); i = time1.toString(); System.out.println(i);
//							 time2.setFormat(2); i = time1.toString(); System.out.println(i);
//							 System.out.println(""); cont++; }
//			
//			if (cont == 3) { time3.setFormat(0);i = time1.toString(); System.out.println(i);
//							 time3.setFormat(1);i = time1.toString(); System.out.println(i);
//							 time3.setFormat(2);i = time1.toString(); System.out.println(i);
//							 System.out.println(""); cont++; } 
//			
//			if (cont == 4) { i = time4.toString(); System.out.println(i); cont++; }
//			if (cont == 5) { i = date5.toString(); System.out.println(i); cont++; }
//			if (cont == 6) { i = time6.toString(); System.out.println(i); cont++; }
//		}

	// como acceder a los metodos de DAte



	}

}
