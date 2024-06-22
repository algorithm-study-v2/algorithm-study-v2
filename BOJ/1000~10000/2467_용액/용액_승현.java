import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 용액_승현 { //244ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] liquid = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        int minVal = Integer.MAX_VALUE;
        int r1 = 0, r2 = 0;

        for (int i = 0; i < n-1; i++) {
            int left = i+1;
            int right = n-1;

            while (left <= right) {
                int mid = (left + right) / 2;
                int temp = Math.abs(liquid[i] + liquid[mid]);

                if (minVal > temp) {
                    minVal = temp;
                    r1 = liquid[i];
                    r2 = liquid[mid];
                }

                if (liquid[i] + liquid[mid] > 0) {
                    right = mid - 1;
                } else if (liquid[i] + liquid[mid] < 0) {
                    left = mid + 1;
                } else {
                    System.out.println(liquid[i] + " " + liquid[mid]);
                    return;
                }
            }
        }

        System.out.println(r1 + " " + r2);
    }
}
