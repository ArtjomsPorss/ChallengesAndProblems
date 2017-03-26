package cag.hangman_game;

/**
 * @author Artjoms Porss
 * 
 */
import javax.swing.JApplet;


public class GuessWord extends JApplet {

	/**
	 * Create the applet.
	 */
	public GuessWord() {
		this.setContentPane(new GuessWordGui());
	}
	
	public void init(){
		this.setSize(390, 230);
	}
	
	public void start(){}
	
	public void stop(){}
	
	public void destroy(){}

}
