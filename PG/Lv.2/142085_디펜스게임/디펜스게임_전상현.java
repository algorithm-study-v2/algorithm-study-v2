import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int max = enemy.length;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        if(k >= max) return max;
        
        int sum = 0;
        
        for(int i = 0; i < k; i++) {
            pq.add(enemy[i]);
            sum += enemy[i];
        }
        int round = k;
        while(n >= 0 && round < max) {
            int tmp = sum - pq.peek();
            
            if(tmp + enemy[round] > sum) {
                int first = pq.poll();
                pq.add(enemy[round]);
                n -= first;
            } else {
                n -= enemy[round];
            }
            
            if(n < 0) break;
            round++;
        }
        
        return round;
    }
}