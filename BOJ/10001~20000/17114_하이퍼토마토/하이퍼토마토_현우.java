import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 17114 하이퍼 토마토
 * - 664 ms
 * - BFS
 * - 몇 차원 문제인지 (BOJ7576, BOJ7569, BOJ17114)
 * - 계산 후 1 차원으로 변환
 * - BFS 2N방 탐색
 * */
public class 하이퍼토마토_현우 {
    private static final int FAIL = -1;
    private static final int RIPE = 1;
    private static final int UNRIPE = 0;

    private static int cnt; // 안익은 토마토 개수
    private static int dimension; // 차원
    private static int[] arr; // 각 차원 규모
    private static int[] delta; // 특정 차원에서 한 칸 이동 시 1 차원에서의 좌표 변화량
    private static int[] map; // 1차원으로 변환된 창고
    private static ArrayDeque<Integer> q;
    private static BufferedReader br;

    private static void input(int pos, int depth) throws IOException { // 재귀 입력
        int i;
        StringTokenizer st;

        if (depth == dimension - 1) { // 마지막 차원
            st = new StringTokenizer(br.readLine());
            for (i = 0; i < arr[depth]; i++) { // 한 줄 입력
                map[pos] = Integer.parseInt(st.nextToken());
                if (map[pos] == RIPE) { // 익은 토마토
                    q.addLast(pos); // Queue에 삽입
                } else if (map[pos] == UNRIPE) { // 안익은 토마토
                    cnt++; // 안익은 토마토 개수
                }
                pos++; // 다음 칸
            }
            return;
        }
        for (i = 0; i < arr[depth]; i++) {
            input(pos, depth + 1); // 다음 차원으로 재귀
            pos += delta[depth]; // 시작 좌표 해당 차원에서 한 칸 이동
        }
    }

    private static int bfs() {
        int i;
        int d;
        int pos;
        int size;
        int next;
        int time;

        for (time = 0; cnt > 0 && !q.isEmpty(); time++) { // 시간 계산
            size = q.size();
            for (i = 0; i < size; i++) { // 현재 시간에서의 Queue만 탐색
                pos = q.pollFirst();
                for (d = 0; d < dimension; d++) { // 2N방 탐색
                    if ((d == 0 ? pos : pos % delta[d - 1]) / delta[d] > 0) { // 한 칸 앞 범위 체크
                        next = pos - delta[d]; // 한 칸 앞 좌표
                        if (map[next] == UNRIPE) { // 안익은 토마토면
                            q.addLast(next); // Queue에 삽입
                            map[next]++; // 익음
                            cnt--; // 안익은 토마토 1 개 감소
                        }
                    }
                    if ((d == 0 ? pos : pos % delta[d - 1]) / delta[d] < arr[d] - 1) { // 한 칸 뒤 범위 체크
                        next = pos + delta[d]; // 한 칸 뒤 좌표
                        if (map[next] == UNRIPE) { // 안익은 토마토면
                            q.addLast(next); // Queue에 삽입
                            map[next]++; // 익음
                            cnt--; // 안익은 토마토 1 개 감소
                        }
                    }
                }
            }
        }
        return cnt == 0 ? time : FAIL; // 모두 익었으면 시간 반환
    }

    public static void main(String[] args) throws IOException {
        int i;
        int size;
        StringTokenizer st;
        ArrayDeque<Integer> stack;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        stack = new ArrayDeque<>();
        while (st.hasMoreTokens()) { // 첫 줄 입력
            stack.addFirst(Integer.parseInt(st.nextToken()));
        }
        dimension = stack.size(); // 몇 차원 문제인지 계산 (BOJ7576, BOJ7569, BOJ17114)
        arr = new int[dimension]; // 각 차원 규모
        size = 1;
        for (i = 0; i < dimension; i++) {
            arr[i] = stack.pollFirst(); // 첫 줄 거꾸로 입력
            size *= arr[i]; // 전체 창고 사이즈
        }
        delta = new int[dimension]; // 특정 차원에서 한 칸 이동 시 1 차원에서의 좌표 변화량
        delta[dimension - 1] = 1; // 가장 안쪽 차원에서 1 칸 이동시 좌표 변화 1
        for (i = dimension - 1; i > 0; i--) {
            delta[i - 1] = delta[i] * arr[i]; // 1 칸 이동 시 하위 차원들의 규모 곱만큼 좌표 변화
        }
        map = new int[size]; // 1차원으로 변환된 창고
        cnt = 0; // 안익은 토마토 개수
        q = new ArrayDeque<>(); // BFS에서 사용할 Queue
        input(0, 0); // 토마토 입력
        System.out.print(bfs()); // BFS
    }
}
