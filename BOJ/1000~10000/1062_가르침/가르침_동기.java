import com.sun.org.apache.xml.internal.utils.StringToIntTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n,k,max;
    static String[] words;
    static int[] staticArr = {0,2,8,13,19};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(k<5) {
            System.out.println(0);
            return;
        }
        max =0;
        words = new String[n];
        for (int i=0;  i<n; i++){
            words[i] = br.readLine();
        }


        boolean[] arr = new boolean[26];
        for (int i=0; i<5; i++) {
            arr[staticArr[i]] = true;
        }
        combination(arr, 0,5);
        System.out.println(max);
    }

    static void combination(boolean[] arr, int depth, int trueSize) {
        if(k == trueSize){
            findMax(arr);
            return;
        }
        if(depth>=26) return;

        if(depth == 0 || depth == 2 || depth == 8 || depth ==13 || depth ==19){
            combination(arr,depth+1, trueSize);
            return;
        }

        arr[depth] = true;
        combination(arr, depth+1, trueSize+1);
        arr[depth] = false;
        combination(arr, depth+1, trueSize);
    }

    static void findMax(boolean[] arr) {
        int cnt =0;
        for (String word : words){
            if(isAble(arr, word)) cnt++;
        }
        max = Math.max(max,cnt);
    }

    static boolean isAble(boolean[] arr, String word) {
        for(char c : word.toCharArray()){
            if(!arr[c-97]) return false;
        }
        return true;
    }
}
