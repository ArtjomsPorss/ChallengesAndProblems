/**
 * GUI app that accepts text and calculates number of words and letters
 * @author Artjoms Porss
 * 
 */

package cag.word_count;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Button;

import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WordCountGui extends JPanel implements Highlighter{
	//INSTANCE VARIABLES
	private JTextField textField_EnterWord;
	private JTextField textField_NumOfParagraphs;
	private JTextField textField_CharNoSpaces;
	private JTextField textField_CharInclSpaces;
	private JTextField textField_NumOfWords;
	private JTextField textFieldStatus;
	private JTextArea textArea;
	
	
	//CONSTRUCTOR
	public WordCountGui(){
		this.setLayout(null);
		this.setBackground(Color.LIGHT_GRAY);
		this.setSize(500, 300);
		
		Button btnFindWord = new Button("Find Word");
		btnFindWord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//removes highlights
				textArea.getHighlighter().removeAllHighlights();
				
				String areaStr = textArea.getText();
				String fieldStr = textField_EnterWord.getText();
				
				//if both text area and text field aren't empty, search for word
				if(!checkFieldEmpty(areaStr) && !checkFieldEmpty(fieldStr)){
					findText();
				}
			}
		});
		btnFindWord.setBackground(Color.LIGHT_GRAY);
		btnFindWord.setForeground(Color.BLACK);
		btnFindWord.setBounds(333, 128, 63, 28);
		add(btnFindWord);
		
		Button btnClear = new Button("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllFields();
			}
		});
		btnClear.setBackground(Color.LIGHT_GRAY);
		btnClear.setForeground(Color.BLACK);
		btnClear.setBounds(409, 128, 63, 28);
		add(btnClear);
		
		Button btnWordCount = new Button("Word Count");
		btnWordCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkFieldEmpty(textArea.getText())){
					wordCount();
				}
			}
		});
		btnWordCount.setForeground(Color.BLACK);
		btnWordCount.setBackground(Color.LIGHT_GRAY);
		btnWordCount.setBounds(358, 183, 86, 42);
		add(btnWordCount);
		
		textFieldStatus = new JTextField();
		textFieldStatus.setEditable(false);
		textFieldStatus.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textFieldStatus.setBackground(Color.LIGHT_GRAY);
		textFieldStatus.setBounds(333, 102, 139, 20);
		add(textFieldStatus);
		textFieldStatus.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblStatus.setBounds(333, 83, 46, 14);
		add(lblStatus);
		
		textField_EnterWord = new JTextField();
		textField_EnterWord.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_EnterWord.setBounds(333, 52, 139, 20);
		add(textField_EnterWord);
		textField_EnterWord.setColumns(10);
		
		JLabel lblEnterWord = new JLabel("Enter word");
		lblEnterWord.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEnterWord.setBounds(333, 31, 77, 14);
		add(lblEnterWord);
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBounds(20, 32, 302, 132);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		add(textArea);
		
		JLabel lblEnterText = new JLabel("Enter text");
		lblEnterText.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEnterText.setBounds(113, 13, 86, 14);
		add(lblEnterText);
		
		JLabel lblCountOfWords = new JLabel("Count of words and characters");
		lblCountOfWords.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCountOfWords.setBounds(83, 175, 200, 14);
		add(lblCountOfWords);
		
		textField_NumOfParagraphs = new JTextField();
		textField_NumOfParagraphs.setBackground(Color.LIGHT_GRAY);
		textField_NumOfParagraphs.setEditable(false);
		textField_NumOfParagraphs.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_NumOfParagraphs.setBounds(259, 272, 63, 20);
		add(textField_NumOfParagraphs);
		textField_NumOfParagraphs.setColumns(10);
		
		textField_CharNoSpaces = new JTextField();
		textField_CharNoSpaces.setBackground(Color.LIGHT_GRAY);
		textField_CharNoSpaces.setEditable(false);
		textField_CharNoSpaces.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_CharNoSpaces.setBounds(259, 248, 63, 20);
		add(textField_CharNoSpaces);
		textField_CharNoSpaces.setColumns(10);
		
		textField_CharInclSpaces = new JTextField();
		textField_CharInclSpaces.setBackground(Color.LIGHT_GRAY);
		textField_CharInclSpaces.setEditable(false);
		textField_CharInclSpaces.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_CharInclSpaces.setBounds(259, 224, 63, 20);
		add(textField_CharInclSpaces);
		textField_CharInclSpaces.setColumns(10);
		
		textField_NumOfWords = new JTextField();
		textField_NumOfWords.setBackground(Color.LIGHT_GRAY);
		textField_NumOfWords.setEditable(false);
		textField_NumOfWords.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textField_NumOfWords.setBounds(259, 200, 63, 20);
		add(textField_NumOfWords);
		textField_NumOfWords.setColumns(10);
		
		JLabel labelNumOfParagraphs = new JLabel("Number of paragraphs");
		labelNumOfParagraphs.setBounds(25, 275, 183, 14);
		add(labelNumOfParagraphs);
		
		JLabel lblNumberOfCharacters = new JLabel("Number of characters without spaces");
		lblNumberOfCharacters.setBounds(25, 251, 216, 14);
		add(lblNumberOfCharacters);
		
		JLabel lblNumberOfCharacters_1 = new JLabel("Number of characters including spaces");
		lblNumberOfCharacters_1.setBounds(25, 227, 233, 14);
		add(lblNumberOfCharacters_1);
		
		JLabel lblNumberOfWords = new JLabel("Number of words");
		lblNumberOfWords.setBounds(25, 203, 183, 14);
		add(lblNumberOfWords);	
	}// END CONSTRUCTOR
	
	
	/**
	 * Performs more complicated but precise search for a word
	 * It ignores the character after the searched word, if
	 * for example, it is a dot or exclamation mark 
	 */
	private void findText(){
		char[] searched = textArea.getText().toCharArray();					//text area array
		char[] lookingFor = textField_EnterWord.getText().toCharArray();	//word searching for
		boolean wordFound = false;
		int wordPosition = 0;
		int letterCounter = 0;
		try{
			search:
			for(int s = 0; s < searched.length; ++s){						//compares every letter of textArea text array
				for(int l = letterCounter; l < lookingFor.length; ++l){		//to textField_EnterWord text array
					if(searched[s] != lookingFor[l]){						//IF character doesn't match
						wordPosition = 0;									//reset position
						letterCounter = 0;									//reset counter
						break;												//go to next character
					}else if(s == 0){										//IF letter match and it's a first letter in text
						++letterCounter;									//assume it's beginning of the word searched for
						break;												//go to next character
					}else if(letterCounter == 0 && searched[s-1] != ' '){		//IF letter match, its first letter in the word and before is NOT a space
						wordPosition = 0;										//reset position - it's not a separate word
						letterCounter = 0;										//reset counter for same reason
						break;													//go to next character
					}else if(letterCounter == 0 && ((searched[s-1] == ' ') ||
							(searched[s-1] == 10))){							//IF letter match, its first letter in the word - before is a space or new line
						wordPosition = s;										//assume its the beginning position of the correct word
						++letterCounter;										//start counting word letters 	
						break;													//and go to next letter	
					}else{														//ELSE letter match - assume its middle of the word
						++letterCounter;										//increment letter counter
						if(l == lookingFor.length-1){							//if it's a last letter of the word
							if(!isLetter(searched[s + 1])){						//and next is not a letter, or throws an exception out of bounds(end of text array)
								wordFound = true;								//it's THE WORD!
								break search;									//break out of searching
							}else{												//if the letter after last one is another letter, it's just
								wordPosition = 0;								//a part of the word, reset counters
								letterCounter = 0;
							}
						}
						break;
					}
					
				}
			}
		//if it was last word in text, this exception would be thrown, and still it was a correct word
		}catch(ArrayIndexOutOfBoundsException e){
			wordFound = true;		
		}
		
		//check if word is found, display appropriate message
		if(wordFound){
			textFieldStatus.setText("Word found at: " + wordPosition);
			textFieldStatus.setForeground(Color.BLUE);
			Highlighter h = textArea.getHighlighter();
			try {
				h.addHighlight(wordPosition, wordPosition + lookingFor.length, DefaultHighlighter.DefaultPainter);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			textFieldStatus.setText("Word not found");
			textFieldStatus.setForeground(Color.RED);
		}
	}//   END FIND TEXT METHOD
	
	
	
	//makes calculations of words, characters with and without spaces, paragraphs in textArea
	private void wordCount(){
		char[] text = textArea.getText().toCharArray();
		int charsInclSpaces = 0;
		int charsExclSpaces = 0;
		int paragraphs = 1;						//including first paragraph
		int words = 0;
		
		//count characters including spaces
		for(char c: text){
			if(c == 10){
				continue;
			}else{
				++charsInclSpaces;
			}
		}
		
		//count characters excluding spaces
		for(char c : text){
			if((c == ' ') || (c == 10)){
				continue;
			} else {
				++charsExclSpaces;
			}
		}
		
		//count paragraphs
		for(char c : text){
			if(c == 10){
				++paragraphs;
			}
		}
		
		//count words
		for(int i = 0; i < text.length; ++i){
			//first character is a letter - incerement
			if(i == 0 && text[i] != ' '){
				++words;
			//if character is not space and previous character is space - increment
			} else if(text[i] != ' ' && ((text[i-1] == ' ') || (text[i-1] == 10))){
				++words;
			}
		}
		
		//show results in appropriate text fields
		textField_CharInclSpaces.setText("" + charsInclSpaces);
		textField_CharNoSpaces.setText("" + charsExclSpaces);
		textField_NumOfParagraphs.setText("" + paragraphs);
		textField_NumOfWords.setText("" + words);
	}
	
	
	//clears all text fields and text areas
	public void clearAllFields(){
		textField_EnterWord.setText("");
		textField_NumOfParagraphs.setText("");
		textField_CharNoSpaces.setText("");
		textField_CharInclSpaces.setText("");
		textField_NumOfWords.setText("");
		textFieldStatus.setText("");
		textArea.setText("");
	}
	
	
	//TODO method that returns true if char is a letter
	private boolean isLetter(char c){
		if((c >= 65 && c <= 90) || (c >= 97 && c <= 122)){
			return true;
		} else {
			return false;
		}

	}
	
	//returns false string passed is not empty
	public boolean checkFieldEmpty(String text){
		if(text.length() > 0){
			return false;
		}else{
			return true;
		}
	}


	@Override
	public Object addHighlight(int arg0, int arg1, HighlightPainter arg2)
			throws BadLocationException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void changeHighlight(Object arg0, int arg1, int arg2)
			throws BadLocationException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deinstall(JTextComponent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Highlight[] getHighlights() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void install(JTextComponent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeAllHighlights() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeHighlight(Object arg0) {
		// TODO Auto-generated method stub
		
	}
}
