import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static final int MAX = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) arr[i] = Integer.parseInt(st.nextToken());

        int[] answer1 = new int[n + 1];
        int[] answer2 = new int[n + 1];
        Arrays.fill(answer2, MAX);


        Stack<Info> stack = new Stack<>();
        stack.add(new Info(1, arr[1]));

        for (int i = 2; i <= n; i++) {
            while (!stack.isEmpty()) {
                Info prev = stack.pop();
                if (prev.height > arr[i]) {
                    stack.add(prev);
                    answer1[i] += stack.size();
                    answer2[i] = prev.index;
                    break;
                }
            }
            stack.add(new Info(i, arr[i]));
        }

        stack = new Stack<>();
        stack.add(new Info(n, arr[n]));

        for (int i = n - 1; i >= 1; i--) {
            while (!stack.isEmpty()) {
                Info prev = stack.pop();
                if (prev.height > arr[i]) {
                    stack.add(prev);
                    answer1[i] += stack.size();

                    if (answer2[i] == MAX) {
                        answer2[i] = prev.index;
                    } else {
                        int diff1 = Math.abs(answer2[i] - i);
                        int diff2 = Math.abs(prev.index - i);
                        if (diff1 > diff2) answer2[i] = prev.index;
                    }
                    break;
                }
            }
            stack.add(new Info(i, arr[i]));
        }

        for (int i = 1; i <= n; i++) {
            if (answer2[i] == MAX) {
                System.out.println(0);
            } else {
                System.out.println(answer1[i] + " " + answer2[i]);
            }
        }

    }

    static class Info {
        int index;
        int height;

        Info(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }
}

