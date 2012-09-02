package lectures.lecture2_atomicity;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CountingFactorizerServletWithReentrantLock extends FactorizerServlet {
	private long count = 0; // guarded by lock
	private Lock lock = new ReentrantLock();
	
	public long getCount() {
		lock.lock();
		try {
			return count;
		} finally {
			lock.unlock();
		}
	}

	public void service (Object req, Object resp) {  
		BigInteger i = extractFromReq(req);
		BigInteger [] factors = factor(i);
		count ++;
		encodeIntoResp(resp,factors);
	}
}
