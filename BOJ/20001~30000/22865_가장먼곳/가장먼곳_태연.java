import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 가장먼곳_태연 {

    /*
     * - 1548ms
     * 
     * - 다익스트라 이후 거리비교
     */

    static int getDist(int i){
        return Math.min(dist[0][i], Math.min(dist[1][i], dist[2][i]));
    }

    static class Edge{
        int to;
        int dist;

        Edge(int to, int dist){
            this.to=to;
            this.dist=dist;
        }
    }

    static class Node implements Comparable<Node>{
        int id;
        int dist;

        Node(int id, int dist){
            this.id = id;
            this.dist = dist;
        }

        public int compareTo(Node n){
            return this.dist - n.dist;
        }
    }

    static final int INF = 100_000_001;

    static int n;
    static int m;
    static int[] home;

    static List<Edge>[] adj;
    static int[][] dist;
    static PriorityQueue<Node> q;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n+1];
        for(int i=0; i<=n; i++){
            adj[i] = new ArrayList<>();
        }
        dist = new int[3][n+1];
        for(int i=0; i<3; i++){
            Arrays.fill(dist[i], INF);
        }
        home = new int[3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++){
            home[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());

            adj[n1].add(new Edge(n2, n3));
            adj[n2].add(new Edge(n1, n3));
        }

        for(int i=0; i<3; i++){
            q = new PriorityQueue<>();
            dist[i][home[i]] = 0;
            q.add(new Node(home[i], 0));
            v = new boolean[n+1];

            while(!q.isEmpty()){
                Node cur = q.poll();
                if(v[cur.id]){
                    continue;
                } else {
                    v[cur.id] = true;
                }

                for(Edge e: adj[cur.id]){
                    if(!v[e.to] && dist[i][e.to] > dist[i][cur.id] + e.dist){
                        dist[i][e.to] = dist[i][cur.id] + e.dist;
                        q.add(new Node(e.to, dist[i][e.to]));
                    }
                }
            }
        }

        int maxDist = Integer.MIN_VALUE;
        int curId = -1;

        for(int i=1; i<=n; i++){
            if(i==home[0] || i==home[1] || i==home[2]) continue;

            int curDist = getDist(i);
            //System.out.println(i + " : " + curDist);
            if(curDist > maxDist){
                maxDist = curDist;
                curId = i;
            }
        }

        System.out.print(curId);
    }
}
