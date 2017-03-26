/**
 * Definition: Simple app that uses File Input and Output streams
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CharacterStreamApp {

	public static void main(String[] args) throws IOException {
		FileReader inputStream = null;
		FileWriter outputStream = null;

		try{
			inputStream = new FileReader("C:/Users/User1/Desktop/workspace/Git OCJA apps/OCJA apps/src/com/artjomsporss/oca/example/net/socketecho/xanadu.txt");
			outputStream = new FileWriter("C:/Users/User1/Desktop/workspace/Git OCJA apps/OCJA apps/src/com/artjomsporss/oca/example/net/socketecho/characteroutput.txt");
			
			
			int c;
			while((c = inputStream.read()) != -1){
				outputStream.write(c);
			}
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
			if(outputStream != null){
				outputStream.close();
			}
		}
	}

}
