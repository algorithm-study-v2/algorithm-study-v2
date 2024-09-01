import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티_지민 {
	static class Node implements Comparable<Node> {
		int node;
		int cost;
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	static int N;
	static int M;
	static int X;
	static List<List<Node>> graph;
	static List<List<Node>> revGraph;
	static final int INF = 987654321;

	private static int[] dijkstra(List<List<Node>> graph)  {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1);

		dist[X] = 0;
		pq.add(new Node(X, 0));

		while (!pq.isEmpty()) {
			Node here = pq.poll();

			if(here.cost > dist[here.node]) continue;

			for(Node next : graph.get(here.node)) {
				int nextDist = dist[here.node] + next.cost;
				if(dist[next.node] == -1 || dist[next.node] > nextDist) {
					dist[next.node] = nextDist;
					pq.add(new Node(next.node, nextDist));
				}
			}
		}

		return dist;
	}

	private static int findLongestDistance() {
		int[] dist = dijkstra(graph);
		int[] revDist = dijkstra(revGraph);

		int res = 0;
		for (int i = 1; i <= N; i++) {
			if(i == X) continue;
			if(res < dist[i] + revDist[i]) res = dist[i] + revDist[i];
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		revGraph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
			revGraph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(u).add(new Node(v, cost));
			revGraph.get(v).add(new Node(u, cost));
		}

		System.out.println(findLongestDistance());
	}
}
