package edu.ufl.cise.cop5618fa12.hw1;

public class PetersonTest implements HW1Test{

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

	@Override
	public boolean testLock(HW1Lock lock, int N0, int N1)
			throws InterruptedException {
		return false;
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
