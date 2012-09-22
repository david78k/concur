package edu.ufl.cise.cop5618fa12.hw1;

public class PetersonTest implements HW1Test{

//	RunnableThread rt = new RunnableThread(0, arrayLock);
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("done");
	}
	
	class RunnableThread implements Runnable {
		private int ID;
		private HW1Lock lock;
		
		public RunnableThread (int ID, HW1Lock lock) {
			this.ID = ID;
			this.lock = lock;
		}
		
		@Override
		public void run() {
			lock.lock(ID);
			
			// critical section
			System.out.println("ID = " + ID + ", turn = " );
//			System.out.println("ID = " + ID + ", turn = " + turn + ", " + flag[ID]);
			
			lock.unlock(ID);
		}
		
	}

	@Override
	public boolean testLock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		t0 = new Thread(new RunnableThread(0, lock));
		t1 = new Thread(new RunnableThread(1, lock));
		
		t0.start();
		t1.start();
		
		return true;
	}

	@Override
	public boolean testTrylock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return false;
	}

	@Override
	public boolean testLockInterruptibly(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return false;
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
