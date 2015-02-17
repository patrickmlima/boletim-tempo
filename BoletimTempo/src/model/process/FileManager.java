package model.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * model.FileManager
 * Creation date: 10/11/2014
 * @author Elloá B. Guedes
 * 
 * Class which returns a BufferedReader object associated with
 * the file which contains the data that will be processed 
 */
public class FileManager {

	private int linesToSkip = 3;
	private BufferedReader br;

	public FileManager() throws IOException{
		br = new BufferedReader(
				new InputStreamReader(
						ClassLoader
								.getSystemResourceAsStream("./data/CR3000_Estacao_Baixa1.dat")));
		skipLines();
	}

	public BufferedReader getFile(){
		return br;
	}


	/**
	 * The number of lines to skip in the baixa1 file before the data, regarding the file header.
	 * 
	 * @throws IOException
	 */
	private void skipLines() throws IOException{
		for (int i = 0; i<= linesToSkip; i++){
			br.readLine();
		}
	}
	
	/**
	 * Checks if there are still available lines in the file yet
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
	 * Takes the next file line, if there
	 * @return a file line
	 */
	public String nextLine() {
        try {
            return br.readLine();
        } catch (IOException e) {
            return null;
        }
    }
}
