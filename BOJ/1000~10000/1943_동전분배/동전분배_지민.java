import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 동전분배_지민 {
	static int N;
	static int[][] coins;
	static int sum;
	static final int MAX = 100_000;

	private static int divide() {
		sum /= 2;
		boolean[] dp = new boolean[MAX + 1];

		dp[0] = true;
		for (int i = 0; i < coins.length; i++) {
			for (int j = sum; j > 0; j--) {
				int num = coins[i][0];
				int cnt = coins[i][1];
				if(j - num < 0) continue;
				if(dp[j - num]) {
					for (int k = 0; k < cnt; k++) {
						if(j + num * k > sum) continue;
						dp[j + num * k] = true;
					}
				}
			}
		}
		return dp[sum] ? 1: 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st;

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			coins = new int[N][2];
			sum = 0;
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				coins[j][0] = Integer.parseInt(st.nextToken());
				coins[j][1] = Integer.parseInt(st.nextToken());
				sum += coins[j][0] * coins[j][1];
			}
			if(sum % 2 == 1) System.out.println(0);
			else System.out.println(divide());
		}

	}
}
