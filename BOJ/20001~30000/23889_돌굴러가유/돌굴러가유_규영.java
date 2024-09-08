import java.io.*;
import java.util.*;

/*
	44464KB 564ms
 */

public class 돌굴러가유_규영 {
	static class Info implements Comparable<Info> {
		int sum, idx;
		Info(int sum, int idx) {
			this.sum = sum;
			this.idx = idx;
		}
		@Override
		public int compareTo(Info o) {
			if (this.sum == o.sum) return Integer.compare(this.idx, o.idx);
			return Integer.compare(o.sum, this.sum);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] castle = new int[N], rock = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) castle[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) rock[i] = Integer.parseInt(st.nextToken())-1;
		List<Info> destroyed = new ArrayList<>();
		for (int i = 0; i < K-1; i++) {
			int sum = 0;
			for (int j = rock[i]; j < rock[i+1]; j++) sum += castle[j];
			destroyed.add(new Info(sum, rock[i]+1));
		}
		int lastSum = 0;
		for (int i = rock[K-1]; i < N; i++) lastSum += castle[i];
		destroyed.add(new Info(lastSum, rock[K-1]+1));
		Collections.sort(destroyed);
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < Math.min(M, destroyed.size()); i++) {
			result.add(destroyed.get(i).idx);
		}
		Collections.sort(result);
		for (int i : result) sb.append(i).append('\n');
		System.out.print(sb);
	}
}