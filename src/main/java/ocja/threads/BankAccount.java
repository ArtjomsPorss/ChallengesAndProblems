package ocja.threads;

public class BankAccount {
	private int amount;

	BankAccount(int amount){
		this.amount = amount;
	}
	
	public void withdraw(int cash){
		if(cash < this.amount){
			System.out.println(Thread.currentThread().getName()
					+ "'s about ot get some money");
			amount -= cash;
			System.out.println(Thread.currentThread().getName() + " withdraws "
					+ cash + ", " + amount + " is left in account");
		}else{
			System.out.println(Thread.currentThread().getName()
					+ " didn't get any money, awwww..");
		}
//		if(amount < 95){
//			amount = 100;
//		}
	}
	

}
