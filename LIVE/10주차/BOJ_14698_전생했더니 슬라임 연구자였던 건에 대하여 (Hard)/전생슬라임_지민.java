import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 전생슬라임_지민 {
    static int N;
    static PriorityQueue<Long> energy;
    static final int MOD = 1000000007;

    private static Long calc() {
        long res = 1L;

        for (int i = 0; i < N - 1; i++) {
            Long m1 = energy.poll();
            Long m2 = energy.poll();
            energy.add(m1 * m2);
            res = (res * ((m1 * m2) % MOD)) % MOD;
        }

        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            energy = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                energy.add(Long.parseLong(st.nextToken()));
            }

            if (N == 1) sb.append(1).append("\n");
            else sb.append(calc()).append("\n");
        }
        System.out.println(sb);
    }
}
