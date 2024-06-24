import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 가운데를말해요_규태 {
	/*
	 * 340ms
	 * 최대힙, 최소힙을 구성하여 구하고자 하는 가운뎃값을 항상 최대힙의 루트에 저장하는 방식으로 구성
	 * 가웃뎃값과 다음수를 비교하여 최대힙/최소힙에 다음수 삽입
	 * 이후 최대힙 최소힙의 size를 비교하여 가운뎃값을 재조정 
	 */
	static int n;
	static PriorityQueue<Integer> bigger = new PriorityQueue<>();
	static PriorityQueue<Integer> smaller = new PriorityQueue<>(new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2-o1;
		}
	});
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for(int i=0; i<n; i++) {
			int num = Integer.parseInt(br.readLine());
			if(i==0) smaller.offer(num);
			else {
				if(smaller.peek()>num) smaller.offer(num);
				else bigger.offer(num);
				checkHeap();
			}
			sb.append(smaller.peek()).append('\n');
		}
		System.out.println(sb);
		
	}
	static void checkHeap() {
		if(smaller.size()+1==bigger.size()) {
			int num = bigger.poll();
			smaller.offer(num);
		}
		else if(smaller.size()==bigger.size()+2) {
			int num = smaller.poll();
			bigger.offer(num);
		}
	}
}
