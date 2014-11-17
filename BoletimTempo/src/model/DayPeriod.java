package model;

import java.util.Iterator;
import java.util.List;

/**
 * Equivale a um período do dia e as suas respectivas medições.
 * Construído a partir de um conjunto de DataLine das quais
 * são extraídas e processadas as métricas de interesse.
 * 
 * @author Elloa
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



	/**
	 * Cria um período do dia e processa as metricas relativas a este
	 * período
	 * 
	 * @param data
	 */
	public DayPeriod(List<DataLine> data){

		size = data.size();
		if (!data.isEmpty()){

			highTemp = data.get(0).getTemperature();
			lowTemp = data.get(0).getTemperature();
			highHumid = data.get(0).getHumidity();
			lowHumid = data.get(0).getHumidity();
			maxSpeed = data.get(0).getSpeed();
			maxDirect = data.get(0).getDirection();

			Iterator<DataLine> it = data.iterator();

			while (it.hasNext()){

				DataLine d = it.next();

				//update Max Temperature
				if (d.getTemperature() > highTemp) {
					highTemp = d.getTemperature();
				}

				if (d.getTemperature() < lowTemp) {
					lowTemp = d.getTemperature();
				}


				if (d.getHumidity() > highHumid){
					highHumid = d.getHumidity();
				}

				if (d.getHumidity() < lowHumid){
					lowHumid = d.getHumidity();
				}

				if (d.getSpeed() > maxSpeed){
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
	 * Retorna a quantidade de medições passadas como
	 * parâmetro pra este turno do dia
	 * 
	 * @return
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





}
