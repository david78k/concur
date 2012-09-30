package exams;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * SP07 Mid
 * SP08 Mid
 * SP09 Mid
 * 
 * SP07/SP09 Mid 1)
 * a) (20 points) Give Java code to implement the design as given using explicit locks and conditions. 
 * 				Your code should be (very close to) a valid Java class.
 * b) Extra credit (10 points) Modify the high level design so that all the variables that need to be 
 * 		implemented have bounded values. Give a justification in terms of a strengthened invariant and 
 * 		preconditions for each atomic action.
 * c) (10 points) A thread waiting in the exchanger might be interrupted in order to cancel it. Your 
 * 		solution should handle this properly-after a thread has been cancelled, it should be possible for 
 * 		other threads to continue using the Exchanger object. Briefly explain what you have done. (Good comments are sufficient.)
 * 
 * @author david78k
 * @param <E>
 *
 */

public class Exchanger<E> {

	public static void main(String[] args) {

	}

	//the number of objects that have been inserted in a slot by the first arriving thread in a pair
	private long nafirst;
	//the number of objects that have been presented (and delivered to) the second arriving thread in a
	private long nsecond;
	//the number of objects that have been delivered to the first arriving thread in a pair
	private long ndfirst;
	
	private E first_slot;
	private E second_slot;
	
	ReentrantLock lock = new ReentrantLock();
	Condition cond1 = lock.newCondition();
	Condition cond2 = lock.newCondition();
	
	// For SP07/SP09 mid: use locks and condition variables
	public E exchange(E e) throws InterruptedException {
		// < await (nafirst == ndfirst || nsecond < nafirst)
		lock.lock();
		
		try {
			while (!(nafirst == ndfirst || nsecond < nafirst)) {
				cond1.await();
			}
			
			if (nafirst == ndfirst) {	// empty and first arrives
				nafirst ++;
				first_slot = e;
//				cond1.signal();
			} else { // second arrives
				nsecond ++;
				second_slot = e;
				cond2.signalAll();
				return first_slot;
			} 

		// < await (ndfirst < nsecond)
			while (!(ndfirst < nsecond)) {
				cond2.await();
			}

			// first thread returns
			ndfirst ++;
			cond1.signalAll();
			return second_slot;
		} finally {
			lock.unlock();
		}
		// >
	}
	
	// for SP08 Mid: use synchronized methods/blocks
	public E exchangeSynchronized(E e) throws InterruptedException {
		// < await (nafirst == ndfirst || nsecond < nafirst)
		synchronized (this) {
			while (!(nafirst == ndfirst || nsecond < nafirst)) {
				wait();
			}
			
			if (nafirst == ndfirst) {	// empty and first arrives
				nafirst ++;
				first_slot = e;
				notifyAll();
			} else { // second arrives
				nsecond ++;
				second_slot = e;
				notifyAll();
				return first_slot;
			} 
			
		}
		
		// < await (ndfirst < nsecond)
		synchronized (this) {
			while (!(ndfirst < nsecond)) {
				wait();
			}
			
			// first thread returns
			ndfirst ++;
			return second_slot;
		}
		// >
	}
}
