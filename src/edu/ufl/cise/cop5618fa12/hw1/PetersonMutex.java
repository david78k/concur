package edu.ufl.cise.cop5618fa12.hw1;

public class PetersonMutex { 
	int flag0;
	int flag1;
	int turn;
//	boolean done;
	final int numIters = 500000;
	
	class T0 extends Thread {
		public void run() { 
			for (int i =0; i != numIters; i++) {
				flag0 = 1;
				turn = 1;
				while(flag1==1 && turn==1){/*busy wait*/}
				//critical section
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
				flag1 = 0;
			}
		}
	}
	
	public static void main(String[] args) 	{ 
		PetersonMutex obj = new PetersonMutex();
		Thread t0 = obj.new T0();
		Thread t1 = obj.new T1();
		t0.start();t1.start();
		
		System.out.println("Threads started");

		try{ t0.join(); t1.join();}
		catch(InterruptedException e){}

		System.out.println("done");
	}
}