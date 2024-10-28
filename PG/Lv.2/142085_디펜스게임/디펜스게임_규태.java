import java.util.*;
class 디펜스게임_규태 {
    public int solution(int n, int k, int[] enemy) {
        int answer=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0; i<enemy.length; i++) {
            pq.offer(enemy[i]);
            if(pq.size()<=k) {
                answer++;
                continue;
            }
            n-=pq.poll();
            if(n<0) 
                break;
            answer++;
        }
            
        return answer;
    }
}