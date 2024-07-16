import java.util.*;

class 카페확장_지민 {
    public int solution(int[] menu, int[] order, int k) {
		Queue<int[]> queue = new LinkedList<>();
		int time = 0;
		int idx = 0;
		int res = 0;

		while (idx < order.length || !queue.isEmpty()) {
			if(!queue.isEmpty() && queue.peek()[1] <= time) {
				// System.out.println(queue.peek()[0] + ", " + queue.peek()[1] + " : " + res);
				queue.poll();

				if(!queue.isEmpty()) {
					if(time >= queue.peek()[0]) queue.peek()[0] = time;
					queue.peek()[1] += queue.peek()[0];
				}
			}

			if(time % k == 0 && idx < order.length) {
				if(queue.isEmpty()) queue.add(new int[]{k * idx, k * idx + menu[order[time/k]]});
				else queue.add(new int[]{k * idx, menu[order[time/k]]});
				idx++;
			}
			res = Math.max(res, queue.size());
			time++;

		}

		return res;
    }
}