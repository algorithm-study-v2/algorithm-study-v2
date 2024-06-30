import java.io.*;
import java.util.*;

public class 프로도의선물포장_규영 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            int[][] arr = new int[6][2];
            for (int i = 0; i < 3; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = arr[i+3][1] = Integer.parseInt(st.nextToken());
                arr[i][1] = arr[i+3][0] = Integer.parseInt(st.nextToken());
            }
            int ans = 50000;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    for (int k = 0; k < 6; k++) {
                        if (i%3 != j%3 && j%3 != k%3 && k%3 != i%3) {
                            ans = Math.min(ans, Math.min((arr[i][0]+arr[j][0]+arr[k][0])*Math.max(arr[i][1], Math.max(arr[j][1], arr[k][1])), Math.max(arr[i][0]+arr[j][0], arr[k][0])*(Math.max(arr[i][1],arr[j][1])+arr[k][1])));
                        }
                    }
                }
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }
}