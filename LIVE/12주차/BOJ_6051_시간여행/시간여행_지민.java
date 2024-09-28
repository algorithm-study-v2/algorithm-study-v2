import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * a: K인 문제 저장 후, K반환
 * s: 가장 최근 문제 삭제 후, 마지막 문제 반환
 * t: K - 1번 째 쿼리 수행 직후로 이동 후, 마지막 문제 반환
 * 가장 최근에 푼 문제 없으면 -1 반환
 * 
 * 272ms
 */
public class 시간여행_지민 {
	static int N;
	static int[] query;
	static int[] prev;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		query = new int[N + 1];
		prev = new int[N + 1];
		query[0] = -1;
		prev[0] = 0;
		
		StringBuilder sb = new StringBuilder();
		for (int idx = 1; idx <= N; idx++) {
			st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int k = -1;

			if (c == 'a') {
				k = Integer.parseInt(st.nextToken());
				query[idx] = k;
				prev[idx] = idx - 1;
			}
			if (c == 's') {
				query[idx] = query[prev[idx - 1]];
				prev[idx] = prev[prev[idx - 1]];
			}
			if (c == 't') {
				k = Integer.parseInt(st.nextToken());
				query[idx] = query[k - 1];
				prev[idx] = prev[k - 1];
			}
			sb.append(query[idx]).append("\n");
			
		}
		System.out.println(sb);

	}
}