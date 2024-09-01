import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int[] function;
    static int[][] sparse;

    static int[] binaryArray;
    static int m;
    static final int log = (int) (Math.log(500000) / Math.log(2));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        m = Integer.parseInt(br.readLine());
        function = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) function[i] = Integer.parseInt(st.nextToken());
        setSparse();
        setBinaryArray();

        int q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            while (n > 0) {
                int binary = (int) (Math.log(n) / Math.log(2));
                n -= binaryArray[binary];
                x = sparse[binary][x];
            }
            sb.append(x).append("\n");

        }
        System.out.println(sb);
    }

    static void setSparse() {
        sparse = new int[log + 1][m + 1];
        for (int i = 1; i <= m; i++)
            sparse[0][i] = function[i];

        for (int i = 1; i < sparse.length; i++) {
            for (int j = 1; j <= m; j++) {
                sparse[i][j] = sparse[i - 1][sparse[i - 1][j]];
            }
        }
    }

    static void setBinaryArray() {
        binaryArray = new int[log + 1];
        binaryArray[0] = 1;
        for (int i = 1; i < binaryArray.length; i++) {
            binaryArray[i] = binaryArray[i - 1] * 2;
        }
    }
}
