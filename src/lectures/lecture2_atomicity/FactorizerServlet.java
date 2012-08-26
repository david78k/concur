package lectures.lecture2_atomicity;

import java.math.BigInteger;

public class FactorizerServlet implements Servlet {
	
	public void service (ServletRequest req, ServletResponse resp) {  
		BigInteger i = extractFromReq(req);
		BigInteger [] factors = factor(i);
		encodeIntoRes(resp,factors);
	}

}
