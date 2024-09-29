import java.io.*;
import java.util.*;

public class 정상회담2_태연 {
	
	/*
	 *  - 124ms
	 * 
	 *  - dp
	 */
	
	static long[] dp;
	static final long DIV = 987_654_321L;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		if(n==2) {
			System.out.println(1);
			return;
		}

		dp = new long[n/2+1];
		dp[0] = 1L;
		dp[1] = 1L;
				
		for(int i=2; i<=n/2; i++) {
			for(int j=0; j<i; j++) {
				dp[i] = (dp[i] + ((dp[j] * dp[i-j-1])%DIV))%DIV;
			}
		}
		
		System.out.println(dp[n/2]);
	}
}
