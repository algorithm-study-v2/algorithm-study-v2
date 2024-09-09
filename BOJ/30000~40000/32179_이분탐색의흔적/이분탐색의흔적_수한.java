package algorithm.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_32179_이분탐색의흔적 {

	static final int MOD = 1_000_000_007;
	static int[][] dp;

	public static int comb(int n, int r) {
		if(n == r || r == 0) return 1;
		if(dp[n][r] != -1) return dp[n][r];
		return dp[n][r] = (comb(n - 1, r - 1) + comb(n - 1, r)) % MOD;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer tokens;
		
		/*
		 * A 배열
		 * - 오름차순 정렬
		 * - 길이 N
		 *
		 * 준성
		 * - 배열 원소 중 무작위 하나 선택
		 *
		 * 답 구간
		 * - 준성이 선택한 원소가 존재할 수 있는 인덱스 구간
		 * - [1,N]으로 시작
		 * 
		 * 건이
		 * - 답 구간 원소 중 한 원소 물어봄
		 * - 준성이 Yes, Up, Down중 하나 답
		 * - Yes시 종료
		 * - [s,e] => a_(s + [(e-s)/2]) 물어봄
		 *
		 * 이분탐색 흔적
		 * - 건이가 부른 K개 원소
		 *
		 * 이분탐색 흔적이 주어졌을 때, 배열 A로 가능한 배열의 개수
		 *
		 * N = 3, t = {50}
		 *
		 * [49,50,51]
		 *
		 * 왼 = 1 ~ 49 => 49
		 * 오 = 51 ~ 100 => 50
		 *
		 * t = {a_(1+[(1+N)/2])}
		 *
		 * 범위가 줄어들 수록 줄어드는 구간에 있는 경우의 수 곱함
		 * [s,e] => a_(s + [(e-s)/2]) 물어봄
		 * - down => (a_e - a_(s + [(e-s)/2] + 1) + 1)C(e - (s + [(e-s)/2]))
		 */

		tokens = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());

		dp = new int[101][101];

		for (int i = 1; i <= 100; i++) {
			Arrays.fill(dp[i],-1);
		}

		tokens = new StringTokenizer(br.readLine());
		int[] t = new int[K];
		for (int i = 0; i < K; i++) {
			t[i] = Integer.parseInt(tokens.nextToken());
		}

		long result = 1;
		int s = 1;
		int e = N;
		int m;
		int a_s = 1;
		int a_e = 100;
		// Up, Down
		int n,r;
		for (int i = 0; i < K-1; i++) {
			m = s + (e - s) / 2;
			if (t[i] < t[i + 1]) {
				// Up
				n = t[i] - a_s;
				r = m - s;
				s = m + 1;
				a_s = t[i]+1;
			}else{
				// Down
				n = a_e - t[i];
				r = e - m;
				e = m - 1;
				a_e = t[i]-1;
			}
			result = (result * comb(n,r) % MOD);
		}

		// Yes
		m = s + (e - s) / 2;
		n = t[K-1] - a_s;
		r = m - s;
		result = (result * comb(n,r) % MOD);
		n = a_e - t[K-1];
		r = e - m;
		result = (result * comb(n,r) % MOD);

		System.out.println(result);
	}
}
