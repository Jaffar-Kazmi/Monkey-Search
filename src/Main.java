/**
 * Main class to run the Monkey Search Algorithm.
 */
public class Main {
    public static void main(String[] args) {
        // Define the objective function (we're trying to minimize this function)
        ObjectiveFunction function = new SphereFunction();
        
        // Algorithm parameters 
        int dimension = 5;        // Dimension of the search space
        int populationSize = 20;  // Number of monkeys
        double lowerBound = -10;  // Lower bound of search space
        double upperBound = 10;   // Upper bound of search space
        int maxIterations = 100;  // Maximum number of iterations
        
        // Create and run the Monkey Search Algorithm
        MonkeySearchAlgorithm msa = new MonkeySearchAlgorithm(
            function, dimension, populationSize, lowerBound, upperBound, maxIterations
        );
        
        double[] bestSolution = msa.run();
        
        // Print the results
        System.out.println("\nFinal Solution:");
        System.out.print("Position: [");
        for (int i = 0; i < bestSolution.length; i++) {
            System.out.print(bestSolution[i]);
            if (i < bestSolution.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("Fitness: " + function.evaluate(bestSolution));
    }
}