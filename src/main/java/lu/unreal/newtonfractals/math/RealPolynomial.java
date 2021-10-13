package lu.unreal.newtonfractals.math;

import java.text.DecimalFormat;

public class RealPolynomial implements Polynomial {

    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("0.#####");

    private final double[] coefficients;

    public RealPolynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }

    @Override
    public RealPolynomial derivative() {
        double[] d = new double[coefficients.length - 1];

        for (int i = 1; i < coefficients.length; i++) {
            d[i - 1] = coefficients[i] * i;
        }

        return new RealPolynomial(d);
    }

    @Override
    public void evaluate(Complex x, Complex out) {
        out.setValue(0, 0);
        Complex tmp = new Complex(0, 0);
        for (int i = 0; i < coefficients.length; i++) {
            double coefficient = coefficients[i];
            if (i == 0) {
                ComplexMath.add(out, coefficient, out);
            } else {
                ComplexMath.pow(x, i, tmp);
                ComplexMath.multiply(tmp, coefficient, tmp);
                ComplexMath.add(tmp, out, out);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coefficients.length - 1; i >= 0; i--) {
            double coefficient = coefficients[i];
            if (coefficient != 0) {
                if (i < coefficients.length - 1) {
                    if (coefficient > 0) {
                        sb.append("+");
                    }
                }
                if (coefficient != 1) {
                    sb.append(NUMBER_FORMAT.format(coefficient));
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
