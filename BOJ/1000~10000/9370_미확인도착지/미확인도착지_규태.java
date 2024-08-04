import java.util.*;
import java.io.*;

public class 미확인도착지_규태 {
	/*
	 * 612ms
	 * 다익스트라를 출발지, 중간경유지 두곳을 기준으로 세번 진행하여 
	 * 도착지 후보가 출발지에서 출발하여 경유하는 길을 반드시 거쳐가는 경로가 최단경로인지 확인하는 방식으로 구현
	 */
	static int n,m,t,s,g,h,endpoint[], dist[][];
	static ArrayList<Integer> list;
	static Queue<Integer> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<test; i++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			
			dist = new int[n+1][n+1]; endpoint = new int[t];
			list = new ArrayList<>();
			
			st = new StringTokenizer(br.readLine());
			s = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			for(int j=0; j<m; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				dist[a][b]=d; dist[b][a]=d;
			}
			
			for(int j=0; j<t; j++) {
				endpoint[j] = Integer.parseInt(br.readLine());
			}
			int[] min = dijsktra(s);
			int[] mid1 = dijsktra(g);
			int[] mid2 = dijsktra(h);
			
//			System.out.println(Arrays.toString(min));
//			System.out.println(Arrays.toString(mid1));
//			System.out.println(Arrays.toString(mid2));
			
			for(int j=0; j<t; j++) {
				boolean isMin = false;
				if(min[endpoint[j]]==min[g]+dist[g][h]+mid2[endpoint[j]]) isMin=true;
				if(min[endpoint[j]]==min[h]+dist[g][h]+mid1[endpoint[j]]) isMin=true;
				if(isMin) list.add(endpoint[j]);
			}
			Collections.sort(list);
			for(int ans : list)
				sb.append(ans).append(' ');
			sb.append('\n');
		}
		System.out.println(sb);
	}
	static int[] dijsktra(int start) {
		int[] min = new int[n+1];
		Arrays.fill(min, 987654321);
		min[start]=0; 
		q.offer(start);
		while(!q.isEmpty()) {
			int cur = q.poll();
			for(int next=1; next<=n; next++) {
				if(dist[cur][next]==0) continue;
				if(min[next]>min[cur]+dist[cur][next]) {
					min[next]=min[cur]+dist[cur][next];
					q.offer(next);
				}
			}
		}
		return min;
	}
}
