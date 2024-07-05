import java.io.*;
import java.util.*;

public class 가장큰정사각형_승현 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n][m];
        int maxVal = 0;

        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                dp[i][j] = Integer.parseInt(line[j]);
                maxVal = Math.max(maxVal, dp[i][j]);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (dp[i-1][j-1] > 0 && dp[i-1][j] > 0 && dp[i][j-1] > 0 && dp[i][j] > 0) {
                    int min = Math.min(dp[i-1][j-1], dp[i-1][j]);
                    dp[i][j] = Math.min(min, dp[i][j-1]) + 1;
                    maxVal = Math.max(maxVal, dp[i][j]);
                }
            }
        }

//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }

        System.out.println(maxVal * maxVal);
    }
}
