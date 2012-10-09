package exams;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * SP05 Mid (any)
 * SP06 Final (Semaphore): pass the baton
 * 
 * @author david78k
 *
 */
public class FooBarSemaphore {

	public static void main(String[] args) {
		final SubFooBarSemaphore sfb = new SubFooBarSemaphore();
		final Random rand = new Random();
		final int sleepMilliseconds = 1000;
		
		Thread fooThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					sfb.foo();
					try {
						Thread.sleep(rand.nextInt(sleepMilliseconds));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread barThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					sfb.bar();
					try {
						Thread.sleep(rand.nextInt(sleepMilliseconds));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		fooThread.start();
		barThread.start();
	}

	boolean foo() {
		System.out.println("foo");
		return true;
	}
	
	boolean bar() {
		System.out.println("bar");
		return true;
	}
}

class SubFooBarSemaphore extends FooBarSemaphore {
	private boolean fooExecuting = false;
	private boolean barExecuting = false;
	Semaphore e = new Semaphore(1);
	Semaphore foosem = new Semaphore(0);
	Semaphore barsem = new Semaphore(0);
	private int count = 0;

	// balk if bar is executing
	boolean foo() {
		try {
			e.acquire();
			if(barExecuting) return false;
			
			// cs
			fooExecuting = true;
			boolean result = super.foo();
			
			// done
			fooExecuting = false;
			barsem.release();
			
			e.release();
			return result;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// wait if foo is executing
	boolean bar() {
		try {
			e.acquire();
			if (fooExecuting) {
				count  ++; e.release(); barsem.acquire();
			}
			// cs
			barExecuting = true;
			boolean result = super.bar();

			// done
			barExecuting = false;
			foosem.release();
			
			e.release();
			return result;
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return false;
		}
	}
}