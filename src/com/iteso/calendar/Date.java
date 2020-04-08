package com.iteso.calendar;

import java.time.LocalDate;

public class Date {

	/* Constantes mínimo número de año y máximo número de año*/
	public static final int MIN_YEAR = 1900, MAX_YEAR = 3000; 
	/* Constantes mínimo número de mes y máximo número de mes*/
	public static final int MIN_MONTH = 1, MAX_MONTH = 12;

	/* Atributos de clase*/
	private int day;
	private int month;
	private int year;
	private String monthName;
	private int format;

	/*Constructor # 1*/
	public Date() {
		LocalDate ld = LocalDate.now();
		setMonth( ld.getMonthValue() );
		setDay( ld.getDayOfMonth() );
		setYear( ld.getYear() );
	}

	/*Constructor # 2*/
	public Date (int dd, int mm, int yy) {

		if( yy >= MIN_YEAR && yy <= MAX_YEAR ) this.year = yy-2000;

		/* llamamos a setMonth para que se incialize el val de month
		 * en número y en string*/
		if (mm >= MIN_MONTH && mm <= MAX_MONTH) setMonth(mm);

		if ( mm == 4 || mm == 6 || mm == 9 || mm == 11 ) {
			if ( dd >= 1 && dd <= 30) this.day = dd;
		}
		else if (mm==1 || mm==3 || mm==5 || mm==7 || mm==8 || mm==10 || mm==12) {
			if ( dd >= 1 && dd <=31) this.day = dd;
		}
		else if ( mm == 2) this.day = dd;
	}

	public Date ( int dd, int mm, int yy, int format) {
		this(dd,mm,yy);	

		if ( format >= 0 && format <= 2 ) this.format = format;
	}

	/* SETTER & GETTERS*/
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		if (month >= 1 && month <= 12) {
			this.month = month;
		}
		switch ( month ) {
		case  1: this.monthName = "Enero"; 		break;
		case  2: this.monthName = "Febrero";	break;
		case  3: this.monthName = "Marzo";		break;
		case  4: this.monthName = "Abril";		break;
		case  5: this.monthName = "Mayo";		break;
		case  6: this.monthName = "Junio";		break;
		case  7: this.monthName = "Julio";		break;
		case  8: this.monthName = "Agosto";		break;
		case  9: this.monthName = "Septiembre";	break;
		case 10: this.monthName = "Octubre";	break;
		case 11: this.monthName = "Noviembre";	break;
		case 12: this.monthName = "Diciembre";	break;

		}

	}
	public int getYear() {
		return this.year;
	}
	public void setYear(int year) {
		if( year >= MIN_YEAR && year <= MAX_YEAR ) {
			this.year = year;
		}
	}
	public int getFormat() {
		return format;
	}
	public void setFormat(int format) {
		this.format = format;
	}
	public String getMonthName( String monthName) {
		return monthName;
	}
	/*END OF SETTER & GETTERS*/

	public static boolean isLeap( int year) {
		
		if(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) 
		return true;
		else return false;
		
	}
	
	public Date clone() {
		return new Date( this.day, this.month,this.year,this.format); 
	}

	public void next () {
		day += 1;

		if (month == 4 || month == 6 || month == 9 || month == 11 ) {
			if ( day > 30) { day = 1; month++;}
		}		

		if (month==1 || month==3 || month==5 || month==7 || month==8 || month==10 || month==12) { 
			if(day > 31 && month == 12) {
				day = 1;
				setMonth(1);
				year++;
			} else if ( day > 31 && month < 12) {day = 1; month ++;}
		}

		if ( month == 2 && day > 28) {day = 1; month++;}
	}


	public String toString () {
		if (format == 0) 
			return  day +"/" + month + "/" + year;
		else if ( format == 1)
			return day + "-" + month + "-" + year;
		else 
			return day + " de " + monthName + " del " + year;
	}

	public boolean equals( Object obj) {
		if ( !(obj instanceof Date )) return false;
		else {
			Date castObj = (Date) obj;
			if (castObj.day == this.day && castObj.month == this.month && castObj.year == this.year) { return true;} 
		}   return false;
	}


}
