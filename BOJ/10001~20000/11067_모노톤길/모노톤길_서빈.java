import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class 모노톤길_서빈 {
	static List<Pos> cafeList;

	public static class Pos implements Comparable<Pos> {
		int x, y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pos o) {
			if (this.x > o.x) {
				return 1;
			} else if (this.x == o.x) {
				return this.y > o.y ? 1 : -1;
			} else {
				return -1;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		// 테스트 케이스 수 입력
		int t = Integer.parseInt(br.readLine());

		// 각 테스트 케이스 처리
		for (int test_case = 0; test_case < t; test_case++) {
			// 카페의 수 입력
			int n = Integer.parseInt(br.readLine());
			cafeList = new ArrayList<>();
			cafeList.add(new Pos(-1, 0)); // 입구 (0, 0) 추가

			// 카페 좌표 입력
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				cafeList.add(new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			// 카페 좌표 정렬
			Collections.sort(cafeList);

			// 경로를 따라가면서 reverse
			int idx = 1;
			while (idx <= n) {
				if (cafeList.get(idx).x == cafeList.get(idx - 1).x) {
					// 같은 x축일 때 처리
					idx++;
				} else if (cafeList.get(idx).y == cafeList.get(idx - 1).y) {
					// 같은 y축일 때 처리
					idx++;
				} else {
					// 다른 x축으로 넘어가면 reverse
					int current = idx;
					int currentX = cafeList.get(idx).x;

					while (idx <= n && cafeList.get(idx).x == currentX) {
						idx++;
					}

					// 서브리스트 reverse
					List<Pos> subList = cafeList.subList(current, idx);
					Collections.reverse(subList);
				}
			}

			// 좌표 요청 처리
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());

			for (int i = 0; i < m; i++) {
				int cafeNumber = Integer.parseInt(st.nextToken());
				sb.append(cafeList.get(cafeNumber).x + " " + cafeList.get(cafeNumber).y).append("\n");
			}
		}
		System.out.println(sb.toString());
	}
}
