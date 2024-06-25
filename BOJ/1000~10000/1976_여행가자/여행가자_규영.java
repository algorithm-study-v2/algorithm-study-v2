import java.io.*;
import java.util.*;

public class 여행가자_규영 {
    static boolean flag;
    static boolean[] visited;
    static ArrayList<Integer>[] info;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());
        info = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            info[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            info[i].add(i);
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1")) info[i].add(j);
            }
        }
        int[] route = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) route[i] = Integer.parseInt(st.nextToken())-1;
        flag = true;
        for (int i = 0; i < M-1; i++) {
            visited = new boolean[N];
            bfs(route[i], route[i+1]);
            if (!flag) break;
        }
        System.out.print(flag ? "YES" : "NO");
    }
    static void bfs(int from, int to) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(from);
        visited[from] = true;
        while (!q.isEmpty()) {
            int now = q.poll();
            if (info[now].contains(to)) return;
            for (int i = 0; i < info[now].size(); i++) {
                int next = info[now].get(i);
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(next);
            }
        }
        flag = false;
    }
}