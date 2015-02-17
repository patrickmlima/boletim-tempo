package model.util;

import java.io.File;

/**
 * Class which contains useful informations for the system works
 * @author Elloa
 *
 */
public class Util {	
	public static File weatherDataFile;
	
	/**
	 * Method to ranks a period 
	 * @param counter a integer which classifies the period
	 * @return the period's name
	 */
	public static String ranksPeriod(int counter) {
		if (counter == 0) {
			return "madrugada";
		}
		else if (counter == 1){
			return "manha";
		}
		else if (counter == 2) {
			return "tarde";
		}
		else {
			return "noite";
		}
	}
	
	public static String getSpace() {
		if(System.getProperty("os.name").toLowerCase().contains("windows") )
			return " ";
		return "\\ ";
	}
}
