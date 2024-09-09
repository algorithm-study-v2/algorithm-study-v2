import java.io.*;
import java.util.*;

public class 부분수열의합2_규태 {
	/*
	 * 924ms
	 * 전체를 반으로 나누어 가능한 부분수열의 합을 left, right에 저장한 후
	 * 합이 s가 되는 쌍이 존재하는지 확인
	 */
	static int n,s;
	static long cnt=0;
	static long[] num;
	static HashMap<Long,Long> left = new HashMap<>();
	static HashMap<Long,Long> right = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		num = new long[n];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,n/2,0,left);
		dfs(n/2,n,0,right);
		
		
		for(long i:left.keySet()) {
			if(right.containsKey(s-i)) {
				cnt+=left.get(i)*right.get(s-i);
			}	
		}
		
		if(s==0) cnt--;
		System.out.println(cnt);
	}
	static void dfs(int idx, int end, long sum, HashMap<Long,Long> map) {
		if(idx==end) {
			long val = map.getOrDefault(sum, -1L);
			if(val==-1L) map.put(sum, 1L);
			else  map.put(sum, map.get(sum)+1L);
			return;
		}
		
		dfs(idx+1,end,sum,map);
		dfs(idx+1,end,sum+num[idx],map);
	}
}
