package cag;
/**
 *	
 *	GarageApp
 *
 *	An enhanced version of GarageApp by Alan Cowap aka AlanCowap
 *	Modified by - Artjoms Porss aka ArtjomsPorss
 *
 *
 *	Shows example of creating classes, using constructors, inheritance, super, this,
 *	array of classes, polymorphism, overriding methods, creating menu driven app,
 *	parsing variables from string to double and int, comparing classes,
 *	using for loops, do-while, branching menu with switch and if - else if statements, 
 *	try-catch, 
 *
 *	
 *	TASK:
 *	Create program that can hold 100 vehicles
 *	each vehicle must have VIN number and Registration number
 *	
 * 	Change - program must uphold 90 vehicles. out of which 70 cars, 10 motorcycles and 10 vans
 *	Motorcycle can have or not have a sidecar, by default don't have
 *	Vans can have or not a tow hitch, don't have by default. And capacity, by default 25.5 3^m
 *
 *	User must be able to find a vehicle by reference number and change details
 *	user must be able to print vehicle list
 *	
 *
 * 	Last version - MADE IT BULLETPROOF
 *
 */

import java.io.Console;


public class GarageApp{
	private static final int MAX_NUM_VEHICLES = 90;
	private static final int MAX_NUM_CARS = 70;
	private static final int MAX_NUM_MOTORBIKES = 10;
	private static final int MAX_NUM_VANS = 10;
	public Vehicle[] veh = new Vehicle[MAX_NUM_VEHICLES];


	public static void main(String[] args){
		Console con = System.console();
		System.out.println("Hello World");

		GarageApp ga = new GarageApp();
		ga.createVehicles();

		//menu
		boolean menuExit = false;
		do{
			
			String menuOption = con.readLine(	"\n--- CHOOSE OPTION ---" +
 								"\n1)List all vehicles" + 
								"\n2)Change vehicle details" +
								"\n3)Quit" +
								"\nYour option:  " );
			switch(menuOption){
				case "1" :	{							
							for(int i = 0; i < MAX_NUM_VEHICLES; ++i){
								System.out.println(i + " " +  ga.veh[i].toString());
							}
							break;
						}
				case "2" :	{
							String indexStr = con.readLine("\nEnter vehicle reference number:  ");
							int indexInt;
							try{
								indexInt = Integer.parseInt(indexStr);
							} catch(Exception e) {
								System.out.println("!!! Only whole numbers accepted !!!");
								break;
							}

							//Check if index value is within boundaries of array.
							if(indexInt >= MAX_NUM_VEHICLES || indexInt < 0){
								System.out.println("!!! There are no vehicles under such reference !!!");
								break;
							}
							String vinNum = con.readLine("\nEnter new VIN:  ");
							String regNum = con.readLine("\nEnter new Registration number:  ");

							//Check if it is a Motorbike, if yes, assign values.
							if(ga.veh[indexInt] instanceof Motorbike){
								String sideCarStr = con.readLine("\nDoes motorbike has a sidecar (y/n):  ");
								sideCarStr = sideCarStr.toLowerCase();
								boolean SideCarBoo = sideCarStr.equals("y") ? true : false;
								ga.veh[indexInt] = new Motorbike(vinNum, regNum, SideCarBoo);																
							}

							//Check if it is a Van, if yes, assign values.
							else if(ga.veh[indexInt] instanceof Van){
								String towHitchStr = con.readLine("\nDoes van has a Towhitch (y/n):  ");
								towHitchStr = towHitchStr.toLowerCase();
								boolean towHitchBoo = towHitchStr.equals("y") ? true : false;
								String capacityStr = con.readLine("\nVan's capacity:  ");
								double capacityDou;
								try{
									capacityDou = Double.parseDouble(capacityStr);
								} catch(Exception e) {
									System.out.println("!!! Only numbers accepted !!!");
									break;
								}

								ga.veh[indexInt] = new Van(vinNum, regNum, towHitchBoo, capacityDou);																
							}
							//Check if it is a Car, if yes, assign values.
							else if(ga.veh[indexInt] instanceof Car){
								ga.veh[indexInt] = new Car(vinNum, regNum);																
							}
							break;
						}
				case "3" :	{
							System.out.println("\n--- Program ends ---");
							menuExit = true;
							break;
						}
				default : 		System.out.println("\n!!! There is no such option !!!");
			}// switch statement

		}while(!menuExit);
	}// main

	private void createVehicles(){
		//add cars
		for(int i = 0; i < MAX_NUM_CARS; ++i){
			veh[i] = new Car("VIN:" + (i), "152-D-" + (i));
		}
		//add motorbikes
		int lastMotorbike = MAX_NUM_CARS + MAX_NUM_MOTORBIKES;
		for(int i = MAX_NUM_CARS; i < lastMotorbike; ++i){
			veh[i] = new Motorbike("VIN:" + (i), "152-D-" + (i), false);
		}
		//add vans
		for(int i = lastMotorbike; i < MAX_NUM_VEHICLES; ++i){
			veh[i] = new Van("VIN:" + (i), "152-D-" + (i), false, 25.9D);
		}
	}	
}//GarageApp

class Vehicle{
	private String vin;
	private String regNumber;

	public Vehicle(){
		this("Unknown", "Unknown");
	}

	public Vehicle(String vin, String regNumber){
		this.vin = vin;
		this.regNumber = regNumber;
	}
	
	public String toString(){
		return "\t" + this.vin + " \t" + this.regNumber + "  ";
	}
}

class Car extends Vehicle{
	public Car(String vin, String regNumber){
		super(vin, regNumber);
	}
}

class Motorbike extends Vehicle{
	private boolean hasSideCar = false;

	Motorbike(String vin, String regNumber, boolean hasSideCar){
		super(vin, regNumber);
		this.hasSideCar = hasSideCar;
	}

	public String toString(){
		String str = hasSideCar ? "Has side car" : "Has no side car";
		return super.toString() + "  " +  str;
	}
}

class Van extends Vehicle{
	private double capacity = 25.5D;
	private boolean hasTowHitch = false;
	
	public Van(String vin, String regNumber, boolean hasTowHitch, double capacity){
		super(vin, regNumber);
		this.hasTowHitch = hasTowHitch;
		this.capacity = capacity;
	}

	public String toString(){
		String str = hasTowHitch ? "Has Towhitch" : "Has no Towhitch";
		return super.toString() + " " + str + "  " + this.capacity;
	}
}
