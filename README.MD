
# Opticycle - TSP resolution with genetic algorithm

This Java project implements a **genetic algorithm** to solve the Traveling Salesman Problem (TSP), applied to a research ecosystem.

## Features

-   **TSP Resolution**: Implementation of a configurable genetic algorithm (mutation, elitism, etc.).

-   **Ecosystem Management**: Modeling of students, researchers, and faculty members with interactions via a console menu.

-   **Custom Criteria**: Search for Hamiltonian cycles based on criteria such as discipline or age.

-   **Complete Documentation**: Includes Javadocs and a modular architecture facilitating extensibility.

## Installation

### Prerequisites

-   **IDE**: IntelliJ IDEA, Eclipse, or any other compatible Java IDE.

-   **Additional Tools**: The Gradle wrapper is included in the project for dependency management.

### 1. Building the project
To generate the executable file:
```bash
./gradlew shadowJar
```

This command creates a JAR file with all dependencies in the `build/libs` folder.

### 2. Running the program
To launch the application:
```bash
cd build/libs
java -cp opticycle-1.0-all.jar fr.opticycle.Main
```

