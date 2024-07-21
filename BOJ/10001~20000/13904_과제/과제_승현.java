import java.io.*;
import java.util.*;

public class 과제_승현 { // 96ms

    static class Homework implements Comparable<Homework>{
        public int d;
        public int w;

        public Homework(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Homework o) {
            if (this.w == o.w) {
                return this.d - o.d;
            }
            else {
                return o.w - this.w;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Homework> pq = new PriorityQueue<>();
        int[] cnt = new int[1001];
        boolean[] visited = new boolean[1001];

        pq.offer(new Homework(1001, 0));
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            cnt[d]++;
            pq.offer(new Homework(d, w));
        }

        int result = 0;

        while (pq.peek().w != 0) {
            Homework now = pq.poll();
            for (int i = now.d; i > 0; i--) {
                if (!visited[i]) {
                    result += now.w;
                    visited[i] = true;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
