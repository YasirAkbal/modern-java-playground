# Modern Java Playground

Welcome to the **Modern Java Playground**! 

This repository is designed to help you master the evolution of Java from version 8 up to 21.
It uses a realistic **Education Management System (EduMaster)** scenario to provide context for every feature.

##  Project Structure

This is a Maven Multi-Module project.

| Module | Java Version | Key Topics |
|--------|--------------|------------|
| `edu-master` | Core | Domain Model, Data Generator (The Backbone) |
| `functional-style` | Java 8 | Lambda, Stream API, Optional, Functional Interfaces |
| `date-time-and-utils` | Java 8-11 | Date/time API, Collection Factories, File I/O |
| `language-enhancements` | Java 10-14 | `var`, Switch Expressions, Text Blocks |
| `control-flow` | Java 16-21 | Pattern Matching (instanceof, switch) |
| `data-modeling` | Java 14-17 | Records, Sealed Classes |
| `concurrency-and-performance` | Java 8-21 | CompletableFuture, Virtual Threads |
| `api-modernization` | Java 11+ | HTTP Client, String API |

##  How to Use

1. **Explore the Domain**: Check `edu-master` to understand `Student`, `Course`, `Instructor` classes.
2. **Pick a Module**: Start with `functional-style` if you are new to Java 8.
3. **Solve Challenges**:
   - Open the `src/main/java/challenges` folder in any module.
   - Look for files ending with `Challenge.java`.
   - Read the **SCENARIO** and **TASK** comments.
   - Implement the solution where you see `// TODO`.
4. **Verify**: Write a `main` method or use a test class to run your code.

##  Prerequisites

- JDK 21
- Maven
- IDE (IntelliJ IDEA, Eclipse, or VS Code) with Java 21 support enabled.

##  Key Features to Master

- **Functional**: Stream pipelines, defensive coding with Optional.
- **Conciseness**: Records, `var`, Switch Expressions.
- **Performance**: Parallel Streams, Virtual Threads.
- **Safety**: Sealed Classes, Immutable Collections.

Happy Coding!

