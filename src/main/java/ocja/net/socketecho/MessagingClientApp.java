package ocja.net.socketecho;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MessagingClientApp {

	public static void main(String[] args) throws IOException {
		/*
		 * +create socket bound to IP and port of the server
		 * 
		 * create inputStream from server
		 * create outputStream to server
		 * 
		 * create inputStream from user
		 */

		try(
				Socket toServerSocket = new Socket("192.168.0.14", 4040);

				BufferedReader in = new BufferedReader(new InputStreamReader(toServerSocket.getInputStream()));
				PrintWriter out = new PrintWriter(toServerSocket.getOutputStream());

				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				){
			
			String userInput;
			
			while((userInput = stdIn.readLine()) != null){
				out.write(userInput);
				System.out.println("Server: " + in.readLine());
			}


		} catch(Exception e){
			e.printStackTrace();
		} finally {
		}

	}

}
