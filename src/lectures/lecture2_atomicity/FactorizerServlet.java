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
		encodeIntoResp(resp,factors);
	}

	public void encodeIntoResp(Object resp, BigInteger[] factors) {
	}

	public BigInteger[] factor(BigInteger i) {
		return null;
	}

	public BigInteger extractFromReq(Object req) {
		return null;
	}

}
