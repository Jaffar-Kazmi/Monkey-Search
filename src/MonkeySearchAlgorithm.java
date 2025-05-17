import java.util.Arrays;
import java.util.Comparator;

/**
 * Implementation of the Monkey Search Algorithm.
 * The algorithm is inspired by the foraging behavior of monkeys in the forest.
 * It consists of three main processes:
 * 1. Climb process - Local search within a tree
 * 2. Watch-jump process - Moving between nearby trees
 * 3. Somersault process - Moving to entirely new areas of the search space
 */
public class MonkeySearchAlgorithm {
    // Algorithm parameters
    private final ObjectiveFunction function;
    private final int dimension;
    private final int populationSize;
    private final double lowerBound;
    private final double upperBound;
    private final int maxIterations;
    
    // Algorithm specific parameters
    private final double climbingStep = 0.2;  // Step size for climbing
    private final double somersaultRange = 2.0;  // Range for somersault
    
    // Population of monkeys
    private Monkey[] monkeys;
    private Monkey bestMonkey;
    
    /**
     * Initialize the Monkey Search Algorithm.
     * 
     * @param function The objective function to optimize
     * @param dimension The dimension of the search space
     * @param populationSize The number of monkeys
     * @param lowerBound The lower bound of the search space
     * @param upperBound The upper bound of the search space
     * @param maxIterations The maximum number of iterations
     */
    public MonkeySearchAlgorithm(
            ObjectiveFunction function,
            int dimension,
            int populationSize,
            double lowerBound,
            double upperBound,
            int maxIterations) {
        this.function = function;
        this.dimension = dimension;
        this.populationSize = populationSize;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.maxIterations = maxIterations;
        
        // Initialize the population of monkeys
        initializePopulation();
    }
    
    /**
     * Initialize the population of monkeys with random positions.
     */
    private void initializePopulation() {
        monkeys = new Monkey[populationSize];
        for (int i = 0; i < populationSize; i++) {
            monkeys[i] = new Monkey(dimension, lowerBound, upperBound);
            evaluateMonkey(monkeys[i]);
        }
        
        // Find the best monkey
        updateBestMonkey();
    }
    
    /**
     * Evaluate the fitness of a monkey.
     * 
     * @param monkey The monkey to evaluate
     */
    private void evaluateMonkey(Monkey monkey) {
        double fitness = function.evaluate(monkey.getPosition());
        monkey.setFitness(fitness);
    }
    
    /**
     * Update the best monkey found so far.
     */
    private void updateBestMonkey() {
        Arrays.sort(monkeys, Comparator.comparingDouble(Monkey::getFitness));
        if (bestMonkey == null || monkeys[0].getFitness() < bestMonkey.getFitness()) {
            bestMonkey = monkeys[0].clone();
        }
    }
    
    /**
     * Run the Monkey Search Algorithm.
     * 
     * @return The best solution found
     */
    public double[] run() {
        System.out.println("\nStarting Monkey Search Algorithm");
        System.out.println("\nParameters:");
        System.out.println("\tDimension: " + dimension);
        System.out.println("\tPopulation Size: " + populationSize);
        System.out.println("\tSearch Space: [" + lowerBound + ", " + upperBound + "]");
        System.out.println("\tMaximum Iterations: " + maxIterations);
        System.out.println("\n\tIterations");
        
        System.out.println("Iteration\tBest Fitness");
        for (int iteration = 0; iteration < maxIterations; iteration++) {
            // Climb process
            for (Monkey monkey : monkeys) {
                climbProcess(monkey);
            }
            
            // Watch-jump process
            for (Monkey monkey : monkeys) {
                watchJumpProcess(monkey);
            }
            
            // Somersault process
            somersaultProcess();
            
            // Update the best monkey
            updateBestMonkey();
            
            // Print progress
            if ((iteration + 1) % 10 == 0 || iteration == 0) {
                System.out.println((iteration + 1) + 
                                   "\t\t" + bestMonkey.getFitness());
            }
        }
        
        return bestMonkey.getPosition();
    }
    
    /**
     * Climb process - Local search within the current area.
     * 
     * @param monkey The monkey to perform the climb process
     */
    private void climbProcess(Monkey monkey) {
        double[] position = monkey.getPosition();
        double[] newPosition = position.clone();
        double currentFitness = monkey.getFitness();
        
        // Try climbing in each dimension
        for (int i = 0; i < dimension; i++) {
            // Try climbing up
            newPosition[i] = position[i] + climbingStep;
            
            // Ensure the new position is within bounds
            newPosition[i] = Math.min(newPosition[i], upperBound);
            
            double newFitness = function.evaluate(newPosition);
            if (newFitness < currentFitness) {
                // Accept the new position
                position = newPosition.clone();
                currentFitness = newFitness;
            } else {
                // Try climbing down
                newPosition[i] = position[i] - climbingStep;
                
                // Ensure the new position is within bounds
                newPosition[i] = Math.max(newPosition[i], lowerBound);
                
                newFitness = function.evaluate(newPosition);
                if (newFitness < currentFitness) {
                    // Accept the new position
                    position = newPosition.clone();
                    currentFitness = newFitness;
                } else {
                    // Revert the change
                    newPosition[i] = position[i];
                }
            }
        }
        
        // Update the monkey's position and fitness
        monkey.setPosition(position);
        monkey.setFitness(currentFitness);
    }
    
    /**
     * Watch-jump process - Move toward another monkey with better fitness.
     * 
     * @param monkey The monkey to perform the watch-jump process
     */
    private void watchJumpProcess(Monkey monkey) {
        // Find a random monkey with better fitness
        int randomIndex = (int) (Math.random() * populationSize);
        Monkey targetMonkey = monkeys[randomIndex];
        
        // Only jump if the target monkey has better fitness
        if (targetMonkey.getFitness() < monkey.getFitness()) {
            double[] position = monkey.getPosition();
            double[] targetPosition = targetMonkey.getPosition();
            double[] newPosition = new double[dimension];
            
            // Jump toward the target monkey
            for (int i = 0; i < dimension; i++) {
                // Random jump within [0, 1] of the distance
                double jumpFactor = Math.random();
                newPosition[i] = position[i] + jumpFactor * (targetPosition[i] - position[i]);
                
                // Ensure the new position is within bounds
                newPosition[i] = Math.max(lowerBound, Math.min(newPosition[i], upperBound));
            }
            
            // Evaluate the new position
            double newFitness = function.evaluate(newPosition);
            
            // Only accept the jump if it improves fitness
            if (newFitness < monkey.getFitness()) {
                monkey.setPosition(newPosition);
                monkey.setFitness(newFitness);
            }
        }
    }
    
    /**
     * Somersault process - Move to a new area using the center of gravity.
     */
    private void somersaultProcess() {
        // Calculate the center of gravity of the population
        double[] centerOfGravity = new double[dimension];
        for (Monkey monkey : monkeys) {
            double[] position = monkey.getPosition();
            for (int i = 0; i < dimension; i++) {
                centerOfGravity[i] += position[i] / populationSize;
            }
        }
        
        // Somersault each monkey around the center of gravity
        for (Monkey monkey : monkeys) {
            double[] position = monkey.getPosition();
            double[] newPosition = new double[dimension];
            
            for (int i = 0; i < dimension; i++) {
                // Generate a random somersault factor
                double somersaultFactor = somersaultRange * (Math.random() * 2 - 1);
                
                // Somersault around the center of gravity
                newPosition[i] = centerOfGravity[i] + somersaultFactor * (position[i] - centerOfGravity[i]);
                
                // Ensure the new position is within bounds
                newPosition[i] = Math.max(lowerBound, Math.min(newPosition[i], upperBound));
            }
            
            // Evaluate the new position
            double newFitness = function.evaluate(newPosition);
            
            // Update the monkey's position and fitness
            monkey.setPosition(newPosition);
            monkey.setFitness(newFitness);
        }
    }
}