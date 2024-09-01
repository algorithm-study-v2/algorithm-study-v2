import java.io.*;
import java.util.*;

/*
	294356KB 500ms
 */

public class 인구이동_규영 {
	static class Node {
		int r, c;
		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	static boolean hasValue, moved;
	static int N, L, R, sum;
	static boolean[][] visited;
	static ArrayList<Node> union;
	static int[][] arr, dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int ans = 0;
		while (true) {
			visited = new boolean[N][N];
			moved = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j]) continue;
					visited[i][j] = true;
					hasValue = false;
					bfs(new Node(i, j));
					if (hasValue) setPopulation(sum/union.size());
				}
			}
			if (moved) ans++;
			else break;
		}
		System.out.print(ans);
	}

	static void bfs(Node n) {
		Queue<Node> q = new LinkedList<>();
		q.offer(n);
		sum = arr[n.r][n.c];
		union = new ArrayList<>();
		union.add(n);
		while (!q.isEmpty()) {
			Node now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now.r+dir[d][0], nc = now.c+dir[d][1];
				if (!isInRange(nr, nc) || visited[nr][nc]) continue;
				int val = Math.abs(arr[now.r][now.c]-arr[nr][nc]);
				if (L <= val && val <= R) {
					hasValue = true;
					Node next = new Node(nr, nc);
					union.add(next);
					sum += arr[nr][nc];
					q.offer(next);
					visited[nr][nc] = true;
				}
			}
		}
	}

	static void setPopulation(int val) {
		moved = true;
		for (Node n : union) {
			arr[n.r][n.c] = val;
		}
	}

	static boolean isInRange(int nr, int nc) {
		return 0 <= nr && nr < N && 0 <= nc && nc < N;
	}
}