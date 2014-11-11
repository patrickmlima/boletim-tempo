package main;

import model.BoletimGenerator;
import model.InterfaceBoletimGenerator;
import listener.TurnListener;

public class Main implements TurnListener{
	@Override
	public void TurnFinished(InterfaceBoletimGenerator obj) {
		System.out.println("Turno finalizado: "+obj.getTurnName());
	}

	public static void main(String[] args) {
		
//		try {
			BoletimGenerator obj = new BoletimGenerator();
			obj.addModelListener(new Main());
			obj.PrintLines();
//			FileManager f = new FileManager();
//			System.out.println(f.getLine());
//			System.out.println(f.getLine());
//			System.out.println(f.getLine());
//			System.out.println(f.getLine());
//			System.out.println(f.getLine());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}

}
