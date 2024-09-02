import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] select;
    private static boolean[] visit, done;
    private static int res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            select = new int[n + 1];

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= n; j++) {
                int f = Integer.parseInt(st.nextToken());
                select[j] = f;
            }

            visit = new boolean[n + 1];
            done = new boolean[n + 1];

            res = 0;

            for (int j = 1; j <= n; j++) {
                if (done[j]) continue;
                rec(j);
            }
            sb.append(n-res).append("\n");
        }
        System.out.println(sb);
    }

    //1 3 5 4 1
    private static void rec(int t) {
        if (done[t]) return;
        if (visit[t]) {
            done[t] = true;
            res++;
        }
        visit[t] = true;
        rec(select[t]);
        done[t] = true;
        visit[t] = false;
    }
}
