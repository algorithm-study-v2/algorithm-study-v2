import java.io.*;

/*
    15936 KB, 884 ms
 */

public class 비슷한단어_규영_02 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), idx1 = 0, idx2 = 0, max = 0;
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) arr[i] = br.readLine();
        for (int i = 0; i < N-1; i++) {
            String now = arr[i];
            for (int j = i+1; j < N; j++) {
                String next = arr[j];
                int cnt = 0, len = Math.min(now.length(), next.length());
                for (int k = 0; k < len; k++) {
                    if (now.charAt(k) != next.charAt(k)) break;
                    cnt++;
                }
                if (max < cnt) {
                    max = cnt;
                    idx1 = i;
                    idx2 = j;
                }
            }
        }
        System.out.println(arr[idx1]);
        System.out.print(arr[idx2]);
    }
}