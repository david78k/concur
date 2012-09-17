package lectures.lec5_memory_models;

public class HappensBefore {

	// shared variables
	int a = 0;
	int b = 0;

	volatile int va = 0;
	volatile int vb = 0;
	
	int x, y;
	
	public static void main(String[] args) {
		HappensBefore hb = new HappensBefore();
		
		hb.start();
		hb.startVolatiles();
	}

	void start() {
		try {
			// Thread 0
			Thread t0 = new Thread(new Runnable0());
			t0.start();
			
			// Thread 1 
			Thread t1 = new Thread(new Runnable1());
			t1.start();
			
			t0.join();
			t1.join();
			
			System.out.println("(x, y) = (" + x + ", " + y + ")");
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void startVolatiles() {
		try {
			// Thread 0
			Thread t0 = new Thread(new Volatile0());
			t0.start();
			
			// Thread 1 
			Thread t1 = new Thread(new Volatile1());
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
//			System.out.println("Thread0");
			
			a = 1;
			x = b;
		}
	}
	
	class Runnable1 implements Runnable {
		public void run() {
//			System.out.println("Thread1");
			
			b = 1;
			y = a;
		}
	}
	
	class Volatile0 implements Runnable {
		public void run() {
//			System.out.println("Thread0");
			
			va = 1;
			x = vb;
		}
	}
	
	class Volatile1 implements Runnable {
		public void run() {
//			System.out.println("Thread1");
			
			vb = 1;
			y = va;
		}
	}

}
