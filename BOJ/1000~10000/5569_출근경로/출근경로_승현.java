import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 출근경로_승현 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][][] dp = new int[w][h][2][2];

        for (int i = 0; i < w; i++) {
            dp[i][0][0][0] = 1;
        }
        for (int i = 0; i < h; i++) {
            dp[0][i][1][0] = 1;
        }

        for (int i = 1; i < w; i++) {
            for (int j = 1; j < h; j++) {
                dp[i][j][0][0] = (dp[i-1][j][0][0] + dp[i-1][j][0][1]) % 100000;
                dp[i][j][1][0] = (dp[i][j-1][1][0] + dp[i][j-1][1][1]) % 100000;
                dp[i][j][0][1] = dp[i-1][j][1][0] % 100000;
                dp[i][j][1][1] = dp[i][j-1][0][0] % 100000;
            }
        }

        System.out.println((dp[w-1][h-1][0][0] + dp[w-1][h-1][0][1] + dp[w-1][h-1][1][0] + dp[w-1][h-1][1][1]) % 100000);

    }
}
