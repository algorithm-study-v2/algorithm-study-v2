import java.io.*;
import java.util.*;

/*
	47416KB 708ms
 */

public class 돌굴러가유_규영_2 {
	static class Info {
		int sum, index;

		Info(int sum, int index) {
			this.sum = sum;
			this.index = index;
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
		destroyed.sort((o1, o2) -> {
			if (o1.sum == o2.sum) return Integer.compare(o1.index, o2.index);
			return Integer.compare(o2.sum, o1.sum);
		});
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < Math.min(M, destroyed.size()); i++) {
			result.add(destroyed.get(i).index);
		}
		Collections.sort(result);
		for (int i : result) sb.append(i).append('\n');
		System.out.print(sb);
	}
}