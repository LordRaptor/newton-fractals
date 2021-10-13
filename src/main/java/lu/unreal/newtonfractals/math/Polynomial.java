package lu.unreal.newtonfractals.math;

public interface Polynomial {

    Polynomial derivative();

    void evaluate(Complex x, Complex out);
}
