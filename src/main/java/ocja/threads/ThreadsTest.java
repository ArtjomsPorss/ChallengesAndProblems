package ocja.threads;

public class ThreadsTest {

	public static void main(String[] args) {
//		Thread t = new Thread(new Counter("HELLO"));
//		t.start();
//		
//		Thread t1 = new Thread(new Counter("aaaaaaaaaaaaaaaaa"));
//		t1.start();
//		
//		Thread t2 = new Thread(new Counter("----------"));
//		t2.start();		
//
//		Thread t3 = new Thread(new Counter("++++++++++++++++++++++"));
//		t3.start();


//different threads calculate prime number at the same time
//		Thread t4 = new Thread(new Prime("-"));
//		t4.start();
//		
//		Thread t5 = new Thread(new Prime("+"));
//		t5.start();
//		
//		Thread t6 = new Thread(new Prime("~"));
//		t6.start();
		
		
//first println in withdraw method slows the execution so that
//another thread gets time to get into same method so that
//both threads execute one method at the same time
		
//making withdraw method synchronized in BankAccount allows only  
//one thread to run this method
		
//if withdraw is not synchronized any thread may be created and
//executed before the other and thay may get in withdraw method
//at the same time, which can cause bugs- like two people withdraw
//money at the same time and make negative balance in account
//which is unacceptable in this case
		
//so it's like both threads enter withdraw method, pass positive
//balance check one by one and then one by one withdraw money
//leaving negative balance
		
//there is one lock per object, if any thread accesses sychronized
//method, it gets the object lock. Non of synchronized methods can
//be accessed by any other threads while one threads has the 
//object lock
		
//for synchronized static methods there is class lock. If any 
//thread accesses synchronized static method, non other thread can
//access any synchronized methods of this class
//		Thread t7 = new Thread(new Person("Jhonny"), "Jhonny");		
//		Thread t8 = new Thread(new Person("Nicola"), "Nicola");
//		t7.start();
//		t8.start();
		
		
		Thread t9 = new Thread(new Deadlock(), "First");
		Thread t10 = new Thread(new Deadlock(), "Second");
		t9.start();
		t10.start();
				
	}

}
