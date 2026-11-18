# PamakBook-Social-Network
A Java-based social network simulation featuring OOP architecture, graph algorithms, and object serialization.

## Overview
A comprehensive desktop social network simulation built in Java. This application models user interactions, social graphs, and group dynamics while demonstrating core Object-Oriented Programming (OOP) principles and algorithmic data processing. 

## Key Features
* **Graph Visualization & Analytics**: Integrates the JUNG framework to render undirected sparse graphs of the user network, dynamically calculating the network's diameter using Unweighted Shortest Path algorithms.
* **Algorithmic Social Features**: Features custom feed generation with timestamp-based sorting, mutual friend identification, and a "Friends of Friends" recommendation engine.
* **Contact Tracing Simulation**: Employs a network traversal approach to identify potential virus transmissions across the user base up to a 2-degree depth.
* **State Persistence**: Utilizes Object Serialization (`ObjectOutputStream`/`ObjectInputStream`) to persistently save and restore the complete application state locally.
* **Access Control & OOP**: Demonstrates inheritance and polymorphism via `ClosedGroup` policies, algorithmically restricting entry to users who share a friendship edge with at least one existing member.
* **User Management & GUI**: Desktop interface built with Java Swing, featuring strict regex-based input validation for academic email domains.

## Tech Stack
* **Language:** Java (JDK 8+)
* **UI Framework:** Java Swing
* **Build Tool:** Maven

## How to Run
1. Clone the repository to your local machine.
2. Open your terminal in the project directory and run the following command to compile and launch the application:
   ```bash
   mvn clean compile exec:java
