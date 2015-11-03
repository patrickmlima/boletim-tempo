package model.util;

/**
 * Class which contains useful informations for the system works
 * 
 * @author Elloá B. Guedes
 *
 */
public class Util {

	public static String pathBaixa1;
	public static String pathBaixa2;
	/**
	 * Method to ranks a period
	 * 
	 * @param counter
	 *            a integer which classifies the period
	 * @return the period's name
	 */
	public static String ranksPeriod(int counter) {
		if (counter == 0) {
			return "madrugada";
		} else if (counter == 1) {
			return "manha";
		} else if (counter == 2) {
			return "tarde";
		} else {
			return "noite";
		}
	}
}