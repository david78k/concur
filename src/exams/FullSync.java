package exams;

public class FullSync {
	private int x;
	
	public static void main(String[] args) {
		final FullSync s = new FullSync(10);
		
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println(s.inc(1));
			}
		});
		
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.println(s.inc(2));
			}
		});
		
		t1.start();
		t2.start();
	}
	
	FullSync (int init) {
		x = init;
	}
	
	synchronized int inc(int d) {
		x = x + d;
		return x;
	}
}

class UseFullSync {
}