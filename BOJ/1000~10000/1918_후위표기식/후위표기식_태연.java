import java.io.*;
import java.util.*;

public class 후위표기식_태연 {
	
	/*
	 * - 68ms
	 * 
	 * - 들어갈때랑 스택안에서 우선순위 구분해서 연산
	 */
	
	static boolean isAlphabet(char c) {
		return c>='A' && c<='Z';
	}
	
	static boolean toSkip(char c) {
		return c=='(';
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<Character, Integer> inputPriority = new HashMap<>();
		Map<Character, Integer> inStackPriority = new HashMap<>();
		
		inputPriority.put('(', 0);
		inputPriority.put('*', 1);
		inputPriority.put('/', 1);
		inputPriority.put('+', 2);
		inputPriority.put('-', 2);
		inputPriority.put(')', 3);
		
		// 시작점
		inStackPriority.put('#', 100);
		inStackPriority.put('(', 3);
		inStackPriority.put('*', 1);
		inStackPriority.put('/', 1);
		inStackPriority.put('+', 2);
		inStackPriority.put('-', 2);
		inStackPriority.put(')', 10);

		char[] infix = br.readLine().toCharArray();
		StringBuilder sb = new StringBuilder();
		Deque<Character> stack = new ArrayDeque<>();
		stack.addLast('#');
		
		for(int i=0; i<infix.length; i++) {
			if(isAlphabet(infix[i])) {
				sb.append(infix[i]);
			} else {
				if(infix[i] == ')') {
					while(stack.peekLast() != '(') {
						sb.append(stack.pollLast());
					}
					stack.pollLast();
				} else if(inStackPriority.get(stack.peekLast()) > inputPriority.get(infix[i])) {
					stack.addLast(infix[i]);
				} else {
					while(inStackPriority.get(stack.peekLast()) <= inputPriority.get(infix[i])){
						char c = stack.pollLast();
						if(!toSkip(c)) {
							sb.append(c);
						}
					}
					stack.addLast(infix[i]);
				}
			}
		}
		
		while(inStackPriority.get(stack.peekLast()) != 100){
			char c = stack.pollLast();
			if(!toSkip(c)) {
				sb.append(c);
			}
		}
		System.out.print(sb);
	}
}
