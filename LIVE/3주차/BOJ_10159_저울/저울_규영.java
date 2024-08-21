import java.io.*;
import java.util.*;

/*
	13416KB 112ms
 */

public class 저울_규영 {
	static int[] cnt;
	static boolean[] visited;
	static ArrayList<Integer>[] info;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());
		info = new ArrayList[N];
		for (int i = 0; i < N; i++) info[i] = new ArrayList<>();
		cnt = new int[N];
		Arrays.fill(cnt, N);
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			info[Integer.parseInt(st.nextToken())-1].add(Integer.parseInt(st.nextToken())-1);
		}
		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, i);
		}
		for (int i : cnt) sb.append(i).append('\n');
		System.out.print(sb);
	}
	static void dfs(int start, int now) {
		cnt[now]--;
		for (int next : info[now]) {
			if (visited[next]) continue;
			visited[next] = true;
			cnt[start]--;
			dfs(start, next);
		}
	}
}