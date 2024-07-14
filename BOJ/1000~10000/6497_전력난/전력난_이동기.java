import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 716ms
 */
public class Main {
    static int n, m;
    static int[] parents;
    static Info[] infoList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (m + n == 0) break;
            solution(br);

        }
        System.out.println(sb);
    }

    static void solution(BufferedReader br) throws IOException {
        StringTokenizer st;

        parents = new int[m];
        infoList = new Info[n];
        make();

        int result = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            result += z;
            infoList[i] = new Info(x, y, z);
        }
        Arrays.sort(infoList);
        int cnt = 0;
        for (Info info : infoList) {
            if (union(info.start, info.end)) {
                result -= info.cost;
                if (++cnt == m - 1) {
                    break;
                }
            }
        }

        sb.append(result).append("\n");
    }

    static void make() {
        for (int i = 0; i < m; i++) {
            parents[i] = i;
        }
    }

    static int find(int x) {
        if (parents[x] == x) return x;

        return parents[x] = find(parents[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x > y) {
            parents[x] = y;
        } else {
            parents[y] = x;
        }
        return true;
    }


    static class Info implements Comparable<Info> {
        int start;
        int end;
        int cost;

        Info(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            return this.cost - o.cost;
        }
    }

}
