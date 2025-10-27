package src;

import java.util.*;

public class UnionFind {
    private Map<String, String> parent;
    private Map<String, Integer> rank;

    public UnionFind(List<String> nodes) {
        parent = new HashMap<>();
        rank = new HashMap<>();

        for (String node : nodes) {
            parent.put(node, node);
            rank.put(node, 0);
        }
    }

    public String find(String node) {
        List<String> path = new ArrayList<>();
        String current = node;

        while (!parent.get(current).equals(current)) {
            path.add(current);
            current = parent.get(current);
        }
        String root = current;

        for (String compressedNode : path) {
            parent.put(compressedNode, root);
        }

        return root;
    }

    public void union(String node1, String node2) {
        String root1 = find(node1);
        String root2 = find(node2);

        if (!root1.equals(root2)) {
            if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);
            } else if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);
            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
        }
    }
}