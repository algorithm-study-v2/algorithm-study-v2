import java.io.*;
import java.util.*;

public class 괄호_태연 {

    /*
     * - 108ms
     */
	
	static long[] memo;
	static final long MOD = 1_000_000_007;
	
	static void calculate(int target, int cur){
		
		
		while(cur<=target) {
			memo[cur] = memo[cur-1];
			
			for(int n=1; n < cur; n++) {
				memo[cur] += memo[n] * memo[cur-n-1];
				memo[cur] %= MOD;
			}
			
			cur++;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		memo = new long[2501];
		memo[0] = 1;
		memo[1] = 1;
		memo[2] = 2;
		int curLastIndex = 2;
		
		for(int i=0; i<t; i++) {
			int cur = Integer.parseInt(br.readLine());
			
			if (cur%2!=0) {
				sb.append(0).append('\n');
			} else if (memo[cur/2]>0) {
				sb.append(memo[cur/2]).append('\n');
			} else {
				cur/=2;
				calculate(cur, curLastIndex);
				curLastIndex = cur;
				sb.append(memo[cur]).append('\n');
			}
		}
		
		System.out.print(sb);

	}
}
