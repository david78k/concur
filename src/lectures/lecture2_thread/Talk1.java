package lectures.lecture2_thread;

public class Talk1 {
	public static void main(String[] args)
	{	
		Stutterer s = new Stutterer("Go ");
		s.run();
		Stutterer t = new Stutterer("Gators!");
		t.run();

	}
}
