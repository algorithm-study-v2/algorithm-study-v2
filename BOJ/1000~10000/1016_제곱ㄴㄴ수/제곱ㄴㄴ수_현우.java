import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1016 제곱 ㄴㄴ 수
 * 100 ms
 * 에라토스테네스의 체
 * min ~ max 중에
 * 2부터 sqrt(max)까지의 제곱수로
 * 나누어 떨어지는 수 표시
 * */
public class 제곱ㄴㄴ수_현우 {
	public static void main(String[] args) throws IOException {
		int ans;
		int size;
		long i;
		long j;
		long min;
		long max;
		long num;
		long thr;
		boolean[] sqrt;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		min = Long.parseLong(st.nextToken());
		max = Long.parseLong(st.nextToken());
		size = (int) (max - min + 1L);
		sqrt = new boolean[size];
		thr = (long) Math.ceil(Math.sqrt(max));
		ans = size; // min ~ max까지 모든 수로 초기화
		for (i = 2; i <= thr; i++) { // 2부터 sqrt(max)까지의 수
			num = i * i; // 제곱
			for (j = (min - 1) / num * num + num; j <= max; j += num) { // min ~ max
				if (!sqrt[(int) (j - min)]) { // 나누어 떨어진적 없으면
					sqrt[(int) (j - min)] = true; // 나누어 떨어짐 표시
					ans--; // 제곱 ㄴㄴ 수 감소
				}
			}
		}
		System.out.print(ans);
	}
}
