import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 최소비용구하기2_규태 {
	/*
	 * 468ms
	 * 다익스트라를 활용한 풀이
	 * visit 배열을 놓치지 않도록 주의 필요 
	 */
	static final int INF = 100000000;
	static int n,m,start,end;
	static int[] min,before;
	static boolean[] visit; //없으면 시간초과
	static ArrayList<int[]>[] list;
	static PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[1]-o2[1];
		}
	});
	static Stack<Integer> s = new Stack<>(); 
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		min = new int[n+1]; before = new int[n+1];
		Arrays.fill(min, INF); Arrays.fill(before, -1);
		
		visit = new boolean[n+1];
		list = new ArrayList[n+1];
		for(int i=1; i<=n; i++)
			list[i] = new ArrayList<>();
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[a].add(new int[] {b,cost});
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		dijsktra();
		getAnswer();
	}
	static void dijsktra() {
		min[start] = 0;
		pq.offer(new int[] {start,0});
		while(!pq.isEmpty()) {
			int[] cur = pq.poll();
			int vertex = cur[0]; int mindist = cur[1];
			if(visit[vertex]) continue;
			visit[vertex] = true;
			for(int[] next : list[vertex]) {
				if(min[next[0]] > mindist+next[1]) {
					min[next[0]]=mindist+next[1];
					before[next[0]]=vertex;
					pq.offer(new int[] {next[0],min[next[0]]});
				}
			}
		}
	}
	static void getAnswer() {
		sb.append(min[end]).append('\n');
		
		int cnt=0;
		s.push(end);
		while(before[end] != -1) {
			cnt++;
			s.push(before[end]);
			end = before[end];
		}
		sb.append(cnt+1).append('\n');
		while(!s.isEmpty()) {
			int cur = s.pop();
			sb.append(cur).append(" ");
		}
		System.out.println(sb);
	}
}