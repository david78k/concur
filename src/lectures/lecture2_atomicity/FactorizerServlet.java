package lectures.lecture2_atomicity;

import java.math.BigInteger;

//import javax.servlet.Servlet;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;

//public class FactorizerServlet implements Servlet {
public class FactorizerServlet {
	
//	public void service (ServletRequest req, ServletResponse resp) {  
	public void service (Object req, Object resp) {  
		BigInteger i = extractFromReq(req);
		BigInteger [] factors = factor(i);
		encodeIntoRes(resp,factors);
	}

	private void encodeIntoRes(Object resp, BigInteger[] factors) {
	}

	private BigInteger[] factor(BigInteger i) {
		return null;
	}

	private BigInteger extractFromReq(Object req) {
		return null;
	}

}
