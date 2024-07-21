import java.util.*;

class 단어변환_지민 {
    public Set<String> set;
    public int N;
    
    public static class Word {
        public String word;
        public int cost;
        public Word(String word, int cost) {
            this.word = word;
            this.cost = cost;
        }
    }
    
    public int bfs(String begin, String target) {
        Queue<Word> queue = new LinkedList<>();
        Set<String> check = new HashSet<>();
        
        queue.add(new Word(begin, 0));
        check.add(begin);
        
        while(!queue.isEmpty()) {
            String here = queue.peek().word;
            int cost = queue.peek().cost;
            queue.poll();
            
            if(cost > N) continue;
            if(here.equals(target)) return cost;
            
            System.out.print("\n" + here + " : ");
            char[] word = here.toCharArray();
            for(int i = 0; i < word.length; i++) {
                char orign = word[i];
                for(int j = 0; j < 26; j++) {
                    word[i] = (char)('a' + j);
                    String next = String.valueOf(word);
                    if(set.contains(next) && !check.contains(next)) {
                        System.out.print(next + " ");
                        check.add(next);
                        queue.add(new Word(next, cost + 1));
                    }
                }
                word[i] = orign;
            }
        }
        
        return 0;
    }
    
    public int solution(String begin, String target, String[] words) {
        N = words.length;
        
        set = new HashSet<>();
        for(String word : words) set.add(word);
        
        if(!set.contains(target)) return 0;
        
        return bfs(begin, target);
    }
}