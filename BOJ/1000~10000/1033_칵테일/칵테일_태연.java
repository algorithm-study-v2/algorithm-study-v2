import java.io.*;
import java.util.*;

public class 칵테일_태연 {

    /*
     * - 76ms
     *
     * - 계수를 dfs로 update, 유클리드 호제법으로 최대공약수 나누기연산
     */

    static int n;
    static long[] coef;
    static boolean[][] relation;

    static void update(long value, int cur, int prev){
        coef[cur] *= value;

        for(int i=0; i<n; i++){
            if(relation[cur][i] && i != prev){
                update(value, i, cur);
            }
        }
    }

    static long euclidean(long a, long b){
        if(b==0){
            return a;
        } else {
            return euclidean(b, a%b);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        coef = new long[n];

        relation = new boolean[n][n];

        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long p = Integer.parseInt(st.nextToken());
            long q = Integer.parseInt(st.nextToken());

            if (coef[a] == 0 && coef[b] == 0) {
                coef[a] = p;
                coef[b] = q;
            } else if(coef[a] > 0 && coef[b]==0){
                coef[b] = coef[a]*q;
                update(p, a, -1);
            } else if(coef[b] > 0 && coef[a]==0){
                coef[a] = coef[b]*p;
                update(q, b, -1);
            } else {
                long coefA = coef[a];
                long coefB = coef[b];

                update(p*coefB, a, -1);
                update(q*coefA, b, -1);
            }

            relation[a][b] = true;
            relation[b][a] = true;
        }

        long div = euclidean(coef[0], coef[1]);

        for(int i=2; i<n; i++){
            div = euclidean(div, coef[i]);
        }

        for(int i=0; i<n; i++){
            System.out.print((coef[i]/div) + " " );
        }
    }
}
