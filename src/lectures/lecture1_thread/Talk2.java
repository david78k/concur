package lectures.lecture1_thread;

public class Talk2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread s = new Stutterer("Go");
		Thread t = new Stutterer("Gators");
		s.start();
		t.start();
	}

}
