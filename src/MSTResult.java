package src;

import java.util.List;

public class MSTResult {
    private List<Edge> mstEdges;
    private int totalCost;
    private int operationsCount;
    private double executionTimeMs;
    private String algorithmName;

    public MSTResult(List<Edge> mstEdges, int totalCost, int operationsCount,
                     double executionTimeMs, String algorithmName) {
        this.mstEdges = mstEdges;
        this.totalCost = totalCost;
        this.operationsCount = operationsCount;
        this.executionTimeMs = executionTimeMs;
        this.algorithmName = algorithmName;
    }

    public List<Edge> getMstEdges() { return mstEdges; }
    public int getTotalCost() { return totalCost; }
    public int getOperationsCount() { return operationsCount; }
    public double getExecutionTimeMs() { return executionTimeMs; }
    public String getAlgorithmName() { return algorithmName; }

    public boolean costsMatch(MSTResult other) {
        return this.totalCost == other.totalCost;
    }

    @Override
    public String toString() {
        return String.format("%s: Cost=%d, Ops=%,d, Time=%.3fms",
                algorithmName, totalCost, operationsCount, executionTimeMs);
    }
}