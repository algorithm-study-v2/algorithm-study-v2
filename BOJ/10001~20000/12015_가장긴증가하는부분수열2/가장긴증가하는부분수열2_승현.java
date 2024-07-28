import java.io.*;
import java.util.*;

public class 가장긴증가하는부분수열2_승현 { // 484ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        int[] result = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        result[0] = a[0];
        int len = 1;

        for (int i = 1; i < n; i++) {
            int now = a[i];

            if (result[len-1] < now) {
                len++;
                result[len-1] = now;
            }
            else {
                int left = 0;
                int right = len;
                while (left < right) {
                    int mid = (left + right) / 2;

                    if (result[mid] < now) {
                        left = mid + 1;
                    }
                    else {
                        right = mid;
                    }
                }
                result[left] = now;
            }
        }

        System.out.println(len);
    }
}
