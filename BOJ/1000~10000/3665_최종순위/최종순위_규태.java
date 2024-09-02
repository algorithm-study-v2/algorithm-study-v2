import java.io.*;
import java.util.*;

public class 최종순위_규태 {
	/*
	 * 976ms
	 * 위상정렬을 통해 indegree를 계산하고 재조정하며,
	 * indegree가 0인 점부터 반복적으로 제거하고, 완료된 경우 성공, 이외의 경우에는 실패로 값을 출력한다
	 */
	static int n,indegree[],graph[][];
	static Queue<Integer> q;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int test = Integer.parseInt(br.readLine());		
		for(int t=1; t<=test; t++) {
			n = Integer.parseInt(br.readLine());
			indegree = new int[n+1];
			graph = new int[n+1][n+1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) {
				int cur = Integer.parseInt(st.nextToken());
				indegree[cur]=i-1;
				for(int j=1; j<=n; j++) {
					if(graph[cur][j]==0) graph[cur][j]=1;
					if(graph[j][cur]==0) graph[cur][j]=-1;
				}
				graph[cur][cur]=-1;
			}
			
			int m = Integer.parseInt(br.readLine());
			for(int i=1; i<=m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(graph[a][b]==1) {
					graph[a][b]=-1; graph[b][a]=1;
					indegree[a]--; indegree[b]++;
				} else {
					graph[a][b]=1; graph[b][a]=-1;
					indegree[a]++; indegree[b]--;
				}
			}
			
			q = new ArrayDeque<>();
			list = new ArrayList<>();
			topology();
		}
	}
	static void topology() {
		for(int i=1; i<=n; i++) {
			if(indegree[i]==0) {
				q.offer(i);
			}
		}
		
		for(int i=1; i<=n; i++) {
			if(q.size()>1) {
				System.out.println("?");
				return;
			} else if(q.isEmpty()) {
				System.out.println("IMPOSSIBLE");
				return;
			}
			
			int cur = q.poll();
			list.add(cur);
			
			for(int j=1; j<=n; j++) {
				if(graph[j][cur]==1) {
					graph[j][cur]=-1;
					indegree[j]--;
					if(indegree[j]==0) {
						q.offer(j);
					}
				}
			}
		}
		getList();
	}
	static void getList() {
		for(int i=0; i<list.size(); i++) {
			System.out.print(list.get(i));
			if(i==list.size()-1) System.out.println();
			else System.out.print(" ");
		}
	}
}
