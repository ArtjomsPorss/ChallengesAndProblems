package cag;
/** 
 * 
 * Example GUI-based app created in one day during City&Gilds Java Course
 * This app uses swing, event handling, ceasar encryption algorithm, File I/O, manipulating JComponents using buttons
 * 
 * @author Artjoms Porss, Gregory Harman, Babajide Williams, Alan Corvin
 * @version 1.0  
 * @dependencies none
 * 
 */

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.swing.SwingConstants;


public class CaesarCipher extends JFrame {

	private JPanel contentPane;
	private JButton btnClear;

	private static int encryptionIndex = 0;

	private static boolean hidden1 = false; 	//for left text area
	private static boolean hidden2 = false;		//for right text area
	private static String filePath = "C:/EncryptedText/EncryptedText.txt";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CaesarCipher frame = new CaesarCipher();
					frame.setTitle("Caesar Cipher Encryption");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CaesarCipher() {
		setTitle("Encryption App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 28, 206, 224);
		contentPane.add(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(353, 28, 206, 224);
		contentPane.add(textArea_1);
		textArea_1.setLineWrap(true);
		textArea_1.setWrapStyleWord(true);
		
		JLabel lblCurrentAction = new JLabel("");
		lblCurrentAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentAction.setBounds(99, 7, 352, 23);
		contentPane.add(lblCurrentAction);
		
		
		//To Encrypt text
		JButton btnEncrypt = new JButton("--> Encrypt -->");
		btnEncrypt.setToolTipText("Encrypts text from left INPUT text area to OUTPUT text area using index defined above");
		btnEncrypt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnEncrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String outputText = encryptText(textArea.getText(), encryptionIndex); // INDEX GOES HERE
				textArea_1.setText(outputText);
				lblCurrentAction.setText("Text has been encrypted using index " + encryptionIndex);				
			}
		});
		
		 
		btnEncrypt.setBounds(226, 93, 117, 23);
		contentPane.add(btnEncrypt);
		
		JLabel lblOutput = new JLabel("OUTPUT");
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOutput.setBounds(480, 7, 79, 14);
		contentPane.add(lblOutput);
		
		JLabel lblInput = new JLabel("INPUT");
		lblInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInput.setBounds(31, 2, 58, 28);
		contentPane.add(lblInput);
		
		
		//Clears input textArea
		btnClear = new JButton("Clear");
		btnClear.setToolTipText("Clears INPUT window");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
			}
		});
		btnClear.setBounds(118, 263, 98, 23);
		contentPane.add(btnClear);
		
		
		//Changes encryption index
		JSpinner spinner = new JSpinner();
		spinner.setToolTipText("Sets the encryption index");
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				encryptionIndex = (int) spinner.getValue();
			}
		});
		spinner.setBounds(247, 54, 68, 28);
		contentPane.add(spinner);
		
		JLabel lblNewLabel = new JLabel("Encryption index");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setBounds(241, 29, 89, 23);
		contentPane.add(lblNewLabel);
		
		
		//Hides-Unhides input textArea 
		JButton btnHideunhide = new JButton("Hide/Unhide");
		btnHideunhide.setToolTipText("Hides INPUT windod");
		btnHideunhide.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHideunhide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hidden1 == false){
					textArea.setVisible(false);
					hidden1=true;
				}else if(hidden1 == true){
					textArea.setVisible(true);
					hidden1=false;
				}			
			}
		});		
		btnHideunhide.setBounds(10, 263, 98, 23);
		contentPane.add(btnHideunhide);
		
		
		//Decrypts text from right textArea, prints it to left textArea
		JButton btnDecrypt = new JButton("<-- Decrypt <--");
		btnDecrypt.setToolTipText("Decrypts text from Output text area to Input text area using same index used to Encrypt");
		btnDecrypt.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnDecrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//in order to decrypt without changing the spinner, encryptionIndex is * -1
				String outputText = encryptText(textArea_1.getText(), (encryptionIndex * -1));
				textArea.setText(outputText);
				lblCurrentAction.setText("Your text has been decrypted using index " + encryptionIndex);
			}
		});
		btnDecrypt.setBounds(226, 127, 117, 23);
		contentPane.add(btnDecrypt);
		
		
		//Swaps text between text areas
		JButton btnSwap = new JButton("<-- Swap -->");
		btnSwap.setToolTipText("Swaps texts between INPUT and OUTPUT");
		btnSwap.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSwap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textArea.getText();
				String temp=str;
				String Stt = textArea_1.getText();
				String sTemp = Stt;
				textArea.setText(sTemp);
				textArea_1.setText(temp);				
			}
		});
		btnSwap.setBounds(226, 161, 117, 23);
		contentPane.add(btnSwap);
		
		
		//clears right text area
		JButton btnClear2 = new JButton("Clear");
		btnClear2.setToolTipText("Clears OUTPUT window");
		btnClear2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnClear2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_1.setText(null);
			}
		});
		btnClear2.setBounds(461, 263, 98, 23);
		contentPane.add(btnClear2);
		
		
		//hides-unhides right text area
		JButton btnHideunhide2 = new JButton("Hide/Unhide");
		btnHideunhide2.setToolTipText("Hides OUTPUT window");
		btnHideunhide2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnHideunhide2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(hidden2 == false){
					textArea_1.setVisible(false);
					hidden2=true;
				}else if(hidden2 == true){
					textArea_1.setVisible(true);
					hidden2=false;
				}
			}
		});
		btnHideunhide2.setBounds(353, 263, 98, 23);
		contentPane.add(btnHideunhide2);
		

		//loads text from file to right textArea
		JButton btnLoad = new JButton("Load -->");
		btnLoad.setToolTipText("Loads text from a file to OUTPUT");
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String textLoaded = null;
				List<String> lines;
				try{
					File file = new File(filePath);
					BufferedReader br = new BufferedReader(new FileReader(filePath));
					if (file.exists()){
						if(br.readLine() == null){
							lblCurrentAction.setText("The file is empty. Nothing to load.");
						} else {
							lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
							for(String line : lines){
								if(textLoaded == null){
									textLoaded = line + "\n";
								} else{
									textLoaded += line + "\n";
								}
							}
							textArea_1.setText(textLoaded);
							lblCurrentAction.setText("Loaded text from file " + filePath);
						}						
					}else{
						lblCurrentAction.setText("No file to load.");
					}
					br.close();
				}catch(Exception i){					
				}finally{					
				}				
			}
		});
		btnLoad.setBounds(226, 229, 117, 23);
		contentPane.add(btnLoad);
		
		
		//saves text in right textArea to file
		JButton btnSave = new JButton("Save -->");
		btnSave.setToolTipText("Saves text from OUTPUT to a file");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				 
				try{
					String textToSave = textArea_1.getText();
							File folder = new File("C:/EncryptedText/");
							folder.mkdir();
							File file = new File(filePath);
					if (!file.exists()) {
						file.delete();
					}else{
							file.createNewFile();
						}					
						FileWriter fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);
						bw.write(textToSave);
						bw.flush();
						bw.close();
						lblCurrentAction.setText("Saved to:  \"" + filePath + "\"");
				}
				catch(Exception f){					
				}finally{					
				}
			}			
		});
		btnSave.setBounds(226, 195, 117, 23);
		contentPane.add(btnSave);
		
		
		//clears both textAreas
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.setToolTipText("Clears text in both INPUT and OUTPUT");
		btnClearAll.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				textArea_1.setText(null);
			}
		});
		btnClearAll.setBounds(226, 263, 117, 23);
		contentPane.add(btnClearAll);		
	}
	
	
	// method that encrypts string input by index and returns string output
	public String encryptText(String text, int index){
		int lowerBound = 32;
		int upperBound = 126;
		String output;
		char[] charArray = text.toCharArray();
		//increments array values by index, checks them to be within boundary values - 32, 126
		for(int i = 0; i < charArray.length; ++i){
			char tempChar = charArray[i];
			boolean isValid = false;
			do{
				if(tempChar == 10){		//skips the new line char '10' to save paragraphs
					isValid = true;
					continue;
				}
				if(tempChar + index > upperBound){
					tempChar += - upperBound + lowerBound + index;
					charArray[i] = tempChar;
				}else if(tempChar + index < lowerBound){
					tempChar += upperBound - lowerBound + index;
					charArray[i] = tempChar;
				}
				else{
					tempChar += index;
					charArray[i] = tempChar;
				}
				if((charArray[i] >= lowerBound) && (charArray[i] <= upperBound)){
					isValid = true;
				}
			} while (!isValid);
		}
		output = new String(charArray);
		return output;
	}
}
