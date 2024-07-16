import java.io.*;
import java.util.*;

public class 데스노트_태연 {
	
	static int n;
	static int m;
	
	static int[] arr;
	static int[] dp;
	
	static int pow(int x) {
		return (int) Math.pow(m-x, 2);
	}
	
	static int getMin(int i) {
		int ptr = i;
		int curSum = arr[i];
		
		int min = dp[i-1] + pow(curSum);
		
		while(curSum <= m && ptr>0) {
			curSum += arr[--ptr]+1;
			
			if(curSum > m) break;
			
			min = Math.min(min, ((ptr>0) ? dp[ptr-1] : 0) + pow(curSum));
			
		}
		
		return min;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		if(n==1) {
			System.out.println(0);
			return;
		}
		
		arr = new int[n];
		
		// i번째 뒤에서 개행했을때 최솟값
		dp = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		dp[0] = pow(arr[0]);
		
		for(int i=1; i<n; i++) {
			dp[i] = getMin(i);
		}
		
		int ptr = n-1;
		int min = Integer.MAX_VALUE;
		int curSum = arr[ptr];
		
		while(curSum <= m && ptr>0) {
			curSum += arr[--ptr]+1;
			
			min = Math.min(min, dp[ptr]);
		}
		
		//System.out.println(Arrays.toString(dp));
		System.out.println((curSum > m) ? min : 0);
	}
}
