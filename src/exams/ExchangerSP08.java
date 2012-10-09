package exams;

/**
 * synchronized methods/blocks
 * 
 * states
 * - 0: empty
 * - 1: first arrives
 * - 2: second arrives and returns 
 * 
 * @author david78k
 * @param <E>
 *
 */
public class ExchangerSP08<E> {

	private int state;
//	private long nfirst; 
//	private long nsecond; 
//	private long ndfirst; 
	private E first_slot;
	private E second_slot;
	
	public synchronized E exchange (E e) throws InterruptedException {
		while(state == 2) { 
			wait(); 
		}
		if(state == 0) {
//			nfirst ++;
			state = 1;
			first_slot = e;
		} else {
//			nsecond ++;
			state = 2;
			second_slot = e;
			notifyAll();
			return first_slot;
		}
		
		while (state != 2) {
			wait();
		}
//		ndfirst ++;
		state = 0;
		notifyAll();
		return second_slot;
	}
}

