import java.io.*;
import java.util.*;

public class 망가진나무_규태 {
	/*
	 * 508ms
	 * 1번 노드부터 시작해 뒤집을 간선의 개수를 dp에 저장하고
	 * dfs로 탐색하며 최소 변경 횟수가 되는 위치를 찾고 해당 위치에서 다시 간선의 개수를 확인하여 답을 유도한다.
	 */
	static int n, dp[], reverse[];
	static boolean visit[];
	static ArrayList<Node>[] adj;
	static class Node {
		int idx,dir,num;
		public Node(int idx, int dir, int num) {
			this.idx=idx;
			this.dir=dir;
			this.num=num;
		}
	}
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		dp = new int[n+1];
		reverse = new int[n+1];
		visit = new boolean[n+1];
		adj = new ArrayList[n+1];
		for(int i=1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=1; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[a].add(new Node(b,0,i));
			adj[b].add(new Node(a,1,i));
		}
		
		dp[1] = init(1); 
		visit = new boolean[n+1];
		visit[1]=true;
		for(Node next:adj[1]) {
			dfs(1, next.idx, next.dir);
		}
		
		int min=987654321;
		int idx=-1;
		for(int i=1; i<=n; i++) {
			if(dp[i]<min) {
				min=dp[i];
				idx=i;
			}
		}
	
		visit = new boolean[n+1];
		dfs(idx);
		
		for(int i=1; i<n; i++) {
			sb.append(reverse[i]);
		}
		System.out.println(sb);
	}
	static int init(int cur) {
		visit[cur]=true;
		int tmp=0;
		for(Node next:adj[cur]) {
			if(!visit[cur]) {
				tmp+=init(next.idx)+next.dir;
			}
		}
		return tmp;
	}
	static void dfs(int cur, int nxt, int dir) {
		visit[nxt]=true;
		if(dir==0) dp[nxt]+=(dp[cur]+1);
		else dp[nxt]+=(dp[cur]-1);
		
		for(Node next:adj[nxt]) {
			if(!visit[next.idx]) {
				dfs(nxt,next.idx,next.dir);
			}
		}
	}
	static void dfs(int cur) {
		visit[cur]=true;
		for(Node next:adj[cur]) {
			if(!visit[next.idx]) {
				dfs(next.idx);
				reverse[next.num]=next.dir;
			}
		}
	}
}