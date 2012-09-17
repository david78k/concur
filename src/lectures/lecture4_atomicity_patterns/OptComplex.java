package lectures.lecture4_atomicity_patterns;

class OptComplex {
	protected Complex v;
	
	synchronized Complex getComplex() { return v; }
	
	void scale(double x) {
		boolean success = false;
		do {
			Complex old = getComplex();
//			Complex newval = new Complex(old.real * x, old.imag * x);
			Complex newval = new Complex(v.real * x, v.imag * x);
			success = commit(old , newval);
		} while (!success);
	}

	private synchronized boolean commit(Complex old, Complex newval) {
		if (old == v) {
			v = newval;
			return true;
		}
		return false;
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
