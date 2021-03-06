package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.atomic.AtomicBoolean;

public class PetersonAtomicBooleanArray implements HW1Lock {

	//flag[] is boolean array; and turn is an integer
//	volatile boolean flag[] = new boolean[2];
//	AtomicBoolean flag[];
//	AtomicIntegerArray flag[];
	private static final AtomicBoolean flag[] = new AtomicBoolean[2];
//	AtomicIntegerArray flag[];
//	private static final AtomicInteger turn = new AtomicInteger();
	private volatile int turn;
	
	public PetersonAtomicBooleanArray () {
		flag[0] = new AtomicBoolean();
		flag[1] = new AtomicBoolean();
//		turn = new AtomicInteger();
	}
	
	@Override
	public void lock(int threadID) {
		int other = 1 - threadID;
		
		flag[threadID].set(true);
		turn = other;
//		turn.set(other);
		
//		while (flag[other].get() && turn.get() == other) {
		while (flag[other].get() && turn == other) {
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}

	@Override
	public void lockInterruptibly(int threadID) throws InterruptedException {
		int other = 1 - threadID;
		
		flag[threadID].set(true);
		turn = other;
//		turn.set(other);
		
//		while (flag[other].get() && turn.get() == other) {
		while (flag[other].get() && turn == other) {
			if(Thread.interrupted()) {
				throw new InterruptedException();
			} 
		}
	}

	@Override
	public boolean tryLock(int threadID) {
		int other = 1 - threadID;
		
		flag[threadID].set(true);
		turn = other;
//		turn.set(other);
		
		return (flag[other].get() && turn == other);
//		return (flag[other].get() && turn.get() == other);
	}

	@Override
	public void unlock(int threadID) {
//		flag[threadID].compareAndSet(true, false);
		flag[threadID].set(false);
//		notifyAll();
	}

}
