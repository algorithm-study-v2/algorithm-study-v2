import java.io.*;
import java.util.*;

public class K진트리_규태 {
	/*
	 * 428ms
	 * 1진트리의 경우 단순 거리차로 계산 - 보통 1진트리는 고려하지 않으므로 실수 주의
	 * K진트리의 경우 부모노드와 특정 자식노드 간의 관계성을 파악하여 식을 일반화하는 방식으로 구현
	 * 번호가 같아질때 까지 두 노드의 부모를 찾아가며 거리를 계산
	 */
	static long n;
	static int k,q;
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Long.parseLong(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<q; i++) {
			st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());
			if(k==1) sb.append(Math.abs(x-y)).append('\n');
			else sb.append(dist(x,y,0)).append('\n');
		}
		System.out.println(sb);
	}
	static int dist(long x, long y, int cur) {
		if(x==y) return cur;
		if(x<y) return dist(x,parent(y),cur+1);
		else return dist(parent(x),y,cur+1);
	}
	static long parent(long a) {
		if(a%k>1) return a/k+1;
		else return a/k;
	}
}
