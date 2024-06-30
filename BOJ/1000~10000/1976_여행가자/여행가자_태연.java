import java.io.*;
import java.util.*;

public class 여행가자_태연 {
	
	/*
	 * - 120ms
	 * 
	 * - union-find
	 */
	
    static int find(int x) {
    	if(parent[x]==x) {
    		return x;
    	} else {
    		return parent[x] = find(parent[x]);
    	}
    }

    static void union(int x, int y) {
        int pX = find(x);
        int pY = find(y);
        if (pX != pY) {
            parent[pY] = pX;
        }
    }
	
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for (int i=1; i<=n; i++) {
            parent[i] = i;
        }

        for (int i=1; i<=n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j<=n; j++) {
                if (Integer.parseInt(st.nextToken())==1) {
                    union(i, j);
                }
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] plan = new int[m];
        
        for (int i=0; i<m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        int target = find(plan[0]);
        for (int i=1; i<m; i++) {
            if (find(plan[i]) != target) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
        
    }
}
