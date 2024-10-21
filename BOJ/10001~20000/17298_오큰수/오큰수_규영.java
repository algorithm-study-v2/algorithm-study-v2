import java.io.*;
import java.util.*;

/*
	199144KB, 992ms
 */

public class 오큰수_규영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		for(int i = 0; i < N; i++) {
			while(!stack.isEmpty() && arr[stack.peek()] < arr[i]) arr[stack.pop()] = arr[i];
			stack.push(i);
		}
		while(!stack.isEmpty()) arr[stack.pop()] = -1;
		for(int i = 0; i < N; i++) sb.append(arr[i]).append(' ');
		System.out.print(sb);
	}
}