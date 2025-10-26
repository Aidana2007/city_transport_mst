package src;

import java.util.*;

public class KruskalAlgorithm {
    private int operationsCount;

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        operationsCount = 0;

        if (graph.getNodes().isEmpty()) {
            throw new IllegalArgumentException("Graph cannot be empty");
        }

        List<Edge> edges = new ArrayList<>(graph.getEdges());
        List<Edge> mstEdges = new ArrayList<>();

        edges.sort(Comparator.comparingInt(Edge::getWeight));
        operationsCount += edges.size() * (int) Math.log(edges.size());

        UnionFind uf = new UnionFind(graph.getNodes());
        operationsCount += graph.getNodes().size();

        for (Edge edge : edges) {
            operationsCount++;

            String fromRoot = uf.find(edge.getFrom());
            String toRoot = uf.find(edge.getTo());
            operationsCount += 2;

            if (!fromRoot.equals(toRoot)) {
                mstEdges.add(edge);
                uf.union(edge.getFrom(), edge.getTo());
                operationsCount += 2;
            }

            if (mstEdges.size() == graph.getNodes().size() - 1) {
                break;
            }
        }

        if (mstEdges.size() != graph.getNodes().size() - 1) {
            System.out.println("⚠️  Warning: Graph may be disconnected. MST has " +
                    mstEdges.size() + " edges, expected " + (graph.getNodes().size() - 1));
        }

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        int totalCost = mstEdges.stream().mapToInt(Edge::getWeight).sum();
        operationsCount++;

        return new MSTResult(mstEdges, totalCost, operationsCount, executionTimeMs, "Kruskal");
    }
}