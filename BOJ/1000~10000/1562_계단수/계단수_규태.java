import java.io.*;

public class 계단수_규태 {
	/*
	 * 112ms
	 * 3차원 dp와 비트마스킹을 통해 자릿수, 끝난 숫자, 0~9까지의 숫자 존재여부를 dp에 저장하여
	 * 가능한 계단수의 경우의 수를 계산
	 */
	static final int MOD=1000000000;
	static long[][][] dp;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new long[n][10][1<<10];
        for(int i=1; i<10; i++) {
			dp[0][i][1<<i] = 1;
		}
        
        for(int i=1; i<n; i++) {
			for(int j=0; j<10; j++) {
				for(int k=0; k<1024; k++) {
					int bit = k | (1<<j);
					
					if(j==0) {
						dp[i][0][bit]+=dp[i-1][1][k]; 
						dp[i][0][bit]%=MOD;
					}
					else if(j==9) {
						dp[i][9][bit]+=dp[i-1][8][k];
						dp[i][9][bit]%=MOD;
					}
					else {
						dp[i][j][bit]+=(dp[i-1][j-1][k] + dp[i-1][j+1][k]);
						dp[i][j][bit]%=MOD;
					}
				}
			}
		}
		
		long sum = 0;
		for(int i=0; i<10; i++) {
			sum += dp[n-1][i][1023];
			sum %= MOD;
		}
		System.out.println(sum);
	}
}