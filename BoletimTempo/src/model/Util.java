package model;

import java.io.File;

/**
 * Classe contendo informações utilitárias para o funcionamento do
 * programa
 * 
 * @author Elloa
 *
 */
public class Util {
	public static String SEPARATOR = File.separator;
	public static String OUTPUT_FOLDER = "." + Util.SEPARATOR + "src" + Util.SEPARATOR + "data" + Util.SEPARATOR;
	public static String BAIXA1 = OUTPUT_FOLDER + "CR3000_Estacao_Baixa1.dat";
	public static String IM_SUN = OUTPUT_FOLDER + "img" + Util.SEPARATOR + "img_sun.png";
	public static String IM_RAIN = OUTPUT_FOLDER + "img" + Util.SEPARATOR + "img_rain.png";
	public static String IM_WIND = OUTPUT_FOLDER + "img" + Util.SEPARATOR + "img_wind.png";
	public static String IM_THERMO = OUTPUT_FOLDER + "img" + Util.SEPARATOR + "img_thermometer.png";
}
