/*You are working as a network administrator for a company. The company has several computers (nodes) 
connected via network cables (edges). The connections are bidirectional, and not all computers are directly 
connected to each other.

Sometimes, when a computer sends a broadcast message, it should reach all other computers in the network
in the shortest number of hops (i.e., fewest intermediate computers). You are tasked to simulate this broadcast 
operation using Breadth-First Search (BFS).

Your goal is to write a function that:

Traverses the network from a given source computer

Returns the order in which the computers receive the broadcast message

Assumes each edge takes equal time to traverse	

Test Case 1 – Small Office Network

Computers: [A, B, C, D]

Connections:

A - B
A - C
B - D

Start: A

Expected Output:

Broadcast Order: A, B, C, D

Test Case 2 – Medium Network

Computers: [P, Q, R, S, T, U]

Connections:

P - Q
P - R
Q - S
R - T
T - U

Start: P

Output :

Broadcast Order: P, Q, R, S, T, U*/
import java.util.*;
public class Q3 {
    private Map<String, List<String>> graph;
    public Q3() {
        graph = new HashMap<>();
    }
    public void addConnection(String a, String b) {
        graph.putIfAbsent(a, new ArrayList<>());
        graph.putIfAbsent(b, new ArrayList<>());
        graph.get(a).add(b);
        graph.get(b).add(a);
    }
    public List<String> broadcast(String start) {
        List<String> order = new ArrayList<>(); 
        Queue<String> queue = new LinkedList<>(); 
        Set<String> visited = new HashSet<>();    
        queue.add(start); 
        visited.add(start);
        while (!queue.isEmpty()) {
            String curr = queue.poll(); 
            order.add(curr);
            for (String neighbor : graph.get(curr)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        return order;
    }
    public static void main(String[] args) {
        Q3 g = new Q3();
        g.addConnection("P", "Q");
        g.addConnection("P", "R");
        g.addConnection("Q", "S");
        g.addConnection("R", "T");
        g.addConnection("T", "U");
        System.out.println("Broadcast Order: " + g.broadcast("P"));

        Q3 g2 = new Q3();
        g2.addConnection("A", "B");
        g2.addConnection("A", "C");
        g2.addConnection("B", "D");
        g2.addConnection("C", "E");
        g2.addConnection("D", "F");
        System.out.println("Broadcast Order: " + g2.broadcast("A"));

    }
}
