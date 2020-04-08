package test.TestDate;
import java.io.ObjectInputStream.GetField;

import com.iteso.calendar.Date;
public class TestDate {

	public static final int FORMAT_0 = 0,FORMAT_1 = 1,FORMAT_2 = 2;
	public static void main(String[] args) {


		Date d1 = new Date();
		Date d2 = new Date(31,12,2016,FORMAT_2);
		Date d3 = d1.clone();
		Date d4 = new Date(d1.getDay(),d2.getMonth(),d3.getYear(),FORMAT_1);

		d2.next(); // the next day of d2
		Date d5 = new Date(d2.getDay(),d2.getMonth(),d2.getYear(),FORMAT_0);

		d3.setMonth(4); // asignamos Abril a d3
//
//		//Comparamos contenido d1 & d3
//		System.out.println(d1.equals(d3));
//		//Comparamos el contenido d1&d5
//		System.out.println(d1.equals(d5)+"\n");
		
//		System.out.println(Date.isLeap(2021));

		System.out.println(d3.getYear());
//		String fechas[] = new String[5];

//		int cont = 1;
//		for ( String i: fechas ) {
//			if (cont == 1) { i = d1.toString(); System.out.println(i); cont++; }
//			if (cont == 2) { i = d2.toString(); System.out.println(i); cont++; }
//			if (cont == 3) { i = d3.toString(); System.out.println(i); cont++; } //pregunta?prof
//			if (cont == 4) { i = d4.toString(); System.out.println(i); cont++; }
//			if (cont == 5) { i = d5.toString(); System.out.println(i); cont++; }
//		}

	}

}
