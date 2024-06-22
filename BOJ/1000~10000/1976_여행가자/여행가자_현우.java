import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1976 여행 가자
 * 88 ms
 * Union-Find
 * 간선이 존재하는 곳 Union
 * 여행경로에 속하는 도시들이
 * 모두 같은 집합에 존재하는지 확인
 * */
public class 여행가자_현우 {
	private static final char CONNECTED = '1';
	private static final String YES = "YES";
	private static final String NO = "NO";
	
	private static int[] roots; // 양수 : 부모, 0 또는 음수 : -(트리의 높이 - 1)
	
	private static final int find(int v) { // Path Compression
		if (roots[v] <= 0) {
			return v;
		}
		return roots[v] = find(roots[v]);
	}
	
	private static final void union(int u, int v) { // Union by Rank
		int ru, rv;
		
		if ((ru = find(u)) == (rv = find(v))) { // 이미 같은 집합에 존재
			return;
		}
		if (roots[ru] < roots[rv]) { // u트리가 더 높은 경우
			roots[rv] = ru; // v트리를 u트리의 루트에 연결
		} else {
			if (roots[ru] == roots[rv]) { // 높이가 같은 경우
				roots[rv]--; // v트리 높이 증가
			}
			roots[ru] = rv; // u트리를 v트리의 루트에 연결
		}
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int i;
		int j;
		int start;
		String str;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		roots = new int[n + 1];
		for (i = 1; i <= n; i++) {
			str = br.readLine();
			for (j = i; j < n;) {
				if (str.charAt(j++ << 1) == CONNECTED) { // 간선 존재
					union(i, j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		start = find(Integer.parseInt(st.nextToken())); // 시작 도시가 속한 집합
		while (--m > 0) {
			if (start != find(Integer.parseInt(st.nextToken()))) { // 서로 다른 집합
				System.out.print(NO);
				return;
			}
		}
		System.out.print(YES);
	}
}
