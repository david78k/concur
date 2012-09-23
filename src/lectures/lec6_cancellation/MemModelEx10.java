package lectures.lec6_cancellation;

public class MemModelEx10 {
	
	static int x, y;
	static Thread main;

	public static void main(String[] args) {
		x = 10;
		y = 20;
		main = Thread.currentThread();
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int lx = x;
				int ly = y;
				int sum = lx + ly;
				x = sum;
				System.out.println("t: " + x);
				main.interrupt();
			}
		});
		
		t.start();
		
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			System.out.println("main: " + x);
		}
	}

}
