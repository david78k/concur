package edu.ufl.cise.cop5618fa12.hw1;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Latch {

	int N = 2;
	final CountDownLatch startLatch = new CountDownLatch(1);
	final CountDownLatch endLatch = new CountDownLatch(N);  
	
	public static void main(String[] args) {
		new Latch().start();
	}

	void start() {
		Thread t0 = new Thread(new RunnableLatch());
//		¡¦
		Thread tN = new Thread(new RunnableLatch());
		
		t0.start();
		tN.start();
		
		long startTime = System.nanoTime();
		startLatch.countDown();
		
		try {
			endLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long elapsedTime = System.nanoTime()-startTime;
		System.out.println(elapsedTime + " nanoseconds");
		System.out.println(elapsedTime/1000000.0 + " milliseconds");
//		System.out.println(TimeUnit.NANOSECONDS.toMillis(elapsedTime) + " milliseconds");
		System.out.println(elapsedTime/1000000000.0 + " seconds");
//		System.out.println(TimeUnit.NANOSECONDS.toSeconds(elapsedTime) + " seconds");
	}
	
	class RunnableLatch implements Runnable {
		@Override
		public void run() {
			try{startLatch.await();}
			catch(InterruptedException e){/*ignore*/}
//			do work
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			endLatch.countDown();
		}
	}
}
