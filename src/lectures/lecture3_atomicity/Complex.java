package lectures.lecture3_atomicity;

/*
 * immutable object
 */
public class Complex {
	final long real;
	final long imag;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Complex c = new Complex(4, 5);
		Complex d = new Complex(1, 2);
		c = c.add(d);
	}

	public Complex(long real, long imag) {
		this.real = real;
		this.imag = imag;
	}
	
	private Complex add(Complex b) {
		// TODO Auto-generated method stub
		return new Complex(this.real + b.real, this.imag + b.imag);
	}
}
