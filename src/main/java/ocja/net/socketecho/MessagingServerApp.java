package ocja.net.socketecho;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;

public class MessagingServerApp {

	public static void main(String[] args) {
		
		/*
		 * Server socket with port number
		 * 
		 * input stream from client
		 * outputstream to client
		 * 
		 * standard input from user
		 * 
		 * looping construct for messaging
		 */

		System.out.println("Server's running");
		
		try(
				ServerSocket servingSocket = new ServerSocket(4040);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(servingSocket.accept().getInputStream()));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(servingSocket.accept().getOutputStream()));
				
				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
							
				){
			
			String userInput;
			
			
			while((userInput = in.readLine()) != null){
				System.out.println("User: " + userInput);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
