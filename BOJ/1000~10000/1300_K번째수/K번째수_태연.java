import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class K번째수_태연 {

    static boolean getLessThanTarget(int n, int target, int k){
        long cnt = 0L;
        for(int i=1; i<=n; i++){
            cnt += Math.min(target/i, n);
        }
        
        return cnt >= k;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int left = 1;
        int right = k;

        while(left < right){
            int mid = (left+right)/2;

            if(getLessThanTarget(n, mid, k)){
                right = mid;
            } else {
                left = mid+1;
            }
        }

        System.out.print(left);
    }
}
