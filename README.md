# Monkey Search Algorithm Implementation

This repository contains a Java implementation of the Monkey Search Algorithm (MSA), a nature-inspired optimization algorithm that mimics the behavior of monkeys searching for food in the forest.

## Algorithm Overview

The Monkey Search Algorithm consists of three main processes:
1. **Climb Process**: Local search within a tree (current solution space)
2. **Watch-jump Process**: Moving between nearby trees (exploring neighboring solutions)
3. **Somersault Process**: Moving to entirely new areas of the search space (global exploration)

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
javac -d ./bin *.java
java -cp ./bin Main
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
Starting Monkey Search Algorithm with parameters:
Dimension: 5
Population Size: 20
Search Space: [-10, 10]
Maximum Iterations: 100

Iterating...
Iteration 1, Best Fitness: [value]
...
Final Solution:
Position: [x1, x2, x3, x4, x5]
Fitness: [value]
```
## Author 
Syed Jaffar Raza Kazmi
