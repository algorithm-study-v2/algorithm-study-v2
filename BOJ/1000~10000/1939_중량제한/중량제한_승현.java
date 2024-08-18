import java.util.*;
import java.io.*;

public class 중량제한_승현 { // 476ms
    static int n, m, sn, en;
    static ArrayList<Info>[] graph;

    static class Info implements Comparable<Info>{
        public int node;
        public long weight;

        public Info(int node, long weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public int compareTo(Info o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            graph[a].add(new Info(b, c));
            graph[b].add(new Info(a, c));
        }

        st = new StringTokenizer(br.readLine());
        sn = Integer.parseInt(st.nextToken());
        en = Integer.parseInt(st.nextToken());
        System.out.println(dijkstra());
    }

    public static long dijkstra() {
        PriorityQueue<Info> q = new PriorityQueue<>();
        long[] dist = new long[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, 0);
        q.offer(new Info(sn, -1000000000));

        while (!q.isEmpty()) {
            Info now = q.poll();
            long w = -now.weight;
            if (w > dist[now.node]) {
                dist[now.node] = w;
            }

            if (visited[now.node]) {
                continue;
            }
            visited[now.node] = true;

            for (Info next : graph[now.node]) {
                if (dist[next.node] < Math.min(dist[now.node], next.weight)) {
                    dist[next.node] = Math.min(dist[now.node], next.weight);
                    q.offer(new Info(next.node, -dist[next.node]));
                }
            }
        }
        return dist[en];
    }
}
