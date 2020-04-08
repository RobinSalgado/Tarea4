package com.iteso.calendar;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Millis extends DateTime{

	public static final String DAY = "day";
	public static final String HOUR = "hour";
	public static final String MINUTE = "minute";
	public static final String SECOND = "second";
	public static final String MILLISECOND = "millisecond";
	public  String formattedDtm = null;

	final int MIN_MILLISECONDS = 0;
	final int MAX_MILLISECONDS = 1000;
	// the number of milliseconds since the epoch of 1970-01-01T00:00:00Z;
    long currentTimeStamp = Instant.now().toEpochMilli(); 
	private static long timestamp; // Inicializamos la estampaDeTiempo en la actual.
	private int milliseconds;
	
	/*COMIENZA DECLARACIÓN DE CONSTRUCTORES*/
	public Millis ( ) { 
//		// se invoca a super para inicializar todo a la fecha y hora actual.
//		super();
//		setFormat(4);
//		// se muestran los milisegundos siempre en números de 3 dígitos
//		setMilliseconds( (int) (System.currentTimeMillis() % 1000 ) ); 
//		setTimestamp(currentTimeStamp);
		
	}

	public Millis ( long insertTimeStamp ) {
		setFormat(3);
		final DateTimeFormatter formatter = 
			    DateTimeFormatter.ofPattern("HH:mm:ss.SSS dd/MM/yy ");

			final long unixTime = insertTimeStamp;
			 formattedDtm = Instant.ofEpochSecond(unixTime)
			        .atZone(ZoneId.of("GMT-5"))
			        .format(formatter);
	
			 

	}
	
	public Millis ( int day, int month, int year ) {
		super( 0/*hh*/,  0/*mi*/,  0/*ss*/, day,  month, year,  0/*format*/ );
		setFormat(4);
		setMilliseconds(0);
		

	}
		
		
	public Millis ( int hours, int minutes, int seconds, int day, int month, int year ) {
		super( hours,  minutes, seconds, day, month, year,  0/*format*/ ); 
		setMilliseconds(0);
		setFormat(4);
	}

	public Millis ( int hours, int minutes, int seconds, int millis, int day, int month, int year ) {
		super( hours,  minutes,  seconds, day,  month, year,  0/*format*/ ); 
		setFormat(4);
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
		
			this.milliseconds = milliseconds;
			
	}

	public int getMilliseconds() {return this.milliseconds;}

	public void setTimestamp(long setTimeStamp) { 
		setFormat(3);
		final DateTimeFormatter formatter = 
			    DateTimeFormatter.ofPattern("HH:mm:ss.SSS dd/MM/yy ");

			final long unixTime = setTimeStamp;
			 formattedDtm = Instant.ofEpochSecond(unixTime)
			        .atZone(ZoneId.of("GMT-6"))
			        .format(formatter);
	
	}

	public long getTimestamp() { 
		/* Gets dd/hh/yyyy hh:mm:ss.SSS en timeStampformat	*/	
		final String strDate = (  getMonth()        + "/" 
								+ getDay()          + "/" 
								+ getYear()         + " "
								+ getHours()        + ":"
								+ getMinutes()      + ":"
								+ getSeconds()      + "."  
								+ getMilliseconds() );
		try {
			Long toTimeStamp = new SimpleDateFormat("MM/dd/yy hh:mm:ss.SSS").parse(strDate).getTime();
			timestamp = toTimeStamp;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}
	/*Termina la declaración de SETTERS & GETTERS*/
	
	private String formatDtm (String formattedDtm) {
		String[] format = formattedDtm.split(" "); // 23:10:45, 19/07/2010
		//23:10:45
		//19/07/2010
		return "["+format[0]+"] "+ format[1];
	}
	public static long timestampOf(Date d) {
		/* Gets dd/hh/yyyy hh:mm:ss.SSS en timeStampformat	*/	
		final String strDate = (  d.getMonth()       + "/" 
								+ d.getDay()         + "/" 
								+ d.getYear()        + " ");
								
		try {
			Long toTimeStamp = new SimpleDateFormat("MM/dd/yy").parse(strDate).getTime();
			timestamp = toTimeStamp;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return timestamp;
	}

	public static long timestampOf (DateTime dt) {

		/* Gets dd/hh/yyyy hh:mm:ss.SSS en timeStampformat	*/	
			final String strDate = (  dt.getMonth()       + "/" 
									+ dt.getDay()         + "/" 
									+ dt.getYear()        + " "
									+ dt.getHours()       + ":"
									+ dt.getMinutes()     + ":"
									+ dt.getSeconds()     + "."  );
			try {
				Long toTimeStamp = new SimpleDateFormat("MM/dd/yy hh:mm:ss").parse(strDate).getTime();
				timestamp = toTimeStamp;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return timestamp;
	}

	public boolean isAfter( Millis m) {

		if ( m.getYear()     < super.getYear()) {
			System.out.println("El año de la fecha anteriorior: " + m.getYear());
			System.out.println("El año de la fecha despues: " + getYear());
			return true;
		} if (  m.getMonth() < super.getMonth() ) {
			System.out.println("Los meses de la fecha anterior: " + m.getMonth());
			System.out.println("Los meses de la fecha despues: " + getYear());
			return true;}
		 if (  m.getDay()    < super.getDay() ) {
			 System.out.println("El día de la fecha anteriori: " +m.getDay());
			 System.out.println("El día de la fecha despues: " + getYear());
			return true;}
		 if ( m.getHours()   < super.getHours()) {
			 System.out.println("La hora de la fecha anteriori: " +m.getHours());
			 System.out.println("La hora de la fecha despues: " +getYear());
			return true;}
		 if ( m.getMinutes() < super.getMinutes()) {
			 System.out.println("Los minutos de la fecha anteriorior: " +m.getMinutes());
			 System.out.println("Los minutos de la fecha despues: " + getYear());
			return true;}
		 if ( m.getSeconds() < super.getSeconds()) {
			 System.err.println("Los segundos de la fecha anterioria: " +m.getSeconds());
			 System.err.println("Los segundos de la fecha despues: " + getYear());
			return true;}
		 if ( m.getMilliseconds() < this.milliseconds) {
			 System.err.println("Los millis de la fecha anteriorior: " +this.milliseconds);
			 System.err.println("Los millis de la fecha despues: " + m.milliseconds);
			return true;}
		return false;
	}

	public boolean isBefore( Millis m) {

		if ( m.getYear() > this.getYear()) { 
			
			return true;}
		if (  m.getMonth() > this.getMonth() ) { 
			
			return true;}
		if (  m.getDay() > this.getDay() ) { 
		
			return true;}
		if ( m.getHours() > this.getHours()) {
			
			return true;}
		if ( m.getMinutes() > this.getMinutes()) {
			
			return true;}
		if ( m.getSeconds() > this.getSeconds()) {
			
			return true;}
		if ( m.getMilliseconds() > this.milliseconds) {
			
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

		if( getMilliseconds() > 999 ) {
			if(getSeconds() == 59 && getMinutes() == 59 && getHours() == 23){
				setMilliseconds(0);
				setSeconds(0);
				setMinutes(0);
				setHours(0);
				if ( getDay() == 31 && getMonth() == 12) {
					setDay(1);
					setMonth(1);
					setYear( getYear() + 1);
				}
			} else if(getSeconds() == 59 && getMinutes() == 59 && getHours() < 23) {
				setMilliseconds(0);
				setSeconds(0);
				setMinutes(0);
				setHours(getHours() + 1);
			} else if(getSeconds() == 59 && getMinutes() < 59) {
				setMilliseconds(0);
				setSeconds(0);
				setMinutes(getMinutes() + 1);
			} else if(getSeconds() < 59) {
				setMilliseconds(0);
				setSeconds(getSeconds() + 1);
			}
		}// fin del 999
	} // fin de método next

	@Override
	public String toString() {

		String s_timestamp = String.valueOf(currentTimeStamp); // devolverá el timestamp en formato String

		String pattern="000";
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String s_milliseconds = myFormatter.format(getMilliseconds());

		String pattern2="00";
		DecimalFormat myFormatter2 = new DecimalFormat(pattern2);
		String s_hours = myFormatter2.format(getHours()); 	  // cambiando el patron para que imprima en dos dígitos siempre "00"
		String s_minutes = myFormatter2.format(getMinutes()); // cambiando el patron para que imprima en dos dígitos siempre "00"
		String s_seconds = myFormatter2.format(getSeconds()); // cambiando el patron para que imprima en dos dígitos siempre "00"
		String s_day = myFormatter2.format(getDay()); 	  // cambiando el patron para que imprima en dos dígitos siempre "00"
		String s_month = myFormatter2.format(getMonth()); // cambiando el patron para que imprima en dos dígitos siempre "00"
		


		switch(getFormat()) {
		
		case 0: return super.toString();
		
		case 1: return super.toString() + " " + "(" + s_timestamp +")"; 

		case 2: return super.toString() + " " + "(" + s_timestamp +")";

		case 3: System.out.print(formatDtm(formattedDtm));break; 

		case 4 :  return "[" 
		+ s_hours + ":"
		+ s_minutes + ":" 
		+ s_seconds + "."
		+ s_milliseconds + "]" + " "
		+ s_day +"/"
		+ s_month + "/"
		+ getYear();
		
		case 5 : return formattedDtm;
		} // fin del switch
		
		
		return "";	
	} // fin del método toString
	
	
}

