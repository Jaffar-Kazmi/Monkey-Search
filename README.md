# Monkey Search Algorithm

This project implements the Monkey Search Algorithm, a population-based metaheuristic inspired by the foraging and movement behavior of monkeys in the wild. It is designed to solve optimization problems by minimizing a given objective function — in this implementation, the **Sphere function** is used as a benchmark.

## Features
- Object-oriented Java implementation
- Easy to extend with other objective functions
- No external dependencies — runs on plain Java
- Prints best fitness and position after each iteration

## Algorithm Overview

The Monkey Search Algorithm consists of three main processes:
1. **Climb Process**: Local search within a tree (current solution space)
2. **Watch-jump Process**: Moving between nearby trees (exploring neighboring solutions)
3. **Somersault Process**: Moving to entirely new areas of the search space (global exploration)

## Sphere Function

The Sphere function is a widely used benchmark in optimization problems. It is defined as the sum of the squares of its input variables:
```
    f(x) = ∑(xᵢ²)        for i = 1 to n (where n is the number of dimensions)
```
This function is simple, convex, and continuous, with a global minimum at the origin (0, 0, ..., 0) where the function value is zero. Its smooth landscape and mathematical simplicity make it ideal for testing and comparing the performance of optimization algorithms, particularly in high-dimensional search spaces.


## Project Structure

- `Main.java` - Entry point of the application
- `MonkeySearchAlgorithm.java` - Core implementation of the algorithm
- `Monkey.java` - Class representing a monkey agent
- `ObjectiveFunction.java` - Interface for the optimization function
- `SphereFunction.java` - Implementation of the Sphere benchmark function

## Requirements

- Java Development Kit (JDK) 8 or higher
- Node.js (for running npm scripts)

## Installation

1. Clone this repository
2. Ensure you have JDK installed and properly configured

## Usage

Compile and run manually:
```bash
cd path/to/MonkeySearchProject
javac *.java
java Main
```

## Algorithm Parameters

The current implementation uses the following parameters:
- Dimension: 5
- Population Size: 20
- Search Space Bounds: [-10, 10]
- Maximum Iterations: 100
- Climbing Step: 0.2
- Somersault Range: 2.0

These parameters can be modified in the `Main.java` file.

## Output

The program will display:
1. Initial parameters
2. Progress updates every 10 iterations
3. Final solution coordinates and fitness value

## Example Output
```
Starting Monkey Search Algorithm

Parameters:
        Dimension: 5
        Population Size: 20
        Search Space: [-10.0, 10.0]
        Maximum Iterations: 100

        Iterations
Iteration       Best Fitness
[#]             [Value]

...
Final Solution:
Position: [x1, x2, x3, x4, x5]
Fitness: [value]
```
## Author 
Syed Jaffar Raza Kazmi
