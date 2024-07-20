import java.io.*;
import java.util.*;

public class 개구리점프_태연 {
	
	/*
	 * - 788ms
	 * 
	 * - 정렬 후 그룹화
	 */
	
	static class Log implements Comparable<Log>{
		int id;
		int start;
		int end;
		
		Log(int id, int start, int end){
			this.id=id;
			this.start=start;
			this.end=end;
		}
		
		@Override
		public int compareTo(Log l) {
			if(this.start==l.start) {
				return this.end = l.end;
			} else {
				return this.start - l.start;
			}
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nLog = Integer.parseInt(st.nextToken());
		int nQuery = Integer.parseInt(st.nextToken());
		
		Log[] logs = new Log[nLog];
		
		for(int i=0; i<nLog; i++) {
			st = new StringTokenizer(br.readLine());
			logs[i] = new Log(i
					,Integer.parseInt(st.nextToken())
					,Integer.parseInt(st.nextToken())
					);
		}
		
		Arrays.sort(logs);
		
		int curGroup = 0;
		int curEnd = -1;
		int[] group = new int[nLog];
		
		for(int i=0; i<nLog; i++) {
			Log cur = logs[i];
			
			if(cur.start > curEnd) {
				curGroup++;
			}
			
			group[cur.id] = curGroup;
			if(curEnd < cur.end) {
				curEnd = cur.end;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int q=0; q<nQuery; q++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			
			if(group[n1-1]==group[n2-1]) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			
			sb.append('\n');
		}
		
		System.out.print(sb);
	}
}
