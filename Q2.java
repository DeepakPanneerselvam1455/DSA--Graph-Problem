package Test1;
import java.util.*;
public class Q2 {
	static class Pair {
		int v, p, w;
		Pair(int v, int p, int w) {
			this.v = v;
			this.p = p;
			this.w = w;
		}
	}
	static final String[] cities = {"A", "B", "C", "D", "E"};
	public static void main(String[] args) {
		int V = 5;
		List<List<int[]>> g = new ArrayList<>();
		for (int i = 0; i < V; i++) {
			g.add(new ArrayList<>());
		}
		addEdge(g, 0, 1, 2); // A B 2
		addEdge(g, 0, 2, 3); // A C 3
		addEdge(g, 1, 2, 1); // B C 1
		addEdge(g, 1, 3, 4); // B D 4
		addEdge(g, 2, 3, 5); // C D 5
		addEdge(g, 2, 4, 6); // C E 6
		addEdge(g, 3, 4, 7); // D E 7
		prim(g, V);
	}

	static void addEdge(List<List<int[]>> g, int u, int v, int w) {
		g.get(u).add(new int[]{v, w});
		g.get(v).add(new int[]{u, w});
	}

	static void prim(List<List<int[]>> g, int V) {
		boolean[] visited = new boolean[V];
		PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.w));
		pq.offer(new Pair(0, -1, 0));
		int sum = 0;
		List<Pair> mst = new ArrayList<>();
		while (!pq.isEmpty()) {
			Pair curr = pq.poll();
			if (visited[curr.v]) continue;
			visited[curr.v] = true;
			if (curr.p != -1) {
				mst.add(new Pair(curr.v, curr.p, curr.w));
				sum += curr.w;
			}
			for (int[] neigh : g.get(curr.v)) {
				if (!visited[neigh[0]]) {
					pq.offer(new Pair(neigh[0], curr.v, neigh[1]));
				}
			}
		}
		mst.sort(Comparator.comparingInt(a -> a.w));
		System.out.println("Selected Edges in MST");
		for (Pair edge : mst) {
			System.out.println(cities[edge.p] + " - " + cities[edge.v] + " with cost " + edge.w);
		}
		System.out.println("Total Minimum Cost: " + sum);
	}
}
 