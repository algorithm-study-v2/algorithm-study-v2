import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 오큰수_태연 {

    static class Info{
        int val;
        int index;

        Info(int val, int index){
            this.val=val;
            this.index=index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Info[] stack = new Info[n+1];
        int[] nge = new int[n+1];
        int ptr = 0;
        stack[0] = new Info(Integer.MAX_VALUE, -1);

        for(int i=0; i<n; i++){
            Info cur = new Info(Integer.parseInt(st.nextToken()), i);
            while(stack[ptr].val < cur.val){
                nge[stack[ptr].index] = cur.val;
                ptr--;
            }

            ptr++;
            stack[ptr] = cur;
        }

        while(ptr>0){
            nge[stack[ptr].index] = -1;
            ptr--;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            sb.append(nge[i]).append(' ');
        }

        System.out.print(sb);
    }
}
