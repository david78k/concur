package exams;

import java.util.concurrent.Semaphore;

public class HoneyPotPassTheBaton {

	int H = 10;
	int h = 0;
	int counter = 0;
	int notFullCounter = 0;
	int isFullCounter = 0;
	
	Semaphore e = new Semaphore(1);	// entry semaphore
	Semaphore isFull = new Semaphore(0);
	Semaphore notFull = new Semaphore(0);
	
	// e.P()
	// if (!B) {counter ++; e.V(); s.P();}
	// Si
	// SIGNAL
	void beeAddsHoney() {
		// if (!B) {counter ++; e.P(); s.V();}
		try {
			e.acquire();
			if (h >= H) {
				notFullCounter ++;	// increase the number of waiting threads for not full
				e.release();
				notFull.acquire();	// blocking
			}
			
			// CS: adds honey
			h ++;
			
			// SIGNAL
			if (h < H && notFullCounter > 0) { // not full and still waiting threads
				notFullCounter --;	// decrease the number of waiting threads
				notFull.release();	// notFull.V()
			} else if (h == H && isFullCounter > 0) { // full and no waiting threads
				isFullCounter --;	// decrease the number of waiting threads for full
				isFull.release();
			} else {
				e.release();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	void bearEatsHoney() throws InterruptedException {
		e.acquire();	// lock lock
		if (h < H) {	
			isFullCounter ++; // I'm waiting
			e.release();	// release lock
			isFull.acquire();
		}
		
		// CS: eats honey
		h = 0;	
		
		// SIGNAL
		if (notFullCounter > 0) {	// if there are waiting threads
			notFullCounter --;		// wake up one
			notFull.release();
		} else e.release();
	}
}
