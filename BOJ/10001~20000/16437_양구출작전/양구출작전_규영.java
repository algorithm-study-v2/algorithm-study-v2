import java.io.*;
import java.util.*;

/*
    96012KB, 988ms
 */

public class 양구출작전_규영 {
    static long sum;
    static int[] amounts;
    static ArrayList<Integer>[] bridges;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        bridges = new ArrayList[N];
        amounts = new int[N];
        for (int i = 0; i < N; i++) bridges[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int amount = Integer.parseInt(st.nextToken()), bridge = Integer.parseInt(st.nextToken())-1;
            amounts[i] = s.equals("S") ? amount : -amount; // S면 양, W면 늑대
            bridges[bridge].add(i);
        }
        System.out.print(dfs(0));
    }

    static long dfs (int parent) {
        // 맨 아래 찍었다 올라오며 연산
        sum = 0;
        for (int child : bridges[parent]) sum += dfs(child);
        sum += amounts[parent];
        return 0 < sum ? sum : 0;
    }
}