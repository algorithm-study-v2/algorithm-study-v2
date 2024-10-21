import java.io.*;
import java.util.*;

/*
	279152KB, 3084ms
 */

public class 미확인도착지_규영 {
	static class Node implements Comparable<Node> {
		int end, value;
		Node(int end, int value) {
			this.end = end;
			this.value = value;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
	}
	static int n;
	static ArrayList<Node>[] info;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-->0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken())-1, g = Integer.parseInt(st.nextToken())-1, h = Integer.parseInt(st.nextToken())-1;
			info = new ArrayList[n];
			for (int i = 0; i < n; i++) info[i] = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1, d = Integer.parseInt(st.nextToken());
				info[a].add(new Node(b, d));
				info[b].add(new Node(a, d));
			}
			int[] destinations = new int[t];
			for (int i = 0; i < t; i++) destinations[i] = Integer.parseInt(br.readLine())-1;
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int i : destinations) {
				int res1 = findMinValue(s, h)+findMinValue(h, g)+findMinValue(g, i);
				int res2 = findMinValue(s, g)+findMinValue(g, h)+findMinValue(h, i);
				int res3 = findMinValue(s, i);
				if (Math.min(res1, res2) == res3) pq.offer(i);
			}
			while (!pq.isEmpty()) sb.append(pq.poll()+1).append(' ');
			sb.append('\n');
		}
		System.out.print(sb);
	}
	static int findMinValue(int start, int end) {
		boolean[] visited = new boolean[n];
		int[] values = new int[n];
		Arrays.fill(values, Integer.MAX_VALUE);
		values[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int next = now.end;
			if (visited[next]) continue;
			visited[next] = true;
			if (now.value > values[next]) continue;
			for (Node n : info[next]) {
				if (values[n.end] > values[next]+n.value) {
					values[n.end] = values[next]+n.value;
					pq.offer(new Node(n.end, values[n.end]));
				}
			}
		}
		return values[end];
	}
}