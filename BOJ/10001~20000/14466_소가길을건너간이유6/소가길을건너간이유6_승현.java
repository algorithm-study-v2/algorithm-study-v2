import java.io.*;
import java.util.*;

public class 소가길을건너간이유6_승현 {

    static int result;
    static int n, k, r;
    static int[][][] farm;
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static ArrayList<Pos> cows;

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        farm = new int[n+1][n+1][4];
        cows = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            if (r2 - r1 == 0 && c2 - c1 == 1) {
                farm[r1][c1][0] = -1;
                farm[r2][c2][2] = -1;
            }
            else if (r2 - r1 == 1 && c2 - c1 == 0) {
                farm[r1][c1][1] = -1;
                farm[r2][c2][3] = -1;
            }
            else if (r2 - r1 == 0 && c2 - c1 == -1) {
                farm[r1][c1][2] = -1;
                farm[r2][c2][0] = -1;
            }
            else if (r2 - r1 == -1 && c2 - c1 == 0) {
                farm[r1][c1][3] = -1;
                farm[r2][c2][1] = -1;
            }

        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int cr = Integer.parseInt(st.nextToken());
            int cc = Integer.parseInt(st.nextToken());
            cows.add(new Pos(cr, cc));
        }

        for (Pos cow : cows) {
            bfs(cow.x, cow.y);
        }
        System.out.println(result / 2);
    }

    public static void bfs(int sx, int sy) {
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];
        visited[sx][sy] = true;
        q.offer(new Pos(sx, sy));

        while (!q.isEmpty()) {
            Pos now = q.poll();
            for (int i = 0; i < 4; i++) {
                if (farm[now.x][now.y][i] == -1) {
                    continue;
                }
                int nx = now.x + dirs[i][0];
                int ny = now.y + dirs[i][1];
                if (nx <= 0 || ny <= 0 || nx > n || ny > n || visited[nx][ny]) {
                    continue;
                }
                q.offer(new Pos(nx, ny));
                visited[nx][ny] = true;
            }
        }

        for (Pos cow : cows) {
            if (cow.x == sx && cow.y == sy) {
                continue;
            }
            if (!visited[cow.x][cow.y]) {
                result++;
            }
        }
    }
}
