import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 소가길을건너간이유6_지민 {
	static class Cow {
		int y, x;
		public Cow(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int N;
	static int K;
	static int R;
	static boolean[][] load;
	static Cow[] cows;
	static List<List<Integer>> cowGroups;
	static int[][] group;
	static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	private static boolean go(int y1, int x1, int y2, int x2) {
		return !load[y1 * N + x1][y2 * N + x2];
	}

	private static void dfs(int y, int x, int g) {
		group[y][x] = g;
		for (int i = 0; i < 4; i++) {
			int ny = y + dir[i][0];
			int nx = x + dir[i][1];
			if(!inRange(ny, nx)) continue;
			if(go(y, x, ny, nx) && group[ny][nx] == 0) dfs(ny, nx, g);
		}
	}

	private static int solve() {
		group = new int[N][N];
		int g = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(group[i][j] != 0) continue;
				dfs(i, j, g);
				g++;
			}
		}

		cowGroups = new ArrayList<>();
		for (int i = 0; i < g; i++) cowGroups.add(new ArrayList<>());

		for (int i = 0; i < K; i++) {
			cowGroups.get(group[cows[i].y][cows[i].x]).add(i);
		}

		int res = 0;
		boolean[][] go = new boolean[K][K];

		for (int i = 1; i < cowGroups.size(); i++) {
			List<Integer> cg = cowGroups.get(i);
			for (int j = 0; j < cg.size(); j++) {
				for (int k = j + 1; k < cg.size(); k++) {
					go[cg.get(j)][cg.get(k)] = true;
					go[cg.get(k)][cg.get(j)] = true;
				}
			}
		}

		for (int i = 0; i < K; i++) {
			for (int j =  i + 1; j < K; j++) {
				if(go[i][j]) continue;
				res += 1;
			}
		}

		return res;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		load = new boolean[N * N][N * N];
		cows = new Cow[K];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken()) - 1;
			int c1 = Integer.parseInt(st.nextToken()) - 1;
			int r2 = Integer.parseInt(st.nextToken()) - 1;
			int c2 = Integer.parseInt(st.nextToken()) - 1;
			int idx1 = r1 * N + c1;
			int idx2 = r2 * N + c2;
			load[idx1][idx2] = load[idx2][idx1] = true;
		}
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			cows[i] = new Cow(r, c);
		}
		System.out.println(solve());
	}
}
