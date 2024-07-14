import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 교환_규태{
	/*
	 * 176ms
	 * 가능한 최대 크기의 수와 이 수가 변경될 수 있는 번째를 고려하여 visit 배열을 2차원으로 구성
	 * BFS로 방문여부 판단
	 * 맨 앞자리가 0인 경우는 고려하지 않도록 주의가 필요됨.
	 */
    static String num;
    static int k,ans;
    static boolean[][] visit; 
    static Queue<int[]> q = new ArrayDeque<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = st.nextToken();
        k = Integer.parseInt(st.nextToken());
        visit = new boolean[1000001][k+1];

        bfs();
        if(ans==0) System.out.println(-1);
        else System.out.println(ans);
    }
    static void bfs() {
        int n = Integer.parseInt(num);
        visit[n][0]=true;
        q.offer(new int[] {n,0});
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            String curnum = cur[0]+""; int curround = cur[1];
            
            if(curround==k) {
            	ans=Math.max(ans, Integer.parseInt(curnum));
            	continue;
            }
            
            for(int i=0; i<curnum.length()-1; i++){
                for(int j=i+1; j<curnum.length(); j++) {
                	String swapnum = swap(curnum,i,j);
                	if(swapnum.charAt(0)=='0') continue;
                	n = Integer.parseInt(swapnum);
                	if(!visit[n][curround+1]) {
                		visit[n][curround+1]=true;
                		q.offer(new int[] {n,curround+1});
                	}
                }
            }
        }
    }
    static String swap(String num, int i, int j) {
    	return num.substring(0, i)+num.charAt(j)+num.substring(i+1, j)+num.charAt(i)+num.substring(j+1);
    }
}