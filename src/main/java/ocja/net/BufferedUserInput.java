/**
 * Definition: Remembering how to create an app that stores user input in buffer
 * and then displays it back to the user
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BufferedUserInput {

	public static void main(String[] args) {
		
		try(
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				
				){
			String userInput;
			while((userInput = stdIn.readLine()) != null){
				System.out.println("echo " + userInput);
			}
			
		}catch(Exception e){
			
		}

	}

}
