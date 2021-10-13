package lu.unreal.newtonfractals.newton;

import lu.unreal.newtonfractals.math.Complex;

public class NewtonPixel {

    private static final long serialVersionUID = 5959153576206427541L;
    public final double real;
    public final double imaginary;
    public final long iteration;
    public final double maxAbs;

    public NewtonPixel(Complex c, long iteration, double maxAbs) {
        this.real = c.getReal();
        this.imaginary = c.getImaginary();
        this.iteration = iteration;
        this.maxAbs = maxAbs;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public Complex getComplex() {
        return new Complex(real, imaginary);
    }

    public double getAbs() {
        return getComplex().abs();
    }

    public double getAngle() {
        return normalizeAngle(Math.atan2(imaginary, real));
    }

    public long getIteration() {
        return iteration;
    }

    public double getMaxAbs() {
        return maxAbs;
    }

    private double normalizeAngle(double angle) {
        if (angle < 0) {
            angle = (2 * Math.PI) + angle;
        }

        return angle;
    }
}
