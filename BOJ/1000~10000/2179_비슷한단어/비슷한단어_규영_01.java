import java.io.*;

/*
    16104 KB, 176 ms
 */

public class 비슷한단어_규영_01 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()), idx1 = 0, idx2 = 0, max = 0;
        String[] arr = new String[N];
        for (int i = 0; i < N; i++) arr[i] = br.readLine();
        for (int i = 0; i < N-1; i++) {
            String now = arr[i];
            if (now.length() <= max) continue;
            for (int j = i+1; j < N; j++) {
                String next = arr[j];
                if (next.length() <= max) continue;
                int cnt = 0;
                for (int k = 0; k < Math.min(now.length(), next.length()); k++) {
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