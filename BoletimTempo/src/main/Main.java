package main;

import java.io.IOException;

import controller.Controller;

public class Main{

	public static void main(String[] args) {
		
		try {
//			Controller.getInstance().computeWeatherDay();
//			Controller.getInstance().computeWeatherDay("06/02/2014", "08/02/2014");
			Controller.getInstance().computeWeatherDay("07/02/2014");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			


	}

}
