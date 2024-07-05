import java.io.*;
import java.util.*;

public class 교환_승현 {

    static int n, k, len;
    static int result;
    static boolean[] visited1;
    static boolean[] visited2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String temp = st.nextToken();
        len = temp.length();
        n = Integer.parseInt(temp);
        k = Integer.parseInt(st.nextToken());
        visited1 = new boolean[1000001];
        visited2 = new boolean[1000001];

        bfs();
        if (result == 0) {
            System.out.println(-1);
        }
        else {
            System.out.println(result);
        }
    }

    public static void bfs() {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                if (i == 0 && intAt(n, j) == 0) {
                    continue;
                }
                int next = swap(n, i, j);
                q1.offer(next);
                if (k % 2 == 1) {
                    result = Math.max(result, next);
                }
            }
        }

        for (int ki = 0; ki < k-1; ki++){
            if (ki % 2 == 0){
                while (!q1.isEmpty()) {
                    int now = q1.poll();
                    for (int i = 0; i < len; i++) {
                        for (int j = i+1; j < len; j++) {
                            if (i == 0 && intAt(now, j) == 0) {
                                continue;
                            }
                            int next = swap(now, i, j);
                            if (visited1[next]) {
                                continue;
                            }
                            q2.offer(next);
                            visited1[next] = true;
                            if (ki % 2 == k % 2) {
                                result = Math.max(result, next);
                            }
                        }
                    }
                }
            }
            else{
                while (!q2.isEmpty()) {
                    int now = q2.poll();
                    for (int i = 0; i < len; i++) {
                        for (int j = i+1; j < len; j++) {
                            if (i == 0 && intAt(now, j) == 0) {
                                continue;
                            }
                            int next = swap(now, i, j);
                            if (visited2[next]) {
                                continue;
                            }
                            q1.offer(next);
                            visited2[next] = true;
                            if (ki % 2 == k % 2) {
                                result = Math.max(result, next);
                            }
                        }
                    }
                }
            }
        }

    }

    public static int swap(int num, int i, int j) {
        String s = num + "";
        return Integer.parseInt(s.substring(0, i) + s.charAt(j)
                    + s.substring(i + 1, j) + s.charAt(i) + s.substring(j + 1));
    }

    public static int intAt(int num, int idx) {
        for (int i = 0; i < len - idx - 1; i++) {
            num /= 10;
        }
        return num % 10;
    }
}
