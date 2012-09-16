package lectures.lecture4_atomicity_patterns;

/*
 * Identify stateful and stateless part
 */
@ThreadSafe
public class Account {
	private static final int MINBALANCE = 0;
	
//	@GuardedBy(this)
	String name;
//	@GuardedBy(this)
	String address;
//	@GuardedBy(this)
	double balance;
	
	double computeFeeAndDeduct(double param) {
		double fee = computeFee(param);	// stateless part
		
		// stateful part
		synchronized (this) {
			if (balance < MINBALANCE) {
				balance = balance - fee; // deduct fee from balance
			}
		}
		
		return fee;
	}

	private double computeFee(double balance) {
		return balance;
	}
	
	double getProjectedBalance(double rate) {
		double b, projected_balance; // local vars
		
		synchronized (this) {
			b = balance;
		}
		
		projected_balance = f(b);
		
		return projected_balance;
	}

	private double f(double b) {
		return 0;
	}
}
