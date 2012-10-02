package edu.ufl.cise.cop5618fa12.hw1;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.collections.FastArrayList;

public class FastArrayListTest {

//	FastArrayList array = new FastArrayList();
	ArrayList<Integer> array = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		FastArrayListTest test = new FastArrayListTest();
		test.start();
	}

	void start() {
//		array.setFast(true);
		
		Thread t1 = new Thread(new R1());
		Thread t2 = new Thread(new R2());
		Thread t3 = new Thread(new R3());
		
		t1.start();
		t2.start();
		t3.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		t3 = new Thread(new R3());
//		t3.start();
		
		System.out.println("rest = " + array.size());
//		System.out.println(array);
	}

	class R1 implements Runnable{
		public void run() {
			for (int i = 0; i < 1000; i++) {
				array.add(i);
//				System.out.println(i + "");
			}
		}
	}
	
	class R2 implements Runnable{
		public void run() {
			for (int i = 1000; i < 2000; i++) {
				array.add(i);
//				System.out.println(i + "");
			}
		}
	}
	class R3 implements Runnable{
		public void run() {
			for (Iterator<Integer> iterator = array.iterator(); iterator.hasNext();) {
				Integer item = (Integer) iterator.next();
				System.out.println(item);
			}
			System.out.println(array);
		}
	}
	
	class R4 implements Runnable{
		public void run() {
			for (int i = 0; i < 1000; i++) {
				array.remove(i + "");
			}
		}
	}
	
}
