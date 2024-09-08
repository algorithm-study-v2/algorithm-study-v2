import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static ArrayList<Info>[] adjList;
    static final int MAX_VALUE = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        adjList = new ArrayList[n+1];
        int[] friendArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());

        for (int i=1; i<=n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[a].add(new Info(b,cost));
            adjList[b].add(new Info(a,cost));
        }
        int[] aDist = new int[n+1];
        int[] bDist = new int[n+1];
        int[] cDist = new int[n+1];

        setDij(aDist, friendArray[0]);
        setDij(bDist, friendArray[1]);
        setDij(cDist, friendArray[2]);

        Info answer = new Info(0,0);
        for (int i=1; i<=n; i++) {
            int min = Math.min(aDist[i], Math.min(bDist[i],cDist[i]));
            if(answer.cost < min) {
                answer.newSet(i,min);
            }
        }
        System.out.println(answer.end);

    }

    static void setDij(int[] dist, int start) {
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dist,MAX_VALUE);
        PriorityQueue<Info> pq = new PriorityQueue<>((o1,o2) -> {
            return o1.cost - o2.cost;
        });
        pq.add(new Info(start,0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Info cur = pq.poll();
            if (visited[cur.end]) continue;

            visited[cur.end] = true;

            for (Info next : adjList[cur.end]) {
                if(dist[next.end] > cur.cost + next.cost){
                    dist[next.end] = cur.cost + next.cost;
                    pq.add(new Info(next.end, dist[next.end]));
                }
            }
        }
    }

    static class Info {
        int end;
        int cost;

        Info(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        void newSet(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

}

