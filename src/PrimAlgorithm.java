package src;

import java.util.*;

public class PrimAlgorithm {

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        int operationsCount = 0;

        List<String> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();
        List<Edge> mstEdges = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.getWeight() - b.getWeight());

        Map<String, List<Edge>> adj = new HashMap<>();
        for (Edge edge : edges) {
            adj.computeIfAbsent(edge.getFrom(), k -> new ArrayList<>()).add(edge);
            adj.computeIfAbsent(edge.getTo(), k -> new ArrayList<>()).add(edge);
            operationsCount += 2;
        }

        if (!nodes.isEmpty()) {
            visited.add(nodes.get(0));
            operationsCount++;

            if (adj.containsKey(nodes.get(0))) {
                for (Edge edge : adj.get(nodes.get(0))) {
                    pq.add(edge);
                    operationsCount++;
                }
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

                if (adj.containsKey(nextNode)) {
                    for (Edge edge : adj.get(nextNode)) {
                        if (!visited.contains(edge.getFrom()) || !visited.contains(edge.getTo())) {
                            pq.add(edge);
                            operationsCount++;
                        }
                    }
                }
            }
        }

        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;

        int totalCost = 0;
        for (Edge edge : mstEdges) {
            totalCost += edge.getWeight();
        }
        operationsCount++;

        return new MSTResult(mstEdges, totalCost, operationsCount, timeMs, "Prim");
    }
}