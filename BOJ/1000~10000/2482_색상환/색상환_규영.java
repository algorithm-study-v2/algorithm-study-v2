import java.io.*;

public class 색상환_규영 {
    public static void main(String[] args) throws Exception {
        final int MOD = 1000000003;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), K = Integer.parseInt(br.readLine());
        int[][] dp = new int[N+1][K+1];
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][1] = i;
        }
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= K; j++) {
                dp[i][j] = (dp[i-2][j-1]+dp[i-1][j])%MOD;
            }
        }
        System.out.print((dp[N-1][K]+dp[N-3][K-1])%MOD);
    }
}