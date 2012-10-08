package exams;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SP09 Final
 * 
 * @author david78k
 *
 */
public class Philosopher implements Runnable{

	private static final int NUM_PHILS = 5;
	int id; // 0 <= id <= NUM_PHILS - 1
	static boolean chopsticks[] = new boolean[NUM_PHILS];
	final static int sleep_milliseconds=1000;
	static ReentrantLock lock = new ReentrantLock();
	static Condition[] cond = new Condition[NUM_PHILS];
	Random rand = new Random();
	
	public static void main(String[] args) {
//		Philosopher[] phils = new Philosopher[NUM_PHILS];
		for (int i = 0; i < NUM_PHILS; i++) {
			cond[i] = lock.newCondition();
			new Thread(new Philosopher(i)).start();
		}
	}
	
	Philosopher(int id) {
		this.id = id;
	}
	
	public void run() {

		try {
			while (true) {
				// think
				Thread.sleep(rand.nextInt(sleep_milliseconds));

				// pick up chopsticks and eat
//				if(id < (id+1)%NUM_PHILS) {
//					thinkAndEat();
//				} 
				synchronized (this){
					while(chopsticks[id] || chopsticks[(id+1)%NUM_PHILS]) {
						wait();
//						cond[id].await();
					}
					chopsticks[id] = true;
					chopsticks[(id+1)%NUM_PHILS] = true;
					
					System.out.println(id + " is eating");
					Thread.sleep(rand.nextInt(sleep_milliseconds));
					
					// put the chopsticks back on the table
					chopsticks[id] = false;
					chopsticks[(id+1)%NUM_PHILS] = false;
					
					notifyAll();
//					cond[id].signalAll();
				}
			}
		} catch (InterruptedException e) {
			// ignore
		}
	}
	
	private synchronized void thinkAndEat() throws InterruptedException {
//		while(true) {
			if(chopsticks[id])
				wait();
			if(chopsticks[(id+1)%NUM_PHILS]) 
				wait();
			else
				return;
//		}
//		while(chopsticks[id] || chopsticks[(id+1)%NUM_PHILS]) {
//			wait();
//		}
		chopsticks[id] = true;
		chopsticks[(id+1)%NUM_PHILS] = true;

		System.out.println(id + " is eating");
		Thread.sleep(rand.nextInt(sleep_milliseconds));

		// put the chopsticks back on the table
		chopsticks[id] = false;
		chopsticks[(id+1)%NUM_PHILS] = false;

		notifyAll();
	}
}
