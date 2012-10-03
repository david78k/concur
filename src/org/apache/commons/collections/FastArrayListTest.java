package org.apache.commons.collections;

import java.util.ArrayList;
import java.util.Iterator;

public class FastArrayListTest extends Thread{

	public static void main(String argv[]) throws Exception{

		FastArrayList array=new FastArrayList();
		array.add("this");
		array.add("is");
		array.add("a");

		FastArrayListTest tester = new FastArrayListTest(array);
		tester.toStringTest();
		
		array.setFast(true);
		tester.start();
		array.setFast(true);
		Thread.sleep(2000);
		array.add("test");
	}

	protected FastArrayList array;

	public FastArrayListTest(FastArrayList array){
		this.array=array;
	}

	public void run(){
		for(Iterator i=array.iterator();i.hasNext();){
			try{Thread.sleep(1000);}catch(Exception e){}
			System.out.println(i.next());
		}
	}

	public void toStringTest() {
		ArrayList l = new FastArrayList(); 
		l.add(l); 
//		l.toString(); // StackOverflowError 

		l = new ArrayList(); 
		l.add(l); 
		l.toString(); // OK 
	}
}