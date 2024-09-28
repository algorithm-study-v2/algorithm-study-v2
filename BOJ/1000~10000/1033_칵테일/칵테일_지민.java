import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 84ms
 */
public class 칵테일_지민 {
	static int N;
	static int[] res;
	static List<List<Integer>> graph;

	private static int GCD(int a, int b) {
		return (a % b == 0) ? b : GCD(b, a % b);
	}

	private static void dfs(int u, int t, boolean[] visited) {
		visited[u] = true;
		res[u] *= t;
		for(int v : graph.get(u)) {
			if(visited[v]) continue;
			dfs(v, t, visited);
		}
	}

	private static void calc(int a, int b, int p, int q) {
		int k = res[b] * p;
		int t = res[a] * q;

		int gcd = GCD(k, t);
		k /= gcd;
		t /= gcd;

		dfs(a, k, new boolean[N]);
		dfs(b, t, new boolean[N]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		res = new int[N];
		Arrays.fill(res, 1);
		
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());

			int gcd = GCD(p, q);
			p /= gcd;
			q /= gcd;
			calc(a, b, p, q);
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) sb.append(res[i]).append(" ");
		System.out.println(sb);
	}
}
