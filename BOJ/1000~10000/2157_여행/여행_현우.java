import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 2157 여행
 * 324 ms
 * DP
 * dp[i][j] : i 도시에 j 개의 도시를 경유하여
 * 	도착할 때 최대 기내식 점수
 * adj[i][l] : i 도시에서 l 도시로 가는 항로의 기내식 점수
 * dp[l][j + 1]
 * = Math.max(dp[l][j + 1], dp[i][j] + adj[i][l]);
 * */
public class 여행_현우 {
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int k;
		int a;
		int b;
		int i;
		int j;
		int l;
		int max;
		int[][] dp;
		int[][] adj;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		adj = new int[n][n + 1];
		for (i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			if (a < b) { // 기내식 점수 최대값만 저장
				adj[a][b] = Math.max(adj[a][b], Integer.parseInt(st.nextToken()));
			}
		}
		dp = new int[n + 1][m + 1];
		dp[1][1] = 1; // 초기값 1로 설정 후 추후에 보정
		for (i = 1; i < n; i++) {
			for (j = 1; j < m; j++) {
				if (dp[i][j] != 0) { // i 도시에 j 개의 도시를 경유하여 도달할 수 있는 경우
					for (l = i + 1; l <= n; l++) {
						if (adj[i][l] != 0) { // 항로가 개설된 경우
							dp[l][j + 1] = Math.max(dp[l][j + 1], dp[i][j] + adj[i][l]);
						}
					}
				}
			}
		}
		max = 1;
		for (i = 1; i <= m; i++) { // N 도시에 도달한 모든 경우 비교
			max = Math.max(max, dp[n][i]);
		}
		System.out.print(max - 1); // 초기값 1 보정
	}
}
