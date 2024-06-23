import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여행_규태 {
	/*
	 * 312ms
	 * 2차원배열에 기내식 만족도 저장후 2차원 dp를 활용해 m개 이하를 지나면서 만족도가 최대인경우 계산 
	 */
	static int n,m,k, cost[][], dp[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		cost = new int[n+1][n+1]; dp = new int[n+1][m];
		for(int i=1; i<=n; i++)
			Arrays.fill(dp[i], -1);
		
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int food = Integer.parseInt(st.nextToken());
			if(a<b) cost[a][b] = Math.max(cost[a][b], food);
		}
		
		dp[1][0]=0;
		for(int next=2; next<=n; next++) {
			for(int cur=1; cur<next; cur++) {
				for(int cnt=1; cnt<m; cnt++) {
					if(cost[cur][next]!=0 && dp[cur][cnt-1]!=-1)
						dp[next][cnt]=Math.max(dp[next][cnt], dp[cur][cnt-1]+cost[cur][next]);
				}
			}
		}
		
		int ans=0;
		for(int i=1; i<m; i++)
			ans = Math.max(ans, dp[n][i]);
		
		System.out.println(ans);
	}
}
