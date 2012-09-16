package lectures.lecture4_atomicity_patterns;
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
		double fee = computeFee(param);
		
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
		return 0;
	}
}
