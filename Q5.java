import java.util.*;

public class Q5 {
    static class Edge {
        String u, v;
        int weight;
        Edge(String u, String v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }
        public String toString() {
            return u + "-" + v + "(" + weight + ")";
        }
    }

    static class UnionFind {
        Map<String, String> parent = new HashMap<>();

        public String find(String x) {
            if (!parent.containsKey(x)) parent.put(x, x);
            if (!x.equals(parent.get(x))) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public boolean union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);
            if (rootX.equals(rootY)) return false;
            parent.put(rootX, rootY);
            return true;
        }
    }

    public void kruskalMST(List<Edge> edges, List<String> cities) {
        UnionFind uf = new UnionFind();
        List<Edge> mst = new ArrayList<>();
        int totalCost = 0;

        edges.sort(Comparator.comparingInt(e -> e.weight));

        for (Edge edge : edges) {
            if (uf.union(edge.u, edge.v)) {
                mst.add(edge);
                totalCost += edge.weight;
            }
        }

        System.out.println("Selected Tracks in MST:");
        for (Edge e : mst) System.out.println(e);
        System.out.println("Total Minimum Cost = " + totalCost);
        System.out.println();
    }

    public static void main(String[] args) {
        Q5 solver = new Q5();

        // Test Case 1
        System.out.println("=== Test Case 1 ===");
        List<Edge> edges1 = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("A", "C", 3),
            new Edge("B", "C", 2),
            new Edge("B", "D", 4),
            new Edge("C", "D", 5)
        );
        solver.kruskalMST(edges1, Arrays.asList("A", "B", "C", "D"));

        // Test Case 2
        System.out.println("=== Test Case 2 ===");
        List<Edge> edges2 = Arrays.asList(
            new Edge("P", "Q", 6),
            new Edge("P", "R", 1),
            new Edge("Q", "R", 5),
            new Edge("Q", "S", 3),
            new Edge("R", "S", 5),
            new Edge("S", "T", 2),
            new Edge("R", "T", 4)
        );
        solver.kruskalMST(edges2, Arrays.asList("P", "Q", "R", "S", "T"));
    }
}
