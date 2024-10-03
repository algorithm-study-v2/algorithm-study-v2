import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 1240ms
 */
public class Main {
	static int n, m, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int[] sandArr = new int[n + 1];
		for (int i = 1; i <= n; i++)
			sandArr[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] rockArr = new int[k];
		for (int i = 0; i < k; i++)
			rockArr[i] = Integer.parseInt(st.nextToken());

		PriorityQueue<Info> pq = new PriorityQueue<Info>();
		for (int i = 0; i < k; i++) {
			int s = rockArr[i];
			int e = i == k-1 ? n+1 : rockArr[i + 1];
			int sum =0;
			for (int j=s; j<e; j++) {
				sum+= sandArr[j];
			}
			pq.add(new Info(s,sum));
		}
		ArrayList<Integer> answer = new ArrayList<>();
		for (int i=0; i<m; i++) {
			answer.add(pq.poll().index);
		}
		Collections.sort(answer);
		for(int a : answer) {
			System.out.println(a);
		}

	}

	static class Info implements Comparable<Info> {
		int index;
		int sum;

		Info(int index, int sum) {
			this.index = index;
			this.sum = sum;
		}

		@Override
		public int compareTo(Info o) {
			if (o.sum == this.sum) return this.index - o.index;
			return o.sum - this.sum;

		}
	}

}

