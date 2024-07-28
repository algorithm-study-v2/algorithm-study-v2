import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회문이면 0, 유사회문이면 1, 그 외는 2
 */
public class 회문_지민 {
	static char[] str;

	private static int solve(int lo, int hi, int cnt) {
		while (lo < hi) {
			if(str[lo] != str[hi]) {
				if(cnt == 0) {
					if(solve(lo + 1, hi, cnt + 1) == 0
						|| solve(lo, hi - 1, cnt + 1) == 0)  return 1;
					else return 2;
				}
				else return 2;
			}
			lo++;
			hi--;
		}
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T = Integer.parseInt(st.nextToken());

		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			str = br.readLine().toCharArray();
			sb.append(solve(0, str.length - 1, 0)).append("\n");
		}
		System.out.println(sb);
	}
}
