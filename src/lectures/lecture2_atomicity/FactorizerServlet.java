package lectures.lecture2_atomicity;

import java.math.BigInteger;

import javax.servlet.Servlet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FactorizerServlet implements Servlet {
	
	public void service (ServletRequest req, ServletResponse resp) {  
		BigInteger i = extractFromReq(req);
		BigInteger [] factors = factor(i);
		encodeIntoRes(resp,factors);
	}

}
