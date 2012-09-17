package lectures.lec5_memory_models;

public class HappensBefore {

	// shared variables
	int a = 0;
	int b = 0;

	volatile int va = 0;
	volatile int vb = 0;
	
	int x, y;
	
	public static void main(String[] args) {
		
		int iter = 15;
		for (int i = 0; i < iter ; i++) {
			HappensBefore hb = new HappensBefore();
			hb.start();
			
			hb = new HappensBefore();
			hb.startVolatiles();
			
			System.out.println();
		}
	}
	
	void init() {
		x = 0; y = 0;
		a = 0; b = 0;
		va = 0; vb = 0;
	}

	void start() {
		init();
		try {
			// Thread 0
			Thread t0 = new Thread(new Runnable0());
			
			// Thread 1 
			Thread t1 = new Thread(new Runnable1());
			
			t0.start();
			t1.start();
			
			t0.join();
			t1.join();
			
			System.out.println("(x, y) = (" + x + ", " + y + ")");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void startVolatiles() {
		init();
		
		try {
			// Thread 0
			Thread t0 = new Thread(new Volatile0());
			
			// Thread 1 
			Thread t1 = new Thread(new Volatile1());
			
			t0.start();
			t1.start();
			
			t0.join();
			t1.join();
			
			System.out.println("(x, y) = (" + x + ", " + y + ")");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class Runnable0 implements Runnable {
		public void run() {
			a = 1;
//			sleep(1);
			x = b;
		}
	}
	
	class Runnable1 implements Runnable {
		public void run() {
			b = 1;
//			sleep(1);
			y = a;
		}
	}
	
	class Volatile0 implements Runnable {
		public void run() {
			va = 1;
//			sleep(1);
			x = vb;
		}
	}
	
	class Volatile1 implements Runnable {
		public void run() {
			vb = 1;
//			sleep(1);
			y = va;
		}
	}

	void sleep(long millis) {
		try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
//	void sleep() {
//		sleep(0);
//	}
}
