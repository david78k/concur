package exams;

import java.util.concurrent.Semaphore;

/**
 * PV/Chunk
 * 
 * @author david78k
 *
 */
public class HoneyPotSemaphore {

	int H = 10;
	int h = 0;
	
	Semaphore s0 = new Semaphore(H);	// rest to fill
	Semaphore s1 = new Semaphore(1);	// 
	
	void beeAddsHoney() {
		try {
			s0.acquire(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// adds honey
		h ++;
		s1.release(0);
	}
	
	void bearEatsHoney() {
		try {
			s1.acquire(H);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// eats honey
		h = 0;
		s0.release(H);
	}
}
