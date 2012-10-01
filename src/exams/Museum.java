package exams;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SP05 Final (diff)
 * FA11 Mid: lock and condition variables
 * 
 * @author david78k
 *
 */
public class Museum {

	public static void main(String[] args) {

	}

	private long g1;
	private long g2;
	ReentrantLock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	
	void enter1() throws InterruptedException {
		// < await ((g1 + g2) < 200 && ((g1 + g2) < 5 || (g1 + g2 - 1) * 0.4 <= g2))); g1 ++ >
		lock.lock();
		
		try {
			while (!((g1 + g2) < 200 && ((g1 + g2) < 5 || (g1 + g2 - 1) * 0.4 <= g2))) {
				c1.await();
			}
			g1 ++;
			if ((g1 + g2) < 5 || (g1 + g2) * .4 <= g1)
				c2.notifyAll();
		} finally {
			lock.unlock();
		}
	}
	
	void exit1() throws InterruptedException {
		// < g1 -- >
		lock.lock();
		try {
			g1 --;
			c2.notifyAll();
			c1.notifyAll();
		} finally {
			lock.unlock();
		}
	}
	
	void enter2() throws InterruptedException {
		// < await ((g1 + g2) < 200 && ((g1 + g2) < 5 || (g1 + g2 - 1) * 0.4 <= g2))); g2 ++ >
		lock.lock();

		try {
			while (!((g1 + g2) < 200 && ((g1 + g2) < 5 || (g1 + g2 - 1) * 0.4 <= g1))) {
				c2.await();
			}
			g2 ++;
			if ((g1 + g2) < 5 || (g1 + g2) * .4 <= g2)
				c1.notifyAll();
		} finally {
			lock.unlock();
		}
	}
	
	void exit2() {
		// < g2 -- >
		lock.lock();
		try {
			g2 --;
			c1.notifyAll();
			c2.notifyAll();
		} finally {
			lock.unlock();
		}
	}
}
