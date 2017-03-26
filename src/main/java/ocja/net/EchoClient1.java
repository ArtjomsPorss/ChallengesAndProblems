/**
 * Definition: 
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient1 {

	public static void main(String[] args) {
		/*
		 * client socket with server port and address declared
		 *
		 * socket reader
		 * socket writer
		 *
		 * user input reader
		 *
		 * send the message to the server
		 *
		 *
		 * looping construct
		 * 	when server sends the message - print it out
		 * 	when user inputs the message - send it
		 *
		 */

		try(
				Socket clientSocket = new Socket("192.168.1.67", 4040);

				BufferedReader socketIn = new BufferedReader(
							new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter socketOut = new PrintWriter(
							clientSocket.getOutputStream(), true); //true turns on autoflush feature
				BufferedReader standardIn = new BufferedReader(
							new InputStreamReader(System.in));

				){

					String userInput;
					String serverInput;

					while(true){
						if((userInput = standardIn.readLine()) != null){
							socketOut.println(userInput);
						//	socketOut.flush(); // not needed, autoflush is on
						}
						//display incoming message from server
						if((serverInput = socketIn.readLine()) != null){
							System.out.println("server> " + serverInput);
						}

					}

		}catch(Exception e){
			e.printStackTrace();
		}finally{

		}
	}
}
