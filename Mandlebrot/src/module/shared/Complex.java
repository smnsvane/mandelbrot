package module.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Complex implements IsSerializable {

	/**
	 * real part of the complex number
	 */
	public float r;
	/**
	 * imaginary part of the complex number
	 */
	public float i;

	public Complex(float real, float imaginary) {
		r = real;
		i = imaginary;
	}

	// to make GWT-RPC happy
	@SuppressWarnings("unused")
	private Complex() {}

	public static Complex addition(Complex c0, Complex c1) {
		return new Complex(c0.r + c1.r, c0.i + c1.i);
	}

	public static Complex multiply(Complex c0, Complex c1) {
		return new Complex(c0.r * c1.r - c0.i * c1.i, c0.r * c1.i + c0.i * c1.r);
	}

	/**
	 * Complex number to the power of n (the exponent)<br/>
	 * (real, imaginary * i) ^ n
	 * @param base Complex number base
	 * @param exponent or n
	 * @return Complex number result
	 */
	public static Complex exponentiation(Complex base, int exponent) {
		if (exponent == 0)
			return new Complex(1, 0);
		if (exponent < 0)
			throw new IllegalArgumentException("exponent '"+exponent+" was negative");
		Complex result = base;
		for (int iterations = 1; iterations < exponent; iterations++) {
			result = multiply(result, base);
		}
		return result;
	}

	public double absolute() {
		return Math.sqrt(r*r + i*i);
	}

	@Override
	public String toString() {
		return "("+r+", "+i+"i)";
	}

	// overwriting object.clone causes issues on GWT
	public Complex copy() {
		return new Complex(r, i);
	}

	// test
	public static void main(String[] args) {
		Complex c = new Complex(5, 9);
		Complex multiply2 = Complex.multiply(c, c);
		Complex exponent2 = Complex.exponentiation(c, 2);
		System.out.println("multiplication (5, 9i) * (5, 9i): "+multiply2);
		System.out.println("exponentiation (5, 9i)^2: "+exponent2);

		Complex multiply3 = Complex.multiply(Complex.multiply(c, c), c);
		Complex exponent3 = Complex.exponentiation(c, 3);
		System.out.println("multiplication (5, 9i) * (5, 9i) * (5, 9i): "+multiply3);
		System.out.println("exponentiation (5, 9i)^3: "+exponent3);
	}
}
