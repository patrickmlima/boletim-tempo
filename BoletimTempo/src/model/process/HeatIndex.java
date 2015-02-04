package model.process;

public class HeatIndex {
	
	private double humidMaxTemp;
	private double index;
	private final double C1 = -42.379;
	private final double C2 = 2.04901523;
	private final double C3 = 10.14333127;
	private final double C4 = 0.22475541;
	private final double C5 = 6.83783e-3;
	private final double C6 = 5.481717e-2;
	private final double C7 = 1.22874e-3;
	private final double C8 = 8.5282e-4;
	private final double C9 = 1.99e-6;
	private double fTemp;
	
	
	
	public HeatIndex(double temp, double humidMaxTemp){
		//this.temp = temp;
		this.fTemp = 32 + (temp * 9/5);
		this.humidMaxTemp = humidMaxTemp;
		
		obtainIndex(); 
		//System.out.println(temp + " " + humidMaxTemp + " " + 5*(index-32)/9);
	}
	
	private void obtainIndex(){
		
		
		double hm2 = Math.pow(humidMaxTemp,2);
		double fTemp2 = Math.pow(fTemp, 2);
		
		index = C1 + (C2*fTemp) + (C3*humidMaxTemp) -
				(C4*fTemp*humidMaxTemp) - (C5*fTemp2) -
				(C6* hm2)  + (C7 * fTemp2 * humidMaxTemp)
				+ (C8*fTemp*hm2) - (C9*fTemp2*hm2);
		
		if ((humidMaxTemp < 13)&&(fTemp >= 80.06)&&(fTemp <= 111.92)){
			double adjustment1 = ((13 - humidMaxTemp)/4)*Math.sqrt((17 - Math.abs(fTemp - 95.))/17);
			index -= adjustment1;
		} else if ((humidMaxTemp > 85)&&(fTemp >= 80.06)&&(fTemp<=87.08)){
			double adjustment2 = ((humidMaxTemp - 85)/10)*((87-fTemp)/5);
			index -= adjustment2;
		}
		
	}
	
	public double getIndex(){
		return (index -32.0)/1.8;
	}
	
	

}
