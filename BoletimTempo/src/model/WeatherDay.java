package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a meteorological day, which is composed from 4 periods and
 * your respective measurements. The data from this day are initially stored
 * and then are processed 
 * 
 * Representa um dia meteorológico, o qual é composto de 4 períodos e
 * suas respectivas medições. Os dados referentes a este dia são inicialmente armazenados
 * e só depois processados
 * 	
 * @author Elloa
 *
 */
public class WeatherDay {

	private int year;
	private int month;
	private int day;
	private double heatIndex = 0.0;

	private DayPeriod dawn;
	private DayPeriod morning;
	private DayPeriod afternoon;
	private DayPeriod night;

	private List<DataLine> listDawn;
	private List<DataLine> listMorning;
	private List<DataLine> listAfternoon;
	private List<DataLine> listNight;
	
	private int size = 0;

	/**
	 * Informations about the date of this weather day
	 * 
	 * @param year the weather day year
	 * @param month the weather day month
	 * @param day the day of the weather day 
	 */
	public WeatherDay(int year, int month, int day){
		this.year = year;
		this.day = day;
		this.month = month;
		listDawn = new ArrayList<DataLine>();
		listMorning = new ArrayList<DataLine>();
		listAfternoon = new ArrayList<DataLine>();
		listNight = new ArrayList<DataLine>();
	}

	/**
	 * After use the constructor a DataLine with the informations
	 * which should be added to this day must be passed.
	 * Only after all the lines has been inserted must have processing. 
	 * 
	 * @param d a DataLine to be added on the weather of this day.
	 */
	public void addMeasurement(DataLine d){
		if ((d.getHour()==0 && d.getMinute()>=10) || (d.getHour()>0 && d.getHour()<6) || (d.getHour()==6 && d.getMinute()==0)){
			listDawn.add(d);
		} else if ((d.getHour()==6 && d.getMinute()>=10) || (d.getHour()>6 && d.getHour()<12) || (d.getHour()==12 && d.getMinute()==0)){
			listMorning.add(d);
		} else if ((d.getHour()==12 && d.getMinute()>=10) || (d.getHour()>12 && d.getHour()<18) || (d.getHour()==18 && d.getMinute()==0)){
			listAfternoon.add(d);
		} else {
			listNight.add(d);
		}
		
		size++;
	}


	/**
	 * After all the data of the day have been added, then 
	 * must have processing of the measurements of the day.
	 */
	public void processMeasurements(){
		dawn = new DayPeriod(listDawn);
		morning = new DayPeriod(listMorning);
		afternoon = new DayPeriod(listAfternoon);
		night = new DayPeriod(listNight);

		double highTemp=0.0, humidityHTemp=0.0;
		for(DayPeriod d : getDayPeriods())
		{
			if(d.getHighTemp()>highTemp)
			{
				highTemp = d.getHighTemp();
				humidityHTemp = d.getHumidityHighTemp();
			}
		}
		this.heatIndex = (new HeatIndex(highTemp, humidityHTemp)).getIndex();

	}

	/**
	 * Checks if two dates are equals
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return if the year, month and day are equals to this day.
	 */
	public boolean equals(int year, int month, int day){
		return (this.day == day && this.month == month && this.year == year);
	}
	
	/**
	 * Checks if a date belongs to the same WeatherDay
	 * @param year 
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @return if the date and time (hour and minute) belongs to this WeatherDay
	 */
	public boolean isSameWeatherDay(int year, int month, int day, int hour, int minute) {
		return ( this.equals(year, month, day) || (this.equals(year, month, day-1) && hour == 0 && minute == 0) ); 
	}

	/**
	 * Prints the current day in the format year, month and day
	 */
	public String toString(){
		return this.year + "-" + this.month + "-" + this.day;
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
	public List<DayPeriod> getDayPeriods(){
		List<DayPeriod> d = new ArrayList<DayPeriod>();
		d.add(dawn);
		d.add(morning);
		d.add(afternoon);
		d.add(night);
		return d;

	}

	/**
	 * Returns the number of measurements used to compose the data of this meteorological day.
	 * 
	 * @return an integer containing the number of measurements of the day
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Returns the heat index of this day
	 * @return a double containing the heat index of this meteorological day.
	 */
	public double getHeatIndex() {
		return heatIndex;
	}
}
