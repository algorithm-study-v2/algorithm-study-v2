import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class 보석도둑_지환 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[] bag = new int[k];
		int[][] obj = new int[n][2];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			obj[i][0] = m; obj[i][1] = v;
		}

		for(int i=0; i<k; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(bag);
		Arrays.sort(obj, (o1, o2) -> o1[0] - o2[0]);

		Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

		long sum = 0;
		for(int i=0, j=0; i<k; i++) {
			while(j<n && obj[j][0] <= bag[i]) {
				pq.add(new int[] {obj[j][0], obj[j][1]});
				j++;
			}
			if(!pq.isEmpty()) {
				int[] cur = pq.poll();
				sum += cur[1];
			}
		}
		System.out.println(sum);
	}
}
