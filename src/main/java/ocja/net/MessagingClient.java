/**
 * Definition: Messaging client app with GUI
 * 
 * @author Artjoms Porss
 * Version No: 1.0
 * 
 */
package ocja.net;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessagingClient {
	//GUI state
	private JFrame frmMessagingClient;
	private JTextField messageField;
	private JTextArea messagesArea;


	//Chat state
	private String userName;



	//net state
	private String hostName = "192.168.1.67"; //TODO changing IP address
	private int hostPort = 4040;
	private Socket clientSocket;

	private PrintWriter out;	//to send messages to server
	private BufferedReader in;	//to receive messages from server



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessagingClient window = new MessagingClient();
					window.frmMessagingClient.setVisible(true);
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
	public MessagingClient() {
		getUserName();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMessagingClient = new JFrame();
		frmMessagingClient.setTitle("Messaging Client");
		frmMessagingClient.setBounds(100, 100, 481, 487);
		frmMessagingClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMessagingClient.getContentPane().setLayout(null);

		messagesArea = new JTextArea();
		messagesArea.setEditable(false);
		messagesArea.setLineWrap(true);
		messagesArea.setWrapStyleWord(true);
		messagesArea.setBounds(10, 11, 308, 396);
		frmMessagingClient.getContentPane().add(messagesArea);

		messageField = new JTextField();
		messageField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == 10){
					netSend("SHIT, new message!!!!");	//send message to server
					messageField.setText("");
				}
			}
		});
		messageField.setBounds(10, 418, 445, 20);
		frmMessagingClient.getContentPane().add(messageField);
		messageField.setColumns(10);

		JTextArea usersArea = new JTextArea();
		usersArea.setLineWrap(true);
		usersArea.setWrapStyleWord(true);
		usersArea.setEditable(false);
		usersArea.setBounds(328, 11, 127, 396);
		frmMessagingClient.getContentPane().add(usersArea);
	}


	//prompts user for a username at the launch of the app
	private void getUserName(){
		JLabel message = new JLabel("Enter your name:" + "\nNo spaces, only 10 characters..");
		String name;
		do{
			name = (String)JOptionPane.showInputDialog(message);
		}while(name == null || name.isEmpty() || name.contains(" ") || name.length() > 10);	//TODO proper algorithm that checks username

		this.userName = name;
	}


	//initialises socket and network IO
	private void initialiseNet() throws UnknownHostException, IOException{
		clientSocket = new Socket(hostName, hostPort);
		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	}


	//listens to the messages from server and displays them when received
	private void netListen(){
		String msgFromServer;

		try {
			while((msgFromServer = in.readLine()) != null){
				messagesArea.setText(msgFromServer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//accepts message and sends to server(printwriter assigned to server socket)
	private void netSend(String text){

		try{
				out.println(text);
				System.out.println("Your message is: " + text);
		} catch (Exception e){
			//e.printStackTrace();
		}
	}
}
