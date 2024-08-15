import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class 열쇠_규태 {
	/*
	 * 208ms
	 * 큐 두개를 활용하여 현재 접근할 수 있는 공간과 열쇠를 첫 큐 q에 저장하고
	 * 접근할 수 없는 구역을 두번째 큐 q2에 저장하며, 첫 큐에서 활동이 모두 끝났을때
	 * 막힌 지점들의 모임인 q2의 각 요소를 첫 큐로 옮겨닮고, 해당 지점들에서 시작하여 반복적으로 진행하여 문제 해결 
	 */
	static int n, m,document=0;
	static char[][] map;
	static boolean[][] visit;
	static int move[][] = {{1,0},{0,1},{-1,0},{0,-1}};
	static Queue<int[]> q = new ArrayDeque<int[]>();
	static Queue<int[]> q2 = new ArrayDeque<int[]>();
	static Set<Character> key = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		for(int test=1; test<=t; test++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			map = new char[n][m];
			visit = new boolean[n][m];
			key = new HashSet<>();
			document=0;
			q = new ArrayDeque<int[]>();
			q2 = new ArrayDeque<int[]>();
			
			for(int i=0; i<n; i++) {
				String line = br.readLine();
				for(int j=0; j<m; j++) {
					map[i][j] = line.charAt(j);
					if(isEdge(i,j) && map[i][j]!='*') {
						q.offer(new int[] {i,j});
						if(map[i][j]>='A' && map[i][j]<='Z') continue;
						else if(map[i][j]>='a' && map[i][j]<='z') {
							key.add(map[i][j]);
							visit[i][j]=true;
						}
						else if(map[i][j]=='$') {
							document++;
							visit[i][j]=true;
						}
						else if(map[i][j]=='.') {
							visit[i][j]=true;
						}
					}
				}
			}
			String line = br.readLine();
			if(!line.equals("0")) {
				for(int i=0; i<line.length(); i++)
					key.add(line.charAt(i));
			}
			boolean flag=true;
			while(flag) {
				int size = key.size();
				bfs();
				if(key.size()==size) flag=false;
				while(!q2.isEmpty()) {
					q.offer(q2.poll());
				}
			}
			sb.append(document).append('\n');
		}
		System.out.println(sb);

	}
	static void bfs() {
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			q2.offer(cur);
			if(!visit[cur[0]][cur[1]]) {
				char door = (char)(map[cur[0]][cur[1]]+32);
				if(key.contains(door)) {
					visit[cur[0]][cur[1]]=true;
					map[cur[0]][cur[1]]='.';
				}
			}
			if(visit[cur[0]][cur[1]]) {
				for(int[] m:move) {
				int ni=cur[0]+m[0];
				int nj=cur[1]+m[1];
				if(inRange(ni,nj) && !visit[ni][nj]) {
					if(map[ni][nj]=='*') continue;
					else if(map[ni][nj]=='.') {
						visit[ni][nj]=true;
						q.offer(new int[] {ni,nj});
					}
					else if(map[ni][nj]=='$') {
						visit[ni][nj]=true;
						document++;
						map[ni][nj]='.';
						q.offer(new int[] {ni,nj});
					}
					else {
						char c = map[ni][nj];
						if(c>='a' && c<='z') {
							key.add(c);
							visit[ni][nj]=true;
							map[ni][nj]='.';
							q.offer(new int[] {ni,nj});
						}
						else {
							char door = (char)(map[ni][nj]+32);
							if(key.contains(door)) {
								visit[ni][nj]=true;
								map[ni][nj]='.';
								q.offer(new int[] {ni,nj});
							}
							else {
								q2.offer(new int[] {ni,nj});
							}
						}
					}
				}
			}
			}
			
		}
	}
	static boolean inRange(int i, int j) {
		return(i>=0 && i<n && j>=0 && j<m);
	}
	static boolean isEdge(int i, int j) {
		return(i==0 || i==n-1 || j==0 || j==m-1);
	}
}