package cag.hit_and_miss;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;


public class Circle extends JPanel{
	//INSTANCE VARIABLES=====================
	//location
	private int x;
	private int y;
	
	//circle size
	private final int X_SIZE = 20;
	private final int Y_SIZE = 20;
	
	//circle colour
	private Color colour;
	
	//determines which type of circle to create - fillOval or drawOval
	boolean toFill = false;
	
	
	//CONSTRUCTORS===========================
	public Circle(int x, int y){
		this.x = x;
		this.y = y;
		setPanel();
	}

	public Circle(Color colour, int x, int y){
		this(x,y);
		this.colour = colour;
		toFill = true;							//because constructor with colour is used, fillOval should be created
	}
	
	
	//METHODS==============================
	
	//sets the object opaque property
	private void setPanel() {
		this.setOpaque(false);					//lets the circle panel to be transparent to see gui background colour
		this.setSize(400, 300);
	}
	
	
	//creates graphical representation of circle
	public void paint(Graphics g){
		if(toFill){
			g.setColor(this.colour);
			g.fillOval(this.x, this.y, X_SIZE, Y_SIZE);
		} else{
			g.drawOval(this.x, this.y, X_SIZE, Y_SIZE);
		}
	}
	
	
	//colour getter and setter
	public Color getColour(){
		return this.colour;
	}	
	public void setColour(Color colour){
		this.colour = colour;
	}
}
