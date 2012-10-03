package edu.ufl.cise.cop5618fa12.hw1;

public class PetersonTest implements HW1Test{

	private Thread t0;
	private Thread t1;
	private int shared0 = 0;	// variable shared between threads
	private int shared1 = 0;	// variable shared between threads
	private final static int iter = 1000000;
	private static enum TYPE {testLock, testTrylock, testLockInterruptibly};
	private final static boolean debug = false;
//	private final static boolean debug = true;
	
	public static void main(String[] args) {

		PetersonTest test = new PetersonTest();
		PetersonArray arrayLock = new PetersonArray();
		PetersonTwoVar twoVarLock = new PetersonTwoVar();
		
		int N0 = iter, N1 = iter;
		
		try {
			test.testLock(arrayLock, N0, N1);
			System.out.println();
			test.testTrylock(arrayLock, N0, N1);
			System.out.println();
			test.testLockInterruptibly(arrayLock, N0, N1);
			System.out.println();
			
			test.testLock(twoVarLock, N0, N1);
			System.out.println();
			test.testTrylock(twoVarLock, N0, N1);
			System.out.println();
			test.testLockInterruptibly(twoVarLock, N0, N1);
			System.out.println();
			
			test.comparePerformanceSingleThread(arrayLock, twoVarLock, N0);
			test.comparePerformanceTwoThread(arrayLock, twoVarLock, N0, N1);
			
		} catch (Exception e) {
//		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("********* TESTS COMPLETE. *********");
	}
	
	@Override
	public boolean testLock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return test(lock, N0, N1, TYPE.testLock);
	}
	
	@Override
	public boolean testTrylock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return test(lock, N0, N1, TYPE.testTrylock);
	}
	
	@Override
	public boolean testLockInterruptibly(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return test(lock, N0, N1, TYPE.testLockInterruptibly);
	}
	
	public boolean test(HW1Lock lock, int N0, int N1, TYPE type) throws InterruptedException {
		int expected = N0 + N1;
		boolean result = false;
		
		try {
			init();
			String name = getMethodName(1);
			name = type.name();

			System.out.print("********* ");
			System.out.print(name + ": " + lock.getClass().getSimpleName());
			System.out.println(" ********* ");

			if (type.equals(TYPE.testLock)) {
				t0 = new Thread(new LockThread(0, lock, N0));
				t1 = new Thread(new LockThread(1, lock, N1));
			} else if (name.equals("testTrylock")) {
				t0 = new Thread(new TryLockThread(0, lock, N0));
				t1 = new Thread(new TryLockThread(1, lock, N1));
			} else if (name.equals("testLockInterruptibly")) {
				t0 = new Thread(new LockInterruptiblyThread(0, lock, N0));
				t1 = new Thread(new LockInterruptiblyThread(1, lock, N1));
			} else { return false; }

			t0.start(); t1.start();
			t0.join(); t1.join();

			result = (shared0 == expected && shared1 == expected);
			if(debug) {
				System.out.println("Expected: (shared0, shared1) = (" + expected + ", " + expected + ")");
				System.out.println("Tested:   (shared0, shared1) = (" + shared0 + ", " + shared1 + ")");
			}
			System.out.println("Result:   " + result);
		} catch (InterruptedException ie) {
			throw ie;
		} catch (Exception e) {
			return false;
		}
		
		return result;
	}

	@Override
	public long comparePerformanceSingleThread(HW1Lock lock1, HW1Lock lock2,
			int N) {
		String name = getMethodName(1);
		System.out.print("********* ");
		System.out.print(name + ": " + lock1.getClass().getSimpleName() + " vs " + lock2.getClass().getSimpleName());
		System.out.println(" ********* ");
		
		init();
	
		//--------------- test lock1 -----------------//
		long startTime = System.nanoTime();
		
		try {
			testLock(lock1, N, 0);
			testTrylock(lock1, N, 0);
			testLockInterruptibly(lock1, N, 0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		long elapsedTime0 = System.nanoTime()-startTime;
		if(debug) 
			System.out.println("ElapsedTime0 = " + elapsedTime0);
		
		//--------------- test lock2 -----------------//
		init();
		
		startTime = System.nanoTime();
		
		try {
			testLock(lock2, N, 0);
			testTrylock(lock2, N, 0);
			testLockInterruptibly(lock2, N, 0);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		long elapsedTime1 = System.nanoTime()-startTime;
		if(debug) {
			System.out.println("ElapsedTime1 = " + elapsedTime1);
//			System.out.println("Tested:   (shared0, shared1) = (" + shared0 + ", " + shared1 + ")");
		}
		
		//-------------- compare elapsed times ----------------//
		long diff = elapsedTime1 - elapsedTime0;
		System.out.println("diff(t1-t0) = " + diff + " nanoseconds");
		System.out.println();
		
		return diff;
	}

	@Override
	public long comparePerformanceTwoThread(HW1Lock lock0, HW1Lock lock1,
			int N0, int N1) throws InterruptedException {
		String name = getMethodName(1);
		System.out.print("********* ");
		System.out.print(name + ": " + lock0.getClass().getSimpleName() + " vs " + lock1.getClass().getSimpleName());
		System.out.println(" ********* ");
		
		//--------------- test lock0 -----------------//
		long startTime = System.nanoTime();
		
		try {
			testLock(lock0, N0, N1);
			testTrylock(lock0, N0, N1);
			testLockInterruptibly(lock0, N0, N1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		long elapsedTime0 = System.nanoTime()-startTime;
//		if(debug) 
			System.out.println("ElapsedTime0 = " + elapsedTime0);
//			System.out.println("Tested:   (shared0, shared1) = (" + shared0 + ", " + shared1 + ")");
		
		//--------------- test lock1 -----------------//
		startTime = System.nanoTime();
		
		try {
			testLock(lock1, N0, N1);
			testTrylock(lock1, N0, N1);
			testLockInterruptibly(lock1, N0, N1);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		long elapsedTime1 = System.nanoTime()-startTime;
//		if(debug) 
			System.out.println("ElapsedTime1 = " + elapsedTime1);
//			System.out.println("Tested:   (shared0, shared1) = (" + shared0 + ", " + shared1 + ")");
		
		//-------------- compare elapsed times ----------------//
		long diff = elapsedTime1 - elapsedTime0;
		System.out.println("diff(t1-t0) = " + diff + " nanoseconds");
		System.out.println();
		
		return diff;
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
//			if(debug)
//				System.out.println(lockThreadClass.getSimpleName() + "-" + ID + " started.");
			
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
					}
				} else { return; }

				try {
					// critical section
					shared0 ++; shared1 ++;
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
	
	public void init() {
		shared0 = 0; shared1 = 0;
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
	
}
