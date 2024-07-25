import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 선물전달_승현 { // 100ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        final int MOD = 1000000000;

        long[] dp = new long[n+1];
        dp[1] = 0;
        if (n > 1) {
            dp[2] = 1;
        }

        for (int i = 3; i <= n; i++) {
            dp[i] = ((dp[i-1] + dp[i-2]) * (i-1)) % MOD;
        }

        System.out.println(dp[n]);
    }
}
