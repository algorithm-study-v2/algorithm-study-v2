import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

/**
 * 정현우 : BOJ 1918 후위 표기식
 * 76 ms
 * 스택
 * 피연산자는 출력
 * 여는 괄호의 경우 스택에 삽입
 * 닫는 괄호의 경우 여는 괄호를 만날 때까지 pop
 * 연산자의 경우 우선순위가 낮은 연산자를 만날 때까지
 * pop 후 자신을 스택에 삽입
 * 끝에 도달하면 스택에 남은 요소들 pop
 * */
public class 후위표기식_현우 {
	private static final char A = 'A';
	private static final char Z = 'Z';
	private static final char OPEN = '(';
	private static final char CLOSE = ')';
	private static final char DIVISION = '/';
	private static final char MULTIPLICATION = '*';

	public static void main(String[] args) throws IOException {
		char[] str;
		StringBuilder sb;
		BufferedReader br;
		ArrayDeque<Character> stack;

		br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		stack = new ArrayDeque<>();
		sb = new StringBuilder();
		for (char ch : str) {
			if (A <= ch && ch <= Z) { // 피연산자는 출력
				sb.append(ch);
			} else if (ch == OPEN) { // 여는 괄호의 경우 스택에 삽입
				stack.addFirst(ch);
			} else if (ch == CLOSE) { // 닫는 괄호의 경우 여는 괄호를 만날 때까지 pop
				for (; (ch = stack.pollFirst()) != OPEN; sb.append(ch));
			} else if (ch == MULTIPLICATION || ch == DIVISION) { // 연산자의 경우 우선순위가 낮은 연산자나 여는 괄호를 만날 때까지 pop 후 자신을 스택에 삽입
				for (; !stack.isEmpty() && (stack.peekFirst() == MULTIPLICATION || stack.peekFirst() == DIVISION); sb.append(stack.pollFirst()));
				stack.addFirst(ch);
			} else { // +, -는 우선순위가 낮으므로 여는 괄호를 만날 때까지 pop 후 삽입
				for (; !stack.isEmpty() && stack.peekFirst() != OPEN; sb.append(stack.pollFirst()));
				stack.addFirst(ch);
			}
		}
		for (; !stack.isEmpty(); sb.append(stack.poll())); // 스택에 남은 요소들 pop
		System.out.print(sb);
	}
}
