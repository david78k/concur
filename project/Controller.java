
public class Controller implements Runnable{
	int vwnd = 1;
	double total_bandwidth;
	double avg_bandwidth;
	private PM src;
	private PM dest;
	Migrator migrator;
	
	Controller(PM src, PM dest) {
		this.src = src;
		this.dest = dest;
	}
	
	@Override
	public void run() {
		int remainingVMs = src.getTotalVMs();
		
		Thread mThread = new Thread(migrator);
		mThread.start();
		
//		while(remainingVMs) {
			
//		}
	}
	
	/**
	 * Constantly migrate VMs if any
	 * 
	 * @author david78k
	 *
	 */
	public class Migrator implements Runnable{
		PM src;
		PM dest;
		
		@Override
		public void run() {
			int remainingVMs = src.getTotalVMs();
			while(remainingVMs > 0) {
				src.migrate(dest, vwnd);
			}
		}
	}
}
