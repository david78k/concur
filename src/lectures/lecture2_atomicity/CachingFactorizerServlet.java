package lectures.lecture2_atomicity;

import java.math.BigInteger;

public class CachingFactorizerServlet extends FactorizerServlet {
	private BigInteger lastNumber;
	private BigInteger[] lastFactors;
	
	public void service (Object req, Object resp) {  
		BigInteger i = extractFromReq(req);
		
		if (i.equals(lastNumber)) {
			encodeIntoResp(resp, lastFactors);
		} else {
			BigInteger[] factors = factor(i);
			lastNumber = i;
			lastFactors = factors;
			encodeIntoResp(resp, factors);
		}
	}
}
