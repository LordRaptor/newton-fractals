package lu.unreal.newtonfractals.math;

import com.google.common.base.MoreObjects;
import org.apache.commons.math3.util.FastMath;

import java.text.DecimalFormat;
import java.util.Objects;

public class Complex {

    public static final Complex ONE = new Complex(1, 0);
    public static final Complex ZERO = new Complex(0, 0);

    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("0.#####");

    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex setValue(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
        return this;
    }

    public Complex setValue(Complex c) {
        this.real = c.getReal();
        this.imaginary = c.getImaginary();
        return this;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public double abs() {
        return FastMath.sqrt(real * real + imaginary * imaginary);
    }

    public double subtractAndAbs(Complex subtrahend) {
        double r = real - subtrahend.getReal();
        double i = imaginary - subtrahend.getImaginary();
        return FastMath.sqrt(r * r + i * i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Complex complex = (Complex) o;
        return Double.compare(complex.real, real) == 0 && Double.compare(complex.imaginary, imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    @Override
    public String toString() {
        if (real == 0 && imaginary == 0) {
            return "0";
        }
        if (real == 0) {
            return String.format("%si", NUMBER_FORMAT.format(imaginary));
        }
        if (imaginary == 0) {
            return String.format("%s", NUMBER_FORMAT.format(real));
        }
        return String.format("(%s + %si)", NUMBER_FORMAT.format(real), NUMBER_FORMAT.format(imaginary));
    }
}
