import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class 어금지_태연 {
		
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		StringTokenizer st1 = new StringTokenizer(br.readLine());
		StringTokenizer st2 = new StringTokenizer(br.readLine());
		StringTokenizer st3 = new StringTokenizer(br.readLine());

		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		// n번째 시각을 지났을때 가질수있는 최대값
		long[] dp = new long[n];
								
		Entry<Integer, Integer> temp;
		
		for(int i=0; i<n; i++) {
			
			int time = Integer.parseInt(st1.nextToken());
			int coolTime = Integer.parseInt(st2.nextToken());
			int value = Integer.parseInt(st3.nextToken());
			
			map.put(time, i);
			
			if(i==0) {
				dp[0] = value;
			} else {
				if((temp=map.lowerEntry(time - coolTime)) != null) {
					dp[i] = Math.max(dp[i-1], value + dp[temp.getValue()]);
				} else {
					dp[i] = Math.max(dp[i-1], value);
				}
			}
		}
		
		System.out.println(dp[n-1]);
	}
}
