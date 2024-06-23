import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 가운데를말해요_승현 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pqLeft = new PriorityQueue<>((o1, o2) -> o2 - o1);
        PriorityQueue<Integer> pqRight = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (pqLeft.size() == pqRight.size()) {
                if (!pqRight.isEmpty() && num > pqRight.peek()) {
                    pqLeft.offer(pqRight.poll());
                    pqRight.offer(num);
                }
                else {
                    pqLeft.offer(num);
                }
            }
            else {
                if (!pqLeft.isEmpty() && num < pqLeft.peek()) {
                    pqRight.offer(pqLeft.poll());
                    pqLeft.offer(num);
                }
                else {
                    pqRight.offer(num);
                }
            }

            sb.append(pqLeft.peek()).append('\n');
        }

        System.out.println(sb);
    }
}
