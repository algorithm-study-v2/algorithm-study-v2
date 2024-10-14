import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//골드1, 어려웠던 문제 https://cykei.tistory.com/132
public class 배달_정지환 {
	static int n, m;
	static char[][] board;
	static int[] dx = new int[]{-1, 0, 1, 0};
	static int[] dy = new int[]{0, 1, 0, -1};
	static int[][][][] visited;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		board = new char[n][m];
		boolean first = true;
		int[] start = new int[2];
		for (int i = 0; i < n; i++) {
			char[] arr = br.readLine().toCharArray();
			for(int j = 0; j < m; j++) {
				if(arr[j] == 'C') {
					if(first) {
						board[i][j] = 'C';
						first = false;
					} else {
						board[i][j] = 'D';
					}
					continue;
				}
				if(arr[j] == 'S') {
					start[0] = i;
					start[1] = j;
				}
				board[i][j] = arr[j];
			}
		}

		visited = new int[50][50][4][4];
		fill(50, 50, 4, 4);
		dfs(start[0], start[1], -1, 0, 0);
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

	public static void dfs(int x, int y, int dir, int check, int time) {
		if(answer <= time) return;
		if(check == 3) {
			answer = time;
			return;
		}
		for(int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(!checkRange(nx, ny) || dir == i || board[nx][ny] == '#') continue;
			//System.out.println(nx + " " + ny+ " " + board[nx][ny] + " " + (check | 2));
			if(board[nx][ny] == 'C' && time + 1 < visited[nx][ny][i][check | 2]) {
				//System.out.println("C " + nx + " " + ny);
				visited[nx][ny][i][check | 2] = time + 1;
				dfs(nx, ny, i, check | 2, time+1);
			} else if (board[nx][ny] == 'D' && time + 1 < visited[nx][ny][i][check | 1]) {
				//System.out.println("D " + nx + " " + ny);
				visited[nx][ny][i][check | 1] = time + 1;
				dfs(nx, ny, i, check | 1, time+1);
			} else if(time + 1 < visited[nx][ny][i][check]) {
				//System.out.println("E " + nx + " " + ny);
				visited[nx][ny][i][check] = time + 1;
				dfs(nx, ny, i, check, time+1);
			}
		}
	}

	public static void fill(int a, int b, int c, int d) {
		for(int i=0; i<a; i++) {
			for(int j=0; j<b; j++) {
				for(int k=0; k<c; k++) {
					for(int l=0; l<d; l++) {
						visited[i][j][k][l] = Integer.MAX_VALUE;
					}
				}
			}
		}
	}
	

	public static boolean checkRange(int x, int y) {
		return x>=0 && x<n && y>=0 && y<m;
	}
}
