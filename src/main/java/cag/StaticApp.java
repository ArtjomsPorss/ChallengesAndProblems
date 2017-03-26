package cag;
class StaticApp{
	public static int alanCounter = 0;
	public static int MAX_PEOPLE = 20;

	public static void main(String[] args){
		new StaticApp().runProgram();
	}

	public void runProgram(){
		Person[] person = new Person[MAX_PEOPLE];
		for(int i = 0; i < MAX_PEOPLE; ++i){
			person[i] = new Person("Alan", 21);
		}

		for(int i = 0; i < MAX_PEOPLE; ++i){
			System.out.println("Person name and age: " + person[i]);
		}

		System.out.println("\nAlan's spawned: " + alanCounter);
				
	}
}

class Person{
	private String name;
	private int age;

	Person(String name, int age){
		this.name = name;
		this.age = age;
		StaticApp.alanCounter++;
	}

	public String toString(){
		return this.name + ", " + this.age;
	}
}
