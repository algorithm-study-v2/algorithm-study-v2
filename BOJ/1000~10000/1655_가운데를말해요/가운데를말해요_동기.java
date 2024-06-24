import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 408ms
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int x = Integer.parseInt(br.readLine());
        maxQueue.add(x);
        sb.append(x).append("\n");
        for (int i = 1; i < n; i++) {
            x = Integer.parseInt(br.readLine());
            if (maxQueue.size() > minQueue.size())
                minQueue.add(x);
            else
                maxQueue.add(x);

            swap(maxQueue, minQueue);
            sb.append(maxQueue.peek()).append("\n");
        }
        System.out.println(sb);
    }

    static void swap(PriorityQueue<Integer> maxQueue, PriorityQueue<Integer> minQueue) {
        if (maxQueue.peek() > minQueue.peek()) {
            int maxValue = maxQueue.poll();
            int minValue = minQueue.poll();
            maxQueue.add(minValue);
            minQueue.add(maxValue);
        }
    }

}