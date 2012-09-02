package lectures.lecture2_atomicity;

import java.math.BigInteger;

public class CountingFactorizerServletSynchronized extends FactorizerServlet {
	private long count = 0;
	
	public synchronized long getCount() {
		return count;
	}

	public void service (Object req, Object resp) {  
		BigInteger i = extractFromReq(req);
		BigInteger [] factors = factor(i);
		synchronized (this) {
			count ++;
		}
		encodeIntoResp(resp,factors);
	}
}
