package main;

import java.io.IOException;

import model.FileManager;

public class Main {

	public static void main(String[] args) {
		
		try {
			FileManager f = new FileManager();
			System.out.println(f.getLine());
			System.out.println(f.getLine());
			System.out.println(f.getLine());
			System.out.println(f.getLine());
			System.out.println(f.getLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
