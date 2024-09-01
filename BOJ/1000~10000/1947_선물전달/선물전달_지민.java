import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 선물전달_지민 {
	static int N;
	static final int MOD = 1_000_000_000;

	private static long solve() {
		if(N <= 2) return N - 1;

		long[] dp = new long[N + 1];
		dp[1] = 0;
		dp[2] = 1;

		for (int i = 3; i <= N; i++) {
			dp[i] += ((i - 1) * (dp[i - 1] + dp[i - 2])) % MOD;
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
