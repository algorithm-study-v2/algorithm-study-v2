import java.io.*;
import java.util.*;

public class 선물교환_규영 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] indegree = new int[N];
		ArrayList<Integer[]>[] arr = new ArrayList[N];
		for (int i = 0; i < N; i++) arr[i] = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int stud1 = Integer.parseInt(st.nextToken())-1, stud2 = Integer.parseInt(st.nextToken())-1;
			indegree[stud1]++;
			indegree[stud2]++;
			arr[i].add(new Integer[]{stud1, stud2});
		}
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (indegree[i] < 2) q.offer(i);
		}
		while (!q.isEmpty()) {
			int now = q.poll();
			if (visited[now]) continue;
			visited[now] = true;
			for (Integer[] next : arr[now]) {
				int next1 = next[0], next2 = next[1];
				if (--indegree[next1] < 2) q.offer(next1);
				if (--indegree[next2] < 2) q.offer(next2);
			}
		}
		ArrayList<Integer> result = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if (indegree[i] == 2) result.add(i);
		}
		sb.append(result.size()).append('\n');
		for (Integer i : result) {
			sb.append(i+1).append(' ');
		}
		System.out.print(sb);
	}
}