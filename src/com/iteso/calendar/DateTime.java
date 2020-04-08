package com.iteso.calendar;

import java.text.DecimalFormat;
import java.time.LocalTime;

public class DateTime extends Date {

	private int seconds;
	private int minutes;
	private int hours;
	
	/* Asigna hora y fecha actuales*/
	public DateTime () {
		super();
		LocalTime ld = LocalTime.now();
		setSeconds( ld.getSecond());
		setMinutes( ld.getMinute());
		setHours( ld.getHour());
		

	}

	/*Asigna hora y fecha recibidas*/
	public DateTime(int hh, int mi, int ss, int dd, int mm, int yy, int format ) {
		super (dd,mm,yy); // Invoca al segundo constructor de Date.
		setHours(hh);
		setMinutes(mi);
		setSeconds(ss);
		setFormat(format);
	}

	/*Asigna hora y fecha recibidas*/
	public DateTime(int hh, int mi, int ss, int dd, int mm, int yy) {
		super (dd,mm,yy); // Invoca al segundo constructor de Date.
		setHours(hh);
		setMinutes(mi);
		setSeconds(ss);
	
	}
	/* Asigna hora recibida y fecha actual*/ 
	public DateTime ( int hh, int mi, int ss) {
		super();
		if ( seconds >= 0 && seconds <= 59 ) setHours(hh);
		if ( minutes >= 0 && minutes <= 59 ) setMinutes(mi);
		if ( hours   >= 0 && hours   <= 23 ) setSeconds(ss);

		
	}
	
	/*Asigna hora actual y fecha recibida*/
	public DateTime (Date d) {
		super( d.getDay(),d.getMonth(), d.getYear());

		LocalTime ld = LocalTime.now();
		setMinutes(getMinutes());
		setSeconds( ld.getSecond());
		setMinutes( ld.getMinute());
		setHours( ld.getHour());
	}
	public void toSystemTime() {


	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		if (seconds >= 0 && seconds <= 59 ) this.seconds = seconds;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		if (minutes >= 0 && minutes <= 59 ) this.minutes = minutes;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		if ( hours >= 0 && hours <= 23) this.hours = hours;
	}

	@Override
	public String toString() {
		
		String pattern="00";
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String s_hours = myFormatter.format(getHours()); // cambiando el patron para que imprima en dos dígitos siempre "00"
		String s_minutes = myFormatter.format(getMinutes()); // cambiando el patron para que imprima en dos dígitos siempre "00"
		String s_seconds = myFormatter.format(getSeconds()); // cambiando el patron para que imprima en dos dígitos siempre "00"
		
		switch (getFormat()) {

		case 0 : return "[" + s_hours + ":" + s_minutes + ":" + s_seconds + "]" + " " + super.toString();
		case 1 : if ( getHours() < 12) 
			 return "[" + s_hours + ":" + s_minutes + ":" + s_seconds + " " +"AM]"+ " "+ super.toString() ;
		else return "[" + s_hours + ":" + s_minutes + ":" + s_seconds + " " +"PM]"+ " "+ super.toString() ;
		default :  if ( getHours() < 12) 
			 return "[" + s_hours + ":" + s_minutes + ":" + s_seconds + " " +"AM]"+ " "+ super.toString() ;
		else return "[" + s_hours + ":" + s_minutes + ":" + s_seconds + " " +"PM]"+ " "+ super.toString() ;
		}
	}

	public boolean equals( Object objDT ) { 
		if ( ! ( objDT instanceof DateTime) ) return false;
		else {
			DateTime castODT = (DateTime) objDT;
			if (castODT.getSeconds() == this.seconds && castODT.getMinutes() == this.minutes
					&& castODT.getHours() == this.hours && super.equals(objDT) == true)
				return true;
			else return false;				
		}
	}

	public DateTime clone() {
		return new DateTime( hours, minutes, seconds, getDay(), getMonth(), getYear(),getFormat());
		
	}
	
	public void nextPadre() {
		super.next();
	}
	
	@Override
	public void next(){
	
		this.seconds = getSeconds() + 1;

		if(getSeconds() > 59) {
			if(getMinutes() == 59) {
				if(getHours() == 23) {
				setSeconds(0);
				setMinutes(0);
				setHours(0);
				} else {
					setSeconds(0);
					setMinutes(0);
					setHours(getHours()+1);
				}
			} else {
				setSeconds(0);
				setMinutes(getMinutes()+1);
			}	
		}
		
	}

		public Date toDate(){
			
			Date dt = new Date(getDay(),getMonth(),getYear());
			return dt;
			
		
		}

}

