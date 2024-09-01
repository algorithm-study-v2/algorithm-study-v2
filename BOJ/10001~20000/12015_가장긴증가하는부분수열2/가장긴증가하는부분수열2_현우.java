import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 12015 가장 긴 증가하는 부분 수열 2
 * - 392 ms
 * - LIS
 * - 지금까지 나온 수 중에 현재 수보다
 * - 크거나 같은 최소의 수(lowerBound)를
 * - 현재 수로 교체
 * - 현재 수가 가장 클 경우 LIS++
 * */
public class 가장긴증가하는부분수열2_현우 {
	private static int[] arr;

	public static int binarySearch(int target, int end) {
		int left;
		int right;
		int mid;

		left = 0;
		right = end;
		while (left < right) { // lowerBound 이분탐색
			mid = left + right >> 1;
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return right;
	}

	public static void main(String[] args) throws IOException {
		int n;
		int num;
		int idx;
		int size;
		BufferedReader br;
		StringTokenizer st;

		br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		size = 0;
		st = new StringTokenizer(br.readLine());
		while (n-- > 0) {
			num = Integer.parseInt(st.nextToken()); // 현재 수
			idx = binarySearch(num, size); // lowerBound 위치 탐색
			arr[idx] = num; // 현재 수로 교체
			if (idx == size) { // 현재 수가 가장 클 경우
				size++; // LIS++
			}
		}
		System.out.print(size);
	}
}
