import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 미확인도착지_승현 { // 400ms
    static int n, m, t, s, g, h;
    static StringBuilder sb;
    static ArrayList<Info>[] graph;
    static ArrayList<Integer> targets;

    static class Info implements Comparable<Info> {
        public int node;
        public int d;
        public boolean checked;

        public Info(int node, int d, boolean checked) {
            this.node = node;
            this.d = d;
            this.checked = checked;
        }

        @Override
        public int compareTo(Info o) {
            return this.d - o.d;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());

        for (int testCase = 1; testCase <= tc; testCase++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            graph = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            targets = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                if ((a == g && b == h) || (a == h && b == g)) {
                    graph[a].add(new Info(b, d, true));
                    graph[b].add(new Info(a, d, true));
                }
                else {
                    graph[a].add(new Info(b, d, false));
                    graph[b].add(new Info(a, d, false));
                }
            }

            for (int i = 0; i < t; i++) {
                targets.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(targets);

            dijkstra();
        }
        System.out.print(sb);
    }

    public static void dijkstra() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist, 1000000000);
        dist[s] = 0;
        pq.offer(new Info(s, 0, false));

        while (!pq.isEmpty()) {
            Info now = pq.poll();
            if (now.d > dist[now.node]) {
                continue;
            }

            for (Info next : graph[now.node]) {
                int newDist = dist[now.node] + next.d;
                if (newDist < dist[next.node]) {
                    pq.offer(new Info(next.node, newDist, now.checked || next.checked));
                    dist[next.node] = newDist;
                    visited[next.node] = now.checked || next.checked;
                }
                else if (newDist == dist[next.node] && (now.checked || next.checked) && !visited[next.node]) {
                    pq.offer(new Info(next.node, newDist, true));
                    visited[next.node] = true;
                }
            }
        }

        for (int target : targets) {
            if (visited[target]) {
                sb.append(target).append(' ');
            }
        }
        sb.append('\n');
    }
}
