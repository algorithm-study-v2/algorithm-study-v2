import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 2467 용액
 * 260 ms
 * 투 포인터
 * 양 끝에서 0에 다가가면서
 * 합이 0에 가까운 두 용액 갱신
 * 포인터가 0에 도달하거나 넘어가면
 * 0 근처의 합과 비교해서
 * 최소값 갱신
 * */
public class 용액_현우 {
	private static final int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		int n;
		int i;
		int sum;
		int min;
		int left;
		int right;
		int ansLeft;
		int ansRight;
		int[] arr;
		BufferedReader br;
		StringTokenizer st;
		
		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (i = 0; i < n; i++) { // 배열 입력
			arr[i] = Integer.parseInt(st.nextToken());
		}
		left = 0;
		right = n - 1;
		ansLeft = left;
		ansRight = right;
		min = INF;
		while (arr[left] < 0 && arr[right] > 0) { // 0에 다가가면서 탐색
			sum = arr[left] + arr[right];
			if (sum > 0) { // 합이 0보다 클 경우
				if (sum < min) { // 최소값 갱신
					min = sum;
					ansLeft = left;
					ansRight = right;
				}
				right--; // 오른쪽 포인터 이동
			} else { // 합이 0 이하인 경우
				if (-sum < min) { // 최소값 갱신
					min = -sum;
					ansLeft = left;
					ansRight = right;
				}
				left++; // 왼쪽 포인터 이동
			}
		}
		if (arr[left] >= 0) { // 왼쪽 포인터가 0에 도달
			if (arr[left] + arr[left + 1] < min) { // 0 근처와 비교
				ansLeft = left;
				ansRight = left + 1;
			}
		} else if (arr[right] <= 0) { // 오른쪽 포인터가 0에 도달
			if (-arr[right - 1] - arr[right] < min) { // 0 근처와 비교
				ansLeft = right - 1;
				ansRight = right;
			}
		}
		System.out.print(new StringBuilder().append(arr[ansLeft]).append(' ').append(arr[ansRight]));
	}
}
