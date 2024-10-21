import java.io.*;
import java.util.*;

public class 크게만들기_규태 {
	/*
	 * 300ms
	 * 숫자를 지울 수 있을 때 앞자리수보다 뒷자리수가 크면,
	 * 앞자리수를 제거하는 방식으로 스택 형태로 구현 
	 */
	static int n,k;
	static Stack<Integer> s = new Stack<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		String num = br.readLine();
		for(int i=0; i<n; i++) {
			int c = num.charAt(i)-'0';
			while(k>0 && !s.isEmpty() && c>s.peek()) {
				s.pop();
				k--;
			}
			s.push(c);
		}
		
		while(k>0) {
			s.pop();
			k--;
		}

		while(!s.isEmpty()) {
			sb.append(s.pop());
		}
		System.out.println(sb.reverse());
	}
}