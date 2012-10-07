package lectures.lec9_semaphores;

import java.util.concurrent.Semaphore;

public class PVChunk {
	
	public static void main(String[] args) {
		int N = 10;
		Semaphore s = new Semaphore(N);
		
		try {
			// startRead
			s.acquire();
			
			// endRead
			s.release();
			
			// startWrite
			s.acquire(N);
			
			// endWrite
			s.release(N);
			
			System.out.println(s.drainPermits());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
