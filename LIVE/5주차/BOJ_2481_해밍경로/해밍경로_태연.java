import java.io.*;
import java.util.*;

public class 해밍경로_태연 {
	
	static long toLong(String str, int k) {
		long coef = (long) Math.pow(2, k-1);
		long ret = 0;
		int curBit = 0;
		
		while(coef>0) {
			if(str.charAt(curBit++)=='1') {
				ret += coef;
			}
			coef/=2;
		}
		
		return ret;
	}
	
	static Deque<Integer> stack = new ArrayDeque<>();
	static StringBuffer sb;
		
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		Map<Long, Integer> map = new HashMap<>();
		Long[] nodes = new Long[n+1];
		
		int[] p = new int[n+1];
		Arrays.fill(p, -1);
		int[] dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[1] = 0;
		
		for(int i=1; i<=n ;i++) {
			String str = br.readLine();
			nodes[i] = toLong(str,k);
			map.put(nodes[i], i);
		}
		
		Queue<Integer> q = new LinkedList<>();
		q.add(1);
		
		int temp;
		
		while(!q.isEmpty()) {
			int id = q.poll();
			
			for(int i=0; i<k; i++) {
				
				long next = nodes[id] ^ (1 << i);
				if(map.containsKey(next) 
						&& dist[(temp=map.get(next))]==Integer.MAX_VALUE) {
					p[temp] = id;
					dist[temp] = dist[id] + 1;
					q.add(temp);
				}
			}
		}
		
		int nQuery = Integer.parseInt(br.readLine());
		
		for(int i=0; i<nQuery; i++) {
			int target = Integer.parseInt(br.readLine());
			
			sb = new StringBuffer();
			
			if(dist[target] == Integer.MAX_VALUE) {
				sb.append(-1);
			} else {
				
				int cur = target;
				stack.add(target);
				while(cur != 1) {
					stack.addLast(p[cur]);
					cur = p[cur];
				}
				while(!stack.isEmpty()) {
					sb.append(stack.pollLast()).append(' ');
				}
			}
			
			System.out.println(sb);
		}
		
	}
}
