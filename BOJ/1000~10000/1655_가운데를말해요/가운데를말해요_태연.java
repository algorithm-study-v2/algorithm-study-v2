import java.io.*;
import java.util.*;

public class 가운데를말해요_태연 {
	
	/*
	 *  - 376ms
	 * 
	 *  - 2개의 heapq 사용, lower의 개수를 항상 같거나 1개더 많게 유지하면서 lower.peek()를 중앙값으로 유지
	 */
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Integer> higher = new PriorityQueue<>();
		PriorityQueue<Integer> lower = new PriorityQueue<>(Collections.reverseOrder());
		
		// 디버깅 편의를 위한 더미값
		higher.add(Integer.MAX_VALUE);
		lower.add(Integer.MIN_VALUE);
		
		StringBuilder sb= new StringBuilder();
		
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if(i%2 != 0) {
				if(num<lower.peek()) {
					higher.add(lower.poll());
					lower.add(num);
				} else {
					higher.add(num);
				}
			} else {
				if(num>higher.peek()) {
					lower.add(higher.poll());
					higher.add(num);
				} else {
					lower.add(num);
				}
			}
			
			sb.append(lower.peek()).append("\n");
			
		}
		
		System.out.println(sb);
	}

}
