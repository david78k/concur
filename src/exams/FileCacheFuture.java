package exams;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * refer to ArchiveSearcher in lecture 10
 * 
 * @author david78k
 *
 */
public class FileCacheFuture {
	private Map<String, String> cache = new HashMap<String, String>();
	ExecutorService e;
	
	/**
	 * asynchronous get
	 * @param filename
	 * @return
	 */
	String getFuture(String filename) {
		String text = cache.get(filename);
		
		if(text == null) {
			Future<String> future = loadFileFuture(filename);
			
			doOtherThings();
			
			try {
				text = future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			
			cache.put(filename, text);
		}
		
		return text;
	}
	
	private void doOtherThings() {
	}

	/**
	 * synchronous get
	 * @param filename
	 * @return
	 */
	String get(String filename) {
		String text = cache.get(filename);
		
		if(text == null) {
			text = loadFile(filename);
			cache.put(filename, text);
		}
		
		return text;
	}
	
	Future<String> loadFileFuture(final String filename) {
		Future<String> future = e.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return readFile();
			}
		});
		return future;
	}
	
	protected String readFile() {
		return null;
	}

	String loadFile(String filename) {
//		Future<String> file = 
		return null;
	}
}
