import java.io.*;
import java.util.*;

public class 크게만들기_규영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		char[] num = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<>();
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			while(!stack.isEmpty() && stack.peek() < num[i] && cnt < K) {
				stack.pop();
				cnt++;
			}
			stack.push(num[i]);
		}
		while (cnt < K) {
			stack.pop();
			cnt++;
		}
		for (char c : stack) sb.append(c);
		System.out.print(sb);
	}
}