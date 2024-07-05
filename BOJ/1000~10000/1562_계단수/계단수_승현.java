import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 계단수_승현 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int modNum = 1000000000;

        // 길이, 1의자릿수, 비트마스킹
        long[][][] dp = new long[101][10][1024];

        for (int i = 1; i < 10; i++) {
            dp[1][i][1 << i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 1024; k++) {
                    int bit = k | (1 << j);
                    if (j == 0) {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][1][k]) % modNum;
                    }
                    else if (j < 9) {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][j-1][k] + dp[i-1][j+1][k]) % modNum;
                    }
                    else {
                        dp[i][j][bit] = (dp[i][j][bit] + dp[i-1][8][k]) % modNum;
                    }
                }
            }
        }

        long result = 0;
        for (int i = 0; i < 10; i++) {
            result = (result + dp[n][i][1023]) % modNum;
        }

        System.out.println(result);
    }
}
