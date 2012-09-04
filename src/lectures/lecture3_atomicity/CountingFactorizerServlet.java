package lectures.lecture3_atomicity;

import java.math.BigInteger;

public class CountingFactorizerServlet extends FactorizerServlet {
	private long count = 0;
	
	public long getCount() {
		return count;
	}

	public void service (Object req, Object resp) {  
		BigInteger i = extractFromReq(req);
		BigInteger [] factors = factor(i);
		count ++;
		encodeIntoResp(resp,factors);
	}
}
