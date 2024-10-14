import java.io.*;
import java.util.*;

public class 개구리점프_규태 {
	/*
	 * 604ms
	 * 입력 받은 내용을 시작점을 기준으로 정렬하고 union-find를 사용하여 이동할 수 있는 통나무 셋을 생성하고
	 * 쿼리문에 따른 결과를 출력으로 보냄
	 */
	static int n, q;
	static int[] parent;
	static class Log {
		int idx,x1,x2,y;
		public Log(int idx, int x1, int x2, int y) {
			this.idx=idx;
			this.x1=x1;
			this.x2=x2;
			this.y=y;
		}
	}
	static ArrayList<Log> loglist = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			loglist.add(new Log(i,x1,x2,y));
		}
		
		Collections.sort(loglist, new Comparator<Log>() {
			@Override
			public int compare(Log o1, Log o2) {
				return o1.x1-o2.x1;
			}			
		});
		
		parent = new int[n+1];
		for(int i=1; i<=n; i++) {
			parent[i]=i;
		}
		
		int x = loglist.get(0).x2;
		int tmp = loglist.get(0).idx;
		
		for(int i=1; i<n; i++) {
			if(x>=loglist.get(i).x1) {
				union(tmp, loglist.get(i).idx);
				x=Math.max(x, loglist.get(i).x2);
			}
			else {
				x=loglist.get(i).x2;
				tmp=loglist.get(i).idx;
			}
		}
		
		
		for(int i=1; i<=q; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(find(a)==find(b))
				sb.append(1).append('\n');
			else
				sb.append(0).append('\n');
		}
		System.out.println(sb);
	}
	public static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a]=find(parent[a]);
	}
	public static void union(int a, int b) {
		int aroot = find(a);
		int broot = find(b);
		if(aroot==broot) return;
		parent[broot]=aroot;
	}
}