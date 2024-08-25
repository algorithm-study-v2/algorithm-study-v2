import java.io.*;
import java.util.*;

public class 철로_규태 {
	/*
	 * 560ms
	 * 우선순위 큐 두 개를 사용하여 첫번째에 입력을 모두 넣어 각 선분을 끝을 기준으로 정렬하고
	 * 두번째에 시작점을 기준으로 체크하면서 삽입하고, 삽입 삭제가 진행될 때마다 최대 size를 업데이트
	 */
	static int n,d, max=0;
	static Comparator<int[]> c = new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			if(o1[1]==o2[1]) return o1[0]-o2[0];
			return o1[1]-o2[1];
		}
	};
	static PriorityQueue<int[]> pq = new PriorityQueue<>(c); 
	static PriorityQueue<Integer> check = new PriorityQueue<>(); 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a<=b) pq.offer(new int[] {a,b});
			else pq.offer(new int[] {b,a});
		}
		d = Integer.parseInt(br.readLine());
		
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			check.offer(cur[0]);
			
			while(!check.isEmpty() && check.peek()<cur[1]-d) {
				check.poll();
			}
			max = Math.max(max, check.size());
		}
		System.out.println(max);
	}
}