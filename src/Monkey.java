/**
 * Represents a monkey agent in the Monkey Search Algorithm.
 * Each monkey has a position in the search space and a fitness value.
 */
public class Monkey {
    private double[] position;
    private double fitness;

    /**
     * Create a new monkey with the given position. 
     * 
     * @param position The position of the monkey in the search space
     */
    public Monkey(double[] position) {
        this.position = position.clone();
        this.fitness = Double.MAX_VALUE;
    }
    
    /**
     * Create a new monkey with a random position within the given bounds.
     * 
     * @param dimension The dimension of the search space
     * @param lowerBound The lower bound of the search space
     * @param upperBound The upper bound of the search space
     */
    public Monkey(int dimension, double lowerBound, double upperBound) {
        position = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            position[i] = lowerBound + Math.random() * (upperBound - lowerBound);
        }
        this.fitness = Double.MAX_VALUE;
    }
    
    /**
     * Get the position of the monkey.
     * 
     * @return The position of the monkey
     */
    public double[] getPosition() {
        return position;
    }
    
    /**
     * Set the position of the monkey.
     * 
     * @param position The new position of the monkey
     */
    public void setPosition(double[] position) {
        this.position = position.clone();
    }
    
    /**
     * Get the fitness value of the monkey.
     * 
     * @return The fitness value
     */
    public double getFitness() {
        return fitness;
    }
    
    /**
     * Set the fitness value of the monkey.
     * @param fitness The new fitness value
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }
    
    /**
     * Create a clone of this monkey.
     * 
     * @return A new monkey with the same position and fitness
     */
    public Monkey clone() {
        Monkey clone = new Monkey(this.position);
        clone.setFitness(this.fitness);
        return clone;
    }
}