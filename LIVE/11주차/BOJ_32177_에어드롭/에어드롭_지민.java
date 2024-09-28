import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 에어드롭_지민 {
	static class Phone {
		int x, y;
		int v;
		int p;

		public Phone(int x, int y, int v, int p) {
			this.x = x;
			this.y = y;
			this.v = v;
			this.p = p;
		}
	}

	static int N; // 친구 수
	static int K; // 에어드롭 최대 거리
	static int T; // 휴대폰 버전 차이
	static Phone[] friends;
	static List<List<Integer>> graph;

	private static void dfs(int node, boolean[] visit) {
		visit[node] = true;
		for (int next : graph.get(node)) {
			if (!visit[next])
				dfs(next, visit);
		}
	}

	private static double calcDist(int f1, int f2) {
		int x1 = friends[f1].x;
		int y1 = friends[f1].y;
		int x2 = friends[f2].x;
		int y2 = friends[f2].y;
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	private static String solve() {
		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (i == j)
					continue;
				if (Math.abs(friends[i].v - friends[j].v) > T)
					continue;
				if (calcDist(i, j) > K)
					continue;
				graph.get(i).add(j);
				graph.get(j).add(i);
			}
		}

		boolean[] visit = new boolean[N + 1];
		dfs(0, visit);

		boolean ok = false;
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (visit[i] && friends[i].p == 1) {
				sb.append(i).append(" ");
				ok = true;
			}
		}
		return !ok ? "0" : sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		friends = new Phone[N + 1];
		st = new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());
		friends[0] = new Phone(x, y, v, -1);

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			friends[i] = new Phone(x, y, v, p);
		}

		System.out.println(solve());

	}
}
