import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 정현우 : BOJ 1655 가운데를 말해요
 * 236 ms
 * 펜윅트리
 * -10,000 ~ 10,000을 1 ~ 20,001로 변환
 * 펜윅트리 생성
 * 숫자 삽입 시 해당 숫자를 포함하는 노드들 1씩 증가
 * 누적합이 외친 수의 절반 이상이 되는 최소값 출력
 */
public class 가운데를말해요_현우 {
	private static final int DIFF = 10_001; // -10,000을 1로 설정할 차이 값
	private static final int RANGE = 20_001; // -10,000 ~ 10,000 범위
	private static final char LINE_BREAK = '\n';
	
	private static int root; // 트리의 루트
	private static int[] ft; // 펜윅트리
	
	private static final void insert(int num) { // 숫자 삽입
		int i;
		
		for (i = num; i <= root; i += i & -i) { // 해당 숫자를 포함하는 노드들
			ft[i]++; // 모두 1씩 증가
		}
	}
	
	private static final int query(int mid) { // 중간값(누적합이 mid 이상이 되는 최소값) 찾기
		int i;
		int idx;
		int prefix;
		
		idx = 0;
		prefix = 0;
		for (i = root; i > 0; i >>= 1) { // 루트에서 리프로 한 층씩 이동
			if (prefix + ft[idx + i] < mid) { // 누적합에 인덱스의 오른쪽 노드를 더했을 때 mid 미만
				prefix += ft[idx += i]; // 오른쪽 노드 누적합에 포함, 인덱스 이동
			}
		}
		return idx + 1; // 현재 인덱스는 누적합이 mid 미만인 최대값이므로 +1
	}
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		StringBuilder sb;
		BufferedReader br;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (root = 1; root < RANGE; root <<= 1); // 숫자 범위를 커버하는 트리 크기 설정
		ft = new int[root + 1]; // 펜윅트리
		sb = new StringBuilder();
		for (i = 1; i <= n; i++) {
			insert(Integer.parseInt(br.readLine()) + DIFF); // 숫자에 DIFF 값을 더해서 삽입
			sb.append(query((i + 1) >> 1) - DIFF).append(LINE_BREAK); // 출력할 때는 원상복구
		}
		System.out.print(sb);
	}
}
