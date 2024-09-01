import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 1562 계단 수
 * - 68 ms
 * - DP
 * - 계단수가 0과 9를 동시에 가질 경우 0 ~ 9를 모두 가짐
 * - = (전체 계단 수) - (0, 9를 동시에 갖지 않는 계단 수)
 * - bit : 0, 9가 없으면 0b00, 동시에 가질 경우 0b11,
 * -       0만 가질 경우 0b01, 9만 가질 경우 0b10
 * - dp[idx][num][bit]
 * - = 0 ~ idx 부분 계단수가 bit 상태일 때,
 * -   0 ~ N - 1 계단수가 0, 9를 모두 갖지 않을 경우의 수
 * - 대칭성 이용 : num이 5 이상인 경우
 * -   dp[idx][num][bit] = dp[idx][10 - num - 1][뒤집힌 bit]
 * */
public class 계단수_현우 {
    private static final int MOD = 1_000_000_000;
    private static final int DIGIT = 10;
    private static final int HALF = DIGIT >> 1;
    private static final int EMPTY = -1;
    private static final int NONE = 0; // 0b00
    private static final int ZERO = 1; // 0b01
    private static final int NINE = 1 << 1; // 0b10
    private static final int BOTH = ZERO | NINE; // 0b11

    private static long[][] stair; // 전체 계단 수
    private static long[][][] dp; // 0, 9를 동시에 갖지 않는 계단 수

    private static long getStair(int idx, int num) {
        long sum;

        if (num >= HALF) { // num이 5 이상일 경우
            num = DIGIT - num - 1; // 10 - num - 1로 대칭
        }
        if (stair[idx][num] != EMPTY) {
            return stair[idx][num];
        }
        sum = 0;
        if (num > 0) {
            sum = (sum + getStair(idx + 1, num - 1)) % MOD; // num 1 감소
        }
        sum = (sum + getStair(idx + 1, num + 1)) % MOD; // num 1 증가
        return stair[idx][num] = sum;
    }

    private static long getDp(int idx, int num, int bit) {
        long sum;

        if (num >= HALF) { // num이 5 이상일 경우
            num = DIGIT - num - 1; // 10 - num - 1로 대칭
            bit = ((bit & ZERO) << 1) | ((bit & NINE) >> 1); // bit 뒤집기
        }
        if (dp[idx][num][bit] != EMPTY) {
            return dp[idx][num][bit];
        }
        sum = 0;
        if (num == 1) { // num 1 감소, 0 도달
            sum = (sum + getDp(idx + 1, 0, bit | ZERO)) % MOD;
        } else if (num > 1) { // num 1 감소
            sum = (sum + getDp(idx + 1, num - 1, bit)) % MOD;
        }
        sum = (sum + getDp(idx + 1, num + 1, bit)) % MOD; // num 1 증가
        return dp[idx][num][bit] = sum;
    }

    public static void main(String[] args) throws IOException {
        int n;
        int i;
        int j;
        int k;
        int end;
        long sum;
        long ans;
        BufferedReader br;

        br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        stair = new long[n][HALF]; // 전체 계단 수
        dp = new long[n][HALF][BOTH + 1]; // 0, 9를 동시에 갖지 않는 계단 수
        end = n - 1;
        for (i = 0; i < end; i++) { // -1로 초기화
            for (j = 0; j < HALF; j++) {
                stair[i][j] = EMPTY;
                for (k = 0; k < BOTH; k++) { // bit가 0b11인 경우는 dp 0 가지이므로 제외
                    dp[i][j][k] = EMPTY;
                }
            }
        }
        dp[end][0][ZERO] = 1; // dp[N - 1][num = 0][0b01] = 1 가지
        stair[end][0] = 1;
        for (i = 1; i < HALF; i++) {
            stair[end][i] = 1; // stair[N - 1][num] = 1 가지
            for (j = 0; j < BOTH; j++) {
                dp[end][i][j] = 1; // dp[N - 1][num != 0][0b00, 0b01, 0b10] = 1 가지
            }
        }
        sum = 0;
        for (i = 0; i < HALF; i++) { // 0 ~ 4로 시작
            sum = (sum + getStair(0, i)) % MOD; // 전체 계단 수
        }
        sum = (sum << 1) % MOD; // 5 ~ 9 시작과 대칭
        ans = (sum - getStair(0, 0)) % MOD; // 0 시작 제외
        sum = getDp(0, 0, ZERO) % MOD; // 0으로 시작
        for (i = 1; i < HALF; i++) { // 1 ~ 4로 시작
            sum = (sum + getDp(0, i, NONE)) % MOD; // 0, 9를 동시에 갖지 않는 계단 수
        }
        sum = (sum << 1) % MOD; // 5 ~ 9 시작과 대칭
        ans = (ans - ((sum - getDp(0, 0, ZERO)) % MOD)) % MOD; // stair - dp
        System.out.print(ans < 0 ? ans + MOD : ans);
    }
}