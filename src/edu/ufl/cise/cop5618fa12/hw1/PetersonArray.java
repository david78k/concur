package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonArray implements HW1Lock {

	//flag[] is boolean array; and turn is an integer
//	volatile boolean flag[] = new boolean[2];
	private final AtomicIntegerArray flag = new AtomicIntegerArray(2);
	private volatile int turn = 0;
//	private static final AtomicInteger turn = new AtomicInteger();
	
	public PetersonArray () {
		flag.set(0, 0);
		flag.set(1, 0);
	}
	
	@Override
	public void lock(int threadID) {
		int other = 1 - threadID;
		
		flag.set(threadID, 1);
		turn = other;
		
		while ((flag.get(other) == 1) && turn == other) {
			/* busy wait */
		}
	}

	@Override
	public void lockInterruptibly(int threadID) throws InterruptedException {
		int other = 1 - threadID;
		
		flag.set(threadID, 1);
		turn = other;
		
		while ((flag.get(other) == 1) && turn == other) {
//			wait();
			if(Thread.interrupted()) {
//				System.out.println("interruped");
				throw new InterruptedException();
//				throw new InterruptedException("interrupted");
			}
		}
	}

	@Override
	public boolean tryLock(int threadID) {
		int other = 1 - threadID;
		
		flag.set(threadID, 1);
		turn = other;
		
		// if locked by other thread
//		if (flag.get(other) == 1 && turn == other) {
//			return false;
//		}
		
		return !(flag.get(other) == 1 && turn == other);
	}

	@Override
	public void unlock(int threadID) {
		flag.set(threadID, 0);
	}

	class AtomicBooleanArray {
		private AtomicIntegerArray array;

		public AtomicBooleanArray(int length) {
			array = new AtomicIntegerArray(length);
		}
		
		boolean get(int i) {
			return array.get(i) == 1;
		}
		
//		void set()
	}
	
}
