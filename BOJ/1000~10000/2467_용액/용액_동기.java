import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/*
 * 392ms
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.asList(br.readLine().split(" ")).stream().mapToInt(Integer::parseInt).toArray();
        int p1 = 0;
        int p2 = N - 1;
        int answer1 = 0;
        int answer2 = 0;
        int min = 2000000000;

        while (p1 < p2) {
            int check = arr[p1] + arr[p2];
            if (Math.abs(check) < min) {
                min = Math.abs(check);
                answer1 = arr[p1];
                answer2 = arr[p2];
            }
            if (check < 0) p1++;
            else p2--;
        }

        System.out.println(answer1 + " " + answer2);
    }
}
