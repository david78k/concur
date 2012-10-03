package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class PetersonArray implements HW1Lock {

	//flag[] is boolean array; and turn is an integer
	private final AtomicIntegerArray flag = new AtomicIntegerArray(2);
	private volatile int turn = 0;
	
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
			if(Thread.interrupted()) {
				throw new InterruptedException();
			}
		}
	}

	@Override
	public boolean tryLock(int threadID) {
		int other = 1 - threadID;
		
		flag.set(threadID, 1);
		turn = other;
		
		return !(flag.get(other) == 1 && turn == other);
	}

	@Override
	public void unlock(int threadID) {
		flag.set(threadID, 0);
	}

}
