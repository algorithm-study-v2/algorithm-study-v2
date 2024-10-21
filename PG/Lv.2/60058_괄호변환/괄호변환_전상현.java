import java.util.*;
class Solution {
    public String solution(String p) {
        if(p.equals("")) return "";

        String[] div = divide(p);

        if(isCollect(div[0])) {
            return div[0] + solution(div[1]);
        } else {
            p = div[0].substring(1, div[0].length() -1);
            p = convert(p);
            return "(" + solution(div[1]) + ")" + p;
        }

    }

    private static boolean isCollect(String s) {
        if(s.equals("")) return true;
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push('(');
            } else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }

        if(!stack.isEmpty()) return false;
        return true;
    }

    private static String[] divide(String s) {
        int openCnt = 0;
        int closeCnt = 0;
        String[] result = new String[2];
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') openCnt++;
            else closeCnt++;

            if(openCnt == closeCnt) {
                result[0] = s.substring(0, i + 1);
                result[1] = s.substring(i + 1);
                break;
            }
        }
        return result;
    }

    private static String convert(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i) == '(' ? ')' : '(');
        }
        return sb.toString();
    }
}
