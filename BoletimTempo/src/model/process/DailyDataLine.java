package model.process;

/**
 * 
 * @author Patrick M Lima
 *
 */
public class DailyDataLine {
	private int day;
	private int month;
	private int year;
	private double highTemperature;
	private int highTempHour;
	private int highTempMinute;
	private double lowTemperature;
	private double windVelocity;
	private double totalRain;
	private double heatIndex;
	
	
	public DailyDataLine(String line) throws ArrayIndexOutOfBoundsException
	{
		String data[] = line.split(",");
		highTemperature = Double.parseDouble(data[2]);
		lowTemperature = Double.parseDouble(data[4]);
		windVelocity = Double.parseDouble(data[10]);
		totalRain = Double.parseDouble(data[12]);
		setDataTimeStamp(processTimeStamp(data[3]));
	}
	
	private void setDataTimeStamp(int tStamp[]) {
		day = tStamp[0];
		month = tStamp[1];
		year = tStamp[2];
		
		highTempHour = tStamp[3];
		highTempMinute = tStamp[4];
	}
	
	private int[] processTimeStamp(String timeStamp) {
		String temp = timeStamp.replace("\"", "");
		temp = temp.replace("-", " ");
		temp = temp.replace(":", " ");
		String vData[] = temp.split(" ");
		int r[] = new int[5];
		r[0] = Integer.parseInt(vData[2]);
		r[1] = Integer.parseInt(vData[1]);
		r[2] = Integer.parseInt(vData[0]);
		r[3] = Integer.parseInt(vData[3]);
		r[4] = Integer.parseInt(vData[4]);
		
		return r;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getHighTemperature() {
		return highTemperature;
	}

	public void setHighTemperature(double highTemperature) {
		this.highTemperature = highTemperature;
	}

	public int getHighTempHour() {
		return highTempHour;
	}

	public void setHighTempHour(int highTempHour) {
		this.highTempHour = highTempHour;
	}

	public int getHighTempMinute() {
		return highTempMinute;
	}

	public void setHighTempMinute(int highTempMinute) {
		this.highTempMinute = highTempMinute;
	}

	public double getLowTemperature() {
		return lowTemperature;
	}

	public void setLowTemperature(double lowTemperature) {
		this.lowTemperature = lowTemperature;
	}

	public double getWindVelocity() {
		return windVelocity;
	}

	public void setWindVelocity(double windVelocity) {
		this.windVelocity = windVelocity;
	}

	public double getTotalRain() {
		return totalRain;
	}

	public void setTotalRain(double totalRain) {
		this.totalRain = totalRain;
	}
	
	public double getHeatIndex() {
		return heatIndex;
	}
	
	public void setHeatIndex(double heatIndex) {
		this.heatIndex = heatIndex;
	}
	
	public String getDate() {
		return Integer.toString(this.day) + "/" + Integer.toString(this.month)
				+ "/" + Integer.toString(this.year);
	}

	public boolean equals(int day, int month, int year) {
		return (this.day == day && this.month == month && this.year == year);
	}
}
