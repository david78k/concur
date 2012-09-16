package lectures.lecture4_atomicity_patterns;

@ThreadSafe
public class AccountLockSplitting {
	private static final int MINBALANCE = 0;
	
//	@GuardedBy(personLock)
	protected String name;
//	@GuardedBy(personLock)
	protected String address;
//	@GuardedBy(this)
	protected double balance;
	
	private final Object personLock = new Object();
	
	void updateNameAndAddress (String name, String address) {
		synchronized (personLock) {
			this.name = name;
			this.address = address;
		}
	}
	
	synchronized void deposit(double amount) {
		balance += amount;
	}
	
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
