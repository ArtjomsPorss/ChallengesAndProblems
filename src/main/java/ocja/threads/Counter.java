package ocja.threads;

public class Counter implements Runnable{
	private String name = "";
	private static volatile int num;
	private int sum;
	
	public Counter(String name){
		this.name = name;
	}
	@Override
	public void run() {
		for(int i = 0; i < 1000; ++i){
			num++;
			//sum += num;
			System.out.println(num + name);
		}		
	}

}
