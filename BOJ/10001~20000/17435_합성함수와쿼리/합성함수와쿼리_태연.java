import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합성함수와쿼리_태연 {

    /*
     * - 736ms
     *
     * - 비트마스킹
     */

    static int[][] f;
    static int iter;
    static int num;
    static int x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        f = new int[n+1][20];

        StringTokenizer st = new StringTokenizer(br.readLine());


        for(int i=1; i<=n; i++){
            f[i][0] = Integer.parseInt(st.nextToken());
        }

        for(int depth = 1; depth<20; depth++){
            for(int i=1; i<=n; i++){
                f[i][depth] = f[f[i][depth-1]][depth-1];
            }
        }

        int nQuery = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int q=0; q<nQuery; q++){
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            iter = 0;

            while(iter<20){
                if((num & (1<<iter))> 0){
                    x = f[x][iter];
                }
                iter++;
            }

            sb.append(x).append('\n');
        }
        System.out.print(sb);
    }
}
