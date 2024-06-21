import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2157_여행_지민 {
	static int N;	// 도시 개수
	static int M;	// 여행 도시 최대 개수
	static int K; 	// 개설된 항공로의 개수
	static int[][] scores;

	private static int findMaximumTotalSum() {
		int[][] dp = new int[N + 1][M + 1];

		for (int j = 2; j <= N; j++) dp[j][2] = scores[1][j];

		for (int m = 2; m < M; m++) {
			for (int i = 2; i <= N; i++) {
				for (int j = i + 1; j <= N; j++) {
					if(scores[i][j] == 0 || dp[i][m] == 0) continue;
					if(dp[j][m + 1] < dp[i][m] + scores[i][j]) dp[j][m + 1] = dp[i][m] + scores[i][j];
				}
			}
		}

		int res = 0;
		for (int i = 1; i <= M; i++) {
			if(res < dp[N][i]) res = dp[N][i];
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		scores = new int[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(scores[a][b] < c) scores[a][b] = c;
		}
		System.out.println(findMaximumTotalSum());
	}
}
