package exams;

/**
 * FA11 Mid
 * 
 * a) Create a subclass that provides a fully synchronized version of this class.
 * b) If you need more information to be sure that the resulting class is really fully synchronized, indicate what else you need to know.
 * c) Fully synchronized objects are not sufficient in every context. Give an example of something that a client might want to do with this class where full synchronization would not be sufficient?
 * d) Create an adapter class that provides a copy on write version of this class.
 * 
 * @author david78k
 * @param <Y>
 * @param <Z>
 *
 */
public class X {

	public static void main(String[] args) {

	}

	class Y {}
	class Z {}
	
	public void modify1(){}
	public void modify2(){}
	public Y getVal1(){ return null; }
	public Z getVal2(){ return null; }
	public X clone(){ return null; } //returns a deep copy of the object.
}
