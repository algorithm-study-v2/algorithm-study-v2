import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 여행가자_규태 {
	/*
	 * 140ms
	 * union-find를 이용하여 이동가능한 경로인지 파악
	 */
	static int n,m,road[][],plan[],parent[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		road = new int[n][n]; plan = new int[m];
		parent = new int[n];
		for(int i=0; i<n; i++)
			parent[i]=i;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
				if(road[i][j]==1) union(i,j);
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<m; i++) 
			plan[i] = Integer.parseInt(st.nextToken())-1;
		boolean connected=true;
		for(int i=1; i<m; i++)
			if(union(plan[i-1],plan[i])) {
				connected=false; break;
			}
		
		if(connected) System.out.println("YES");
		else System.out.println("NO");
	}
	static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a]=find(parent[a]);
	}
	static boolean union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(aroot==broot) return false;
		parent[broot]=aroot;
		return true;
	}
}
