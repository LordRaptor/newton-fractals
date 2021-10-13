package lu.unreal.newtonfractals.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexPolynomialTest {

    @Test
    public void testCreateFromRoots() {
        ComplexPolynomial polynomial = ComplexPolynomial.createFromRoots(new Complex(1, 0), new Complex(-1, 0));

        System.out.println(polynomial);
        assertEquals(3, polynomial.getCoefficients().length);
        assertEquals(new Complex(-1, 0), polynomial.getCoefficients()[0]);
        assertEquals(new Complex(0, 0), polynomial.getCoefficients()[1]);
        assertEquals(new Complex(1, 0), polynomial.getCoefficients()[2]);
    }

    @Test
    public void testCreateFromRootsSingle() {
        ComplexPolynomial polynomial = ComplexPolynomial.createFromRoots(new Complex(2, 3));

        System.out.println(polynomial);
        assertEquals(2, polynomial.getCoefficients().length);
        assertEquals(new Complex(-2, -3), polynomial.getCoefficients()[0]);
        assertEquals(new Complex(1, 0), polynomial.getCoefficients()[1]);
    }

    @Test
    public void testDerivative() {
        ComplexPolynomial polynomial = new ComplexPolynomial(new Complex(1, 0), new Complex(2, 0), new Complex(3, 0));
        ComplexPolynomial derivative = polynomial.derivative();
        System.out.println(derivative);

        assertEquals(2, derivative.getCoefficients().length);
        assertEquals(new Complex(2, 0), derivative.getCoefficients()[0]);
        assertEquals(new Complex(6, 0), derivative.getCoefficients()[1]);
    }
}
