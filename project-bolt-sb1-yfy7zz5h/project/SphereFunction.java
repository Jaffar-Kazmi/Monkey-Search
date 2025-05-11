/**
 * Implements the Sphere function, a common benchmark function for optimization.
 * f(x) = sum(x_i^2) for i=1 to n
 * The global minimum is at (0,...,0) with f(x) = 0.
 */
public class SphereFunction implements ObjectiveFunction {
    
    @Override
    public double evaluate(double[] position) {
        double sum = 0;
        for (double value : position) {
            sum += value * value;
        }
        return sum;
    }
}