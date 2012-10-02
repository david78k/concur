package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.CountDownLatch;

public class PetersonTest implements HW1Test{

	private Thread t0;
	private Thread t1;
	private int shared;	// variable shared between threads
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PetersonTest test = new PetersonTest();
		PetersonArray arrayLock = new PetersonArray();
		PetersonTwoVar twoVarLock = new PetersonTwoVar();
		
		int N0 = 1, N1 = 1;
		
		try {
			test.testLock(arrayLock, N0, N1);
			test.testTrylock(arrayLock, N0, N1);
			test.testLockInterruptibly(arrayLock, N0, N1);
			
//			test.testLock(twoVarLock, N0, N1);
//			test.testTrylock(twoVarLock, N0, N1);
//			test.testLockInterruptibly(twoVarLock, N0, N1);
//			
//			test.comparePerformanceSingleThread(arrayLock, twoVarLock, N0);
//			test.comparePerformanceTwoThread(arrayLock, twoVarLock, N0, N1);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
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
			System.out.print(name + ", " + lock.getClass().getSimpleName());
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

			System.out.println();
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
		
//		return true;
//		return false;
	}

	@Override
	public boolean testTrylock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return test(lock, N0, N1);
//		return true;
	}

	@Override
	public boolean testLockInterruptibly(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return test(lock, N0, N1);
	}

	@Override
	public long comparePerformanceSingleThread(HW1Lock lock1, HW1Lock lock2,
			int N) {
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch endLatch = new CountDownLatch(N);  
		
//		Thread t0 = new Thread().start();
//		¡¦
//		Thread tN = new Thread().start();
		
		long startTime = System.nanoTime();
		startLatch.countDown();
		
//		try{startLatch.await();}
//		catch(InterruptedException e){/*ignore*/}
		
		try {
			endLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long elapsedTime = System.nanoTime()-startTime;
		return 0;
	}

	@Override
	public long comparePerformanceTwoThread(HW1Lock lock0, HW1Lock lock1,
			int N0, int N1) throws InterruptedException {

		return 0;
	}
	
	class LockThread implements Runnable {
		private int N = 0;	// parameter such as number of critical sections
		protected int ID;	// thread ID
		protected final HW1Lock lock;
		
		public LockThread (int ID, HW1Lock lock) {
			this.ID = ID;
			this.lock = lock;
		}
		
		public LockThread (int ID, HW1Lock lock, int N) {
			this.ID = ID;
			this.lock = lock;
			this.N = N;
		}
		
		@Override
		public void run() {
			String className = this.getClass().getSimpleName();
			System.out.println(className);
			
//			if(lock instanceof PetersonArray) {
			for (int i = 0; i < N; i++) {
				if (className.equals("LockThread")) {
					lock.lock(ID);
				} else if (className.equals("TryLockThread")) {
					lock.tryLock(ID);
				} else if (className.equals("LockInterruptiblyThread")) {
					try {
						lock.lockInterruptibly(ID);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

				try {
					// critical section
					System.out.println("ID = " + ID + ", shared = "	+ (++ shared));
					//			System.out.println("ID = " + ID + ", turn = " + turn + ", " + flag[ID]);
				} finally {
					lock.unlock(ID);
				}
			}
		}
		
	}
	
	class TryLockThread extends LockThread {
		
		public TryLockThread (int ID, HW1Lock lock) {
			super(ID, lock);
		}
		
		public TryLockThread (int ID, HW1Lock lock, int N) {
			super(ID, lock, N);
		}
	}
	
	class LockInterruptiblyThread extends LockThread {
		
		public LockInterruptiblyThread (int ID, HW1Lock lock) {
			super(ID, lock);
		}
		
		public LockInterruptiblyThread (int ID, HW1Lock lock, int N) {
			super(ID, lock, N);
		}
	}
}
