package cag.hangman_game;

/**
 * Represents GUI for hangman game
 * 
 * @author Artjoms Porss
 * 
 */
import javax.swing.JPanel;

import java.awt.Button;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class GuessWordGui extends JPanel{

	private String[] words = new String[]{"MEMORY","COMPUTER","PRINTER","TROUSERS","BUTTERCUP"};
	private int lives = 0;
	private JTextField livesField;
	private JTextField wordField;
	private char[] hiddenWord;
	private char[] asteriskWord;
	private String asteriskStr = "";
	private String hiddenStr = "";
	private int wordCounter = 0;
	private boolean playerWon = false;
	private JLabel lblDialog = new JLabel();
	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 18);
	

	public GuessWordGui(){
		this.setLayout(null);
		lblDialog.setFont(font);
		lblDialog.setHorizontalAlignment(SwingConstants.CENTER);
		
		Button buttonAnswer = new Button("ANSWER");
		buttonAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());
			}
		});
		buttonAnswer.setBounds(321, 199, 55, 22);
		add(buttonAnswer);
		
		Button buttonStart = new Button("START");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());
			}
		});
		buttonStart.setBounds(270, 199, 51, 22);
		add(buttonStart);
		
		Button buttonZ = new Button("Z");
		buttonZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonZ.setBounds(219, 199, 51, 22);
		add(buttonZ);
		
		Button buttonY = new Button("Y");
		buttonY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonY.setBounds(168, 199, 51, 22);
		add(buttonY);
		
		Button buttonX = new Button("X");
		buttonX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonX.setBounds(117, 199, 51, 22);
		add(buttonX);
		
		Button buttonW = new Button("W");
		buttonW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonW.setBounds(66, 199, 51, 22);
		add(buttonW);
		
		Button buttonV = new Button("V");
		buttonV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonV.setBounds(15, 199, 51, 22);
		add(buttonV);
		
		Button buttonU = new Button("U");
		buttonU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonU.setBounds(321, 171, 51, 22);
		add(buttonU);
		
		Button buttonT = new Button("T");
		buttonT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonT.setBounds(270, 171, 51, 22);
		add(buttonT);
		
		Button buttonS = new Button("S");
		buttonS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonS.setBounds(219, 171, 51, 22);
		add(buttonS);
		
		Button buttonR = new Button("R");
		buttonR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonR.setBounds(168, 171, 51, 22);
		add(buttonR);
		
		Button buttonQ = new Button("Q");
		buttonQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonQ.setBounds(117, 171, 51, 22);
		add(buttonQ);
		
		Button buttonP = new Button("P");
		buttonP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonP.setBounds(66, 171, 51, 22);
		add(buttonP);
		
		Button buttonO = new Button("O");
		buttonO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonO.setBounds(15, 170, 51, 22);
		add(buttonO);
		
		Button buttonN = new Button("N");
		buttonN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonN.setBounds(321, 143, 51, 22);
		add(buttonN);
		
		Button buttonM = new Button("M");
		buttonM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonM.setBounds(270, 143, 51, 22);
		add(buttonM);
		
		Button buttonL = new Button("L");
		buttonL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonL.setBounds(219, 142, 51, 22);
		add(buttonL);
		
		Button buttonK = new Button("K");
		buttonK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonK.setBounds(168, 142, 51, 22);
		add(buttonK);
		
		Button buttonJ = new Button("J");
		buttonJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonJ.setBounds(117, 143, 51, 22);
		add(buttonJ);
		
		Button buttonI = new Button("I");
		buttonI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonI.setBounds(66, 143, 51, 22);
		add(buttonI);
		
		Button buttonH = new Button("H");
		buttonH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonH.setBounds(15, 142, 51, 22);
		add(buttonH);
		
		Button buttonG = new Button("G");
		buttonG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonG.setBounds(321, 114, 51, 22);
		add(buttonG);
		
		Button buttonF = new Button("F");
		buttonF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonF.setBounds(270, 114, 51, 22);
		add(buttonF);
		
		Button buttonE = new Button("E");
		buttonE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonE.setBounds(219, 114, 51, 22);
		add(buttonE);
		
		Button buttonD = new Button("D");
		buttonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());
			}
		});
		buttonD.setBounds(168, 114, 51, 22);
		add(buttonD);
		
		Button buttonC = new Button("C");
		buttonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonC.setBounds(117, 114, 51, 22);
		add(buttonC);
		
		Button buttonB = new Button("B");
		buttonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonB.setBounds(66, 114, 51, 22);
		add(buttonB);
		
		Button buttonA = new Button("A");
		buttonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPressed(e.getActionCommand());			}
		});
		buttonA.setBounds(15, 114, 51, 22);
		add(buttonA);
		
		JLabel lblGuessRemaining = new JLabel("GUESSES REMAINING");
		lblGuessRemaining.setForeground(Color.RED);
		lblGuessRemaining.setBounds(128, 70, 142, 14);
		add(lblGuessRemaining);
		
		livesField = new JTextField(3);
		livesField.setHorizontalAlignment(SwingConstants.RIGHT);
		livesField.setEditable(false);
		livesField.setBounds(270, 67,45, 20);
		add(livesField);
		
		wordField = new JTextField(24);
		wordField.setHorizontalAlignment(SwingConstants.RIGHT);
		wordField.setEditable(false);
		wordField.setBounds(128, 37, 187, 20);
		add(wordField);
		
		JLabel lblbuttonPressed = new JLabel("GUESS THE WORD");
		lblbuttonPressed.setForeground(Color.RED);
		lblbuttonPressed.setBounds(168, 12, 142, 14);
		add(lblbuttonPressed);
	}//constructor
	
	
	//setup game at START button press
	private void setupGame(){

		lives = 8;				//reset lives
		playerWon = false;		//reset player state

		//setup a hidden word
		hiddenWord = words[wordCounter].toCharArray();
		++wordCounter;													//go to next word
		if(wordCounter == 5){		// if reached the limit, resey wordCounter
			wordCounter = 0;
		}


		//setup word with asterisks
		asteriskWord = new char[hiddenWord.length];
		for(int i = 0; i < hiddenWord.length; ++i){
			asteriskWord[i] = '*';
		}
		asteriskStr = new String(asteriskWord);		//convert asterisks to string to be displayed
		hiddenStr = new String(hiddenWord);	//convert hidden word to string to be displayed with answer button
		
		livesField.setText("" + lives);
		wordField.setText(asteriskStr);
	}
	
	
	//runs when any of the buttons is pressed
	private void buttonPressed(String text){
		//TODO guess the word
		if(text.equals("START")){
			setupGame();
		}else if(text.equals("ANSWER")){
			wordField.setText(hiddenStr);
			lives = 0;
			livesField.setText(""); 	//clear the guesses remaining text field
		}else if(!playerWon){
			checkLetter(text);
		}
	}
	
	
	//check letter method
	private void checkLetter(String text){
		if(wordField.getText().equals("")){
			return;
		}
		char letter = text.charAt(0);
		boolean guessRight = false;	//assume user is wrong
		if(lives > 0){			//run only if player has lives left
			//search through for a letter and make it visible
			for(int i = 0; i < hiddenWord.length; ++i){
				if(hiddenWord[i] == letter){
					asteriskWord[i] = hiddenWord[i];
					guessRight = true;			//user choice is correct
				}
			}
			
			asteriskStr = new String(asteriskWord);
			wordField.setText(asteriskStr);
			
			playerWon = !checkHasAsterisks();	//if word still has asterisks - player havent won yet
			if(playerWon){
				lblDialog.setText("You Win");
				lblDialog.setForeground(Color.BLUE);
				JOptionPane.showMessageDialog(this, lblDialog, null, JOptionPane.PLAIN_MESSAGE, null);
			}
			
			if(!guessRight){
				--lives;
				livesField.setText(""+ lives);
			}
		}else{
			lblDialog.setText("You Lose");
			lblDialog.setForeground(Color.RED);
			JOptionPane.showMessageDialog(this, lblDialog, null, JOptionPane.PLAIN_MESSAGE, null);
		}
	}
		
	
	//checks if there are asterisks left in the word
	private boolean checkHasAsterisks(){
		boolean hasAsterisks = false;
		for(char c : asteriskWord){
			if(c == '*'){
				hasAsterisks = true;
			}
		}
		return hasAsterisks;
	}
}// GuessWordGui class
