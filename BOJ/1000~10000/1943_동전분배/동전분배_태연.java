import java.io.*;
import java.util.*;

public class 동전분배_태연 {

    /*
     * - 384ms
     *
     * - 냅색
     */

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String input;

        w : while((input = br.readLine()) != null) {
            int n = Integer.parseInt(input);
            int[] coin = new int[n];
            int[] nCoin = new int[n];
            int sum=0;

            for(int i=0; i<n; i++) {
                st = new StringTokenizer(br.readLine());

                coin[i] = Integer.parseInt(st.nextToken());
                nCoin[i] = Integer.parseInt(st.nextToken());
                sum += coin[i]*nCoin[i];
            }

            if((sum%2)==1) {
                sb.append(0).append('\n');
                continue w;
            } else {
                sum /= 2;
            }

            boolean[] able = new boolean[sum];

            int curSum=0;
            able[0]=true;

            for(int i=0; i<n; i++) {

                for(int num=curSum; num>=0; num--) {

                    if (able[num]) {
                        for(int j=1; j<=nCoin[i]; j++) {
                            int next = num+coin[i]*j;

                            if(next>sum) {
                                break;
                            } else if(next==sum) {
                                sb.append(1).append("\n");
                                continue w;
                            } else {
                                able[next] = true;
                            }
                        }
                    }
                }

                curSum = Math.min(curSum+nCoin[i]*coin[i], sum-1);
            }
            sb.append(0).append("\n");
        }

        System.out.print(sb);

    }

}