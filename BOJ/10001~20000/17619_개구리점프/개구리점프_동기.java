import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
/*
 * 692ms
 */
public class Main {
    static int n, q;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        ArrayList<Info> list = new ArrayList<>();
        make();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Info(i, x1, x2));
        }
        Collections.sort(list);
        Info info = list.get(0);

        for (int i = 1; i < list.size(); i++) {
            Info curInfo = list.get(i);

            if (info.end >= curInfo.start) {
                union(info.num, curInfo.num);
                info = new Info(Math.min(info.num, curInfo.num), info.start, Math.max(info.end, curInfo.end));
                continue;
            }
            info = curInfo;
        }

        for (int i = 1; i < parents.length; i++) {
            find(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            sb.append(parents[Integer.parseInt(st.nextToken())] == parents[Integer.parseInt(st.nextToken())] ? 1 : 0).append("\n");
        }

        System.out.println(sb);
    }

    static void make() {
        for (int i = 1; i <= n; i++) parents[i] = i;
    }

    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

    static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return false;

        if (x > y) parents[x] = y;
        else parents[y] = x;
        return true;
    }

    static class Info implements Comparable<Info> {
        int num;
        int start;
        int end;

        Info(int num, int start, int end) {
            this.num = num;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            return this.start - o.start;
        }
    }
}
