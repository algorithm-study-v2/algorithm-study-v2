import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 모노톤길_승현 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        TreeMap<Integer, ArrayList<Integer>> cafes;
        HashMap<Integer, int[]> result;
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            result = new HashMap<>();
            cafes = new TreeMap<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                if (!cafes.containsKey(x)) {
                    cafes.put(x, new ArrayList<>());
                }
                cafes.get(x).add(y);
            }

            int prevY = 0;
            int resultIdx = 1;
            for (Integer key : cafes.keySet()) {
                ArrayList<Integer> temp = cafes.get(key);
                Collections.sort(temp);
                int tempMin = temp.get(0);
                if (tempMin != prevY) {
                    Collections.sort(temp, ((o1, o2) -> o2 - o1));
                }
                for (int y : temp) {
                    result.put(resultIdx++, new int[]{key, y});
                    prevY = y;
                }
            }
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for (int i = 0; i < m; i++) {
                int[] temp = result.get(Integer.parseInt(st.nextToken()));
                System.out.println(temp[0] + " " + temp[1]);
            }
        }

    }
}
