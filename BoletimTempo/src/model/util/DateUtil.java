package model.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class which contains useful methods to manipulate dates
 * @author Patrick M Lima
 *
 */
public class DateUtil {
	public static boolean isValidDate(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(date);
			return true;
		} catch(ParseException e) {
			return false;
		}
	}
	
	public static boolean isChronological(String d1, String d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		Date iDate = null;
		Date fDate = null;
		
		try {
			iDate = sdf.parse(d1);
			fDate = sdf.parse(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return iDate.before(fDate);
	}
	
	/**
	 * Adjusts a date to an array format (with day, month and year sequence)
	 * @param day the date to convert (needs to be separated by '/')
	 * @return an integer array which represents the date
	 */
	public static int[] adjustDateToInt(String day) {
		String[] dayArray = day.split("/");
		int[] d = new int[dayArray.length]; 
		for(int i = 0; i < dayArray.length; i++) {
			d[i] = Integer.parseInt(dayArray[i]);
		}
		return d;
	}
	//adicionadas em 01/02/2015 (movidas de WeatherDayInterface)
	/**
	 * Adjusts a date to the form used in DataLine class (which converts
	 * the integer date in a String).
	 * @param day the data to adjust
	 * @return a String with the date adjusted
	 */
	public static String adjustDate(String day) {
		int[] d = adjustDateToInt(day);
		return "" + d[0] + "/" + d[1] + "/" + d[2];
	}
}
