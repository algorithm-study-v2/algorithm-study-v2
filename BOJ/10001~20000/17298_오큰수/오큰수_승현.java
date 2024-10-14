import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class 오큰수_승현 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while(!stack.isEmpty() && a[stack.peek()] < a[i]) {
                a[stack.pop()] = a[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            a[stack.pop()] = -1;
        }

        for (int i = 0; i < n; i++){
            sb.append(a[i]).append(' ');
        }

        System.out.println(sb);
    }
}
