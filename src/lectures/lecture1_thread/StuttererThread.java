package lectures.lecture1_thread;

public class StuttererThread extends Thread {
	static final int ITERS = 100;
	private String m;

	public StuttererThread(String m)
	{  
		this.m = m;  
	}

//	@Override
	public void run()
	{	
		for (int i = 0; i != ITERS; i++)
		{  
			System.out.println(m);
//			try {
//				sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
	}
}
