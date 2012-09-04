package lectures.lecture3_atomicity;

import java.math.BigInteger;

public class CachingFactorizerServletSynchronized extends FactorizerServlet {
	private BigInteger lastNumber;
	private BigInteger[] lastFactors;
	
	public void service (Object req, Object resp) {  
		BigInteger i = extractFromReq(req);

		BigInteger[] factors = null;	// stateless

		synchronized (this) {
			if (i.equals(lastNumber)) {
				factors = lastFactors;
				encodeIntoResp(resp, factors);
			} 
		}

		if (factors == null) {
			factors = factor(i);	// stateless
			synchronized (this) {
				lastNumber = i;
				lastFactors = factors;
				encodeIntoResp(resp, factors);
			}
		}
	}
}
