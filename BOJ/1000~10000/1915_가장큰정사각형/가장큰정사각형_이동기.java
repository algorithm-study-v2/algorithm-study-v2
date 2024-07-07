import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 344ms
 */
public class Main {
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            int[] arr = new int[m + 1];
            for (int j = 1; j <= m; j++) {
                arr[j] = Integer.parseInt(str.charAt(j - 1) + "");
            }
            map[i] = arr;
        }

        int max = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == 1) {
                    int min = Math.min(map[i - 1][j - 1], Math.min(map[i][j - 1], map[i - 1][j]));
                    map[i][j] = min + 1;
                    max = Math.max(max, min + 1);
                }
            }
        }
        System.out.println(max * max);
    }
}
