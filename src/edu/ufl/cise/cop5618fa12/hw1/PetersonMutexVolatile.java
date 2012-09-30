package edu.ufl.cise.cop5618fa12.hw1;

public class PetersonMutexVolatile { 
//	volatile int flag0;
//	volatile int flag1;
//	int turn;
	int flag0;
	int flag1;
	volatile int turn;
//	volatile boolean done;
	final int numIters = 10000000;
	int shared1 = 0;
	int shared2 = 0;
	
	class T0 extends Thread {
		public void run() { 
			for (int i =0; i != numIters; i++) {
				flag0 = 1;
				turn = 1;
				while(flag1==1 && turn==1){/*busy wait*/}
				//critical section
				shared1 ++; shared2 ++;
				flag0 = 0;
			}
		}
	}
	
	class T1 extends Thread {
		public void run() { 
			for (int i =0; i != numIters; i++){
				flag1 = 1;
				turn = 0;
				while( flag0==1 && turn==0){/*busy wait*/}
				//critical section
				shared1 ++; shared2 ++;
				flag1 = 0;
			}
		}
	}
	
	public static void main(String[] args) 	{ 
		PetersonMutexVolatile obj = new PetersonMutexVolatile();
		Thread t0 = obj.new T0(); 
		Thread t1 = obj.new T1();
		t0.start();t1.start();
		
		System.out.println("Threads started");
		
		try{ t0.join(); t1.join();}
		catch(InterruptedException e){}
		
		System.out.println("done");
		System.out.println(obj.shared1);
		System.out.println(obj.shared2);
	}
}