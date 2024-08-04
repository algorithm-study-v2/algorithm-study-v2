import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 우수마을_승현 { // 200ms
    static int n;
    static int[] population;
    static int[][] dp;
    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        tree = new ArrayList[n+1];
        population = new int[n+1];
        dp = new int[n+1][2];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(1, 0);
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    public static void dfs(int node, int parent) {
        for (int next : tree[node]) {
            if (next != parent) {
                dfs(next, node);
                dp[node][0] += Math.max(dp[next][0], dp[next][1]);
                dp[node][1] += dp[next][0];
            }
        }
        dp[node][1] += population[node];
    }
}
