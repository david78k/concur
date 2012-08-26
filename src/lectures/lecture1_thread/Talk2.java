package lectures.lecture1_thread;

public class Talk2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread s = new StuttererThread("Go");
		Thread t = new StuttererThread("Gators");
		s.start();
		t.start();
	}

}
