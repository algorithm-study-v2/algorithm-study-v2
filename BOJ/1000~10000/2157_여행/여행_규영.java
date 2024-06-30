import java.io.*;
import java.util.*;

// 경로를 HashMap으로 저장했다가 메모리 초과

public class 여행_규영 {
    static class Route {
        int destination, score;
        Route(int destination, int score) {
            this.destination = destination;
            this.score = score;
        }
    }
    static int M;
    static int[][] dp;
    static ArrayList<Route>[] info;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp = new int[N][M+1];
        info = new ArrayList[N];
        for (int i = 0; i < N; i++) info[i] = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1, b = Integer.parseInt(st.nextToken())-1, c = Integer.parseInt(st.nextToken());
            if (a > b) continue;
            info[a].add(new Route(b, c));
        }
        bfs(0);
        int ans = 0;
        for (int i = 1; i <= M; i++) ans = Math.max(ans, dp[N-1][i]);
        System.out.print(ans);
    }
    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        int cnt = 1;
        while (!q.isEmpty() && cnt < M) {
            int size = q.size();
            while (size-->0) {
                int now = q.poll();
                for (Route r : info[now]) {
                    int next = r.destination, score = dp[now][cnt]+r.score;
                    if (dp[next][cnt+1] >= score) continue;
                    dp[next][cnt+1] = score;
                    q.offer(next);
                }
            }
            cnt++;
        }
    }
}