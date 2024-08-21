import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 할로윈의양아치_승현 { // 780ms
    static int n, m, k;
    static int[] candies;
    static int[] parent;
    static int[] count;
    static ArrayList<Info> infos;

    static class Info {
        public int cnt;
        public int value;

        public Info(int cnt, int value) {
            this.cnt = cnt;
            this.value = value;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        candies = new int[n+1];
        parent = new int[n+1];
        count = new int[n+1];
        Arrays.fill(count, 1);
        infos = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            candies[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        makeList();

        int[][] dp = new int[infos.size() + 1][k];
        for (int i = 1; i <= infos.size(); i++) {
            Info now = infos.get(i-1);
            for (int j = 0; j < k; j++) {
                if (now.cnt <= j) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-now.cnt] + now.value);
                }
                else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.println(dp[infos.size()][k-1]);
    }

    public static void makeList() {
        for (int i = 1; i <= n; i++) {
            if (parent[i] != i) {
                int p = find(i);
                candies[p] += candies[i];
                count[p] += count[i];
            }
        }
        for (int i = 1; i <= n; i++) {
            if (parent[i] == i) {
                infos.add(new Info(count[i], candies[i]));
            }
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x < y) {
            parent[y] = x;
        }
        else {
            parent[x] = y;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}
