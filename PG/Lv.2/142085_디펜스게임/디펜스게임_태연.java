import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int inTree = 0;
        int sum = 0;
        
        int round = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(); 
        
        while(round < k && round < enemy.length){
            pq.add(enemy[round]);
            inTree+=enemy[round];
            sum+=enemy[round];
            round++;
        }
        
        while(round < enemy.length){
            if(pq.peek() <= enemy[round]){
                inTree -= pq.poll();
                pq.add(enemy[round]);
                inTree += enemy[round];
            } 
            sum += enemy[round];
            
            if(n >= sum-inTree){
                round++;
            } else {
                break;
            }
        }
        
        return round;
    }
}