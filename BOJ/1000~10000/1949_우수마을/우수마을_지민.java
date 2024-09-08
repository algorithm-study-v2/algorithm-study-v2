import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
- 이 문제는 dp가 아닌 탐욕이다..?
**/
public class Main {
	static int N;
	static int[] person;
	static List<List<Integer>> graph;

	private static void dfs(int curr, int[][] dp, boolean[] visited) {
		visited[curr] = true;
		for (int next : graph.get(curr)) {
			if(visited[next]) continue;
			dfs(next, dp, visited);
		}

		int nextMaxSum = 0;
		boolean check = false;
		for (int next : graph.get(curr)) {
			if(visited[next]) {
				dp[curr][1] += dp[next][0];
				if(dp[next][0] <= dp[next][1]) check = true;
				nextMaxSum += Math.max(dp[next][0], dp[next][1]);

			}
		}

		dp[curr][1] += person[curr];
		if(check) dp[curr][0] = nextMaxSum;
		else {
			for (int next : graph.get(curr)) {
				if(visited[next]) {
					dp[curr][0] = Math.max(dp[curr][0], nextMaxSum - dp[next][0] + dp[next][1]);
				}
			}
		}
	}

	private static int solve(int start) {
		boolean[] visited = new boolean[N + 1];
		int[][] dp = new int[N + 1][2];
		dfs(start, dp, visited);
		return Math.max(dp[start][0], dp[start][1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		person = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			person[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		System.out.println(solve(1));
	}
}
