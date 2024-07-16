import java.util.LinkedList;
import java.util.Queue;

class 단어변환_태연 {
    
    static class Node{
        int index;
        int dist;
        
        Node(int index, int dist){
            this.index = index;
            this.dist = dist;
        }
    }
    
    static boolean isWordAdj(String w1, String w2){
        if(w1.length() != w2.length()){
            return false;
        } else {
            
            int diff = 0;
            
            for(int i=0; i<w1.length(); i++){
                if(w1.charAt(i) != w2.charAt(i)){
                    diff++;
                }
            }
            
            return (diff==1) ? true : false;
        }
    }
    
    public int solution(String begin, String target, String[] words) {
        
        int nWords = words.length;
        int targetIndex = -1;
        
        boolean[][] adjMatrix = new boolean[nWords+1][nWords];
        
        for(int i=0; i<nWords; i++){
            if(words[i].equals(target)){
                targetIndex = i;
            }
            
            for(int j=i+1; j<nWords; j++){
                if(isWordAdj(words[i], words[j])){
                    adjMatrix[i][j] = true;
                    adjMatrix[j][i] = true;
                }
            }
            
            if(isWordAdj(begin, words[i])){
                adjMatrix[nWords][i] = true; 
            }
        }
                
        if(targetIndex == -1){
            return 0;
        }
        
        Queue<Node> queue = new LinkedList<>();
        boolean[] v = new boolean[nWords+1];
        v[nWords] = true;
        queue.add(new Node(nWords, 0));
        
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            
            for(int i=0; i<nWords; i++){
                if(!v[i] && adjMatrix[cur.index][i]) {
                    if(i==targetIndex){
                        return cur.dist+1;
                    } else {
                        v[i] = true;
                        queue.add(new Node(i, cur.dist + 1));
                    }
                }
            }
        }
        
        return 0;
    }
}