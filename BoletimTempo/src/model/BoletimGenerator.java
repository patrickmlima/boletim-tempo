package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;	
import java.io.FileNotFoundException;
import model.InterfaceBoletimGenerator;
import model.FileManager;
import listener.TurnListener;

public class BoletimGenerator 
{
	private String currentDay;
	private double averagePressure;
	private int countPressure;
	private double temperatureMin;
	private double temperatureMax;
	private double humidityMin;
	private double humidityMax;
	private double maxWindVelocity;
	private double directionMaxWind;
	private double totalRain;
	
	private String filePath;
	private String fileOutName;	
	private File fileOut;
	private BufferedReader freader;
	private BufferedWriter fwriter;
	
	private ArrayList <TurnListener>localListeners = new ArrayList<TurnListener>();

	private String SEPARATOR = File.separator;
	
	private Scanner sc = new Scanner(System.in);
	public BoletimGenerator()
	{		
		currentDay = "";
		ResetValues();
		filePath = "."+SEPARATOR+"src"+SEPARATOR+"data"+SEPARATOR;
	}
	
	protected synchronized void ResetValues()
	{
		averagePressure = 0.0;
		countPressure = 0;
		temperatureMin = -0.0;
		temperatureMax = 0.0;
		humidityMin = -0.0;
		humidityMax = 0.0;
		maxWindVelocity = 0.0;
		directionMaxWind = 0.0;
		totalRain = 0.0;
	}
	
	protected synchronized void OpenFileOut(String fname)
	{
		fileOutName = filePath+fname;
		fileOut = new File(fileOutName);
		try
		{
			fwriter = new BufferedWriter(new FileWriter(fileOut));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void PrintLines()
	{
		try{
			freader = new FileManager().getFile();
			String r;
			while((r = freader.readLine()) != null)
			{
				SeparateStrings(r);
			}
			freader.close();
			fwriter.close();
			fileOut = null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*Obtem data e hora dos dados a serem processados,
	 * a partir de uma linha do arquivo Baixa1
	 */
	public void SeparateStrings(String line)
	{
		ArrayList <String> str = new ArrayList<String>(Arrays.asList(line.split(",")));
		//Obtem data e hora
		String time_and_date = str.get(0);
		//procura um espaço em branco (que separa data e hora)
		int index1 = time_and_date.indexOf(" ");
		if(index1 != -1)
		{
			//separa a data e a transforma numa lista 
			ArrayList <String> date = new ArrayList<String>(Arrays.asList((time_and_date.substring(1,index1)).split("-")));

			//Atribui os valores de dia, mês e ano, presentes na lista
			String day = date.get(2);
			String month = date.get(1);
			String year = date.get(0);
			
			//Faz uma lista com as informações do tempo
			ArrayList <String> time = new ArrayList<String>(Arrays.asList(time_and_date.substring(index1+1, time_and_date.length()).split(":")));
			//Atribui os valores de hora e minuto presentes na lista
			String hour = time.get(0);
			String minutes = time.get(1);

			try
			{
				ProcessInformations(str);
				if(fwriter == null)
				{
					OpenFileOut(day+"."+month+"."+year+".txt");
				}
				if(hour.equals("06") && minutes.equals("00"))
				{
					//fim turno madrugada
					SaveResults("Turno Madrugada:");
				}
				else if( hour.equals("12") && minutes.equals("00"))
				{
					//fim turno manhã
					SaveResults("Turno Manhã:");
				}
				else if(hour.equals("18") && minutes.equals("00"))
				{
					//fim turno tarde
					SaveResults("Turno Tarde:");
				}
				else if(hour.equals("00") && minutes.equals("00"))
				{
					//fim turno noite
					SaveResults("Turno Noite:");
				}
				
				if(!this.currentDay.equals(day))
				{
					if(fileOut != null && fileOut.exists())
					{
						fwriter.close();
					}					
					OpenFileOut(day+"."+month+"."+year+".txt");
					this.currentDay = day;
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void ProcessInformations(ArrayList<String> line)
	{
		String rain = line.get(line.size()-1);
		String wind_direction = line.get(line.size()-2);
		String wind_velocity = line.get(line.size()-3);
		String humidity = line.get(line.size()-4);
		String temperature = line.get(line.size()-5);
		String pressure = line.get(line.size()-6);
		
		totalRain += Double.parseDouble(rain);
		averagePressure += Double.parseDouble(pressure);
		countPressure++;
		
		if(temperatureMin == -0.0)
			temperatureMin = Double.parseDouble(temperature);
		else if(temperatureMin > Double.parseDouble(temperature))
			temperatureMin = Double.parseDouble(temperature);
		if(temperatureMax < Double.parseDouble(temperature))
			temperatureMax = Double.parseDouble(temperature);
		
		if(humidityMin == -0.0)
			humidityMin = Double.parseDouble(humidity);
		else if(humidityMin > Double.parseDouble(humidity))
			humidityMin = Double.parseDouble(humidity);
		if(humidityMax < Double.parseDouble(humidity))
			humidityMax = Double.parseDouble(humidity);
		
		if(maxWindVelocity < Double.parseDouble(wind_velocity))
		{
			maxWindVelocity = Double.parseDouble(wind_velocity);
			directionMaxWind = Double.parseDouble(wind_direction);
		}
	}	
	
	private void SaveResults(String turn_name)
	{
		try
		{
			fwriter.append(turn_name);
			fwriter.newLine();
			
			fwriter.append("Pressão média: "+averagePressure/countPressure+" hPa");
			fwriter.newLine();
			fwriter.append("Temp. min: "+temperatureMin+" °C");
			fwriter.newLine();
			fwriter.append("Temp. max: "+temperatureMax+" °C");
			fwriter.newLine();
			fwriter.append("Humidade min: "+humidityMin+"%");
			fwriter.newLine();
			fwriter.append("Humidade max: "+humidityMax+"%");
			fwriter.newLine();
			fwriter.append("Máxima velocidade do vento: "+maxWindVelocity+" m/s"+" -- direção: "+directionMaxWind+"°");
			fwriter.newLine();
			fwriter.append("Total de precipitação: "+totalRain+" mm");
			fwriter.newLine();
			fwriter.newLine();
			
			fwriter.flush();
			ResetValues();
			shootEndTurn(turn_name);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	public void printResult()
	{
		System.out.println("Pressão média: "+averagePressure/countPressure);
		System.out.println("Temp. min: "+temperatureMin);
		System.out.println("Temp. max: "+temperatureMax);
		System.out.println("Humidade min: "+humidityMin);
		System.out.println("Humidade max: "+humidityMax);
		System.out.println("Máxima velocidade do vento: "+maxWindVelocity+" -- direção: "+directionMaxWind);
		System.out.println("Total de precipitação: "+totalRain);
	}

	public double getAveragePressure()
	{ return averagePressure;}
	public double gettemperatureMin()
	{ return temperatureMin;}
	public double gettemperatureMax()
	{ return temperatureMax;}
	public double gethumidityMin()
	{ return humidityMin;}
	public double gethumidityMax()
	{ return humidityMax;}
	public double getmaxWindVelocity()
	{ return maxWindVelocity;}
	public double getdirectionMaxWind()
	{ return directionMaxWind;}
	public double gettotalRain()
	{ return totalRain;}
	public String getFileOutName()
	{ return fileOutName; }
	
	public void addModelListener(TurnListener listener)
	{
		if(this.localListeners.contains(listener))
			return;
		this.localListeners.add(listener);
	}
	
	public void shootEndTurn(String turn_name)
	{
		for(TurnListener ltr : this.localListeners)
		{
			ltr.TurnFinished(new InterfaceBoletimGenerator(this,turn_name));
		}
	}
	
	public String getLastLine(File file) 
	{
	    RandomAccessFile fileHandler = null;
	    try 
	    {
	        fileHandler = new RandomAccessFile( file, "r" );
	        long fileLength = fileHandler.length() - 1;
	        StringBuilder sb = new StringBuilder();

	        for(long filePointer = fileLength; filePointer != -1; filePointer--)
	        {
	            fileHandler.seek( filePointer );
	            int readByte = fileHandler.readByte();

	            if(readByte == 0xA)
	            {
	                if( filePointer == fileLength )
	                    continue;
	                break;
	            }else if(readByte == 0xD)
	            {
	                if( filePointer == fileLength - 1 )
	                    continue;
	                break;
	            }

	            sb.append( ( char ) readByte );
	        }

	        String lastLine = sb.reverse().toString();
	        return lastLine;
	    } catch( java.io.FileNotFoundException e ) {
	        e.printStackTrace();
	        return null;
	    } catch( java.io.IOException e ) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        if (fileHandler != null )
	            try {
	                fileHandler.close();
	            } catch (IOException e) {
	                /* ignore */
	            }
	    }
	}


}

