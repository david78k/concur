package lectures.lec10_servers_executors_futures;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ActiveSearcher {
	interface Searcher {
		String search(String text);
	}
	
	Searcher searcher;
	ExecutorService executor;
	
	public static void main(String[] args) {
//		ActiveSearcher searcher = new ActiveSearcher();
		
	}
	
	void showSearch (final String text) throws InterruptedException {
		Future<String> future = executor.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return searcher.search(text);
			}
		});
		
		doOtherThings();
		
		try {
			String result = future.get();
			displayText(result);
		} catch (ExecutionException e) {
			e.printStackTrace();
			cleanup();
			return;
		}
	}

	private void cleanup() {
	}

	private void displayText(String result) {
	}

	private void doOtherThings() {
	}
}
