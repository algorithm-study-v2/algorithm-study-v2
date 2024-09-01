import java.io.*;
import java.util.*;

/*
	12012KB 188ms
 */

public class 같이눈사람만들래_규영_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		int ans = Integer.MAX_VALUE;
		loop:
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				int now = arr[i]+arr[j];
				int start = 0, end = N-1;
				while (start < end) {
					if (start == i || start == j) {
						start++;
						continue;
					}
					if (end == i || end == j) {
						end--;
						continue;
					}
					int next = arr[start]+arr[end];
					ans = Math.min(ans, Math.abs(now-next));
					if (now > next)	start++;
					else if (now < next) end--;
					else {
						ans = 0;
						break loop;
					}
				}
			}
		}
		System.out.print(ans);
	}
}