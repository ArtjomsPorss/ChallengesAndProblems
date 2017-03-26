/**
 * Definition: simple app that utilises FileInputStream & FileOutputStream to read and write txt file
 * 
 * @author User1
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteStreamApp {

	public static void main(String[] args) throws IOException {
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try{
			in = new FileInputStream("C:/Users/User1/Desktop/workspace/Git OCJA apps/OCJA apps/src/com/artjomsporss/oca/example/net/socketecho/xanadu.txt");
			out = new FileOutputStream("C:/Users/User1/Desktop/workspace/Git OCJA apps/OCJA apps/src/com/artjomsporss/oca/example/net/socketecho/characteroutput.txt");
			int c;
			
			while((c = in.read()) != -1){
				out.write(c);
			}
		} finally{
			if (out != null){
				out.close();
			}
		}
	}

}
