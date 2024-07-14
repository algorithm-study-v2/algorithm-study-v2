
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 332ms
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.asList(br.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
        int[] sum = new int[n];
        int minLength = 100001;
        int p1 = 0, p2 = 0;

        sum[0] = arr[0];
        for (int i = 1; i < n; i++)
            sum[i] = sum[i - 1] + arr[i];

        while (p1 < n && p2 < n) {
            int count = sum[p2] - sum[p1] + arr[p1];
            if (count >= s) {
                minLength = Math.min(minLength, p2 - p1 + 1);
                p1++;
            } else {
                p2++;
            }
        }
        System.out.println(minLength == 100001 ? 0 : minLength);
    }
}
