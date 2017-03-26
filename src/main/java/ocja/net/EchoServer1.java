/**
 * Definition: Learning to create server for messaging
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer1 {

	public static void main(String[] args) {
		/*
		 * server socket with receiving port declared
		 *
		 * buffered reader from socket
		 * printwriter sends through socket
		 *
		 * standard user input writes to printwriter
		 *
		 * looping construct that accepts client input and writes it to screen
		 *
		 */

		try(
				ServerSocket servingSocket = new ServerSocket(4040);
				Socket clientSocket = servingSocket.accept();

				BufferedReader socketIn = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter socketOut = new PrintWriter(clientSocket.getOutputStream(), true);

				BufferedReader standardInput = new BufferedReader(new InputStreamReader(System.in));

				){

			System.out.println("Client connected...");


			String msgFromClient;
			String msgToClient;
			
			while(true){
				if((msgFromClient = socketIn.readLine()) != null){
					System.out.println("client> " + msgFromClient);
				}
				if((msgToClient = standardInput.readLine()) != null){
					socketOut.println(msgToClient);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
}
