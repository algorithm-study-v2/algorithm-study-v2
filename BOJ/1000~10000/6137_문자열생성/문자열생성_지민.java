import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 문자열생성_지민 {
	static int N;
	static char[] A;

	// A[l]과 A[r]이 같은 경우
	private static boolean isLeftSmall(int left, int right) {
		int idx = 1;
		while (idx < (right - left + 1)) {
			if (A[left + idx] < A[right - idx]) return true;
			else if (A[left + idx] > A[right - idx]) return false;
			idx++;
		}
		return true;
	}

	private static String solve() {
		StringBuilder sb = new StringBuilder();
		int l = 0, r = N - 1;
		while (l <= r) {
			if (A[l] < A[r]) sb.append(A[l++]);
			else if(A[l] > A[r]) sb.append(A[r--]);
			else {
				if (isLeftSmall(l, r)) sb.append(A[l++]);
				else sb.append(A[r--]);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());

		A = new char[N];
		for (int i = 0; i < N; i++) {
			A[i] = br.readLine().charAt(0);
		}

		String res = solve();
		for (int i = 0; i < res.length(); i++) {
			if (i != 0 && i % 80 == 0) System.out.println();
			System.out.print(res.charAt(i));
		}
	}
}
