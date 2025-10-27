package src;

import java.util.*;

public class KruskalAlgorithm {

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        int operationsCount = 0;

        List<Edge> edges = new ArrayList<>(graph.getEdges());
        List<Edge> mstEdges = new ArrayList<>();

        edges.sort((a, b) -> a.getWeight() - b.getWeight());
        operationsCount += edges.size();

        UnionFind uf = new UnionFind(graph.getNodes());
        operationsCount += graph.getNodes().size();

        for (Edge edge : edges) {
            operationsCount++;

            if (mstEdges.size() == graph.getNodes().size() - 1) break;

            String root1 = uf.find(edge.getFrom());
            String root2 = uf.find(edge.getTo());
            operationsCount += 2;

            if (!root1.equals(root2)) {
                mstEdges.add(edge);
                uf.union(edge.getFrom(), edge.getTo());
                operationsCount += 2;
            }
        }

        long endTime = System.nanoTime();
        double timeMs = (endTime - startTime) / 1_000_000.0;

        int totalCost = 0;
        for (Edge edge : mstEdges) {
            totalCost += edge.getWeight();
        }
        operationsCount++;

        return new MSTResult(mstEdges, totalCost, operationsCount, timeMs, "Kruskal");
    }
}