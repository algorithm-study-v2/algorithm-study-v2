import java.util.*;

class 카페확장_규영 {
	public int solution(int[] menu, int[] order, int k) {
		int size = order.length, last = 0, base = 0, ans = 1;
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < size; i++, base += k) {
			while (!q.isEmpty() && q.peek() <= base) q.poll();
			if (q.isEmpty()) last = base+menu[order[i]];
			else last += menu[order[i]];
			q.offer(last);
			ans = Math.max(ans, q.size());
		}
		return ans;
	}
}