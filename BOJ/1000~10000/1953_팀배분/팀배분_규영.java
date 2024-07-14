import java.io.*;
import java.util.*;

public class 팀배분_규영 {
    static boolean[] visited;
    static ArrayList<Integer>[] info;
    static ArrayList<Integer> blue, white;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        info = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) info[i] = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            while (num-->0) info[i].add(Integer.parseInt(st.nextToken()));
        }
        blue = new ArrayList<>();
        white = new ArrayList<>();
        visited = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if (visited[i]) continue;
            dfs(i, true);
        }
        if (blue.isEmpty()) {
            blue.add(white.get(0));
            white.remove(0);
        }
        Collections.sort(blue);
        Collections.sort(white);
        sb.append(blue.size()).append('\n');
        for (int i : blue) sb.append(i).append(' ');
        sb.append('\n').append(white.size()).append('\n');
        for (int i : white) sb.append(i).append(' ');
        System.out.print(sb);
    }
    static void dfs(int now, boolean isBlue) {
        if (visited[now]) return;
        visited[now] = true;
        if (isBlue) white.add(now);
        else blue.add(now);
        for (int i : info[now]) dfs(i, !isBlue);
    }
}