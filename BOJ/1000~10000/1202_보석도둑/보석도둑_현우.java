import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 정현우 : BOJ 1202 보석 도둑
 * - 820 ms
 * - 세그먼트 트리
 * - 보석 가격 높은 순으로 처리
 * - 용량이 보석 무게 이상이면서 최소인 가방 선택
 * - 가방 용량 오름차순으로 정렬 후
 * - 최대값 세그먼트 트리 구성
 * - 루트 노드 값(전체 구간 최대값)이
 * -   보석 무게보다 작으면 가방에 넣지 않음
 * - 왼쪽 자식 노드 값(왼쪽 구간 최대 값)이 보석 무게보다
 * -   크거나 같으면 왼쪽 노드, 작으면 오른쪽 노드로 이동
 * - 소모한 가방은 음수 용량으로 업데이트
 * */
public class 보석도둑_현우 {
    private static final int FULL = -1;

    private static final class Jewel implements Comparable<Jewel> {
        int weight; // 무게
        int val; // 가격

        Jewel(int weight, int val) {
            this.weight = weight;
            this.val = val;
        }

        @Override
        public int compareTo(Jewel o) {
            return Integer.compare(o.val, this.val); // 가격이 높은 순으로 정렬
        }
    }

    private static int[] caps;
    private static int[] tree;

    private static int initTree(int node, int start, int end) {
        int mid;

        if (start == end) { // 리프 노드
            return tree[node] = caps[start];
        }
        mid = start + end >> 1;
        return tree[node] = Math.max( // 현재 구간에서 가장 큰 가방의 용량 저장
                initTree(node << 1, start, mid), // 왼쪽 구간 최대 용량
                initTree(node << 1 | 1, mid + 1, end)); // 오른쪽 구간 최대 용량
    }

    private static void update(int node, int start, int end, int weight) {
        int mid;

        if (start == end) { // 리프 노드
            tree[node] = FULL; // 가방 소모 (음수 용량 처리)
            return;
        }
        mid = start + end >> 1;
        if (weight <= tree[node << 1]) { // 왼쪽 노드 값이 보석 무게보다 크거나 같은 경우
            update(node << 1, start, mid, weight); // 왼쪽 노드로 이동
        } else { // 왼쪽 노드 값이 보석 무게보다 작은 경우
            update(node << 1 | 1, mid + 1, end, weight); // 오른쪽 노드로 이동
        }
        tree[node] = Math.max(tree[node << 1], tree[node << 1 | 1]); // 구간의 최대 가방 업데이트
    }

    public static void main(String[] args) throws IOException {
        int n;
        int k;
        int i;
        long ans;
        Jewel jewel;
        BufferedReader br;
        StringTokenizer st;
        PriorityQueue<Jewel> pq;

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();
        while (n-- > 0) { // 보석 가격 높은 순으로 정렬
            st = new StringTokenizer(br.readLine());
            pq.offer(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        caps = new int[k];
        for (i = 0; i < k; i++) {
            caps[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(caps); // 가방 용량 작은 순으로 정렬
        tree = new int[k-- << 2];
        initTree(1, 0, k); // 최대값 세그먼트 트리 초기화
        ans = 0L;
        while (!pq.isEmpty()) {
            jewel = pq.poll();
            if (jewel.weight > tree[1]) { // 남은 가방 최대 용량보다 보석 무게가 클 경우
                continue;
            }
            update(1, 0, k, jewel.weight); // 용량이 보석 무게 이상이면서 최소인 가방
            ans += jewel.val; // 보석 가격 합
        }
        System.out.print(ans);
    }
}
