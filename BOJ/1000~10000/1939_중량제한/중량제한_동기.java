import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 416ms
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Info>[] adjList = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
          adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Info(b,c));
            adjList[b].add(new Info(a,c));
        }
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dist = new int[n+1];
        dist[start] = 1000000000;
        PriorityQueue<Info> pq = new PriorityQueue<Info>();
        pq.add(new Info(start,1000000000));
        boolean[] visited = new boolean[n+1];

        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (visited[cur.node]) continue;
            visited[cur.node] = true;

            for (Info nextInfo : adjList[cur.node]) {
                int cost = Math.min(nextInfo.cost, cur.cost);
                if (dist[nextInfo.node] < cost){
                    pq.add(new Info(nextInfo.node, cost));
                    dist[nextInfo.node] = cost;
                }
            }
        }
        System.out.println(dist[end]);

    }

    static class Info implements Comparable<Info> {
        int node;
        int cost;

        Info(int node, int cost) {
            this.node =node;
            this.cost =cost;
        }

        @Override
        public int compareTo(Info o) {
            return o.cost - this.cost;
        }
    }
}

