package ocja.threads;

public class Prime implements Runnable{
	private String name;
	
	private long num = 1;
	
	Prime(String name){
		this.name = name;
	}

	@Override
	public void run() {
		while(true){
			for(long div = 1; div <= num; ++div){
				if(num % div == 0){
					if(div == 1){
						continue;
					}else if(div == num){
						System.out.println(num + name);
						num++;
						div = 0;
					}else{
						num++;
						div = 0;
					}
				}
			}
		++num;
		}
		
		//if number is divisible by 1 and itself only - it's a prime number
		//if number is divisible by 
	}
	
	
}
