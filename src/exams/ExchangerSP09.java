package exams;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock and condition with buf
 * 
 * @author david78k
 *
 */
//(n1=n2 /\ n2=n1r) //exchanger is empty
//\/ (n1 = n2+1 /\ n2 = n1r) //first thread has arrived and is waiting for second. Its item is in buff
//\/ (n1 = n2 /\ n2=n1r+1) //second thread has arrived and returned, first thread still in exchanger.
////Second thread¡¯s item is in buff.
///\ (n1=n2 /\ n2=n1r) => state = 0
///\ (n1 = n2+1 /\ n2 = n1r) => state = 1
///\ (n1 = n2 /\ n2=n1r+1) => state = 2
public class ExchangerSP09<E> {
	private int n1=0, n2=0;
	private int n1r = 0;
	private int state = 0;
	private E buff;
	ReentrantLock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	public E exchange(E e) throws InterruptedException {
		lock.lock();
		assert inv();
		try{
			//while (!((n1==n2 && n2==n1r) ||
			// (n1 == n2+1 && n2 == n1r))) {c2.await();} 
			while (state==2) {c2.await();}
			//if (n1 == n2+1 && n2 == n1r)
			if(state==1) { //second thread
				E tmp = buff;
				buff = e; n2++; state = 2;
				c1.signalAll();
				return tmp;
			}
			//if control is here, this is the first thread
			assert inv();
			n1++; state = 1;
			buff=e;
			//while (!(n1 == n2 && n2==n1r+1))
			while (state != 2) {
				try {c1.await(); }
				catch(InterruptedException ie) {
					if (state != 2) {n1--; state = 0;}//this is the first thread, restore the state
					throw new InterruptedException(); //rethrow exception
				}
			}
			n1r++; state = 0;
			c2.signalAll();
			return buff;
		}
		finally{ assert inv(); lock.unlock();}
	}
	private boolean inv() {
		return false;
	}
}