import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class 친구네트워크_지민 {
    static int T;
    static int F;
    static HashMap<String, Integer> hashMap;
    static int[] parent;
    static int[] child;

    private static int findParent(int x) {
        if(parent[x] == x) return x;
        parent[x] = findParent(parent[x]);
        return parent[x];
    }

    private static int union(int f1, int f2) {
        int p1 = findParent(f1);
        int p2 = findParent(f2);
        
        if(p1 < p2) {
            parent[p2] = p1;
            child[p1] += child[p2];
            child[p2] = 0;
            return child[p1];
        }
        else if(p1 > p2) {
            parent[p1] = p2;
            child[p2] += child[p1];
            child[p1] = 0;
            return child[p2];
        }

        return child[p1];
    }

    private static int convertNum(String friend) {
        int idx = hashMap.size();
        if(!hashMap.containsKey(friend)) {
            hashMap.put(friend, idx);
            parent[idx] = idx;
            child[idx] = 1;
        }
        return hashMap.get(friend);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            hashMap = new HashMap<>();
            parent = new int[200000];
            Arrays.fill(parent, -1);
            child = new int[200000];

            st = new StringTokenizer(br.readLine());
            F = Integer.parseInt(st.nextToken());
            for(int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                int f1 = convertNum(st.nextToken());
                int f2 = convertNum(st.nextToken());
                sb.append(union(f1, f2)).append("\n");
            }
        }
        System.out.println(sb);
    }
}