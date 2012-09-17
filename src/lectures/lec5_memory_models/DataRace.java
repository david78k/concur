package lectures.lec5_memory_models;

public class DataRace {
	
	// shared variables
	volatile boolean done = false;
//	boolean done = false;
	int x;
	
	public static void main(String[] args) {
		new DataRace().start();
	}

	void start() {
		// Thread 0
		new Thread(new Thread0()).start();
		
		// Thread 1 
		new Thread(new Thread1()).start();
	}

	class Thread0 implements Runnable {
		public void run() {
			System.out.println("Thread0");
			x = f();
			done = true;
			
			System.out.println(done);
		}
	}
	
	class Thread1 implements Runnable {
		public void run() {
			System.out.println("Thread1");
			
			while(!done) {
				/** spin */
//				try {
//					Thread.sleep(1);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
			}
			
			int y = x;
			
			System.out.println(y);
		}
	}
	
	protected int f() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return 100;
	}
}
