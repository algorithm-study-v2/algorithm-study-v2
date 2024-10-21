import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 낮잠시간_태연 {

    /**
     * updateIndex 위치에서 memoization값을 update함
     * 
     */
    static void updateMemo(int[][]dp, int[] memo, int maxB, int updateIndex){
        for(int i=0; i<=maxB; i++){
            if(dp[updateIndex][i] > memo[i]) {
                memo[i] = dp[updateIndex][i];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[][] dp = new int[n][b+1];                   // i시간에 j번째 잠을 잤을때 피로회복 최대량
        int[] memo = new int[b+1];                      // j번 잠을 잤을때 피로회복 최대량

        // 초기값 세팅
        Integer.parseInt(br.readLine());                // 첫번째값 날림
        dp[1][2] = Integer.parseInt(br.readLine());

        for(int i=2; i<n; i++){
            int num = Integer.parseInt(br.readLine());

            updateMemo(dp, memo, b, i-2);      // i-2위치까지 memoization 업데이트
                                                         // i-1위치의 memoization 값은 dp[i]에서 참조하지 않아야함

            for(int j=2; j<=b; j++){
                dp[i][j] = Math.max(dp[i-1][j-1] + num, memo[j-1]);
            }
        }

        updateMemo(dp, memo, b, n-2);
        updateMemo(dp, memo, b, n-1);

        System.out.println(memo[b]);
    }
}
