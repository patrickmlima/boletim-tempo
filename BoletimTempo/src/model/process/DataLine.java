package model.process;

/**
 * Represents the file line containing only the data of interest from the BAIXA1 file:
 *  Timestamp + 6 last columns
 * 
 * @author Ello� B. Guedes
 *
 */
public class DataLine {

	private String timeStamp;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;
	private double pressure;
	private double temperature;
	private double humidity;	//baixa1
	private double speed;
	private double direction;
	private double rain;

	/**
	 * Made from an extracted file string
	 * 
	 * @param s
	 *            string extracted from BAIXA1 file
	 */
	public DataLine(String s) throws ArrayIndexOutOfBoundsException {
		String[] lista = s.split(",");
		timeStamp = lista[0];
		processDay();
		rain = Double.parseDouble(lista[25]);
		direction = Double.parseDouble(lista[24]);
		speed = Double.parseDouble(lista[23]);
		humidity = Double.parseDouble(lista[22]);	 //RH_Avg	//VERIFICAR
		temperature = Double.parseDouble(lista[21]); //temperatura atual
		pressure = Double.parseDouble(lista[20]);
	}
	
	/**
	 * Takes other informations about the data in a line
	 */
	private void processDay(){
		String temp = timeStamp;
		temp = temp.replace("\"", " ");
		temp = temp.replace("-", " ");
		temp = temp.replace(":", " ");
		String[] temp2 = temp.split(" ");
		year = Integer.parseInt(temp2[0]);
		month = Integer.parseInt(temp2[1]);
		day = Integer.parseInt(temp2[2]);
		hour = Integer.parseInt(temp2[3]);
		minute = Integer.parseInt(temp2[4]);
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public double getPressure() {
		return pressure;
	}

	public double getTemperature() {
		return temperature;
	}

	public double getHumidity() {
		return humidity;
	}

	public double getDirection() {
		return direction;
	}

	public double getRain() {
		return rain;
	}

	public double getSpeed() {
		return speed;
	}

	public String toString() {
		return timeStamp + " " + pressure + " " + temperature + " " + humidity
				+ " " + speed + " " + direction + " " + rain;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public String getDate() {
		return Integer.toString(this.day) + "/" + Integer.toString(this.month)
				+ "/" + Integer.toString(this.year);
	}

	public boolean equals(int day, int month, int year) {
		return (this.day == day && this.month == month && this.year == year);
	}
}