import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 92ms
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long mod = 1000000000;
        long[] dp = new long[n + 1];
        if (n > 1) dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 2] + dp[i - 1]) % mod;
        }
        System.out.println(dp[n]);

    }
}