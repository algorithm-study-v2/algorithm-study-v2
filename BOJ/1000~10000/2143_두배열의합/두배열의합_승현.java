import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class 두배열의합_승현 { // 1096ms
    static int t, n, m;
    static long result;
    static int[] a, sumA;
    static int[] b, sumB;
    static Map<Integer, Long> mapA;
    static Map<Integer, Long> mapB;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        a = new int[n+1];
        sumA = new int[n+1];
        mapA = new TreeMap<>();
        mapB = new TreeMap<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            sumA[i] = sumA[i-1] + a[i];
        }
        m = Integer.parseInt(br.readLine());
        b = new int[m+1];
        sumB = new int[m+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
            sumB[i] = sumB[i-1] + b[i];
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                if (!mapA.containsKey(sumA[j] - sumA[i-1])) {
                    mapA.put(sumA[j] - sumA[i-1], 1L);
                }
                else {
                    mapA.replace(sumA[j] - sumA[i-1], mapA.get(sumA[j] - sumA[i-1]) + 1);
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= m; j++) {
                if (!mapB.containsKey(sumB[j] - sumB[i-1])) {
                    mapB.put(sumB[j] - sumB[i-1], 1L);
                }
                else {
                    mapB.replace(sumB[j] - sumB[i-1], mapB.get(sumB[j] - sumB[i-1]) + 1);
                }
            }
        }

        List<Integer> bKeys = mapB.keySet().stream().collect(Collectors.toList());
        for (int aKey : mapA.keySet()) {
            int left = 0;
            int right = bKeys.size();
            while (left < right) {
                int mid = (left + right) / 2;
                if (aKey + bKeys.get(mid) < t) {
                    left = mid + 1;
                }
                else if (aKey + bKeys.get(mid) > t){
                    right = mid;
                }
                else {
                    Long cnt = mapA.get(aKey) * mapB.get(bKeys.get(mid));
                    result += cnt;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}
