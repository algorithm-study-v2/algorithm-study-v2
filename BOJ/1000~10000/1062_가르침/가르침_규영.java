import java.io.*;
import java.util.*;

/*
	a, n, t, i, c을 모두 replace 했더니 시간초과가 떴음
	anta와 tica만 replace 해주니 해결
	292196KB 492ms
 */

public class 가르침_규영 {
	static int N, K, ans;
	static String[] words;
	static boolean[] checked;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (K == 26) ans = N;
		else if (5 <= K) {
			words = new String[N];
			for (int i = 0; i < N; i++) {
				String word = br.readLine().replace("anta", "").replace("tica", "");
				words[i] = word;
			}
			checked = new boolean[26];
			checked[0] = checked[2] = checked['t'-'a'] = checked['i'-'a'] = checked['n'-'a'] = true;
			ans = 0;
			choice(0, 0);
		}
		System.out.print(ans);
	}

	static void choice(int idx, int num) {
		if (num == K-5) {
			int cnt = 0;
			for (String word : words) {
				if (isValid(word)) cnt++;
			}
			ans = Math.max(ans, cnt);
			return;
		}
		for (int i = idx; i < 26; i++) {
			if (checked[i]) continue;
			checked[i] = true;
			choice(i+1, num+1);
			checked[i] = false;
		}
	}

	static boolean isValid(String word) {
		char[] chars = word.toCharArray();
		for (char c : chars) {
			if (checked[c-'a']) continue;
			return false;
		}
		return true;
	}
}