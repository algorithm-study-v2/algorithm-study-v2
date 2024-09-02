import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * java
 * 236 ms
 * 투 포인터
 */

public class 용액_서빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        int min = Integer.MAX_VALUE;
        int left = 0, right = N - 1, ml = 0, mr = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        while (left < right) {
            int sum = nums[left] + nums[right]; // 두 용액의 특성값을 더함
            if (min >= Math.abs(sum)) {
                min = Math.abs(sum);
                ml = left;  // 최솟값을 만드는 인덱스 저장
                mr = right;
            }

            if (sum >= 0)   // 특성값의 합이 0 이상이면, 오른쪽 포인터를 왼쪽으로 이동
                right--;
            else    // 특성값의 합이 0 미만이면, 왼쪽 포인터를 오른쪽으로 이동
                left++;
        }
        System.out.println(nums[ml] + " " + nums[mr]);
    }
}