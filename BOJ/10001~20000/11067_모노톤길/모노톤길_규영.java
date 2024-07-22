import java.io.*;
import java.util.*;

/*
	338744KB, 5820ms
 */

public class 모노톤길_규영 {
	static class Node implements Comparable<Node> {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Node o) {
			if (this.x == o.x) return Integer.compare(this.y, o.y);
			return Integer.compare(this.x, o.x);
		}
	}
	static List<Node> nodes;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		while (T-->0) {
			int n = Integer.parseInt(br.readLine());
			nodes = new ArrayList<>();
			nodes.add(new Node(-1, 0));
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				nodes.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			Collections.sort(nodes);
			int idx = 0;
			while (idx < n) {
				if (nodes.get(idx).x == nodes.get(idx+1).x || nodes.get(idx).y == nodes.get(idx+1).y) idx++;
				else {
					int start = idx;
					// 반대로 된 구간을 찾아 뒤집어준다
					while (nodes.get(start).y != nodes.get(++idx).y && idx < n) {}
					List<Node> temp = nodes.subList(start+1, idx+1);
					Collections.reverse(temp);
				}
			}
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for (int i = 0; i < m; i++) {
				Node node = nodes.get(Integer.parseInt(st.nextToken()));
				sb.append(node.x).append(' ').append(node.y).append('\n');
			}
		}
		System.out.print(sb);
	}
}