import java.io.*;
import java.util.*;

public class 저울_태연 {
	
	static int n;
	
	static boolean[][] rel;
	
	static void setRel(int a, int b) {
		for(int c=1; c<=n; c++) {
			
			if(rel[b][c]) {
				rel[a][c] = true;
			} else if(rel[c][a]) {
				rel[c][b] = true;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		// rel[a][b] => a가 b보다 무겁다
		rel = new boolean[n+1][n+1];
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			rel[a][b] = true;
			
		}
		
		for(int a=1; a<=n; a++) {
			for(int b=1; b<=n; b++) {
				
				if(!rel[a][b]) continue;
				
				setRel(a,b);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=n; i++) {
			int cnt = n-1;
			
			for(int j=1; j<=n; j++) {
				
				if(rel[i][j] || rel[j][i]) cnt--;
			}
			
			sb.append(cnt).append('\n');
		}
		
		System.out.print(sb);
	}
}
