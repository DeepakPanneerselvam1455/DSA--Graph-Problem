/* A telecom company is tasked with restoring communication lines after a massive storm damaged their network in multiple cities.

They have a list of possible fiber-optic cable connections between cities, along with the cost of laying the cable.

The company’s goals are:

Reconnect all cities so that any city can communicate with any other city (directly or indirectly).

Minimize the total cost of laying cables.

Avoid cycles in the network to prevent data packet looping.

If there are multiple ways to achieve the same minimum cost, choose the one with more high-speed (prior) links.

prior links are marked in the data with a flag (1 for high-speed, 0 for normal).

When comparing equal-cost MSTs, prefer the one with a greater count of high-speed links.

You must output:

The total cost of the selected network.

The list of edges (city1, city2, cost, priorFlag) in the order they were added.
	
Test case 01 :

4 5
1 2 1 1
2 3 2 0
3 4 1 1
4 1 2 0
1 3 3 1

Total Cost: 4
Edges in MST:
1 2 1 1
3 4 1 1
2 3 2 0



Test case 02  :

5 6
1 2 3 0
2 3 4 0
3 4 2 0
4 5 6 0
1 5 5 0
2 5 4 0
Total Cost: 13
Edges in MST:
3 4 2 0
1 2 3 0
2 3 4 0
2 5 4 0

Test case 03 :

5 6
1 2 2 1
2 3 2 0
3 4 2 1
4 5 2 0
1 5 2 1
2 4 2 0
Total Cost: 8
Edges in MST:
1 2 2 1
3 4 2 1
1 5 2 1
2 3 2 0
*/

// Aproach 1
import java.util.*;

public class Q6 {
    static int find(int parent[], int i) {
        if (parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] edges = new int[m][4];
        for (int i = 0; i < m; i++) {
            edges[i][0] = sc.nextInt();
            edges[i][1] = sc.nextInt();
            edges[i][2] = sc.nextInt();
            edges[i][3] = sc.nextInt();
        }

        Arrays.sort(edges, (a, b) -> {
            if (a[2] != b[2]) return a[2] - b[2];
            return b[3] - a[3];
        });

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        int totalCost = 0;
        List<int[]> mst = new ArrayList<>();

        for (int[] e : edges) {
            int pu = find(parent, e[0]);
            int pv = find(parent, e[1]);
            if (pu != pv) {
                parent[pu] = pv;
                mst.add(e);
                totalCost += e[2];
            }
            if (mst.size() == n - 1) break;
        }

        System.out.println("Total Cost: " + totalCost);
        System.out.println("Edges in MST:");
        for (int[] e : mst) {
            System.out.println(e[0] + " " + e[1] + " " + e[2] + " " + e[3]);
        }
    }
}


// Approach 2

import java.util.*;

class Edge implements Comparable<Edge> {
    int city1, city2;
    int cost;
    int priority;

    public Edge(int city1, int city2, int cost, int priority) {
        this.city1 = city1;
        this.city2 = city2;
        this.cost = cost;
        this.priority = priority;
    }

    @Override
    public int compareTo(Edge other) {
        if (this.cost != other.cost) {
            return this.cost - other.cost;
        }
        return other.priority - this.priority;
    }
}

class DSU {
    int[] parent;
    int[] rank;

    public DSU(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

public class TelecomNetworkMST {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            int cost = sc.nextInt();
            int priority = sc.nextInt();
            edges.add(new Edge(city1, city2, cost, priority));
        }
        Collections.sort(edges);
        DSU dsu = new DSU(n);
        int totalCost = 0;
        List<Edge> mst = new ArrayList<>();
        for (Edge e : edges) {
            if (dsu.union(e.city1, e.city2)) {
                mst.add(e);
                totalCost += e.cost;
                if (mst.size() == n - 1) break;
            }
        }
        System.out.println("Total Cost: " + totalCost);
        System.out.println("Edges in MST:");
        for (Edge e : mst) {
            System.out.println(e.city1 + " " + e.city2 + " " + e.cost + " " + e.priority);
        }
        sc.close();
    }
}

