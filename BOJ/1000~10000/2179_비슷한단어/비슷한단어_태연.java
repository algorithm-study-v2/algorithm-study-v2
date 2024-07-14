import java.io.*;
import java.util.*;

public class 비슷한단어_태연 {
	
	/*
	 * - 140ms
	 * 
	 * - 정렬 후 앞뒤 prefix 비교
	 */

    static int calculateDepth(String s1, String s2){
        int depth = 0;
        int length = Math.min(s1.length(), s2.length());

        while(depth < length && s1.charAt(depth) == s2.charAt(depth)){
            depth++;
        }

        return depth;
    }

    static class Word implements Comparable<Word>{
        String value;
        int id;

        Word(String value, int id){
            this.value = value;
            this.id=id;
        }

        @Override
        public int compareTo(Word w){
            return this.value.compareTo(w.value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Word[] words = new Word[n];
        String[] origin = new String[n];

        for(int i=0; i<n; i++){
            String word = br.readLine().trim();
            words[i] = new Word(word, i);
            origin[i] = word;
        }

        Arrays.sort(words);

        int depth = -1;
        int maxDepth = -1;

        int left = 0;
        int right = 1;
        
        TreeSet<Integer> answers = new TreeSet<>();

        while(right < n){
            depth = calculateDepth(words[left].value, words[right].value);

            if(depth > maxDepth){
            	answers.clear();
            	answers.add(words[left].id);
            	answers.add(words[right].id);
            	maxDepth = depth;
            } else if (depth == maxDepth) {
            	answers.add(words[left].id);
                answers.add(words[right].id);
            }

            right++;
            left++;
        }
        
        String first = origin[answers.pollFirst()];
        String second = "";
        
        while(!first.substring(0,maxDepth)
        		.equals((second = origin[answers.pollFirst()]).substring(0,maxDepth))){
        }

        System.out.println(first);
        System.out.println(second);
    }
}
