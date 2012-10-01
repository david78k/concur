package exams;

/**
 * FA11 Mid
 * 
 * a) Create a subclass that provides a fully synchronized version of this class.
 * b) If you need more information to be sure that the resulting class is really fully synchronized, 
 * 		indicate what else you need to know.
 * c) Fully synchronized objects are not sufficient in every context. Give an example of something that 
 * 		a client might want to do with this class where full synchronization would not be sufficient?
 * d) Create an adapter class that provides a copy on write version of this class.
 * 
 * @author david78k
 * @param <Y>
 * @param <Z>
 *
 */
public class X {
	class Y {} 	class Z {}
	
	public void modify1(){}
	public void modify2(){}
	public Y getVal1(){ return null; }
	public Z getVal2(){ return null; }
	public X clone(){ return null; } //returns a deep copy of the object.
	
}

class SubclassX extends X {
	synchronized public void modify1(){super.modify1();}
	synchronized public void modify2(){super.modify2();}
	synchronized public Y getVal1(){ return super.getVal1(); }
	synchronized public Z getVal2(){ return super.getVal2(); }
	synchronized public X clone(){ return super.clone(); } //returns a deep copy of the object.
}

class AdapterX<Y, Z> {
	X x;
	
	AdapterX (X x) {
		this.x = x;
	}
	private synchronized boolean compareAndSet(X oldX, X newX) {
		if (oldX == x ) {
			x = newX; return true;
		}
		return false;
	}
	public void modify1(){ 
		X oldX = x;
		X newX = new X();
		do {
			oldX = x;
			newX = oldX.clone();
			newX.modify1(); 
		} while (!compareAndSet(oldX, newX));
	}
	public void modify2(){ 
		X oldX;
		X newX;
		do {
			oldX = x;
			newX = oldX.clone();
			newX.modify2(); 
		} while (!compareAndSet(newX, oldX));
	}
	public exams.X.Y getVal1(){ return x.getVal1(); }
	public exams.X.Z getVal2(){ return x.getVal2(); }
	public X clone(){ return x.clone(); } //returns a deep copy of the object.
}