/**
 * Definition: Messaging server app with GUI
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;


public class MessagingServer {

	//gui state
	private JFrame frmMessagingServer;

	//net state
	private int serverPort = 4040;

	private ServerSocket server;
	private Socket client;
	private PrintWriter out;
	private BufferedReader in;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessagingServer window = new MessagingServer();
					window.frmMessagingServer.setVisible(true);
					window.initialiseNet();
					window.netListen();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MessagingServer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMessagingServer = new JFrame();
		frmMessagingServer.setTitle("Messaging Server");
		frmMessagingServer.setBounds(100, 100, 453, 677);
		frmMessagingServer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMessagingServer.getContentPane().setLayout(null);

	}


	//initialises net
	private void initialiseNet(){
		try {
			server = new ServerSocket(serverPort);
			client = server.accept();
			out = new PrintWriter(client.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	//listens to net messages
	private void netListen(){
		String incomingMsg;

		try {
			while((incomingMsg = in.readLine()) != null){
				System.out.println(incomingMsg);	//TODO testing
				out.println(incomingMsg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
