import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 계단수_지민 {
	static int N;
	static double[] percentages;
	static double res;
	static final int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

	private static void dfs(int y, int x, int depth, double currPercent, boolean[][] visit) {
		if(depth >= N) {
			res += currPercent;
			return;
		}

		for (int i = 0; i < DIR.length; i++) {
			if(percentages[i] == 0) continue;
			int ny = y + DIR[i][0];
			int nx = x + DIR[i][1];

			if(!visit[ny][nx]) {
				visit[ny][nx] = true;
				dfs(ny, nx, depth + 1, currPercent * percentages[i], visit);
				visit[ny][nx] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		percentages = new double[4];
		for (int i = 0; i < 4; i++) {
			percentages[i] = Integer.parseInt(st.nextToken()) * 0.01;
		}
		res = 0;
		boolean[][] visited = new boolean[N * 2 + 1][N * 2 + 1];
		visited[N][N] = true;
		dfs(N, N, 0, 1, visited);
		System.out.println(res);
	}
}
