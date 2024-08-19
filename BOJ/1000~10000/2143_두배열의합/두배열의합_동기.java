import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main {
    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 1; i < n; i++)
            a[i] += a[i - 1];

        for (int i = 1; i < m; i++)
            b[i] += b[i - 1];

        ArrayList<Integer> aSum = new ArrayList<>();
        ArrayList<Integer> bSum = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = n - 1; i >= 0; i--) {
            aSum.add(a[i]);
            for (int j = i - 1; j >= 0; j--) {
                aSum.add(a[i] - a[j]);
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            bSum.add(b[i]);
            if (map.containsKey(b[i])) map.put(b[i], map.get(b[i]) + 1);
            else map.put(b[i], 1);

            for (int j = i - 1; j >= 0; j--) {
                bSum.add(b[i] - b[j]);
                if (map.containsKey(b[i] - b[j])) map.put(b[i] - b[j], map.get(b[i] - b[j]) + 1);
                else map.put(b[i] - b[j], 1);
            }
        }

        Collections.sort(bSum);

        long cnt = 0;
        for (int i = 0; i < aSum.size(); i++) {
            int targetValue = t - aSum.get(i);
            int start = 0;
            int end = bSum.size() - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                int midValue = bSum.get(mid);

                if (midValue > targetValue) {
                    end = mid - 1;
                } else if (midValue < targetValue) {
                    start = mid + 1;
                } else if (midValue == targetValue) {
                    cnt += map.get(midValue);
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}

