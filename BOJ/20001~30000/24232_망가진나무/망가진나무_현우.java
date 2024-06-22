import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 24232 망가진 나무
 * 628 ms
 * DP Tree
 * 모든 정점에 도달할 수 있는 정점 == 트리의 루트
 * 트리의 루트는 나가는 간선만 존재
 * 나머지 노드는 들어오는 간선 한 개와
 * 0개 이상의 나가는 간선이 존재
 * in 메소드
 * : 부모 노드에서 현재 노드로 간선이 들어올 때
 *   서브트리 비용 계산
 *   == 서브트리에 루트 노드 존재 X
 * out 메소드
 * : 현재 노드에서 부모 노드로 간선이 나갈 때
 *   서브트리 비용 계산
 *   == 서브트리에 루트 노드 반드시 존재
 * 서브트리에 루트를 포함하지 않은 경우와 포함한 경우의
 * 비용을 계산하면서 DP
 * */
public class 망가진나무_현우 {
	private static final int MIN = Integer.MIN_VALUE >> 1;
	private static final int DIFF = '0';
	
	private static final class Edge {
		int to; // 도착 노드
		int dir; // 원래 입력과 비교하여 정방향 0, 역방향 1
		int idx; // 간선 번호
		Edge next; // 링크
		
		Edge(int to, Edge next, int idx, int dir) {
			this.to = to;
			this.next = next;
			this.idx = idx;
			this.dir = dir;
		}
	}
	
	private static int[] dp; // in 메소드의 메모이제이션
	private static int[] roots; // 서브트리가 루트 노드를 포함할 때 최적의 루트 노드
	private static char[] ans; // 출력 배열
	private static Edge[] adj; // 그래프
	
	private static final int in(int curr, int parent, int dir) {
		int val;
		Edge edge;
		
		if (dp[curr] != -1) {
			return dp[curr];
		}
		val = dir; // 비용 == 부모노드와 연결된 간선 전환 비용 + 연결된 서브 트리 비용 합
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			val += in(edge.to, curr, edge.dir);
		}
		return dp[curr] = val;
	}
	
	private static final int out(int curr, int parent, int dir) {
		int ret;
		int val;
		int diff;
		int root;
		int outVal;
		int maxDiff;
		Edge edge;
		
		val = in(curr, parent, dir) - dir + (dir ^ 1); // 현재 노드가 루트일 경우의 비용
		ret = -1;
		maxDiff = MIN;
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			outVal = out(edge.to, curr, edge.dir); // 연결된 서브트리에 루트가 존재할 때 비용
			root = roots[edge.to]; // 서브트리에서 루트가 되는 노드
			diff = dp[edge.to] - outVal; // 해당 서브트리에 루트가 존재하지 않을 때와 비용 비교
			if (diff > maxDiff) { // 가장 효율적인 경우
				ret = root;
				maxDiff = diff;
			}
		}
		if (val < val - maxDiff) { // 현재 노드가 루트인 것이 더 효율적인 경우
			roots[curr] = curr;
			return val;
		}
		roots[curr] = ret;
		return val - maxDiff; // 서브트리에 루트가 존재하는 것이 효율적인 경우
	}
	
	private static final void dfs(int curr, int parent) {
		Edge edge;
		
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			ans[edge.idx] = (char) (edge.dir + DIFF); // 간선 변환 여부
			dfs(edge.to, curr);
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int u;
		int v;
		int i;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		adj = new Edge[n + 1];
		for (i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			adj[u] = new Edge(v, adj[u], i, 0); // 정방향 간선 입력
			adj[v] = new Edge(u, adj[v], i, 1); // 역방향 간선 입력
		}
		dp = new int[n + 1];
		for (i = 1; i <= n; i++) {
			dp[i] = -1; // -1로 초기화
		}
		roots = new int[n + 1];
		out(1, -1, 1); // 루트가 존재할 때 최소 비용 계산
		ans = new char[n - 1];
		dfs(roots[1], -1); // 간선 변환 여부 탐색
		System.out.print(ans);
	}
}
