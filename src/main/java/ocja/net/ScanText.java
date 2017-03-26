/**
 * Definition: App that utilises scanner(bufferedreader(filereader(text))) to read from file and print to screen
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class ScanText {

	public static void main(String[] args) throws IOException {
		Scanner scan = null;
		int h = 1086;
		
		try{
			scan = new Scanner(new BufferedReader(new FileReader("C:/Users/User1/Desktop/workspace/Git OCJA apps/OCJA apps/src/com/artjomsporss/oca/example/net/socketecho/xanadu.txt")));
			
			while(scan.hasNext()){
				System.out.print(scan.next() + "---");
			}
			
		} finally {
			if(scan != null){
				scan.close();
				System.out.printf("Scanner closed %b", h);
			}
		}
	}

}
