package lu.unreal.newtonfractals.math;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import org.apache.commons.math3.util.FastMath;

public class ComplexPolynomial implements Polynomial {

    private final Complex[] coefficients;

    public static ComplexPolynomial createFromRoots(Complex... roots) {
        Preconditions.checkArgument(roots.length >= 1, "At least 1 root required");

        if (roots.length == 1) {
            Complex negated = new Complex(0, 0);
            ComplexMath.negate(roots[0], negated);
            return new ComplexPolynomial(negated, new Complex(1, 0));
        } else {
            ComplexPolynomial result = null;
            for (Complex root : roots) {
                if (result == null) {
                    Complex negated = new Complex(0, 0);
                    ComplexMath.negate(root, negated);
                    result = new ComplexPolynomial(negated, new Complex(1, 0));
                } else {
                    Complex negated = new Complex(0, 0);
                    ComplexMath.negate(root, negated);
                    ComplexPolynomial p = new ComplexPolynomial(negated, new Complex(1, 0));
                    result = result.multiply(p);
                }
            }
            return result;

        }
    }

    public ComplexPolynomial(Complex... coefficients) {
        this.coefficients = coefficients;
    }

    public Complex[] getCoefficients() {
        return coefficients;
    }

    @Override
    public ComplexPolynomial derivative() {
        Complex[] d = new Complex[coefficients.length - 1];

        for (int i = 1; i < coefficients.length; i++) {
            Complex c = new Complex(0, 0);
            ComplexMath.multiply(coefficients[i], i, c);
            d[i - 1] = c;
        }

        return new ComplexPolynomial(d);
    }


    @Override
    public void evaluate(Complex x, Complex out) {
        out.setValue(0, 0);
        Complex tmp = new Complex(0, 0);
        for (int i = 0; i < coefficients.length; i++) {
            Complex coefficient = coefficients[i];
            if (i == 0) {
                ComplexMath.add(out, coefficient, out);
            } else {
                ComplexMath.pow(x, i, tmp);
                ComplexMath.multiply(tmp, coefficient, tmp);
                ComplexMath.add(tmp, out, out);
            }
        }
    }

    private ComplexPolynomial multiply(ComplexPolynomial factor) {
        Complex[] productCoefficients = new Complex[this.coefficients.length + factor.coefficients.length - 1];
        for (int i = 0; i < productCoefficients.length; i++) {
            productCoefficients[i] = new Complex(0, 0);
        }

        Complex tmp = new Complex(0, 0);
        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < factor.coefficients.length; j++) {
                ComplexMath.multiply(this.coefficients[i], factor.coefficients[j], tmp);
                Complex productCoefficient = productCoefficients[i + j];
                ComplexMath.add(productCoefficient, tmp, productCoefficient);
            }
        }

        return new ComplexPolynomial(productCoefficients);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coefficients.length - 1; i >= 0; i--) {
            Complex coefficient = coefficients[i];
            if (!coefficient.equals(Complex.ZERO)) {
                if (i < coefficients.length - 1) {
                    if (coefficient.getReal() > 0 || coefficient.getImaginary() != 0) {
                        sb.append("+");
                    }
                }
                if (!coefficient.equals(Complex.ONE)) {
                    sb.append(coefficient.toString());
                }

                if (i > 1) {
                    sb.append("z^").append(i);
                } else if (i == 1) {
                    sb.append("z");
                }
            }

        }
        return sb.toString();
    }
}
