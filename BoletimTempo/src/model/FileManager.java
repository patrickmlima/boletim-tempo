package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
/**
 * model.FileManager
 * Creation date: 10/11/2014
 * @author Elloá B. Guedes
 * 
 * Classe que retorna um objeto do tipo BufferedReader
 * associado ao arquivo que contêm os dados a serem processados.
 * 
 */
public class FileManager {

	private int linesToSkip = 3;
	private BufferedReader br;

	public FileManager() throws IOException{
		br = new BufferedReader(new FileReader(new File(Util.BAIXA1)));
		skipLines();
	}

	public BufferedReader getFile(){
		return br;
	}


	/**
	 * Quantidade de linhas a pular no arquivo baixa1 antes dos dados, refrentes ao cabeçalho.
	 * 
	 * 
	 * @throws IOException
	 */
	private void skipLines() throws IOException{
		for (int i = 0; i<= linesToSkip; i++){
			br.readLine();
		}
	}
	
	/**
	 * Checa se ainda há linhas disponíveis do arquivo
	 * 
	 * @return true, se ainda há linhas no arquivo
	 */
	public boolean hasNextLine() {
		
        try {
            br.mark(1);
            if (br.read() < 0) {
                return false;
            }
            return true;
        } catch (IOException e) {
        	return false;
          }
    }
	
	/**
	 * Pega a próxima linha do arquivo,se houver
	 * @return uma linha do arquivo
	 */
	public String nextLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }

}
