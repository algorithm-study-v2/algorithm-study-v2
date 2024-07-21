import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벼룩시장_승현 { // 232ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] bug = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        long result = 0;
        long bugStore = 0;

        for (int i = 0; i < n; i++) {
            bug[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            bugStore += bug[i];

            result += Math.abs(bugStore);
        }
        System.out.println(result);
    }

}