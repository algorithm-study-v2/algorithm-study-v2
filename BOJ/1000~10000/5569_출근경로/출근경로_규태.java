import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 출근경로_규태 {
	/*
	 * 88ms
	 * 지난 위치와 이동한 방향을 저장하여 다음에 방향전환을 할 수 있는지 여부를 저장하는 4차원 dp (w*h*2*2 크기)로 해결
	 */
	static final int DIV=100000;
	static int w,h,way[][][][];
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        way = new int[101][101][2][2];
        
        for(int i=1; i<=100; i++) 
        	way[i][1][1][0] = way[1][i][1][1] = 1;
        
        for(int i=2; i<=w; i++) {
        	for(int j=2; j<=h; j++) {
        		way[i][j][0][0]=way[i-1][j][1][1];
        		way[i][j][0][1]=way[i][j-1][1][0];
        		way[i][j][1][0]=(way[i-1][j][0][0]+way[i-1][j][1][0])%DIV;
        		way[i][j][1][1]=(way[i][j-1][0][1]+way[i][j-1][1][1])%DIV;
        	}
        }
        
        System.out.println((way[w][h][0][0]+way[w][h][0][1]+way[w][h][1][0]+way[w][h][1][1])%DIV);
	}
}
