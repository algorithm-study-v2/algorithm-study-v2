import java.io.*;
import java.util.*;

/*
	18844KB 216ms
 */

public class 과제_규영 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine()), sum = 0;
		ArrayList<int[]> works = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			works.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
		}
		works.sort(Comparator.comparingInt(o -> o[0]));
		Queue<Integer> q = new PriorityQueue<>();
		for(int[] work : works) {
			q.add(work[1]);
			if(q.size() > work[0]) q.poll();
		}
		while (!q.isEmpty()) sum += q.poll();
		System.out.print(sum);
	}
}
