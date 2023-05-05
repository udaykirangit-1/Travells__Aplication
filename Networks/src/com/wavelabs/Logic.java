package com.wavelabs;

import java.util.*;

public class Logic {

	public int networkDelayTime(int[][] times, int n, int k) {
		Map<Integer, List<int[]>> graph = new HashMap<>();
		for (int[] edge : times) {
			int u = edge[0], v = edge[1], w = edge[2];
			graph.putIfAbsent(u, new ArrayList<>());
			graph.get(u).add(new int[] { v, w });
		}

		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[k] = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> dist[a] - dist[b]);
		pq.offer(k);

		while (!pq.isEmpty()) {
			int u = pq.poll();
			if (!graph.containsKey(u))
				continue;
			for (int[] edge : graph.get(u)) {
				int v = edge[0], w = edge[1];
				if (dist[u] + w < dist[v]) {
					dist[v] = dist[u] + w;
					pq.offer(v);
				}
			}
		}

		int maxDist = 0;
		for (int i = 1; i <= n; i++) {
			if (dist[i] == Integer.MAX_VALUE)
				return -1;
			maxDist = Math.max(maxDist, dist[i]);
		}
		return maxDist;
	}
}
