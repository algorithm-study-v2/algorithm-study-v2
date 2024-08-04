import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 회문_승현 { // 292ms

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            int left = 0;
            int right = s.length() - 1;
            int skip = 0;

            while (left < right) {
                if (skip > 1) {
                    break;
                }
                if (s.charAt(left) == s.charAt(right)) {
                    left++;
                    right--;
                }
                else {
                    if (right - left > 2) {
                        int lcnt = 0;
                        int rcnt = 0;
                        for (int k = 0; k < (right - left) / 2; k++) {
                            if (s.charAt(left+1+k) == s.charAt(right-k)) {
                                lcnt++;
                            }
                            if (s.charAt(left+k) == s.charAt(right-1-k)) {
                                rcnt++;
                            }
                        }
                        if (lcnt > rcnt) {
                            left++;
                        }
                        else {
                            right--;
                        }
                    }
                    else {
                        if (s.charAt(left+1) == s.charAt(right)) {
                            left++;
                        }
                        else if (s.charAt(left) == s.charAt(right-1)) {
                            right--;
                        }
                        else {
                            skip = 2;
                            break;
                        }
                    }
                    skip++;
                }
            }
            sb.append(skip).append('\n');
        }
        System.out.println(sb);
    }
}
