package exams;

import java.util.Random;

/**
 * SP05 Mid (any)
 * SP06 Final (Semaphore)
 * 
 * @author david78k
 *
 */
public class FooBar {

	public static void main(String[] args) {
		final SubFooBar sfb = new SubFooBar();
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

class SubFooBar extends FooBar {
	private boolean fooExecuting = false;
	private boolean barExecuting = false;

	// balk if bar is executing
	synchronized boolean foo() {
		if(barExecuting) return false;
		
		// cs
		fooExecuting = true;
		boolean result = super.foo();
		
		// done
		fooExecuting = false;
//		notifyAll();
		
		return result;
	}
	
	// wait if foo is executing
	synchronized boolean bar() {
		while (fooExecuting) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
				return false;
			}
		}
		// cs
		barExecuting = true;
		boolean result = super.bar();
		
		// done
		barExecuting = false;
//		notifyAll();
		
		return result;
	}
}