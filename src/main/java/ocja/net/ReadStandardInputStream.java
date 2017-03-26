/**
 * Definition: small app that reads from standard input stream and prints it out
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadStandardInputStream {

	public static void main(String[] args) throws IOException {
		BufferedReader br = null;
		
		try{
			br = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			
			while((userInput = br.readLine()) != null){
				System.out.println("echo: " + userInput);
			}
			
		} finally{
			br.close();
		}
	}

}
