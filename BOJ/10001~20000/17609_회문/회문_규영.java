import java.io.*;

/*
    69376KB, 316ms
 */

public class 회문_규영 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(), sb2, sb3, sb4;
        int T = Integer.parseInt(br.readLine());
        while (T-->0) {
            String str = br.readLine();
            sb2 = new StringBuilder(str);
            if (str.equals(sb2.reverse().toString())) sb.append(0);
            else {
                int start = 0, end = str.length()-1;
                while (start < end) {
                    if (str.charAt(start) == str.charAt(end)) {
                        start++;
                        end--;
                    } else {
                        String cutLeft = str.substring(start+1, end+1), cutRight = str.substring(start, end);
                        sb3 = new StringBuilder(cutLeft);
                        sb4 = new StringBuilder(cutRight);
                        sb.append(cutLeft.equals(sb3.reverse().toString()) || cutRight.equals(sb4.reverse().toString()) ? 1 : 2);
                        break;
                    }
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}