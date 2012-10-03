package org.apache.commons.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

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
	
	public void testLookup() {
        final int ITERATIONS = 50000;
 
        // ArrayList
        ArrayList arrayList = new ArrayList();
        insert(arrayList, ITERATIONS);
        lookup(arrayList, ITERATIONS);
 
        // TreeSet
        TreeSet treeSet = new TreeSet();
        insert(treeSet, ITERATIONS);
        lookup(treeSet, ITERATIONS);
    }
 
    private static void insert(Collection col, int qty){
        long startStamp = System.currentTimeMillis();
 
        for (int i=0; i<qty; i++){
            col.add(RandomStringUtils.randomAlphanumeric(10));
        }
 
        long time = System.currentTimeMillis() - startStamp;
 
        System.out.println("Took " + time + " ms to insert " + qty + " to " + col.getClass().getName());
    }
 
    private static void lookup(Collection col, int qty){
        long startStamp = System.currentTimeMillis();
 
        for (int i=0; i<qty; i++){
            col.contains("searchme");
        }
 
        long time = System.currentTimeMillis() - startStamp;
 
        System.out.println("Took " + time + " ms to perform " + qty + " look-ups using " + col.getClass().getName());
    }
}