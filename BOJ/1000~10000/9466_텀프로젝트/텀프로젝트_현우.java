import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 9466 텀 프로젝트
 * - 864 ms
 * - DFS
 * - 사이클이 발생하면 팀 구성
 * - 자식 노드로 이동하면서 방문 표시
 * - 방문된 노드일 경우 해당 노드 반환
 * - 부모로 되돌아가면서 cnt++
 * - 돌아가다가 반환된 노드를 다시 만날 경우
 * - cnt 크기의 사이클(팀)
 * */
public class 텀프로젝트_현우 {
    private static final int MAX_N = 100_001;
    private static final char LINE_BREAK = '\n';

    private static int cnt;
    private static int ans;
    private static int[] selection;
    private static boolean[] visited;
    private static BufferedReader br;

    private static int dfs(int node) {
        int val;

        if (visited[node]) { // 방문된 노드일 경우
            return node; // 노드 반환
        }
        visited[node] = true; // 방문 표시
        val = dfs(selection[node]); // 자식 노드로 이동
        cnt++;
        if (val == node) { // 반환된 노드를 다시 만날 경우
            ans -= cnt; // (아직 팀에 속하지 못한 학생 수) -= (사이클(팀) 크기)
        }
        return val;
    }

    private static int solution() throws IOException {
        int n;
        int i;
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (i = 1; i <= n; i++) { // 선택한 학생 입력
            selection[i] = Integer.parseInt(st.nextToken());
        }
        visited = new boolean[n + 1]; // 방문 표시
        ans = n; // 아직 팀에 속하지 못한 학생 수
        for (i = 1; i <= n; i++) { // 각각의 학생에서 출발
            cnt = 0; // 사이클 크기
            dfs(i); // DFS
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        int t;
        StringBuilder sb;

        br = new BufferedReader(new InputStreamReader(System.in));
        selection = new int[MAX_N];
        sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            sb.append(solution()).append(LINE_BREAK);
        }
        System.out.print(sb);
    }
}
