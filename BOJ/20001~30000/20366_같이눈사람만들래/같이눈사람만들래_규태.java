import java.io.*;
import java.util.*;

public class 같이눈사람만들래_규태 {
	/*
	 * 148ms
	 * 눈덩이를 크기순으로 정렬하고 엘사가 눈사람을 먼저 만든 뒤,
	 * 안나의 눈사람 재료를 엘사가 사용한 눈덩이 두개의 사이의 재료를 사용하여 
	 * 투포인터로 조정하며 최솟값을 업데이트하는 방식으로 구현
	 */
	static int n, h[], min=Integer.MAX_VALUE;
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		h = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(h);
		for(int i=0; i<n; i++) {
			for(int j=n-1; j>i; j--) {
				int start = i+1;
				int end = j-1;
				int elsa = h[i]+h[j];
				
				while(start<end) {
					int anna = h[start]+h[end];
					if(elsa==anna) {
						System.out.println(0);
						return;
					}
					min = Math.min(min, Math.abs(elsa-anna));
					
					if(elsa>anna) start++;
					else end--;
				}
			}
		}
		System.out.println(min);
	}
}