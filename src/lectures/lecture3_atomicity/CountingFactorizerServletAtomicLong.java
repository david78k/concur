package lectures.lecture3_atomicity;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CountingFactorizerServletAtomicLong extends FactorizerServlet {
//	private long count = 0;
	private AtomicLong count = new AtomicLong(0);
	
	public long getCount() {
		return count.get();
	}

	public void service (Object req, Object resp) {  
		BigInteger i = extractFromReq(req);
		BigInteger [] factors = factor(i);
//		count ++;
		count.incrementAndGet();
		encodeIntoResp(resp,factors);
	}
}
