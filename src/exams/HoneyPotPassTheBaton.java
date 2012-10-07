package exams;

import java.util.concurrent.Semaphore;

public class HoneyPotPassTheBaton {

	int H = 10;
	int h = 0;
	int counter = 0;
	int notFullCounter = 0;
	int isFullCounter = 0;
	
	Semaphore e = new Semaphore(H);	// entry semaphore
	Semaphore s0 = new Semaphore(H);
	Semaphore s1 = new Semaphore(1);
	
	// e.P()
	// if (!B) {counter ++; e.V(); s.P();}
	// Si
	// SIGNAL
	void beeAddsHoney() {
		try {
			e.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// if (!B) {counter ++; e.P(); s.V();}
		if (h >= H) {
			counter ++;
			e.release();
			try {
				s0.acquire();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		// SIGNAL
	}
	
	void bearEatsHoney() {
		
	}
}
