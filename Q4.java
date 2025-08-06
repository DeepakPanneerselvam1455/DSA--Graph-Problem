/*You are working as a network engineer responsible for ensuring that all parts of a company's internal computer network are properly connected.
The network consists of several computers (nodes) connected by network cables (edges with weights representing latency). However, due to missing or faulty cables, some computers might be disconnected from others, forming separate components.
You are tasked to:
Apply Prim’s Algorithm starting from any computer in a component.
Continue running Prim's on unvisited components to ensure all parts are checked.
Detect and report all disconnected components in the network.
For each connected component, return:
Its Minimum Spanning Tree (MST)
The total cost (latency)
Input Format:
List of computers (nodes): ["A", "B", "C", "D", "E", "F"]
List of weighted connections: [("A", "B", 3), ("A", "C", 1), ...]
Graph is undirected and weighted
 Output Format:
For each disconnected component:
List of edges in MST with weights
Total cost of that MST
Test Case 1 – Fully Connected Network
Computers: [A, B, C, D]
Connections:
A - B (1)
A - C (2)
B - C (2)
C - D (3)
expected Output:
component 1:
MST Edges: A-B(1), A-C(2), C-D(3)
Total Cost: 6
Test Case 2 – Disconnected Network
Computers: [P, Q, R, S, T, U]
Connections:
P - Q (4)
Q - R (1)
S - T (3)
T - U (2)
Component 1:
MST Edges: Q-R(1), P-Q(4)
Total Cost: 5
Component 2:
MST Edges: T-U(2), S-T(3)
Total Cost: 5*/

import java.util.*;
public class Q4 {
    static class Edge {
        String node;
        int weight;
        Edge(String node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }

    static class Pair {
        String from, to;
        int weight;
        Pair(String from, String to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
        public String toString() {
            return from + "-" + to + "(" + weight + ")";
        }
    }

    Map<String, List<Edge>> graph = new HashMap<>();
    Set<String> visited = new HashSet<>();

    public void addEdge(String u, String v, int w) {
        graph.putIfAbsent(u, new ArrayList<>());
        graph.putIfAbsent(v, new ArrayList<>());
        graph.get(u).add(new Edge(v, w));
        graph.get(v).add(new Edge(u, w));
    }

    public void findMSTForAllComponents() {
        int component = 1;
        for (String node : graph.keySet()) {
            if (!visited.contains(node)) {
                System.out.println("Component " + component + ":");
                primMST(node);
                component++;
            }
        }
    }

    private void primMST(String start) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        List<Pair> mstEdges = new ArrayList<>();
        int totalCost = 0;

        visited.add(start);
        for (Edge e : graph.get(start)) {
            pq.add(new Pair(start, e.node, e.weight));
        }

        while (!pq.isEmpty()) {
            Pair edge = pq.poll();
            if (visited.contains(edge.to)) continue;

            visited.add(edge.to);
            mstEdges.add(edge);
            totalCost += edge.weight;

            for (Edge neighbor : graph.get(edge.to)) {
                if (!visited.contains(neighbor.node)) {
                    pq.add(new Pair(edge.to, neighbor.node, neighbor.weight));
                }
            }
        }

        System.out.println("MST Edges: " + mstEdges);
        System.out.println("Total Cost: " + totalCost + "\n");
    }

    public static void main(String[] args) {
        // Test Case 1
        System.out.println("=== Test Case 1 ===");
        Q4 g1 = new Q4();
        g1.addEdge("A", "B", 1);
        g1.addEdge("A", "C", 2);
        g1.addEdge("B", "C", 2);
        g1.addEdge("C", "D", 3);
        g1.findMSTForAllComponents();

        // Test Case 2
        System.out.println("=== Test Case 2 ===");
        Q4 g2 = new Q4();
        g2.addEdge("P", "Q", 4);
        g2.addEdge("Q", "R", 1);
        g2.addEdge("S", "T", 3);
        g2.addEdge("T", "U", 2);
        g2.findMSTForAllComponents();
    }
}
