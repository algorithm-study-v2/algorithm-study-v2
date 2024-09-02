import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 보석도둑_승현 { //1460ms
    static class Jewelry implements Comparable<Jewelry> {
        public int weight;
        public int value;

        public Jewelry(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewelry o) {
            if (this.weight == o.weight) {
                return o.value - this.value;
            }
            else {
                return this.weight - o.weight;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        Jewelry[] jews = new Jewelry[n];
        int[] c = new int[k];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            jews[i] = new Jewelry(m, v);
        }

        for (int i = 0; i < k; i++) {
            c[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(c);
        Arrays.sort(jews);

        long result = 0;
        int idx = 0;
        for (int i = 0; i < k; i++) {
            while (idx < n && jews[idx].weight <= c[i]) {
                pq.offer(jews[idx++].value);
            }
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }

        System.out.println(result);
    }
}
