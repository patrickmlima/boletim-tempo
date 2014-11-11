package model;
import java.util.EventObject;

/**
 * model.InterfaceBoletimGenerator
 * Creation data: 08/11/2014
 * @author Patrick M Lima
 * A classe atua como InterfaceBoletimGenerator entre a classe DataProcessament
 * e qualquer entidade que queira utilizar os dados.
 */
public class InterfaceBoletimGenerator extends EventObject
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String turnName;
	public InterfaceBoletimGenerator(BoletimGenerator source)
	{
		super (source);
	}
	
	public InterfaceBoletimGenerator(BoletimGenerator source, String turn_name)
	{
		this (source);
		turnName = turn_name;
	}
	/**
	 * Funções que retornam os dados requeridos de DataProcessament
	 * @return
	 */
	public String getTurnName()
	{ return turnName; }
	public double getAveragePressure()
	{ return ((BoletimGenerator)this.source).getAveragePressure();}
	public double getTemperature_min()
	{ return ((BoletimGenerator)this.source).gettemperatureMin();}
	public double getTemperature_max()
	{ return ((BoletimGenerator)this.source).gettemperatureMax();}
	public double getHumidity_min()
	{ return ((BoletimGenerator)this.source).gethumidityMin();}
	public double getHumidity_max()
	{ return ((BoletimGenerator)this.source).gethumidityMax();}
	public double getMax_wind_velocity()
	{ return ((BoletimGenerator)this.source).getmaxWindVelocity();}
	public double getDirection_max_wind()
	{ return ((BoletimGenerator)this.source).getdirectionMaxWind();}
	public double getTotal_rain()
	{ return ((BoletimGenerator)this.source).gettotalRain();}
	public String getFileOutName()
	{ return ((BoletimGenerator)this.source).getFileOutName(); }
	
}
