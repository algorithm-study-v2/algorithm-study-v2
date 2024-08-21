import java.io.*;
import java.util.StringTokenizer;

public class 일의개수세기_규영 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long A = Long.parseLong(st.nextToken()), B = Long.parseLong(st.nextToken());
		System.out.print(solve(B)-solve(A-1));
	}
	static long solve(long n) {
		long cnt = 0, i = 0;
		while ((1L << i) <= n) {
			long len = 1L << (i+1);
			cnt += (n+1)/len*(len>>1)+Math.max(0, (n+1)%len-(len>>1));
			i++;
		}
		return cnt;
	}
}