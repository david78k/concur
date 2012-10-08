package lectures.lec10_servers_executors_futures;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class BeeperControl {

	public static void main(String[] args) {
		new BeeperControl().beepForAnHour();

	}

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	void beepForAnHour () {
		Runnable beeper = new Runnable() {
			@Override
			public void run() {
				System.out.println("beep");
			}
		};
		
		final ScheduledFuture<?> beeperHandler = scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
		
		scheduler.schedule(new Runnable() {

			public void run() {
				beeperHandler.cancel(true);
			}
			
		}, 6 * 6, TimeUnit.SECONDS);
	}
	
}
