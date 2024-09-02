import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    private static int n;
    private static List<Integer>[] adj;
    private static int[] depth, parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());

            adj[t].add(f);
            adj[f].add(t);
        }

        depth = new int[n + 1];
        parent = new int[n + 1];
        Arrays.fill(depth, -1);
        depth[1] = 0;
        parent[1] = 1;

        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[] {1,0});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (Integer next : adj[now[0]]) {
                if (depth[next] == -1) {
                    parent[next] = now[0];
                    depth[next] = now[1] + 1;
                    q.add(new int[] {next, now[1] + 1});
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = find(a,b);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int a, int b) {
        int aD = depth[a];
        int bD = depth[b];

        while (aD > bD) {
            a = parent[a];
            aD--;
        }

        while (aD < bD) {
            b = parent[b];
            bD--;
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }
        return a;
    }
}
