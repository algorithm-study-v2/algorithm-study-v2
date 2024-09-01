import java.io.*;
import java.util.*;

/*
	24560KB 376ms
 */

public class 같이눈사람만들래_규영 {
	static class Snowman implements Comparable<Snowman> {
		int head, body, height;
		public Snowman(int head, int body, int height) {
			this.head = head;
			this.body = body;
			this.height = height;
		}
		@Override
		public int compareTo(Snowman o) {
			return Integer.compare(this.height, o.height);
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
		ArrayList<Snowman> al = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				al.add(new Snowman(i, j, arr[i]+arr[j]));
			}
		}
		Collections.sort(al);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < al.size()-1; i++) {
			Snowman now = al.get(i), next = al.get(i+1);
			if ((now.body != next.body && now.body != next.head) && (now.head != next.body && now.head != next.head)) {
				min = Math.min(min, next.height-now.height);
			}
		}
		System.out.print(min);
	}
}