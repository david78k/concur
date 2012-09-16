package lectures.lecture4_atomicity_patterns;

@ThreadSafe
public class AccountSeparateClasses {
	private static final int MINBALANCE = 0;
	
	/*
	 * initialized in constructor
	 * remark - final indicates object reference doesn't change
	 * 			but the contents can still change
	 */
	private final Person owner = new Person();
	
	//	@GuardedBy(this)
	private double balance;
	
	class Person {
		//	@GuardedBy((Person)this)
		protected String name;
		//	@GuardedBy((Person)this)
		protected String address;

		synchronized void updateNameAndAddress (String name, String address) {
			this.name = name;
			this.address = address;
		}
	}
	
	void updateNameAndAddress(String name, String address) {
		owner.updateNameAndAddress(name, address);
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
