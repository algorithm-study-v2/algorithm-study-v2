import java.io.*;
import java.util.*;

public class 가장큰정사각형_태연 {
	
	/*
	 *  - 220ms
	 * 
	 *  - 가로로한번 세로로한번
	 */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int col = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        char[][] map = new char[col][row];

        for(int c=0; c<col; c++){
            map[c] = br.readLine().toCharArray();
        }

        int max = 0;

        int[][] dpT = new int[col][row];

        for(int r=0; r<row; r++){
            dpT[0][r] = (map[0][r]=='1') ? 1:0;

            if(dpT[0][r] > max) {
                max = dpT[0][r];
            }
        }

        for(int c=1; c<col; c++){
            dpT[c][0] = (map[c][0]=='1') ? 1:0;
            if(dpT[c][0] > max) {
                max = dpT[c][0];
            }
            for(int r=1; r<row; r++){
                if(map[c][r] == '0') continue;

                dpT[c][r] = Math.min(dpT[c-1][r-1], Math.min(dpT[c-1][r], dpT[c][r-1]))+1;

                if(dpT[c][r] > max) {
                    max = dpT[c][r];
                }
            }
        }

        System.out.print(max*max);

    }
}