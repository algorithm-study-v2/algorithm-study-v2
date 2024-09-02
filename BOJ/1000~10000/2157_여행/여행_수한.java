package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2157_여행 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer tokens;
		
		/*
		 * N개 도시 서 <- 동
		 * N, N-1, ... , 1
		 *
		 * M개 이하 도시 지나는 여행
		 *
		 * 1번 -> N번
		 *
		 * 번호가 증가하는 순서대로만 이동
		 *
		 * 기내식 점수 총 합 최대
		 *
		 * N개 도시 1 <= N <= 300
		 * M개 여행 1 <= M <= N
		 * K개 항공로 1 <= K <= 100,000
		 *
		 * dp[i][j] : i번째 도시에 도착했을 때 j개 도시를 지났을 때 최대
		 * dp[i][j] = max(dp[c][j-1]+score[c][i])
		 *
		 */

		tokens = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());

		int[][] scores = new int[N+1][N+1];

		for (int i = 0; i < K; i++) {
			tokens = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int score = Integer.parseInt(tokens.nextToken());

			if (a < b) scores[a][b] = Math.max(scores[a][b],score);
		}

		int[][] dp = new int[N+1][M+1];

		for (int i = 2; i <= N; i++) {
			// i번째 도시에 도착
			for (int c = 1; c < i; c++) {
				if (scores[c][i] > 0) {
					// c도시에서 i도시로 가는 길이 있다면
					for (int j = Math.min(i, M); j >= 2; j--) {
						if((j==2 && c==1) || dp[c][j-1] > 0)
							// c 도시에 도착했을 때 j-1개 도시를 거칠 수 있다면
							dp[i][j] = Math.max(dp[i][j],dp[c][j-1]+scores[c][i]);
					}
				}
			}
		}

		int ans = 0;
		for (int i = 2; i <= M; i++) {
			ans = Math.max(ans, dp[N][i]);
		}

		System.out.println(ans);
	}
}
