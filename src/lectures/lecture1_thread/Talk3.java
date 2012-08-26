package lectures.lecture1_thread;

public class Talk3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			Runnable s = new Stutterer("Go");
			Runnable t = new Stutterer("Gators");
			
			Thread s_thread = new Thread(s);
			Thread t_thread = new Thread(t);
			
			s_thread.start();
			t_thread.start();
	}
}
