import java.io.*;
import java.util.StringTokenizer;

/*
    22388 KB, 192 ms
 */

public class 부분합_규영 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()), S = Integer.parseInt(st.nextToken()), ans = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N+1];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int start = 0, end = 0, sum = 0;
        while (end <= N) {
            if (sum < S) sum += arr[end++];
            else {
                if (ans > end-start) ans = end-start;
                sum -= arr[start++];
            }
        }
        System.out.print(ans != Integer.MAX_VALUE ? ans : 0);
    }
}