package lectures.lecture2_atomicity;

import java.math.BigInteger;

public class CountingFactorizerServletWithObjectLock extends FactorizerServlet {
	private long count = 0;
	private final static Object lock = new Object();
	
	public long getCount() {
		synchronized (lock) {
			return count;
		}
	}

	public void service (Object req, Object resp) {  
		BigInteger i = extractFromReq(req);
		BigInteger [] factors = factor(i);
		synchronized (lock) {
			count ++;
		}
		encodeIntoResp(resp,factors);
	}
}
