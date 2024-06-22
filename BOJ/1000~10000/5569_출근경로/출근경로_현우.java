import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 5569 출근 경로
 * 76 ms
 * DP
 * up[i][j] ((i, j) 교차로를 위로 직진 통과)
 * 	= up[i][j - 1] (아래 칸에서도 직진)
 * 	+ right[i - 1][j - 1] (대각 출발, 아래 칸에서 좌회전)
 * right[i][j] ((i, j) 교차로를 오른쪽으로 직진 통과)
 * 	= right[i - 1][j] (왼쪽 칸에서도 직진)
 * 	+ up[i - 1][j - 1] (대각 출발, 왼쪽 칸에서 우회전)
 * */
public class 출근경로_현우 {
	private static final int MOD = 100_000;
	
	public static void main(String[] args) throws IOException {
		int w;
		int h;
		int i;
		int j;
		int[][] up;
		int[][] right;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		up = new int[w + 1][h + 1];
		right = new int[w + 1][h + 1];
		up[1][1] = 1; // (1, 1) 경우의 수
		right[1][1] = 1;
		for (i = 1, j = 2; i <= w; i++, j = 1) { // (1, 2)부터 탐색하기 위해 for문 조정
			for (; j <= h; j++) {
				up[i][j] = (up[i][j - 1] + right[i - 1][j - 1]) % MOD;
				right[i][j] = (right[i - 1][j] + up[i - 1][j - 1]) % MOD;
			}
		}
		System.out.print((up[w][h] + right[w][h]) % MOD);
	}
}
