package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * model.FileManager
 * Creation date: 10/11/2014
 * @author Elloá B. Guedes
 * Classe que retorna um objeto do tipo BufferedReader
 * associado ao arquivo que contêm os dados a serem processados.
 */
public class FileManager {
	private final String SEPARATE = File.separator;
	private String filePath = "."+SEPARATE+"src"+SEPARATE+"data"+SEPARATE;
	private String fileName = "CR3000_Estacao_Baixa1.dat";
	private int linesToSkip = 3;
	private BufferedReader br;

	public FileManager() throws IOException{
		br = new BufferedReader(new FileReader(new File(filePath + fileName)));
		skipLines();
	}

	public BufferedReader getFile(){
		return br;
	}

	public String getLine() throws IOException {
		return br.readLine();
	}

	private void skipLines() throws IOException{
		for (int i = 0; i<= linesToSkip; i++){
			br.readLine();
		}
	}

}
