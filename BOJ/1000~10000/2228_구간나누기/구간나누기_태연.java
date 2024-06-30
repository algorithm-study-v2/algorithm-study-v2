import java.io.*;
import java.util.*;

public class 구간나누기_태연 {
	
	/*
	 * - 84ms
	 * 
	 * - dp[n][m]을 n번째 수가 m번째 구간의 마지막 값일때로 놓고 연산
	 */
	
	static final int MIN = -13276900;
	
	static void print(int[][] dp) {
		for(int i=0; i<dp.length; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
				
		int[] raw = new int[n];
		
		for(int i=0; i<n; i++) {
			raw[i] = Integer.parseInt(br.readLine());
		}
		
		if(n==1) {
			System.out.println(raw[0]);
			return;
		}
		
		// n번째 수가 m번째 구간의 마지막 값일때 그때 합
		int[][] dp = new int[n][m+1];
		
		for(int i=0; i<n; i++) {
			for(int j=1; j<=m; j++) {
				dp[i][j] = MIN;
			}
		}
		
		dp[0][1] = raw[0];
		dp[1][1] = (raw[0] > 0) ? raw[0]+raw[1] : raw[1];
		
		for(int i=2; i<n; i++) {
			
			for(int j=1; j<=m; j++) {
				dp[i][j] = dp[i-1][j] + raw[i];
				
				for(int k=0; k<i-1; k++) {
					
					int temp = dp[k][j-1] + raw[i];
					
					if(temp > dp[i][j]) {
						dp[i][j] = temp;
					}
				}
			}
		}
		
		int max = Integer.MIN_VALUE;
		
		for(int i=0; i<n; i++) {
			if(dp[i][m] > max) {
				max = dp[i][m];
			}
		}
		
		//print(dp);
		
		System.out.println(max);
	}
}
