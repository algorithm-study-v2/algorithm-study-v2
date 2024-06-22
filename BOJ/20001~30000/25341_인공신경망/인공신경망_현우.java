import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 25341 인공 신경망
 * 1408 ms
 * 수학
 * 편향값은
 * 은닉층의 편향 값에 출력층의 가중치를
 * 곱한 값들의 합 + 출력층의 편향값 으로 적용
 * 입력층의 입력값은
 * 은닉층에서 해당 입력을 참조하는 곳들의 가중치에
 * 해당 은닉층에 대한 출력층의 가중치를 곱한 값들의
 * 합만큼 곱해져서 최종 출력에 적용
 * */
public class 인공신경망_현우 {
	private static final char LINE_BREAK = '\n';
	
	public static void main(String[] args) throws IOException {
		int n;
		int m;
		int q;
		int c;
		int b;
		int i;
		int j;
		int[] p;
		int[] w;
		int[] multiplier;
		long val;
		String[] strs;
		StringBuilder sb;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		strs = new String[m];
		for (i = 0; i < m; i++) { // 출력층을 먼저 입력받기 위해 은닉층 입력은 뒤로 미룸
			strs[i] = br.readLine();
		}
		w = new int[m];
		st = new StringTokenizer(br.readLine()); // 출력층 입력
		for (i = 0; i < m; i++) {
			w[i] = Integer.parseInt(st.nextToken()); // 은닉층에 대한 출력층에서의 가중치
		}
		b = Integer.parseInt(st.nextToken()); // 편향값
		p = new int[n]; // 특정 은닉층에서 참조하는 입력값들의 위치
		multiplier = new int[n + 1]; // 각각의 입력값에 대한 승수
		for (i = 0; i < m; i++) {
			st = new StringTokenizer(strs[i]); // 은닉층 입력
			c = Integer.parseInt(st.nextToken()); // 참조하는 입력 데이터 개수
			for (j = 0; j < c; j++) {
				p[j] = Integer.parseInt(st.nextToken()); // 참조할 입력값들의 위치
			}
			for (j = 0; j < c; j++) { // 승수에 현재 가중치와 출력층에서의 가중치를 곱한 값을 더함
				multiplier[p[j]] += Integer.parseInt(st.nextToken()) * w[i];
			}
			b += Integer.parseInt(st.nextToken()) * w[i]; // 편향값에 현재 편향값과 출력층에서의 가중치를 곱한 값을 더함
		}
		sb = new StringBuilder();
		for (i = 0; i < q; i++) {
			val = b; // 최종 계산된 편향값
			st = new StringTokenizer(br.readLine());
			for (j = 1; j <= n; j++) {
				val += Integer.parseInt(st.nextToken()) * multiplier[j]; // 입력값 * 승수
			}
			sb.append(val).append(LINE_BREAK);
		}
		System.out.print(sb);
	}
}
