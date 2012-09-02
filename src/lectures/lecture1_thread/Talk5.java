package lectures.lecture1_thread;

public class Talk5 {
	static final int ITERS = 100;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final String m0 = "GO";
		
		// TODO Auto-generated method stub
		new Thread (new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < ITERS; i++) {
					System.out.println(m0);
				}
			}
			
		}).start();
		
		new Thread (new Runnable() {
			
			String m1 = "Gators";
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for (int i = 0; i < ITERS; i++) {
					System.out.println(m1);
				}
			}
			
		}).start();
	}

}
