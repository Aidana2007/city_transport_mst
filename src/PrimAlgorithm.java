package src;

import java.util.*;

public class PrimAlgorithm {
    private int operationsCount;

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        operationsCount = 0;

        if (graph.getNodes().isEmpty()) {
            throw new IllegalArgumentException("Graph cannot be empty");
        }

        List<String> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();
        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        Map<String, List<Edge>> adjacencyList = buildAdjacencyList(edges);
        operationsCount += edges.size() * 2;

        String startNode = nodes.get(0);
        visited.add(startNode);
        operationsCount++;

        if (adjacencyList.containsKey(startNode)) {
            for (Edge edge : adjacencyList.get(startNode)) {
                pq.add(edge);
                operationsCount++;
            }
        }

        while (visited.size() < nodes.size() && !pq.isEmpty()) {
            Edge minEdge = pq.poll();
            operationsCount++;

            String nextNode = null;
            if (visited.contains(minEdge.getFrom()) && !visited.contains(minEdge.getTo())) {
                nextNode = minEdge.getTo();
            } else if (visited.contains(minEdge.getTo()) && !visited.contains(minEdge.getFrom())) {
                nextNode = minEdge.getFrom();
            }

            operationsCount += 2;

            if (nextNode != null) {
                mstEdges.add(minEdge);
                visited.add(nextNode);
                operationsCount += 2;

                if (adjacencyList.containsKey(nextNode)) {
                    for (Edge edge : adjacencyList.get(nextNode)) {
                        if (!visited.contains(edge.getFrom()) || !visited.contains(edge.getTo())) {
                            pq.add(edge);
                            operationsCount++;
                        }
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        int totalCost = mstEdges.stream().mapToInt(Edge::getWeight).sum();
        operationsCount++;

        return new MSTResult(mstEdges, totalCost, operationsCount, executionTimeMs, "Prim");
    }

    private Map<String, List<Edge>> buildAdjacencyList(List<Edge> edges) {
        Map<String, List<Edge>> adjacencyList = new HashMap<>();

        for (Edge edge : edges) {
            adjacencyList.computeIfAbsent(edge.getFrom(), k -> new ArrayList<>()).add(edge);
            adjacencyList.computeIfAbsent(edge.getTo(), k -> new ArrayList<>()).add(edge);
        }

        return adjacencyList;
    }
}