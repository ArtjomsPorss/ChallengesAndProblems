package ocja.threads;

public class Deadlock implements Runnable {
	private static Object a = new Object();
	private static Object b = new Object();
	
	public static void m1(){
		
		synchronized (a) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (b) {
				System.out.println(Thread.currentThread().getName() + " in m1");
			}
		}
	}
	
	public static void m2(){
		
		synchronized (b) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (a) {
				System.out.println(Thread.currentThread().getName() + " in m2");		
			}
		}
	}

	@Override
	public void run() {
		m1();
		m2();
	}
}
