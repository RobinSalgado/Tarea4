package com.iteso.calendar;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
//import java.time.Instant;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;

public class Millis extends DateTime{

	private static final String DAY = "day";
	private static final String HOUR = "hour";
	private static final String MINUTE = "minute";
	private static final String SECOND = "second";
	private static final String MILLISECOND = "millisecond";
	public  String formattedDtm = null;

	final int MIN_MILLISECONDS = 0;
	final int MAX_MILLISECONDS = 9999;
	// the number of milliseconds since the epoch of 1970-01-01T00:00:00Z;
	final static long CURRENT_TIMESTAMP = Instant.now().toEpochMilli(); 
	private static long timestamp; // Inicializamos la estampaDeTiempo en la actual.
	private int milliseconds;
	
	/*COMIENZA DECLARACIÓN DE CONSTRUCTORES*/
	public Millis ( ) { 
		// se invoca a super para inicializar todo a la fecha y hora actual.
		super();
		// se muestran los milisegundos siempre en números de 3 dígitos
		setMilliseconds( (int) (System.currentTimeMillis() % 1000 ) ); 
		setTimestamp(CURRENT_TIMESTAMP);
	}

	public Millis ( long insertTimeStamp ) {
		setFormat(3);
		final DateTimeFormatter formatter = 
			    DateTimeFormatter.ofPattern("HH:mm:ss.SSS dd/MM/yy ");

			final long unixTime = 1_307_169_163;
			 formattedDtm = Instant.ofEpochSecond(unixTime)
			        .atZone(ZoneId.of("GMT-5"))
			        .format(formatter);

			 
		

	}
	
	public Millis ( int day, int month, int year ) {
		super( 0/*hh*/,  0/*mi*/,  0/*ss*/, day,  month, year,  0/*format*/ ); 
		setMilliseconds(0);

	}
		
		
	public Millis ( int hours, int minutes, int seconds, int day, int month, int year ) {
		super( hours,  minutes, seconds, day, month, year,  0/*format*/ ); 
		setMilliseconds(0);
	}

	public Millis ( int hours, int minutes, int seconds, int millis, int day, int month, int year ) {
		super( hours,  minutes,  seconds, day,  month, year,  0/*format*/ ); 
		setMilliseconds(millis);

	
	/* Gets dd/hh/yyyy hh:mm:ss.SSS en timeStampformat	*/	
		final String strDate = (  getMonth()       + "/" 
								+ getDay()         + "/" 
								+ getYear()        + " "
								+ getHours()       + ":"
								+ getMinutes()     + ":"
								+ getSeconds()     + "." 
								+ getMilliseconds()  );
		try {
			Long toTimeStamp = new SimpleDateFormat("MM/dd/yy hh:mm:ss.SSS").parse(strDate).getTime();
			timestamp = toTimeStamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/* SE TERMINA LA DECLARACIÓN DE CONSTRUCTORES el constructor con guines bajos nada más falta*/
	

	/*Comienza la declaración de SETTERS & GETTERS*/
	public void setMilliseconds(int milliseconds) {
		if ( milliseconds >= MIN_MILLISECONDS && milliseconds <= MAX_MILLISECONDS)
			this.milliseconds = milliseconds;
	}

	public int getMilliseconds() { return milliseconds; }

	public void setTimestamp(long setTimeStamp) { timestamp = setTimeStamp; }

	public long getTimestamp() { return Millis.timestamp;}
	/*Termina la declaración de SETTERS & GETTERS*/
	

	public static long timestampOf(Date d) {return timestamp;}

	public static long timestampOf (DateTime dt) {return timestamp;}

	public boolean isAfter( Millis m) {

		if ( m.getYear() < super.getYear()) {
			return true;
		}else if (  m.getMonth() < super.getMonth() ) 
			return true;
		else if (  m.getDay() < super.getDay() ) 
			return true;
		else if ( m.getHours() < super.getHours())
			return true;
		else if ( m.getMinutes() < super.getMinutes())
			return true;
		else if ( m.getSeconds() < super.getSeconds())
			return true;
		else if ( m.getMilliseconds() < this.milliseconds)
			return true;
		return false;
	}

	public boolean isBefore( Millis m) {

		if ( m.getYear() > this.getYear()) { 
			System.out.println("El año es: " + this.getYear());
			return true;}
		if (  m.getMonth() > this.getMonth() ) { 
			System.out.println("El mes es: " +this.getMonth());
			return true;}
		if (  m.getDay() > this.getDay() ) { 
			System.out.println("El día es: " +this.getDay());
			return true;}
		if ( m.getHours() > this.getHours()) {
			System.out.println("La hora es: " +this.getHours());
			return true;}
		if ( m.getMinutes() > this.getMinutes()) {
			System.out.println("Los minutos son: " +this.getMinutes());
			return true;}
		if ( m.getSeconds() > this.getSeconds()) {
			System.out.println("Los segundos son: " +this.getSeconds());
			return true;}
		if ( m.getMilliseconds() > this.milliseconds) {
			System.out.println("Los miliseg son: " +this.milliseconds);
			return true;}
		return false;
	}

	public void add( String field, int numToAdd) {

		if ( field.equals( Millis.DAY ) ) setDay(getDay() + numToAdd);
		if ( field.equals( Millis.HOUR) ) setHours(getHours() + numToAdd);
		if ( field.equals( Millis.MINUTE ) ) setMinutes(getMinutes() + numToAdd);
		if ( field.equals( Millis.SECOND ) ) setSeconds(getSeconds() + numToAdd);
		if ( field.equals( Millis.MILLISECOND ) ) setMilliseconds(getMilliseconds() + numToAdd);
	}

	@Override // ¿CÓMO ASEGURARME QUE SE CASCADEA?
	// ES DECIR SI SE AUMENTA UNA MILÉSIMA-> SEGUNDO-> MINUTO -> HORA -> DÍA -> MES -> AÑO
	public void next() {
		setMilliseconds( getMilliseconds() + 1); 


		if( getMilliseconds() > 999 ){
			if( getSeconds() == 59 ){
				if( getMinutes() == 59 ){
					if( getHours() == 23 ){
						setMilliseconds(0);
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
	} // fin de método next

	@Override
	public String toString() {

		String s_timestamp = String.valueOf(timestamp); // devolverá el timestamp en formato String

		String pattern="000";
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String s_milliseconds = myFormatter.format(milliseconds);

		String pattern2="00";
		DecimalFormat myFormatter2 = new DecimalFormat(pattern2);
		String s_hours = myFormatter2.format(getHours()); 	  // cambiando el patron para que imprima en dos dígitos siempre "00"
		String s_minutes = myFormatter2.format(getMinutes()); // cambiando el patron para que imprima en dos dígitos siempre "00"
		String s_seconds = myFormatter2.format(getSeconds()); // cambiando el patron para que imprima en dos dígitos siempre "00"


		switch(getFormat()) {

		case 1: return super.toString() + " " + "(" + s_timestamp +")"; 

		case 2: return super.toString() + " " + "(" + s_timestamp +")"; 

		case 3: System.out.println("[ "+formattedDtm+"]");break; 
		
		default :return "[" 
		+ s_hours + ":"
		+ s_minutes + ":" 
		+ s_seconds + "."
		+ s_milliseconds + "]" + " "
		+ getDay() +"/"
		+ getMonth() + "/"
		+ getYear();
		} // fin del switch
		return "";	
	} // fin del método toString
}

