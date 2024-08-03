import java.io.*;

/*
    11708KB, 88ms
 */

public class 문자열생성_규영 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine()), start = 0, end = N-1;
        char[] arr = new char[N];
        for (int i = 0; i < N; i++) arr[i] = br.readLine().charAt(0);
        while (N-->0) {
            boolean flag = true;
            int mid = (end-start+1)/2;
            for (int i = 0; i < mid; i++) {
                if (arr[start+i] != arr[end-i]) {
                    if (arr[start+i] > arr[end-i]) flag = false;
                    break;
                }
            }
            sb.append(flag ? arr[start++] : arr[end--]);
            if (sb.length() == 80) {
                System.out.println(sb);
                sb = new StringBuilder();
            }
        }
        System.out.print(sb);
    }
}