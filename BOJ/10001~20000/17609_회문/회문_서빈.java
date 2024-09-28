import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 회문_서빈 {
	static char[] charArr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			charArr = br.readLine().toCharArray();
			sb.append(palindrome(0, charArr.length - 1, 0)).append("\n");
		}
		System.out.println(sb.toString());
	}

	// left: 왼쪽 인덱스, right: 오른쪽 인덱스, check: 회문 횟수
	public static int palindrome(int left, int right, int check) {
		// check가 2 이상이면 회문이 아님을 의미하므로 2
		if (check >= 2) {
			return 2;
		}
		// 왼쪽 인덱스가 오른쪽 인덱스보다 작을 때까지 반복
		while (left < right) {
			// 두 문자가 같으면 포인터를 이동하여 계속 확인
			if (charArr[left] == charArr[right]) {
				right--;
				left++;
			} else {
				// 문자가 다를 경우, 한 문자를 건너뛰는 두 가지 경우
				return Math.min(palindrome(left + 1, right, check + 1), palindrome(left, right - 1, check + 1));
			}
		}
		return check;
	}
}
