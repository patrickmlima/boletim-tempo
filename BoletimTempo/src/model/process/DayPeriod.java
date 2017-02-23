package model.process;

import java.util.Iterator;
import java.util.List;

/**
 * It's equivalent to a day period and their measurements. Made from a set of
 * DataLine in which are extracted and processed the metrics of interest
 * 
 * @author Ello� B. Guedes
 *
 */
public class DayPeriod {
	private int size;
	private double avgPressure = 0.0;
	private double highTemp;
	private double lowTemp;
	private double highHumid;
	private double lowHumid;
	private double maxSpeed;
	private double maxDirect;
	private double acumRain = 0.0;

	private double humidityHighTemp = 0.0;    //UR instante da máxima

	/**
	 * Creates a DayPeriod and processes its metrics
	 * 
	 * @param data
	 *            a List which contains DataLine objects
	 */
	public DayPeriod(List<DataLine> data) {  //DataLine.java
		size = data.size();
		if (!data.isEmpty()) {

			highTemp = data.get(0).getTemperature();
			lowTemp = data.get(0).getTemperature();
			highHumid = data.get(0).getHumidity();
			lowHumid = data.get(0).getHumidity();
			maxSpeed = data.get(0).getSpeed();
			maxDirect = data.get(0).getDirection();

			Iterator<DataLine> it = data.iterator();

			while (it.hasNext()) {

				DataLine d = it.next();
				//busca a MaxTemp e pega a umidade
				// update Max Temperature
				if (d.getTemperature() > highTemp) {
					highTemp = d.getTemperature();
					humidityHighTemp = d.getHumidity();
				} else if (d.getTemperature() < lowTemp) {
					lowTemp = d.getTemperature();
				}

				if (d.getHumidity() > highHumid) {
					highHumid = d.getHumidity();
				} else if (d.getHumidity() < lowHumid) {
					lowHumid = d.getHumidity();
				}

				if (d.getSpeed() > maxSpeed) {
					maxSpeed = d.getSpeed();
					maxDirect = d.getDirection();
				}

				acumRain += d.getRain();
				avgPressure += d.getPressure();

			}
			avgPressure /= size;
		}	
	}

	/**
	 * Returns the number of measurements passed through parameter for this day
	 * period
	 * 
	 * @return the number of measurements from this day period
	 */
	public int getSize() {
		return size;
	}

	public double getAvgPressure() {
		return avgPressure;
	}

	public double getHighTemp() {
		return highTemp;
	}

	public double getLowTemp() {
		return lowTemp;
	}

	public double getHighHumid() {
		return highHumid;
	}

	public double getLowHumid() {
		return lowHumid;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}

	public double getMaxDirect() {
		return maxDirect;
	}

	public double getAcumRain() {
		return acumRain;
	}

	public double getHumidityHighTemp() {
		return humidityHighTemp;
	}
}