package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.atomic.AtomicBoolean;

public class PetersonAtomicBooleanArray implements HW1Lock {

	//flag[] is boolean array; and turn is an integer
	volatile boolean flag[] = new boolean[2];
//	AtomicBoolean flag[];
//	AtomicIntegerArray flag[];
	int turn;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

	public PetersonAtomicBooleanArray () {
		flag[0] = false;
		flag[1] = false;
	}
	
	@Override
	public void lock(int threadID) {
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
		notifyAll();
	}

}
