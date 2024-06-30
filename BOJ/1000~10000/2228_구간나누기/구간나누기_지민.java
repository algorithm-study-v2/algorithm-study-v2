import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 구간나누기_지민 {
	static int N;
	static int M;
	static int[] nums;
	static final int MIN = -987654321;

	private static int[][] calcContinuousSum() {
		int[][] cSumMax = new int[N + 1][N + 1];
		for (int i = 0; i <= N; i++) Arrays.fill(cSumMax[i], MIN);

		for (int i = 1; i <= N; i++) {
			cSumMax[i][i] = nums[i];
			for (int j = i + 1; j <= N; j++) {
				int[] dp = new int[N + 1];
				for (int k = i; k <= j; k++) {
					dp[k] = Math.max(nums[k], dp[k - 1] + nums[k]);
					cSumMax[i][j] = Math.max(cSumMax[i][j], dp[k]);
				}
			}
		}
		return cSumMax;
	}

	private static int findMaxOfTotalSum() {
		int[][] cSumMax = calcContinuousSum();

		int[][] dp = new int[N + 1][M + 1];
		for (int i = 0; i <= N; i++) {
			Arrays.fill(dp[i], MIN);
		}

		for (int i = 1; i <= N; i++) dp[i][1] = cSumMax[1][i];
		for (int i = 2; i <= N; i++) {
			for (int k = 2; k <= M; k++) {
				dp[i][k] = dp[i - 1][k];
				for (int j = 1; j < i - 1; j++) {
					dp[i][k] = Math.max(dp[i][k], dp[j][k - 1] + cSumMax[j + 2][i]);
				}
			}
		}
		return dp[N][M];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		nums = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			nums[i] = Integer.parseInt(st.nextToken());
		}
		System.out.println(findMaxOfTotalSum());
	}
}
