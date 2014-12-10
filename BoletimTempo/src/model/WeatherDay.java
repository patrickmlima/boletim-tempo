package model;

import java.util.ArrayList;
import java.util.List;

/**
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
	 * Informacoes sobre a data deste dia meteorologico
	 * 
	 * @param year
	 * @param month
	 * @param day
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
	 * Apos utilizar o construtor deve ser passada uma linha
	 * DataLine com as informações que devem ser acrescentadas a 
	 * este dia. Só depois de inseridas todas as linhas é que deve
	 * haver o processamento.
	 * 
	 * @param d uma DataLine a ser acrescentada na meteorologia deste dia.
	 */
	//função alterada em 19/11/2014
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
	 * Apos todos os dados deste dia terem sido adicionados,
	 * é que deve ser efetuado o processamento das medicoes do dia.
	 */
	public void processMeasurements(){
		dawn = new DayPeriod(listDawn);
		morning = new DayPeriod(listMorning);
		afternoon = new DayPeriod(listAfternoon);
		night = new DayPeriod(listNight);

	}

	/**
	 * Checa se duas datas são iguais.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return se ano, mes e dia são iguais ao deste dia.
	 */
	public boolean equals(int year, int month, int day){
		return (this.year == year)&&(this.month == month)&&(this.day == day);
	}

	/**
	 * Imprime o dia atual no formato ano, mês e dia
	 */
	public String toString(){
		return this.year + "-" + this.month + "-" + this.day;
	}

	/**
	 * Fornece os dados do periodo classificado como sendo madrugada
	 * 
	 * @return dados da madrugada
	 */
	public DayPeriod getDawn() {
		return dawn;
	}

	/**
	 * Fornece os dados do periodo classificado como sendo manha
	 * 
	 * @return dados da manha
	 */
	public DayPeriod getMorning() {
		return morning;
	}

	/**
	 * Fornece os dados do periodo classificado como sendo tarde
	 * 
	 * @return dados da tarde
	 */
	public DayPeriod getAfternoon() {
		return afternoon;
	}

	/**
	 * Fornece os dados do periodo classificado como sendo noite
	 * 
	 * @return dados da noite
	 */
	public DayPeriod getNight() {
		return night;
	}

	/**
	 * Retorna ano
	 * @return um inteiro contendo o ano deste dia
	 */
	public int getYear() {
		return year;
	}

	/**
	 * retorna mes
	 * 
	 * @return um inteiro contendo o mes deste dia
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * retorna dia
	 * @return um inteiro contendo o dia deste dia meteorologico
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Devolve uma lista contendo os quatro períodos do dia
	 * 
	 * @return lista contendo os quatro periodos do dia
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
	 * Retorna a quantidade de medicoes utilizadas para composicao dos dados deste dia meteorologico.
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	



}
