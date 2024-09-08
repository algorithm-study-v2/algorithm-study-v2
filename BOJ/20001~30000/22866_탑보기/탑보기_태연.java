import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 탑보기_태연 {

    /*
     *  - 300ms
     * 
     *  - 배열로 스택구현
     */
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] stack = new int[n+1];
        int[] height = new int[n+1];
        int[][] closest = new int[n+1][2];
        int[] nStack = new int[n+1];

        height[0] = Integer.MAX_VALUE;
        stack[0] = 0;

        int size=0;

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=1; i<n+1; i++) {
            height[i] = Integer.parseInt(st.nextToken());
            if (size == 0) {
                stack[++size] = i;
                continue;
            }

            while(height[i] >= height[stack[size]]){
                size--;
            }
            closest[i][0] = stack[size];
            nStack[i] += size;
            stack[++size] = i;
        }

        size=0;

        for(int i=n; i>0; i--) {
            if (size == 0) {
                stack[++size] = i;
                continue;
            }

            while(height[i] >= height[stack[size]]){
                size--;
            }
            closest[i][1] = stack[size];
            nStack[i] += size;
            stack[++size] = i;
        }

        StringBuilder sb = new StringBuilder();

        for(int i=1; i<=n; i++){

            sb.append(nStack[i]);
            if (nStack[i]>0) {
                sb.append(' ');
                if(closest[i][0] == 0){
                    sb.append(closest[i][1]);
                } else if(closest[i][1] == 0){
                    sb.append(closest[i][0]);
                } else {
                    sb.append((i - closest[i][0] <= closest[i][1] - i) ? closest[i][0] : closest[i][1]);
                }
            }
            sb.append('\n');
        }

        System.out.print(sb);

    }

}
