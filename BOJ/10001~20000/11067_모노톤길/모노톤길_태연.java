import java.io.*;
import java.util.*;

public class 모노톤길_태연 {
	
	/*
	 * - 4664ms
	 * 
	 * - 자료구조대잔치
	 */
	
	static class Cafe implements Comparable<Cafe>{
		int x;
		int y;
		
		Cafe(int x, int y){
			this.x=x;
			this.y=y;
		}
		
		public String toString() {
			return this.x + " " + this.y;
		}
		
		@Override
		public int compareTo(Cafe c){
			if(this.x==c.x) {
				return this.y-c.y;
			} else {
				return this.x-c.x;
			}
		}
	}
	
	static HashMap<Integer, Cafe> cafeMap;
	
	static PriorityQueue<Cafe> cafeQueue;
	static TreeSet<Cafe> temp;
	
	static void init() {
		cafeMap = new HashMap<>();
		cafeQueue = new PriorityQueue<>();
		temp = new TreeSet<>();
	}
		
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t=0; t<testCase; t++) {
			init();
			
			int nCafe = Integer.parseInt(br.readLine());
			
			for(int i=0; i<nCafe; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				Cafe cafe = new Cafe(
						Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken())
						);
				cafeQueue.add(cafe);
			}
			cafeQueue.add(new Cafe(Integer.MAX_VALUE, Integer.MAX_VALUE));
			
			int curX = 0;
			int curY = 0;
			int index = 1;
			Cafe cur;
			
			while(index <= nCafe) {
				cur = cafeQueue.poll();
				temp.add(cur);
				curX = cur.x;
				
				while(cafeQueue.peek().x == curX) {
					temp.add(cafeQueue.poll());
				}
				
				if(temp.first().y == curY) {
					while(temp.size()!=1) {
						cafeMap.put(index++, temp.pollFirst());
					}
				} else {
					while(temp.size()!=1) {
						cafeMap.put(index++, temp.pollLast());
					}
				}
				cur = temp.pollFirst();
				curY = cur.y;
				cafeMap.put(index++, cur);
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int nQuery = Integer.parseInt(st.nextToken());
			for(int i=0; i<nQuery; i++) {
				sb.append(cafeMap.get(Integer.parseInt(st.nextToken())).toString()).append('\n');
			}
		}
		
		System.out.print(sb);
	}
}
