# Transportation Network Optimization

## Project Overview
This project implements and compares two Minimum Spanning Tree (MST) algorithms—Prim's and Kruskal's—for optimizing transportation networks. The solution analyzes algorithm performance across graphs of varying sizes and complexities.

## Algorithms Implemented
- **Prim's Algorithm**: Uses priority queue for efficient edge selection
- **Kruskal's Algorithm**: Utilizes Union-Find with path compression

## Class Descriptions

### Core Classes
- **Graph**: Represents a transportation network with nodes and edges
- **Edge**: Defines connections between nodes with weights
- **MSTResult**: Stores algorithm results including MST edges, cost, and performance metrics

### Algorithm Classes
- **PrimAlgorithm**: Implements Prim's MST algorithm using priority queue
- **KruskalAlgorithm**: Implements Kruskal's MST algorithm with Union-Find
- **UnionFind**: Disjoint-set data structure for efficient cycle detection

### Support Classes
- **GraphResult**: Aggregates results from both algorithms for comparison
- **Main**: Orchestrates the testing process and generates output

## Features
- Processes graphs of various sizes (4-30+ vertices)
- Generates comprehensive performance metrics
- Produces JSON-formatted input/output
- Compares algorithm efficiency across different graph types
- Validates correctness through cost matching
