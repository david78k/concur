package edu.ufl.cise.cop5618fa12.hw1;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.collections.FastArrayList;

public class FastArrayListTest {

	FastArrayList array = new FastArrayList();
//	ArrayList<Integer> array = new ArrayList<Integer>();
	final int iter = 100;
	private boolean done = false;
	
	public static void main(String[] args) {
		FastArrayListTest test = new FastArrayListTest();
		test.start();
	}

	void start() {
		array.setFast(true);
		
		Thread t1 = new Thread(new R1());
		Thread t2 = new Thread(new R2());
		Thread t3 = new Thread(new R3());
		Thread t4 = new Thread(new R4());
		
//		t1.start();
//		t2.start();
		adds();
		t3.start();
		t4.start();
		
		try {
//			t1.join();
//			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("size = " + array.size());
//		System.out.println(array);
	}

	class R1 implements Runnable{
		public void run() {
			for (int i = 0; i < iter; i++) {
				if(done) return;
//				synchronized (array) {
					array.add(i);
//				}
//				System.out.println(i + "");
			}
		}
	}
	
	class R2 implements Runnable{
		public void run() {
			while(!done) {
//				synchronized (array) {
					for (Iterator<Integer> iterator = array.iterator(); iterator.hasNext();) {
						Integer item = (Integer) iterator.next();
//						System.out.println(item);
						if(item == (iter/2)) {
							done = true;
						}
//					}
					if(array.size() == iter) done = true;
				}
			}
			System.out.println(array);
		}
	}
	
	class R3 implements Runnable{
		public void run() {
			for (int i = 0; i < 10; i++) {
				System.out.println(array.get(9));
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	class R4 implements Runnable{
		public void run() {
//			array = new FastArrayList();
			for (int i = 0; i < 10; i++) {
				array.add (1, i + 10);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	void adds() {
		array = new FastArrayList();
		for (int i = 0; i < 10; i++) {
			array.add (i);
		}
	}
}
