import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * 68ms
 */
public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[] arr = new char[n];
        for (int i = 0; i < n; i++)
            arr[i] = br.readLine().charAt(0);

        int cnt = 0;
        int p1 = 0;
        int p2 = n - 1;

        while (p1 < p2) {
            if (cnt % 80 == 0 && cnt != 0) sb.append("\n");
            char front = arr[p1];
            char back = arr[p2];
            int result = Character.compare(front, back);

            if (result < 0) {
                sb.append(front);
                p1++;
            } else if (result > 0) {
                sb.append(back);
                p2--;
            } else {
                int p3 = p1 + 1;
                int p4 = p2 - 1;
                while (p3 < p4) {
                    result = Character.compare(arr[p3], arr[p4]);
                    if (result < 0) {
                        sb.append(front);
                        p1++;
                        break;
                    } else if (result > 0) {
                        sb.append(back);
                        p2--;
                        break;
                    } else {
                        p3++;
                        p4--;
                    }
                }
                if (p3 >= p4) {
                    sb.append(front);
                    p1++;
                }
            }
            cnt++;
        }
        sb.append(arr[p1]);

        System.out.println(sb);
    }
}