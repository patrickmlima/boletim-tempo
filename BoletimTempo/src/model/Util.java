package model;

import java.io.File;

/**
 * Class which contains useful informations for the system works
 * @author Elloa
 *
 */
public class Util {
	public static String SEPARATOR = File.separator;
	public static String OUTPUT_FOLDER = "." + Util.SEPARATOR + "src" + Util.SEPARATOR + "data" + Util.SEPARATOR;
	public static String BAIXA1 = OUTPUT_FOLDER + "CR3000_Estacao_Baixa1.dat";
	
	public static String PERIOD_FIGURES_FOLDER = OUTPUT_FOLDER + "period_figures" + Util.SEPARATOR;
	public static String SUNNY = OUTPUT_FOLDER + "img" + Util.SEPARATOR + "sunny.png";
	public static String RAINY = OUTPUT_FOLDER + "img" + Util.SEPARATOR + "rainy.png";
	public static String NIGHT = OUTPUT_FOLDER + "img" + Util.SEPARATOR + "night.png";
	public static String RAINY_NIGHT = OUTPUT_FOLDER + "img" + Util.SEPARATOR + "rainy_night.png";
	
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
}
