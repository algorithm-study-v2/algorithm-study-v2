import java.util.*;

public class 단어변환_승현 {

    static class Info {
        public int idx;
        public int step;

        Info(int idx, int step) {
            this.idx = idx;
            this.step = step;
        }
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;

        int[][] diff = new int[words.length][words.length];
        boolean[] visited = new boolean[words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                int d = 0;
                for (int k = 0; k < begin.length(); k++) {
                    if (words[i].charAt(k) != words[j].charAt(k)) {
                        d++;
                    }
                }
                diff[i][j] = d;
                diff[j][i] = d;
            }
        }

        Queue<Info> q = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            int d = 0;
            for (int k = 0; k < begin.length(); k++) {
                if (words[i].charAt(k) != begin.charAt(k)) {
                    d++;
                }
            }
            if (d == 1) {
                q.offer(new Info(i, 1));
                visited[i] = true;
            }
        }

        while (!q.isEmpty()) {
            Info now = q.poll();
            if (words[now.idx].equals(target)) {
                return now.step;
            }
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && diff[now.idx][i] == 1) {
                    visited[i] = true;
                    q.offer(new Info(i, now.step+1));
                }
            }
        }

        return answer;
    }
}