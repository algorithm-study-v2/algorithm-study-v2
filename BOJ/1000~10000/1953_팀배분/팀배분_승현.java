import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 팀배분_승현 { // 76ms
    static int n;
    static ArrayList<Integer>[] hate;
    static boolean[] visited;
    static ArrayList<Integer> blue;
    static ArrayList<Integer> white;

    static class Node {
        public int n;
        public int level;

        public Node(int n, int level) {
            this.n = n;
            this.level = level;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        hate = new ArrayList[n+1];
        visited = new boolean[n+1];
        blue = new ArrayList<>();
        white = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            hate[i] = new ArrayList<>();
        }

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int c = 0; c < cnt; c++) {
                hate[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!visited[i]){
                bfs(i);
            }
        }

        Collections.sort(blue);
        Collections.sort(white);
        System.out.println(blue.size());
        for (int b : blue) {
            System.out.print(b + " ");
        }
        System.out.println();
        System.out.println(white.size());
        for (int w : white) {
            System.out.print(w + " ");
        }
    }
    public static void bfs(int s) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(s, 0));
        visited[s] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (now.level % 2 == 0) {
                blue.add(now.n);
            }
            else {
                white.add(now.n);
            }
            for (int next : hate[now.n]) {
                if (!visited[next]) {
                    q.offer(new Node(next, now.level+1));
                    visited[next] = true;
                }
            }
        }

    }
}
