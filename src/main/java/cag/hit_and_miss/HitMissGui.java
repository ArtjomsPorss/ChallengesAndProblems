package cag.hit_and_miss;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.util.Random;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Artjoms Porss
 *	Hit and Misses game where you have to match the colour combination
 *
 */
public class HitMissGui extends JPanel{
	//INSTANCE VARIABLES=====================
	JLabel lblGuessTheHidden;							//stores "you win" and "you lose" message
	//colours used in the game
	private Color[] colours = new Color[]{Color.RED, Color.BLUE,
							Color.GREEN ,Color.YELLOW, Color.BLACK};
	//stores the hidden combination, starts at white
	private Color[] combination = new Color[3]; 			
	private Point[] coordinates = new Point[21];		//stores coordinates for all circles in the game
	private Circle[] lowerCircles = new Circle[3];		//stores lower circles
	private Circle[] userCircles = new Circle[18];		//stores user picked circles
	private Circle[] emptyCircles = new Circle[3];
	private int circlesDrawn = 0;						//stores current circle picked by the user
	private int lineCounter = 1; 						//stores current line number
	private int circlesInRow = 0;						//store how many circles are in a row, used to check when to draw new line of empty circles
	private boolean playerWin = false;					//becomes true if payer guesses the combination
	
	//CLASS CONSTRUCTOR======================
	public HitMissGui(){
		setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		JLabel labelClickAColour = new JLabel("Click a colour");
		labelClickAColour.setBounds(60, 11, 95, 14);
		add(labelClickAColour);
		
		JButton btnYellow = new JButton("Yellow");
		btnYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(Color.YELLOW);
			}
		});
		btnYellow.setMargin(new Insets(2, 4, 2, 4));
		btnYellow.setBounds(53, 60, 51, 23);
		add(btnYellow);
		
		JButton btnBlack = new JButton("Black");
		btnBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonPress(Color.BLACK);
			}
		});
		btnBlack.setMargin(new Insets(2, 4, 2, 4));
		btnBlack.setBounds(108, 60, 51, 23);
		add(btnBlack);
		
		JButton btnGreen = new JButton("Green");
		btnGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(Color.GREEN);
			}
		});
		btnGreen.setMargin(new Insets(2, 4, 2, 4));
		btnGreen.setBounds(138, 34, 51, 23);
		add(btnGreen);
		
		JButton btnRed = new JButton("Red");
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonPress(Color.RED);
			}
		});
		btnRed.setMargin(new Insets(2, 4, 2, 4));
		btnRed.setBounds(28, 34, 51, 23);
		add(btnRed);
		
		JButton btnBlue = new JButton("Blue");
		btnBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPress(Color.BLUE);
			}
		});
		btnBlue.setMargin(new Insets(2, 4, 2, 4));
		btnBlue.setBounds(83, 34, 51, 23);
		add(btnBlue);
		
		JLabel lblHitsAndMisses = new JLabel("HITS AND MISSES");
		lblHitsAndMisses.setForeground(Color.BLUE);
		lblHitsAndMisses.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblHitsAndMisses.setBounds(108, 253, 206, 36);
		add(lblHitsAndMisses);
		
		lblGuessTheHidden = new JLabel("Guess the hidden code");
		lblGuessTheHidden.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGuessTheHidden.setBounds(29, 227, 160, 23);
		add(lblGuessTheHidden);
		
		setupGame();
	}
	
	//METHODS=========================================================
	
	
	//sets up the game for the user
	public void setupGame(){
		initialiseCoordinates();
		drawBottomCircles();
		setupColourCombination();
		drawEmptyLine();
	}
	
	//runs on colour buttons press
	private void buttonPress(Color colour){
		if(circlesDrawn < 18 && playerWin == false){				//to make sure user doesnt go beyound boundaries 
			userCircles[circlesDrawn] = new Circle(colour, coordinates[circlesDrawn].x, coordinates[circlesDrawn].y);
			this.add(userCircles[circlesDrawn], null);
			this.repaint();
			++circlesDrawn;					//go to next circle
			++circlesInRow;
			if(circlesInRow == 3){		//if there are 3 circles in a row, draw new line of empty circles
				checkWin();
				if(circlesDrawn < 17){	//and if its not the last guess line
					drawEmptyLine();
				}
				circlesInRow = 0;			//reset counter
			}
		}
	}
	
	
	//checks if user won
	public void checkWin(){
		int correctGuess = 0;					//stores how many correct circles user had chosen
		int colourCounter = 0;					//to iterate through combination array
		for(int i = circlesDrawn-3; i < circlesDrawn; ++i){
			if(userCircles[i].getColour() == combination[colourCounter]){
				++correctGuess;
			}
			++colourCounter;
		}
		if(correctGuess == 3){
			playerWin = true;
			lblGuessTheHidden.setText("You Win");
			//iterate through bottom cirlces to change colour to combination
			showWinCombination();
		} else if(circlesDrawn == 18){
			lblGuessTheHidden.setText("You Lose");
			showWinCombination();
		}
	}
	
	
	//draws bottom circles
	public void drawBottomCircles(){
		for(int i = 0; i < lowerCircles.length; ++i){
			lowerCircles[i] = new Circle(coordinates[18 + i].x, coordinates[18 + i].y);
			this.add(lowerCircles[i]);
			lowerCircles[i] = new Circle(Color.WHITE, coordinates[18 + i].x, coordinates[18 + i].y);
			this.add(lowerCircles[i]);
		}
	}
	
	
	//shows the hidden combination
	public void showWinCombination(){
		for(int i = 0; i < lowerCircles.length; ++i){
			lowerCircles[i].setColour(combination[i]);
		}
	}
	
	
	//sets up the combination to be guessed by the user
	private void setupColourCombination(){
		Random random = new Random();
		for(int i = 0; i < combination.length; ++i){
			combination[i] = colours[random.nextInt(5)];
			//combination[i] = Color.RED;					//TODO used for testing
		}
	}
	
	//draw a line of empty circles
	private void drawEmptyLine(){
		for(int i = 0; i < emptyCircles.length; ++i){
			emptyCircles[i] = new Circle(coordinates[circlesDrawn + i].x, coordinates[circlesDrawn + i].y);
			this.add(emptyCircles[i]);
			emptyCircles[i] = null;
		}
	
		//add guess labels
		String ordinalSuffix = "th";
		if(lineCounter == 1){
			ordinalSuffix = "st";
		} else if (lineCounter == 2){
			ordinalSuffix = "nd";
		} else if (lineCounter == 3){
			ordinalSuffix = "rd";
		}
		JLabel guessLabel = new JLabel();
		guessLabel.setText(lineCounter + ordinalSuffix + " Guess");
		guessLabel.setBounds(coordinates[circlesDrawn].x + 90, coordinates[circlesDrawn].y + 2, 76, 14);
		this.add(guessLabel);
		
		++lineCounter;				//go to next line
	}
	
	
	//initialises coordinates to be used when creating circles
	public void initialiseCoordinates(){
		int x = 230;		//+= 25
		int y = 35;			//+=30
		for(int i = 0; i < coordinates.length; ++i){
			coordinates[i] = new Point(x, y);			
			if(i == 2){
				x = 230;
				y += 30;
			}else if(i == 5){
				x = 230;
				y += 30;
			}else if(i == 8){
				x = 230;
				y += 30;
			}else if(i == 11){
				x = 230;
				y += 30;
			}else if(i == 14){
				x = 230;
				y += 30;
			}else if(i == 17){
				x = 230;
				y += 42;
			}else{
				x += 25;
			}
		}
	}
}
