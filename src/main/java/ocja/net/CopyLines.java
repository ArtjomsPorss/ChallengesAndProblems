/**
 * Definition: Simple app that reads from text file into buffer, than writes from buffer to another file
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CopyLines {

	public static void main(String[] args) throws IOException {
		BufferedReader inStream = null;
		PrintWriter outStream = null;
		
		try{
			inStream = new BufferedReader(new FileReader("C:/Users/User1/Desktop/workspace/Git OCJA apps/OCJA apps/src/com/artjomsporss/oca/example/net/socketecho/xanadu.txt"));
			outStream = new PrintWriter(new FileWriter("C:/Users/User1/Desktop/workspace/Git OCJA apps/OCJA apps/src/com/artjomsporss/oca/example/net/socketecho/characteroutput.txt"));
			
			String l;
			while((l = inStream.readLine()) != null){
				outStream.println(l);
			}
		} finally{
			if(inStream != null){
				inStream.close();
			}
			if(outStream != null){
				outStream.close();
			}
		}

	}

}
