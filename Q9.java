/*A delivery company operates in a city where locations are connected by roads with different travel times (in minutes).

The company wants to determine the shortest delivery time from its main warehouse to all other locations in the city.

However, due to traffic restrictions:

Travel times between some locations may be different in each direction.

Some locations are not directly connected.

You are given:

V → Total number of locations (numbered from 0 to V-1)

A list of roads in the form (source, destination, travel_time)

A start location representing the warehouse.

Your task:

Using Dijkstra’s Algorithm, calculate the shortest delivery time from the warehouse to every location in the city.

If a location is unreachable, mark its delivery time as "INF".

Input :

V = 6
roads = [
    (0, 1, 7),
    (0, 2, 9),
    (0, 5, 14),
    (1, 2, 10),
    (1, 3, 15),
    (2, 3, 11),
    (2, 5, 2),
    (3, 4, 6),
    (4, 5, 9)
]
start = 0

Output :

Shortest delivery times from warehouse (0):
Location 0 → 0 minutes
Location 1 → 7 minutes
Location 2 → 9 minutes
Location 3 → 20 minutes
Location 4 → 20 minutes
Location 5 → 11 minutes

*/

import java.util.*; 
public class Q9 {
    public static void main(String[] args) {
        int V = 6;
        List<int[]> roads = Arrays.asList(
            new int[]{0, 1, 7},
            new int[]{0, 2, 9},
            new int[]{0, 5, 14},
            new int[]{1, 2, 10},
            new int[]{1, 3, 15},
            new int[]{2, 3, 11},
            new int[]{2, 5, 2},
            new int[]{3, 4, 6},
            new int[]{4, 5, 9}
        );
        int start = 0;

        dijkstra(V, roads, start);
    }

    public static void dijkstra(int V, List<int[]> roads, int start) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] road : roads) {
            graph.putIfAbsent(road[0], new ArrayList<>());
            graph.get(road[0]).add(new int[]{road[1], road[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{start, 0});
        
        Map<Integer, Integer> distances = new HashMap<>();
        for (int i = 0; i < V; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];
            int dist = current[1];

            if (dist > distances.get(node)) continue;

            for (int[] neighbor : graph.getOrDefault(node, Collections.emptyList())) {
                int nextNode = neighbor[0];
                int travelTime = neighbor[1];
                if (dist + travelTime < distances.get(nextNode)) {
                    distances.put(nextNode, dist + travelTime);
                    pq.offer(new int[]{nextNode, dist + travelTime});
                }
            }
        }

        System.out.println("Shortest delivery times from warehouse (" + start + "):");
        for (int i = 0; i < V; i++) {
            System.out.println("Location " + i + " -> " + (distances.get(i) == Integer.MAX_VALUE ? "INF" : distances.get(i) + " minutes"));
        }
    }
}