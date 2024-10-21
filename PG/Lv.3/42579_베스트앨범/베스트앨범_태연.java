import java.util.*;

class Solution {
    
    static class Songs implements Comparable<Songs>{
        TreeSet<Song> list;
        int plays;
        
        Songs(){
            this.list = new TreeSet<>();
            this.plays = 0;
        }
        
        void add(Song s){
            this.list.add(s);
            this.plays+=s.plays;
        }
        
        public int compareTo(Songs s){
            return s.plays-this.plays;
        }
    }
    
    static class Song implements Comparable<Song>{
        int id;
        int plays;
        
        Song(int id, int plays){
            this.id=id;
            this.plays=plays;
        }
        
        public int compareTo(Song s){
            if(this.plays==s.plays){
                return this.id-s.id;
            } else {
                return s.plays-this.plays;
            }
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        
        HashMap<String,Integer> genreToIndex = new HashMap<>();
        List<Songs> songList = new ArrayList<>();
        int curIdx = 0;
        
        for(int i=0; i<genres.length; i++){
            String genre = genres[i];
            int play = plays[i];
            
            if(!genreToIndex.containsKey(genre)){
                genreToIndex.put(genre, curIdx++);
                songList.add(new Songs());
            }
            
            songList.get(genreToIndex.get(genre)).add(new Song(i, play));
        }
        
        Collections.sort(songList);
        
        Queue<Integer> q = new LinkedList<>();
        
        for(Songs songs : songList){
            q.add(songs.list.pollFirst().id);
            if(!songs.list.isEmpty()){
                q.add(songs.list.pollFirst().id);
            }
        }
        
        int[] ret = new int[q.size()];
        
        int idx=0;
        while(!q.isEmpty()){
            ret[idx++] = q.poll();
        }
        
        return ret;
    }
    
}