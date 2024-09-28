import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 인구이동_지민 {
	static int N;
	static int L;
	static int R;
	static int[][] country;
	static final int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

	private static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	private static boolean inDiff(int p1, int p2) {
		int diff = Math.abs(p1 - p2);
		return L <= diff && diff <= R;
	}

	private static boolean move(int y, int x, boolean[][] visit) {
		Queue<int[]> point = new LinkedList<>();
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{y, x});
		point.add(new int[]{y, x});
		visit[y][x] = true;

		int countryCnt = 1;
		int people = country[y][x];
		while (!queue.isEmpty()) {
			int[] here = queue.poll();
			for (int i = 0; i < dir.length; i++) {
				int ny = here[0] + dir[i][0];
				int nx = here[1] + dir[i][1];
				if(!inRange(ny, nx)) continue;
				if(visit[ny][nx]) continue;
				if(inDiff(country[here[0]][here[1]], country[ny][nx])) {
					queue.add(new int[] {ny, nx});
					point.add(new int[]{ny, nx});
					visit[ny][nx] = true;
					countryCnt += 1;
					people += country[ny][nx];
				}
			}
		}

		int change = people / countryCnt;

		while (!point.isEmpty()) {
			int[] here = point.poll();
			country[here[0]][here[1]] = change;
		}

		return countryCnt > 1;
	}

	private static int solve() {
		int day = 0;
		boolean[][] visit;
		while (true) {
			boolean isEnd = true;
			visit = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(visit[i][j]) continue;
					if(move(i, j, visit)) isEnd = false;
				}
			}
			if(isEnd) break;
			day += 1;
		}

		return day;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		country = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				country[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(solve());
	}
}