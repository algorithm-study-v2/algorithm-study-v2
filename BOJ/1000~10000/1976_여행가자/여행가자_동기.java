import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 112ms
 */
public class Main {
    static int n, m;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                if (st.nextToken().equals("1")) {
                    union(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            find(i);
        }

        st = new StringTokenizer(br.readLine());
        int group = parents[Integer.parseInt(st.nextToken()) - 1];
        boolean isAble = true;
        for (int i = 1; i < m; i++) {
            int city = Integer.parseInt(st.nextToken()) - 1;
            if (parents[city] != group) {
                isAble = false;
                break;
            }
        }
        System.out.println(isAble ? "YES" : "NO");
    }

    static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if (x == y) return;

        if (x < y) {
            parents[y] = x;
        } else {
            parents[x] = y;
        }

    }
}