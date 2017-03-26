package cag.hit_and_miss;

import javax.swing.JApplet;


public class HitMiss extends JApplet {

	/**
	 * Create the applet.
	 */
	public HitMiss() {
		this.setContentPane(new HitMissGui());
	}
	
	public void init(){
		this.setSize(400, 300);
	}
	
	public void start(){}
	
	public void stop(){}
	
	public void destroy(){}
}
