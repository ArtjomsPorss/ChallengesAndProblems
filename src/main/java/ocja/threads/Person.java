package ocja.threads;

public class Person implements Runnable{
	private static BankAccount ba = new BankAccount(100);
	private String name;

	Person(String name){
		this.name = name;
	}

	@Override
	public void run() {
		ba.withdraw(95);		
	}
	
}
