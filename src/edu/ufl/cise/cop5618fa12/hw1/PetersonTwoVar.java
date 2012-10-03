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

	@Override
	public void lock(int threadID) {
		int other = 1 - threadID;
		
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
		int other = 1 - threadID;
		
		if (threadID == 0) {
			flag0 = true;
			turn = other;

			while (flag1 && turn == other) {
				if(Thread.interrupted()) {
					throw new InterruptedException();
				}
			}
		} else {
			flag1 = true;
			turn = other;
			
			while (flag0 && turn == other) {
				if(Thread.interrupted()) {
					throw new InterruptedException();
				}
			}
		}
	}

	@Override
	public boolean tryLock(int threadID) {
		int other = 1 - threadID;

		if (threadID == 0) {
			flag0 = true;
			turn = other;

			return !(flag1 && turn == other);
		} else {
			flag1 = true;
			turn = other;
			
			return !(flag0 && turn == other);
		}
	}

	@Override
	public void unlock(int threadID) {
		if(threadID == 0) flag0 = false;
		else flag1 = false;
	}

}
