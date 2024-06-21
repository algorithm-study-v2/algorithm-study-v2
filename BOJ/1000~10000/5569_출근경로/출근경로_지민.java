import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 출근경로_지민 {
	static int w;
	static int h;
	static final int MOD = 100000;

	private static int countingPath() {
		int[][][] dp = new int[w + 1][h + 1][2];

		dp[0][0][0] = dp[0][0][1] = 1;
		for (int i = 1; i <= w; i++) {
			for (int j = 1; j <= h; j++) {
				dp[i][j][0] = (dp[i - 1][j - 1][1] + dp[i][j - 1][0]) % MOD;
				dp[i][j][1] = (dp[i - 1][j - 1][0] + dp[i - 1][j][1]) % MOD;
			}
		}
		return (dp[w][h][0] + dp[w][h][1]) % MOD;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		System.out.println(countingPath());
	}
}
