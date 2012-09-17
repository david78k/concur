package lectures.lec5_memory_models;

public class DataRace {
	
	// shared variables
	volatile boolean vdone = false;
	boolean done = false;
	int x, y;
	
	public static void main(String[] args) {
		DataRace dr = new DataRace();
		
		System.out.println("====== Volatile ========");
		dr.startVolatile();
		
		System.out.println();
		
		System.out.println("====== Non-volatile =======");
		dr = new DataRace();
		dr.start();
	}

	void init() {
		done = false;
		vdone = false;
		x = 0; y = 0;
	}
	
	void start() {
		init();
		
		try {
			// Thread 0
			Thread t0 = new Thread(new Thread0());
			
			// Thread 1 
			Thread t1 = new Thread(new Thread1());
			t1.start();
			t0.start();
			
			t1.join();
			t0.join();
			
			System.out.println(done + ", x=" + x + ", y=" + y);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	void startVolatile() {
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

			System.out.println(vdone + ", x=" + x + ", y=" + y);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class Volatile0 implements Runnable {
		public void run() {
//			System.out.println("Thread0");
			x = f();
			vdone = true;
			
//			System.out.println(vdone);
		}
	}
	
	class Volatile1 implements Runnable {
		public void run() {
//			System.out.println("Thread1");
			
			while(!vdone) {
				/** spin */
			}
			
			y = x;
			
//			System.out.println(y);
		}
	}

	class Thread0 implements Runnable {
		public void run() {
//			System.out.println("Thread0");
			x = f();
			done = true;
			
//			System.out.println(done);
		}
	}
	
	class Thread1 implements Runnable {
		public void run() {
//			System.out.println("Thread1");
			
			while(!done) {
				/** spin */
			}
			
			y = x;
			
//			System.out.println(y);
		}
	}
	
	protected int f() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 1;
	}
}
