package lectures.lecture2_thread;

public class Talk4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread (new Stutterer ("Go")).start();
		new Thread (new Stutterer ("Gator")).start();
	}

}
