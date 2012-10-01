package exams;

/**
 * Fa11 Mid
 * Fa11 Final
 * 
 * @author david78k
 * @param <T>
 *
 */
public class Holder<T extends Comparable<T>> implements Comparable<Holder<T>>{

	public static void main(String[] args) {

	}

	private T value;
	
	private synchronized T get() {
		return value;
	}
	
	private synchronized void set(T value) {
		this.value = value;
	}
	
	public synchronized void exchange(Holder<T> other) {
		T t = get();
		T v = other.get();
		set(v);
		other.set(t);
	}

	@Override
	public int compareTo(Holder<T> other) {
		return value.compareTo(other.value);
	}
}
