package exams;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SP06 Mid
 * use lock and condition variables
 * 
 * @author david78k
 *
 */
public class HoneyPot {

	int h = 0;
	int H = 0;
	ReentrantLock lock = new ReentrantLock();
	Condition notFull = lock.newCondition();
	Condition isFull = lock.newCondition();
	
	public static void main(String[] args) {

	}

	// < await (h < H) h++ >
	void beeAddsHoney () {
		lock.lock();
		
		try {
			while (h >= H) {
				try {
					notFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// CS
			h ++;
			if(h == H) 
				isFull.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	// < await (h == H) h = 0 >
	void bearEatsHoney() {
		lock.lock();
		
		try {
			while (h < H) {
				try {
					isFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// CS
			h = 0;
			notFull.signalAll();
		} finally {
			lock.unlock();
		}
	}
}

