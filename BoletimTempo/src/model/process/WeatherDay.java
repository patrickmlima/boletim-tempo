package model.process;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a meteorological day, which is composed from 4 periods and your
 * respective measurements. The data from this day are initially stored and then
 * are processed
 * 
 * @author Ello� B. Guedes
 *
 */
public class WeatherDay {

	private int year;
	private int month;
	private int day;

	private DayPeriod dawn;				//periodos
	private DayPeriod morning;
	private DayPeriod afternoon;
	private DayPeriod night;

	private List<DataLine> listDawn;	 
	private List<DataLine> listMorning;
	private List<DataLine> listAfternoon;
	private List<DataLine> listNight;
	
	private DailyDataLine dailyData;	//diario

	private int size = 0;

	/**
	 * Informations about the date of this weather day
	 * 
	 * @param year
	 *            the weather day year
	 * @param month
	 *            the weather day month
	 * @param day
	 *            the day of the weather day
	 */
	public WeatherDay(int year, int month, int day) {
		this.year = year;
		this.day = day;
		this.month = month;
		listDawn = new ArrayList<DataLine>();
		listMorning = new ArrayList<DataLine>();
		listAfternoon = new ArrayList<DataLine>();
		listNight = new ArrayList<DataLine>();
	}

	/**
	 * After use the constructor a DataLine with the informations which should
	 * be added to this day must be passed. Only after all the lines has been
	 * inserted must have processing.
	 * 
	 * @param d
	 *            a DataLine to be added on the weather of this day.
	 */
	public void addMeasurement(DataLine d){
		if ((d.getHour() == 0 && d.getMinute() >= 10)
				|| (d.getHour() > 0 && d.getHour() < 6)
				|| (d.getHour() == 6 && d.getMinute() == 0)) {
			listDawn.add(d);
			
		} else if ((d.getHour() == 6 && d.getMinute() >= 10)
				|| (d.getHour() > 6 && d.getHour() < 12)
				|| (d.getHour() == 12 && d.getMinute() == 0)) {
			listMorning.add(d);
			
		} else if ((d.getHour() == 12 && d.getMinute() >= 10)
				|| (d.getHour() > 12 && d.getHour() < 18)
				|| (d.getHour() == 18 && d.getMinute() == 0)) {
			listAfternoon.add(d);
			
		} else {
			listNight.add(d);
		}
		
		size++;
	}
	
	public void addDailyData(String line) {
		this.dailyData = new DailyDataLine(line);
	}

	/**
	 * After all the data of the day have been added, then must have processing
	 * of the measurements of the day.
	 */
	public void processMeasurements(){				//informacoes diarias
		dawn = new DayPeriod(listDawn);
		morning = new DayPeriod(listMorning);
		afternoon = new DayPeriod(listAfternoon);
		night = new DayPeriod(listNight);

		double humidityHTemp = 0.0;
		int diffMinute;
//		for (DayPeriod d : getDayPeriods()) {
//			if (d.getHighTemp() > highTemp) {
//				highTemp = d.getHighTemp();
//				humidityHTemp = d.getHumidityHighTemp();
//			}
//		}
		
		for(DataLine d : getDataLines() ) {						//compara os dados do baixa1 com baixa2
			if(this.dailyData.getHighTempHour() == d.getHour()) {
				diffMinute = this.dailyData.getHighTempMinute() - d.getMinute(); 
				if( (diffMinute < 10) && (diffMinute > -1) ) {
					humidityHTemp = d.getHumidity();
					break;
				}
			}
		}
	  /*for ()
	   * 
	  */
		
		dailyData.setHeatIndex( (new HeatIndex(dailyData.getHighTemperature(), humidityHTemp)).getIndexInCelsius() ); 	//HEAT INDEX
	}

	/**
	 * Checks if two dates are equals
	 * 
	 * @param year
	 *            the year of the WeatherDay object
	 * @param month
	 *            the month of the WeatherDay object
	 * @param day
	 *            the day of the WeatherDay object
	 * @return true if the year, month and day are equals to this day, false
	 *         otherwise
	 */
	public boolean equals(int year, int month, int day) {
		return (this.day == day && this.month == month && this.year == year);
	}

	/**
	 * Checks if a dataLine object belongs to the same WeatherDay instance
	 * 
	 * @param year
	 *            year of a DataLine object
	 * @param month
	 *            month of a DataLine object
	 * @param day
	 *            day of a DataLine object
	 * @param hour
	 *            hour of a DataLine object
	 * @param minute
	 *            minute of a DataLine object
	 * @return true if the date and time (hour and minute) belongs to this
	 *         WeatherDay, false otherwise
	 */
	public boolean isSameWeatherDay(int year, int month, int day, int hour,
			int minute) {
		return (this.equals(year, month, day) || (this.equals(year, month,
				day - 1) && hour == 0 && minute == 0));
	}

	/**
	 * Prints the current day in the format year, month and day
	 */
	public String getWeatherDayDate(String separator, int option) {
		if(option == 0)
			return this.year + separator + this.month + separator + this.day;
		else
			return this.day + separator + this.month + separator + this.year;
	}

	/**
	 * Supplies the period's data labeled as dawn
	 * 
	 * @return dawn's data
	 */
	public DayPeriod getDawn() {
		return dawn;
	}

	/**
	 * Supplies the period's data labeled as morning
	 * 
	 * @return morning's data
	 */
	public DayPeriod getMorning() {
		return morning;
	}

	/**
	 * Supplies the period's data labeled as afternoon
	 * 
	 * @return afternoon's data
	 */
	public DayPeriod getAfternoon() {
		return afternoon;
	}

	/**
	 * Supplies the period's data labeled as night
	 * 
	 * @return night's data
	 */
	public DayPeriod getNight() {
		return night;
	}

	/**
	 * Returns year
	 * 
	 * @return an integer that contains the year of this day
	 */
	public int getYear() {
		return year;
	}

	/**
	 * returns month
	 * 
	 * @return an integer that contains the month of this day
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * returns day
	 * 
	 * @return a integer that contains the day of this meteorological day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Returns a list which contains the four day periods
	 * 
	 * @return list containing the four day periods
	 */
	public List<DayPeriod> getDayPeriods() {
		List<DayPeriod> d = new ArrayList<DayPeriod>();
		d.add(dawn);
		d.add(morning);
		d.add(afternoon);
		d.add(night);
		return d;

	}
	
	public List<DataLine> getDataLines() {
		List<DataLine> d = new ArrayList<DataLine>();
		d.addAll(listDawn);
		d.addAll(listMorning);
		d.addAll(listAfternoon);
		d.addAll(listNight);
		return d;
	}

	/**
	 * Returns the number of measurements used to compose the data of this
	 * meteorological day.
	 * 
	 * @return an integer containing the number of measurements of the day
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Returns the heat index of this day
	 * 
	 * @return a double containing the heat index of this meteorological day.
	 */
	public double getHeatIndex() {
		return dailyData.getHeatIndex();
	}
	
	public DailyDataLine getDailyDataLine() {
		return dailyData;
	}
}