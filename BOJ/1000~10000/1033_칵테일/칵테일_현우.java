import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1033 칵테일
 * 72 ms
 * 수학
 * a와 b의 질량 비가 p:q일 때
 * (a 비율) *= (기존 b 비율) * p
 * (b 비율) *= (기존 a 비율) * q
 * 을 곱해주면 p:q 달성
 * a와 b에 연관되어 있던 재료들에도
 * 동일한 수를 곱해서 비율 유지
 * 마지막에 모든 수를 최대공약수로 나누어 출력
 */
public class 칵테일_현우 {
	private static final char SPACE = ' ';

	private static final class Edge { // 간선
		int to;
		Edge next;

		Edge(int to, Edge next) {
			this.to = to;
			this.next = next;
		}
	}

	private static int[] arr;
	private static Edge[] adj;

	private static void dfs(int curr, int parent, int multiplier) {
		Edge edge;

		arr[curr] *= multiplier; // 현재 재료에 연산
		for (edge = adj[curr]; edge != null; edge = edge.next) {
			if (edge.to == parent) {
				continue;
			}
			dfs(edge.to, curr, multiplier); // 연관된 재료들에 동일한 수를 곱해줌
		}
	}

	private static int getGcd(int a, int b) { // 최대공약수 반환
		if (b == 0) {
			return a;
		}
		return getGcd(b, a % b);
	}

	public static void main(String[] args) throws IOException {
		int n;
		int a;
		int b;
		int i;
		int gcd;
		int temp;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		for (i = 0; i < n; i++) { // 초기 비율 1:1:1:... 으로 초기화
			arr[i] = 1;
		}
		adj = new Edge[n]; // 그래프
		for (i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			temp = arr[a]; // arr[a]가 먼저 변화하므로 기존의 a 비율 저장
			dfs(a, -1, arr[b] * Integer.parseInt(st.nextToken()));
			dfs(b, -1, temp * Integer.parseInt(st.nextToken()));
			adj[a] = new Edge(b, adj[a]); // 그래프에 간선 추가
			adj[b] = new Edge(a, adj[b]);
		}
		gcd = arr[0];
		sb = new StringBuilder();
		for (i = 1; i < n; i++) { // 모든 수의 최대공약수
			gcd = getGcd(arr[i], gcd);
		}
		for (i = 0; i < n; i++) { // 최대공약수로 나누면서 출력
			sb.append(arr[i] / gcd).append(SPACE);
		}
		System.out.print(sb);
	}
}
