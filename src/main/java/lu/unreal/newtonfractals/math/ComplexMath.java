package lu.unreal.newtonfractals.math;

import org.apache.commons.math3.util.FastMath;

public class ComplexMath {

    public static void negate(Complex in, Complex out) {
        out.setValue(-in.getReal(), -in.getImaginary());
    }

    public static void add(Complex a, Complex b, Complex out) {
        out.setValue(a.getReal() + b.getReal(), a.getImaginary() + b.getImaginary());
    }

    public static void subtract(Complex a, Complex b, Complex out) {
        out.setValue(a.getReal() - b.getReal(), a.getImaginary() - b.getImaginary());
    }

    public static void add(Complex a, double b, Complex out) {
        out.setValue(a.getReal() + b, a.getImaginary());
    }

    public static void subtract(Complex a, double b, Complex out) {
        out.setValue(a.getReal() - b, a.getImaginary());
    }

    public static void multiply(Complex a, Complex b, Complex out) {
        out.setValue(a.getReal() * b.getReal() - a.getImaginary() * b.getImaginary(),
                a.getReal() * b.getImaginary() + a.getImaginary() * b.getReal());
    }

    public static void multiply(Complex a, double factor, Complex out) {
        out.setValue(a.getReal() * factor, a.getImaginary() * factor);
    }

    public static void divide(Complex divident, Complex divisor, Complex out) {
        double c = divisor.getReal();
        double d = divisor.getImaginary();

        if (FastMath.abs(c) < FastMath.abs(d)) {
            double q = c / d;
            double denominator = c * q + d;
            out.setValue((divident.getReal() * q + divident.getImaginary()) / denominator,
                    (divident.getImaginary() * q - divident.getReal()) / denominator);
        } else {
            double q = d / c;
            double denominator = d * q + c;
            out.setValue((divident.getImaginary() * q + divident.getReal()) / denominator,
                    (divident.getImaginary() - divident.getReal() * q) / denominator);
        }
    }

    public static void divide(Complex divident, double divisor, Complex out) {
        out.setValue(divident.getReal() / divisor, divident.getImaginary() / divisor);
    }

    public static void log(Complex in, Complex out) {
        out.setValue(FastMath.log(in.abs()), FastMath.atan2(in.getImaginary(), in.getReal()));
    }

    public static void exp(Complex in, Complex out) {
        double expReal = FastMath.exp(in.getReal());
        out.setValue(expReal * FastMath.cos(in.getImaginary()),
                expReal * FastMath.sin(in.getImaginary()));
    }

    public static void pow(Complex base, Complex exponent, Complex out) {
        log(base, out);
        multiply(out, exponent, out);
        exp(out, out);
    }

    public static void pow(Complex base, double exponent, Complex out) {
        log(base, out);
        multiply(out, exponent, out);
        exp(out, out);
    }
}
