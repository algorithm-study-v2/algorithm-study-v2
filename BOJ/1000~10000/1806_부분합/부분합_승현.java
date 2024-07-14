import java.io.*;
import java.util.*;

public class 부분합_승현 { // 196ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];

        long result = Long.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (arr[i] >= s) {
                System.out.println(1);
                return;
            }
        }

        int left = 0;
        int right = 1;
        int sum = arr[0];
        while (left <= right && right <= n) {
//            System.out.println(left + " " + right + " " + sum);
            if (sum < s) {
                sum += arr[right++];
            }
            else {
                result = Math.min(result, right - left);
                sum -= arr[left++];
            }
        }

        if (result == Long.MAX_VALUE) {
            System.out.println(0);
        }
        else {
            System.out.println(result);
        }
    }
}
