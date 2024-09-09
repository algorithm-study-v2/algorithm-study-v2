import java.io.*;
import java.util.*;

public class 보석도둑_규태 {
	/*
	 * 1324ms
	 * 보석과 가방을 입력받고 정렬한 후 각 가방 당 넣을 수 있는 최대 무게를 확인하며 index 이동
	 */
	static int n,k;
	static long ans=0;
	static Comparator<int[]> c = new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			return o1[0]-o2[0];
		}
	};
	static ArrayList<int[]> list = new ArrayList<>();
	static ArrayList<Integer> bag = new ArrayList<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			list.add(new int[] {m,v});
		}
		
		for(int i=0; i<k; i++) {
			int b = Integer.parseInt(br.readLine());
			bag.add(b);
		}

		Collections.sort(list,c);
		Collections.sort(bag);
		
		int idx=0;
		for(int i=0; i<k; i++) {
			for(; idx<n; idx++) {
				if(list.get(idx)[0]<=bag.get(i))
					pq.add(list.get(idx)[1]);
				else break;
			}
			if(!pq.isEmpty()) {
				ans+=pq.poll();
			}
		}
		
		System.out.println(ans);
	}

}
