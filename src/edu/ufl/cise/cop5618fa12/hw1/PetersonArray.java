package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PetersonArray implements HW1Lock {

//	private static int ID;
	
	//flag[] is boolean array; and turn is an integer
//	volatile boolean flag[] = new boolean[2];
	private static final AtomicBoolean flag[] = new AtomicBoolean[2];
//	AtomicIntegerArray flag[];
	private static final AtomicInteger turn = new AtomicInteger();
//	static int turn;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		PetersonArray arr = new PetersonArray();
//		arr.start();
		
		PetersonArray lock = new PetersonArray();
		
//		Thread t0 = new Thread(new PetersonThread(0));
		Thread t1 = new Thread();
		lock.lock(0);
		
	}

	class PetersonThread implements Runnable {
		private int ID;
		
		public PetersonThread (int ID) {
			this.ID = ID;
		}
		
		@Override
		public void run() {
			lock(ID);
			
			// critical section
			System.out.println("ID = " + ID + ", turn = " + turn + ", " + flag[ID]);
			
			unlock(ID);
		}
		
	}
	
	class RunnableThread implements Runnable {
		private int ID;
		
		public RunnableThread (int ID) {
			this.ID = ID;
		}
		
		@Override
		public void run() {
			lock(ID);
			
			// critical section
			System.out.println("ID = " + ID + ", turn = " + turn + ", " + flag[ID]);
			
			unlock(ID);
		}
		
	}
	
	public void start() {
		Thread t0 = new Thread(new RunnableThread(0));
		Thread t1 = new Thread(new RunnableThread(1));
		
		t0.start();
		t1.start();
		
		System.out.println("done");
	}
	
	public PetersonArray () {
		flag[0] = new AtomicBoolean();
		flag[1] = new AtomicBoolean();
//		turn = new AtomicInteger();
	}
	
	@Override
	public void lock(int threadID) {
		int other = 1 - threadID;
		
		flag[threadID].set(true);
		turn.set(other);
		
		while (flag[other].get() && turn.get() == other) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}

	@Override
	public void lockInterruptibly(int threadID) throws InterruptedException {
	}

	@Override
	public boolean tryLock(int threadID) {
		return false;
	}

	@Override
	public void unlock(int threadID) {
//		flag[threadID].compareAndSet(true, false);
		flag[threadID].set(false);
//		notifyAll();
	}

}
