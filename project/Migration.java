
/**
 * Live migration simulation
 * 
 * @author david78k
 *
 */
public class Migration {

	public static void main(String[] args) {
		int bandwidth;
		int delay;
		int totalVMs = 40;
		
		WAN wan = new WAN();
		PM src = new PM(totalVMs);
		PM dest = new PM(0);
		
		// start migrate thread
//		Result result = src.migrate(dest, cVMs, cPMs);
		
	}
}
