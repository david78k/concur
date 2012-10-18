
/**
 * Constantly migrate VMs if any
 * 
 * @author david78k
 *
 */
public class Migrator implements Runnable{
	PM src;
	DestPM dest;
	
	@Override
	public void run() {
		int remainingVMs = src.getTotalVMs();
		while(remainingVMs > 0) {
//			src.migrate(dest, vwnd);
		}
	}
}
