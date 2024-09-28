import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 112ms
 */
public class 정상회담2_지민 {
	static int N;
	static final int MOD = 987654321;

	public static long solve() {
		long[] dp  = new long[N + 1];

		dp[0] = 1L;
		for (int i = 2; i <= N; i+=2) {
			for (int j = 0; j <= i - 2; j+=2) {
				dp[i] = (dp[i] + (dp[j] * dp[i - j - 2]) % MOD) % MOD;
			}
		}

		return dp[N];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		System.out.println(solve());
	}
}
