import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 496ms
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Info>[] adjList = new ArrayList[n + 1];

        for (int i = 0; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a < b) {
                adjList[a].add(new Info(b, c));
            }
        }

        int[][] dp = new int[m + 1][n + 1];
        int cnt = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        while (!queue.isEmpty() && cnt++ < m) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int curCity = queue.poll();

                for (Info nextInfo : adjList[curCity]) {
                    int preCost = dp[cnt][nextInfo.next];
                    int curCost = dp[cnt - 1][curCity] + nextInfo.cost;

                    if(curCost > preCost){
                        dp[cnt][nextInfo.next] = curCost;
                        queue.add(nextInfo.next);
                    }
                }
            }
        }
        int answer = 0;
        for(int i=2; i<=m; i++){
            answer = Math.max(answer, dp[i][n]);
        }
        System.out.println(answer);
    }

    static class Info {
        int next;
        int cost;

        Info(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }
    }
}
