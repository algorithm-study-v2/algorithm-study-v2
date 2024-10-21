import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 464ms
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();

        while (st.hasMoreTokens()) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);

        int min = Integer.MAX_VALUE;
        for (int i =0; i<n-3; i++) {
            for (int j = i+3; j<n; j++) {
                int s = i+1;
                int e = j-1;
                int elsa = list.get(i) + list.get(j);
                while (s<e) {
                    int result = elsa - (list.get(s) + list.get(e));
                    min = Math.min(min, Math.abs(result));
                    if (result >0){
                        s++;
                    }else if(result < 0){
                        e--;
                    }else{
                        min = 0;
                        break;
                    }
                }
            }
        }
        System.out.println(min);
    }
}

