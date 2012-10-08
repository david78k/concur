package exams;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class  FutureException<T> {
	T result;
	Callable<T> c;
	Executor e;
	Throwable exp;
	CountDownLatch latch;
	
	void run() {
		try {
			result = c.call();
		} catch (Throwable e) {
			exp = e;
		}
	}
	
	T get() throws InterruptedException, ExecutionException {
		if(exp != null) {
			throw new ExecutionException(exp);
		}
		
		return result;
	}
}
