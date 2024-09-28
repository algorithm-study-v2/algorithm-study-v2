import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 길이가 L인 서로 다른 올바른 괄호 문자열의 개수를 출력
 */
public class 괄호_지민 {
	static int L;
	static final int MAX = 5000;
	static final int MOD = 1000000007;

	private static long[] solve() {
		long[] dp = new long[MAX + 1];

		dp[0] = 1;
		for (int i = 2; i <= MAX; i+=2) {
			for (int j = 2; j <= i; j+=2) {
				dp[i] += (dp[j - 2] * dp[i - j]) % MOD;
                dp[i] %= MOD;
			}
		}
		return dp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		long[] dp = solve();
		for (int i = 0; i < T; i++) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			System.out.println(dp[L]);
		}
	}
}
