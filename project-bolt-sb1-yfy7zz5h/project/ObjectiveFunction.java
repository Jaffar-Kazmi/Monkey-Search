/**
 * Interface for the objective function to be optimized.
 */
public interface ObjectiveFunction {
    /**
     * Evaluate the fitness of a position in the search space.
     * 
     * @param position The position to evaluate
     * @return The fitness value
     */
    double evaluate(double[] position);
}