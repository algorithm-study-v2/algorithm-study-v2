import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 후위표기식_승현 { // 76ms

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input = br.readLine();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
//            System.out.println(stack.toString());
            char c = input.charAt(i);
            // 곱셈 나눗셈
            if (c == '*' || c == '/') {
                while (!stack.isEmpty() && stack.peek() != '(' && stack.peek() != ')' && stack.peek() != '+' && stack.peek() != '-') {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
            // 덧셈 뺄셈
            else if (c == '+' || c == '-') {
                while (!stack.isEmpty() && stack.peek() != '(' && stack.peek() != ')') {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }
            // 여는괄호
            else if (c == '(') {
                stack.push(c);
            }
            // 닫는괄호
            else if (c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    sb.append(stack.pop());
                }
                stack.pop();
            }
            // 알파벳
            else {
                stack.push(c);
            }

        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.println(sb);
    }
}
