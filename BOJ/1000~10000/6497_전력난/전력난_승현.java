import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 전력난_승현 {

    static int n, m;
    static int[] parents;
    static int total;
    static PriorityQueue<Edge> pq;

    static class Edge implements Comparable<Edge> {
        int s;
        int e;
        int w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) {
                System.out.print(sb);
                break;
            }
            init();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());
                pq.offer(new Edge(x, y, z));
                total += z;
            }
            int sum = 0;
            while (!pq.isEmpty()) {
                Edge now = pq.poll();
                int s = find(now.s);
                int e = find(now.e);

                if (s != e) {
                    union(s, e);
                    sum += now.w;
                }
            }
            sb.append(total - sum).append('\n');
        }
    }

    public static void init() {
        total = 0;
        pq = new PriorityQueue<>();
        parents = new int[n+1];
        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x != y) {
            parents[y] = x;
        }
    }

    public static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }
}
