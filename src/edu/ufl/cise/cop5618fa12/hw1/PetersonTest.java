package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.CountDownLatch;

public class PetersonTest implements HW1Test{

	private Thread t0;
	private Thread t1;
	private int shared0 = 0;	// variable shared between threads
	private int shared1 = 0;	// variable shared between threads
	private final static int iter = 1000000;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PetersonTest test = new PetersonTest();
		PetersonArray arrayLock = new PetersonArray();
		PetersonTwoVar twoVarLock = new PetersonTwoVar();
		
		int N0 = iter, N1 = iter;
		
		try {
//			test.testLock(arrayLock, N0, N1);
//			test.testTrylock(arrayLock, N0, N1);
//			test.testLockInterruptibly(arrayLock, N0, N1);
			
			test.testLock(twoVarLock, N0, N1);
//			test.testTrylock(twoVarLock, N0, N1);
//			test.testLockInterruptibly(twoVarLock, N0, N1);
//			
//			test.comparePerformanceSingleThread(arrayLock, twoVarLock, N0);
//			test.comparePerformanceTwoThread(arrayLock, twoVarLock, N0, N1);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Tests complete.");
	}
	
	/**
	 * 	0 is getStackTrace(),
	 *	1 is getMethodName(int depth) and
	 *	2 is invoking method.
	 * @param depth
	 * @return
	 */
	public static String getMethodName(final int depth)
	{
	  final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

	  return ste[ste.length - 1 - depth].getMethodName(); 
	}
	
	public boolean test(HW1Lock lock, int N0, int N1) throws InterruptedException {
		try {
			String name = getMethodName(1);

			System.out.print("======== ");
			System.out.print(name + ": " + lock.getClass().getSimpleName());
			System.out.println(" ======== ");

			if (name.equals("testLock")) {
				t0 = new Thread(new LockThread(0, lock, N0));
				t1 = new Thread(new LockThread(1, lock, N1));
			} else if (name.equals("testTrylock")) {
				t0 = new Thread(new TryLockThread(0, lock, N0));
				t1 = new Thread(new TryLockThread(1, lock, N1));
			} else if (name.equals("testLockInterruptibly")) {
				t0 = new Thread(new LockInterruptiblyThread(0, lock, N0));
				t1 = new Thread(new LockInterruptiblyThread(1, lock, N1));
			} else { return false; }

			//		startTest();
			t0.start(); t1.start();
			t0.join(); t1.join();

			System.out.println("(shared0, shared1) = (" + shared0 + ", " + shared1 + ")\n");
		} catch (InterruptedException ie) {
			throw ie;
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean testLock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return test(lock, N0, N1);
	}

	@Override
	public boolean testTrylock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return test(lock, N0, N1);
	}

	@Override
	public boolean testLockInterruptibly(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return test(lock, N0, N1);
	}

	@Override
	public long comparePerformanceSingleThread(HW1Lock lock1, HW1Lock lock2,
			int N) {
		String name = getMethodName(1);
		System.out.print("======== ");
		System.out.print(name + ": " + lock1.getClass().getSimpleName() + " vs " + lock2.getClass().getSimpleName());
		System.out.println(" ======== ");
		
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(N);  
		
//		Thread t0 = new Thread().start();
//		¡¦
//		Thread tN = new Thread().start();
		
		long startTime = System.nanoTime();
		startLatch.countDown();
		
		long elapsedTime = System.nanoTime()-startTime;
		
		System.out.println();
		return 0;
	}

	@Override
	public long comparePerformanceTwoThread(HW1Lock lock0, HW1Lock lock1,
			int N0, int N1) throws InterruptedException {
		String name = getMethodName(1);
		System.out.print("======== ");
		System.out.print(name + ": " + lock0.getClass().getSimpleName() + " vs " + lock1.getClass().getSimpleName());
		System.out.println(" ======== ");
		
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(N0);  
		
//		Thread t0 = new Thread().start();
//		¡¦
//		Thread tN = new Thread().start();
		
		long startTime = System.nanoTime();
		startLatch.countDown();
		
//		try{startLatch.await();}
//		catch(InterruptedException e){/*ignore*/}
		
//		try {
//			endLatch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		
		long elapsedTime = System.nanoTime()-startTime;

		System.out.println();
		return 0;
	}
	
	class LockThread implements Runnable {
		private int N = 0;	// parameter such as number of critical sections
		protected int ID;	// thread ID
		protected final HW1Lock lock;
		
		public LockThread (int ID, HW1Lock lock, int N) {
			this.ID = ID;
			this.lock = lock;
			this.N = N;
		}
		
		@Override
		public void run() {
			Class<? extends LockThread> lockThreadClass = this.getClass();
			System.out.println(lockThreadClass.getSimpleName() + "-" + ID);
			
//			if(lock instanceof PetersonArray) {
			for (int i = 0; i < N; i++) {
				if(lockThreadClass.equals(LockThread.class)) {
					lock.lock(ID);
				} else if (lockThreadClass.equals(TryLockThread.class)) {
					while(!lock.tryLock(ID)) { /* try again */ }
				} else if (lockThreadClass.equals(LockInterruptiblyThread.class)) {
					try {
						lock.lockInterruptibly(ID);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
//						e.printStackTrace();
					}
				} else { return; }

				try {
					// critical section
					shared0 ++; shared1 ++;
//					System.out.println("ID = " + ID + ", shared = "	+ (shared));
				} finally {
					lock.unlock(ID);
				}
			}
		}
		
	}
	
	class TryLockThread extends LockThread {
		
		public TryLockThread (int ID, HW1Lock lock, int N) {
			super(ID, lock, N);
		}
	}
	
	class LockInterruptiblyThread extends LockThread {
		
		public LockInterruptiblyThread (int ID, HW1Lock lock, int N) {
			super(ID, lock, N);
		}
	}
}
