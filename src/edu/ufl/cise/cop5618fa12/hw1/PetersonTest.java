package edu.ufl.cise.cop5618fa12.hw1;

public class PetersonTest implements HW1Test{

	private Thread t0;
	private Thread t1;
	
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
			
			test.testLock(twoVarLock, N0, N1);
			test.testTrylock(twoVarLock, N0, N1);
			test.testLockInterruptibly(twoVarLock, N0, N1);
			
			test.comparePerformanceSingleThread(arrayLock, twoVarLock, N0);
			test.comparePerformanceTwoThread(arrayLock, twoVarLock, N0, N1);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}
	
	class LockThread implements Runnable {
		protected int ID;
		protected final HW1Lock lock;
		
		public LockThread (int ID, HW1Lock lock) {
			this.ID = ID;
			this.lock = lock;
		}
		
		@Override
		public void run() {
//			if(lock instanceof PetersonArray) {
			lock.lock(ID);
			
			try {
				// critical section
				System.out.println("ID = " + ID + ", turn = " );
				//			System.out.println("ID = " + ID + ", turn = " + turn + ", " + flag[ID]);
			} finally {
				lock.unlock(ID);
			}
		}
		
	}
	
	class TryLockThread extends LockThread {
		
		public TryLockThread (int ID, HW1Lock lock) {
			super(ID, lock);
		}
		
		@Override
		public void run() {
			if(!lock.tryLock(ID)) {
				
			} else {
				try {
					// critical section
					System.out.println("ID = " + ID + ", turn = " );
					//			System.out.println("ID = " + ID + ", turn = " + turn + ", " + flag[ID]);
				} finally {
					lock.unlock(ID);
				}
			}
		}
		
	}
	
	class LockInterruptiblyThread extends LockThread {
		
		public LockInterruptiblyThread (int ID, HW1Lock lock) {
			super(ID, lock);
		}
		
		@Override
		public void run() {
			try {
				lock.lockInterruptibly(ID);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				// critical section
				System.out.println("ID = " + ID + ", turn = " );
				//			System.out.println("ID = " + ID + ", turn = " + turn + ", " + flag[ID]);
			} finally {
				lock.unlock(ID);
			}
		}
		
	}

	@Override
	public boolean testLock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		t0 = new Thread(new LockThread(0, lock));
		t1 = new Thread(new LockThread(1, lock));
		
		t0.start();
		t1.start();
		
		return true;
	}

	@Override
	public boolean testTrylock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		t0 = new Thread(new TryLockThread(0, lock));
		t1 = new Thread(new TryLockThread(1, lock));
		
		t0.start();
		t1.start();
		
		return true;
	}

	@Override
	public boolean testLockInterruptibly(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		t0 = new Thread(new LockInterruptiblyThread(0, lock));
		t1 = new Thread(new LockInterruptiblyThread(1, lock));
		
		t0.start();
		t1.start();
		
		return true;
//		return false;
	}

	@Override
	public long comparePerformanceSingleThread(HW1Lock lock1, HW1Lock lock2,
			int N) {
		return 0;
	}

	@Override
	public long comparePerformanceTwoThread(HW1Lock lock0, HW1Lock lock1,
			int N0, int N1) throws InterruptedException {
		return 0;
	}

}
