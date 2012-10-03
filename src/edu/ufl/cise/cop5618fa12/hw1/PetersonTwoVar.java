package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.atomic.AtomicBoolean;

public class PetersonTwoVar implements HW1Lock {

	//flag[] is boolean array; and turn is an integer
//	private AtomicBoolean flag1;
//	private AtomicBoolean flag2;
//	private volatile boolean flag0 = false;
//	private volatile boolean flag1 = false;
	private boolean flag0 = false;
	private boolean flag1 = false;
	private volatile int turn = 0;
//	private int turn = 0;

	@Override
	public void lock(int threadID) {
		int other = 1 - threadID;

//		flag.set(threadID, 1);
		if (threadID == 0) {
			flag0 = true;
			turn = other;

			while (flag1 && turn == other) {
				/* busy wait */
			}
		} else {
			flag1 = true;
			turn = other;
			
			while (flag0 && turn == other) {
				/* busy wait */
			}
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
		if(threadID == 0) flag0 = false;
		else flag1 = false;
	}

}
