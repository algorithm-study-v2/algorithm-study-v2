import java.io.*;
import java.util.*;

public class 최소비용구하기2_승현 { // 480ms

    static int n, m;
    static ArrayList<Node>[] graph;
    static int[] parent;

    static class Node implements Comparable<Node> {
        public int idx;
        public int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", weight=" + weight +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        graph = new ArrayList[n+1];
        parent = new int[n+1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
    }

    public static void dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Stack<Integer> route = new Stack<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.idx] < now.weight) {
                continue;
            }
            for (Node next : graph[now.idx]) {
                int newDist = dist[now.idx] + next.weight;
                if (newDist < dist[next.idx]) {
                    dist[next.idx] = newDist;
                    pq.offer(new Node(next.idx, newDist));
                    parent[next.idx] = now.idx;
                }
            }
        }

        System.out.println(dist[end]);
        route.push(end);
        int cnt = 1;
        while (route.peek() != start) {
            route.push(parent[route.peek()]);
            cnt++;
        }
        System.out.println(cnt);
        while (!route.isEmpty()) {
            System.out.print(route.pop() + " ");
        }
    }
}
