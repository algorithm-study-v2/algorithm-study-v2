import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 224ms
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            sb.append(isPalindrome(br.readLine())).append("\n");
        }
        System.out.println(sb);
    }

    static int isPalindrome(String word) {
        int p1 = 0;
        int p2 = word.length() - 1;
        int fixCnt1 = 0;
        int fixCnt2=0;

        while (p1 < p2) {
            if (word.charAt(p1) != word.charAt(p2)) {
                if (word.charAt(p1 + 1) == word.charAt(p2)) {
                    p1++;
                } else if (word.charAt(p1) == word.charAt(p2 - 1)) {
                    p2--;
                } else {
                    fixCnt1 =2;
                    break;
                }
                fixCnt1++;
                if (fixCnt1 > 1) break;
            }
            p1++;
            p2--;
        }

        p1 = 0;
        p2 = word.length() - 1;
        while (p1 < p2) {
            if (word.charAt(p1) != word.charAt(p2)) {
                if (word.charAt(p1) == word.charAt(p2 - 1)) {
                    p2--;
                }
                else if (word.charAt(p1 + 1) == word.charAt(p2)) {
                    p1++;
                } else {
                    fixCnt2 =2;
                    break;
                }
                fixCnt2++;
                if (fixCnt2 > 1) break;
            }
            p1++;
            p2--;
        }
        return Math.min(fixCnt2,fixCnt1);
    }
}

