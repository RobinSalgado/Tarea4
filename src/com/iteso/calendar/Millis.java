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
	
	public int flag = 0;
	
	final int MIN_MILLISECONDS = 0;
	final int MAX_MILLISECONDS = 1000;
	// the number of milliseconds since the epoch of 1970-01-01T00:00:00Z;
    long currentTimeStamp = Instant.now().toEpochMilli(); 
	private static long timestamp; // Inicializamos la estampaDeTiempo en la actual.
	private int milliseconds;
	private long unixTime;
	/*COMIENZA DECLARACIÓN DE CONSTRUCTORES*/
	public Millis ( ) { 
		// se invoca a super para inicializar todo a la fecha y hora actual.
		super();
		// se muestran los milisegundos siempre en números de 3 dígitos
		setMilliseconds( (int) (System.currentTimeMillis() % 1000 ) ); 

		setTimestamp(currentTimeStamp);
		setFormat(0);
		
	}

	public Millis ( long insertTimeStamp ) {
		
		final DateTimeFormatter formatter = 
			    DateTimeFormatter.ofPattern("HH:mm:ss.SSS dd/MM/yy ");

			unixTime = insertTimeStamp;
			 formattedDtm = Instant.ofEpochSecond(unixTime)
			        .atZone(ZoneId.of("GMT-5"))
			        .format(formatter);	
			 setFormat(3);
			 
			 String[] splitformattedDtm = formattedDtm.split(" "); //09:45:12.000 1/01/2
			 String[] time = splitformattedDtm[0].split(":");
			 String[] ms = time[2].split("\\.");
			 setHours(Integer.valueOf(time[0]));
			 setMinutes(Integer.valueOf(time[1]));
			 setSeconds(Integer.valueOf(ms[0]));
			 setMilliseconds(Integer.valueOf(ms[1]));
			 
			 String[] date = splitformattedDtm[1].split("/");
			 
			 setDay(Integer.valueOf(date[0]));
			 setMonth(Integer.valueOf(date[1]));
			 setYear(Integer.valueOf(date[2]));
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
	
	public void setFormatDtm(int milisegundos) {
		/**
		 * las primeras 2 lineas crean los 90 segundos en formato 000 (090)
		 **/
		DecimalFormat myFormatter = new DecimalFormat("000");
		String s_milliseconds = myFormatter.format(milisegundos);// cambiando el patron para que imprima en dos dígitos siempre "000"
		/**
		 * con este algoritmo, modificamos los ms de formattedDtm.
		 */
		String aux = ""; // se crea un aux para guardar el cambio de los segundos
		
		// se recorre el formattedDtm y se consigue c/u de sus caracteres.
		for(int i = 0; i < this.formattedDtm.length(); i++) {
			char c = this.formattedDtm.charAt(i); // consigue c/u de los caracteres
			aux = aux+c;
			if(c == '.') { // la cualidad es que los ms van despues del .
				// guardo en el string aux los 90 ms de s_milliseconds
				for(int j = 0; j < s_milliseconds.length(); j++) {
					aux = aux + s_milliseconds.charAt(j);//18:00:00.090
				}
				i = i+3;
			}
		}
		this.formattedDtm = aux;
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
	
	public static long timestampOf(String d) {
		String[] date = d.split(" ");
		try {
			Long toTimeStamp = new SimpleDateFormat("MM/dd/yy").parse(date[1].trim()).getTime();
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
		if ( m.getYear()     < this.getYear()) {
			return true;
		} if (  m.getMonth() < super.getMonth() ) {
			return true;}
		 if (  m.getDay()    < super.getDay() ) {
			return true;}
		 if ( m.getHours()   < super.getHours()) {
			return true;}
		 if ( m.getMinutes() < super.getMinutes()) {
			return true;}
		 if ( m.getSeconds() < super.getSeconds()) {
			return true;}
		 if ( m.getMilliseconds() < this.milliseconds) {
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
		String s_milliseconds = myFormatter.format(getMilliseconds());// cambiando el patron para que imprima en dos dígitos siempre "000"

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

		case 3: if(flag == 0) {System.out.print(formatDtm(formattedDtm)); /*flag = 1*/}break;

		case 4 :  return "[" 
		+ s_hours + ":"
		+ s_minutes + ":" 
		+ s_seconds + "."
		+ s_milliseconds + "]" + " "
		+ s_day +"/"
		+ s_month + "/"
		+ getYear();
		} // fin del switch
		
		
		return "";	
	} // fin del método toString
	
	
}

