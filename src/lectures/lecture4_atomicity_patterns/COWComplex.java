package lectures.lecture4_atomicity_patterns;

// Copy on write Complex
public class COWComplex {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		COWComplex c = new COWComplex(0, 0);
		Complex v;
		
		c = new COWComplex(0, 0);
		
		v = c.getComplex();
		double r = v.getReal();
		double i = v.getImag();
	}

	//@GuardedBy(this)
	protected Complex v;
	
	public COWComplex(double real, double imag) {
		v = new Complex(real, imag);
	}

	synchronized Complex getComplex() {
		return v;
	}
	
	synchronized void scale(double x){
		double real = v.real*x;
		double imag = v.imag*x;
		v = new Complex(real, imag);
	}
	
	//	@Immutable
	class Complex {
		final double real;
		final double imag;
		
		public Complex(double real, double imag) {
			this.real = real;
			this.imag = imag;
		}
		
		double getReal() { return real; }
		double getImag() { return imag; }
	}
}
