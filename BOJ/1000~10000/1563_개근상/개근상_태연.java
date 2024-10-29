import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 개근상_태연 {

    static class Dp{
        int o;
        int a;
        int aa;
        int l;
        int lo;
        int la;
        int laa;
        static final int DIV = 1_000_000;

        Dp(int o, int a, int aa, int l, int lo, int la, int laa){
            this.o=o%DIV;
            this.a=a%DIV;
            this.aa=aa%DIV;
            this.l=l%DIV;
            this.lo=lo%DIV;
            this.la=la%DIV;
            this.laa=laa%DIV;
        }

        Dp getNext(){
            return new Dp(this.o + this.a + this.aa,
                this.o,
                this.a,
                this.o+this.a+this.aa,
                this.l+this.la+this.lo+this.laa,
                this.l+this.lo,
                this.la);
        }

        int get(){
            return (this.o+this.a+this.aa+this.l+this.lo+this.la+this.laa)%DIV;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Dp[] dp = new Dp[n+1];
        dp[1] = new Dp(1,1,0,1,0,0,0);
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-1].getNext();
        }

        System.out.print(dp[n].get());
    }
}
