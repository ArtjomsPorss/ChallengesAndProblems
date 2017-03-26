/**
 * @author Artjoms Porss
 * 
 */
package cag.word_count;

import javax.swing.JApplet;

public class WordCountApplet extends JApplet {

	/**
	 * Create the applet.
	 */
	public WordCountApplet() {
		this.setContentPane(new WordCountGui());

	}
	
	public void init(){
		this.setSize(500, 300);
	}
	

}
