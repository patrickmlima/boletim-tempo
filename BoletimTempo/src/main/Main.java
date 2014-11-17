package main;

import java.io.IOException;

import listener.TurnListener;
import model.InterfaceBoletimGenerator;
import controller.Controller;

public class Main implements TurnListener{
	
	@Override
	public void TurnFinished(InterfaceBoletimGenerator obj) {
		System.out.println("Turno finalizado: "+obj.getTurnName());
	}

	public static void main(String[] args) {
		
		
		try {
			Controller.getInstance().readFile();
			Controller.getInstance().saveAllDays();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			


	}

}
