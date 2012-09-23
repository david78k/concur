package lectures.lec6_cancellation;

/**
 * 1. unrestricted asynchronous cancellation: stop() method is deprecated
 * 2. polling: while(flag)
 * 3. interrupt
 * 
 * @author david78k
 *
 */
public class Loop implements Runnable {

	private volatile boolean flag = true;
	private Thread t;
	
	public void start() {
		t = new Thread(this);
		t.start();
	}
	
	public void stop() {
		t.interrupt();
	}
	
	public void stopPolling() {
		flag = false;
	}
	
	@Override
	public void run() {
		// interrupt
		while (!Thread.interrupted()) {
			  
		}
	}
	
	public void run2() {
//		Thread thisThread = Thread.currentThread();
		
		// interrupt
		while (true) {
			if(Thread.interrupted()) {
				throw new RuntimeException("Thread interrupted");
			} else {
				
			}
		}
	}
	
	public void run3() {
		// interrupt
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// clean up and cancel
				throw new RuntimeException("Thread interrupted");
			}
		}
	}
	
	public void runPolling() {
		// polling
		while (flag) {
			
		}
	}

}
