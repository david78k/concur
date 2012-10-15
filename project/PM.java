
/**
 * Physical Machine
 * @author david78k
 *
 */
public class PM {
	int totalVMs;
	int memory;
	int cpu; // number of cores?
	
	PM(int totalVMs) {
		this.totalVMs = totalVMs;
	}
	
	public int getTotalVMs() {
		return totalVMs;
	}

	public void migrate(PM dest, int vwnd) {
//		memory -= bandwidth*time;
		// if complete 
		totalVMs --;
		dest.totalVMs ++;
	}

}
