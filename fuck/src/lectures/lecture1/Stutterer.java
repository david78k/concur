package lectures.lecture1;

public class Stutterer {
	static final int ITERS = 100;
	private String m;

	public Stutterer(String m)
	{  
		this.m = m;  
	}

	public void run()
	{	
		for (int i = 0; i != ITERS; i++)
		{  
			System.out.println(m);  
		}
	}
}
