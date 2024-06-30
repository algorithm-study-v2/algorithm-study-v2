import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자_지민 {
	static int N; // 도시의 수
	static int M; // 여행 도시의 수
	static int[][] graph;
	static int[] trip;
	static int[] parent;

	private static int findParent(int x) {
		if(x == parent[x]) return x;
		return parent[x] = findParent(parent[x]);
	}

	private static void union(int u, int v) {
		int uP = findParent(u);
		int vP = findParent(v);
		if(uP == vP) return;
		if(uP < vP) parent[vP] = uP;
		else parent[uP] = vP;
	}

	private static boolean isPossible() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(graph[i][j] == 0) continue;
				union(i, j);
			}
		}
		for (int i = 1; i < M; i++) {
			if(findParent(trip[i]) != findParent(trip[i - 1])) return false;
		}
		return true;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		trip = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			trip[i] = Integer.parseInt(st.nextToken()) - 1;
		}
		parent = new int[N];
		for (int i = 0; i < N; i++) parent[i] = i;
		System.out.println(isPossible() ? "YES" : "NO");
		// System.out.println(Arrays.toString(parent));
	}
}
