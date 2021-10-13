package lu.unreal.newtonfractals.newton;

import lu.unreal.newtonfractals.math.Complex;
import lu.unreal.newtonfractals.math.ComplexMath;
import lu.unreal.newtonfractals.math.Polynomial;
import org.apache.commons.math3.util.FastMath;

public class NewtonsMethod {

    public static final double HIGH_PRECISION_EPSILON = 0.000001;

    private NewtonPixel findRoot(Polynomial p, Complex x0, long iterationLimit, double epsilon) {
        Polynomial derivative = p.derivative();

        Complex xn = new Complex(0, 0);
        Complex xn1 = new Complex(x0);

        Complex pValue = new Complex(0, 0);
        Complex p1Value = new Complex(0, 0);

        long iteration = 0;
        double maxAbs = 0.0;

        do {
            xn.setValue(xn1);
            p.evaluate(xn, pValue);
            derivative.evaluate(xn, p1Value);

            ComplexMath.divide(pValue, p1Value, xn1);
            ComplexMath.subtract(xn, xn1, xn1);

            iteration++;
            maxAbs = FastMath.max(xn1.abs(), maxAbs);

        } while (xn1.subtractAndAbs(xn) > epsilon && iteration <= iterationLimit);

        return new NewtonPixel(xn1, iteration, maxAbs);
    }
}
