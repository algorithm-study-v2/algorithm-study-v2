import java.io.*;
import java.util.*;

public class 기업투자_규태 {
	/*
	 * 132ms
	 * 현재 기업 i에서 해당 금액 j을 투자했을때의 최고 투자금액을 profit[i][j]로 정의하여
	 * 2차원 dp배열을 채워가는 형식으로 구현
	 */
	static int n,m,invest[][],profit[][],now[][],result[];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		invest = new int[m+1][n+1]; profit = new int[m+1][n+1]; 
		now = new int[m+1][n+1]; result = new int[m+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<=m; j++)
				invest[j][i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1; i<=m; i++) { // 현재기업
			for(int j=1; j<=n; j++) { // 현재금액
				for(int k=0; k<=j; k++) { //
					int tmp = profit[i-1][j-k]+invest[i][k];
					if(tmp>profit[i][j]) {
						profit[i][j]=tmp;
						now[i][j]=k;
					}
				}
			}
		}
		
		System.out.println(profit[m][n]);
		int money=n;
		for(int i=m; i>=1; i--)	{
			result[i]=now[i][money];
			money-=result[i];
		}
		for(int i=1; i<=m; i++) {
			System.out.print(result[i]+" ");
		}
	}
}
