import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 100ms
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Info> pq = new PriorityQueue<>();

        int answer = 0;
        int curTime = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Info(time, cost));
            curTime = Math.max(curTime, time);
        }

        while (curTime != 0 && !pq.isEmpty()) {
            Info info = pq.poll();

            if (info.time > curTime) {
                pq.add(new Info(curTime, info.cost));
            } else if (info.time < curTime) {
                answer += info.cost;
                curTime = info.time - 1;
            } else if (info.time == curTime) {
                answer += info.cost;
                curTime--;
            }
        }
        
        System.out.println(answer);

    }

    static class Info implements Comparable<Info> {
        int time;
        int cost;

        Info(int time, int cost) {
            this.time = time;
            this.cost = cost;
        }

        @Override
        public int compareTo(Info o) {
            if (o.time == this.time) return o.cost - this.cost;
            return o.time - this.time;
        }
    }
}
