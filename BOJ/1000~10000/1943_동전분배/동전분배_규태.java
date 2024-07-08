import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 동전분배_규태 {
	/*
	 * 904ms
	 * 1차원 dp[i]를 동전 i원을 지불 가능한지 여부를 확인하도록 정의하여 채우고 전체 금액의 절반을 지불 가능한지 확인
	 */
	static int n,total;
	static boolean dp[];
	static ArrayList<int[]> list;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line = null;
        while((line = br.readLine())!= null) {
        	total=0;
        	n = Integer.parseInt(line);
        	list = new ArrayList<>();
        	for(int i=0; i<n; i++) {
        		st = new StringTokenizer(br.readLine());
        		int coin = Integer.parseInt(st.nextToken());
        		int count = Integer.parseInt(st.nextToken());
        		list.add(new int[] {coin, count});
        		total+=coin*count;
        	}
        	
        	dp = new boolean[total+1];
        	dp[0]=true;
        	
        	
        	if(total%2==0) {
        		int cur=0;
        		
        		for(int[] info:list) {
        			for(int i=cur; i>=0; i--) {
            			for(int j=0; j<=info[1]; j++) {
            				if(dp[i]) dp[i+info[0]*j]=true;
            			}
            		}
        			cur = Math.min(cur+info[0]*info[1], total);
//        			System.out.println(Arrays.toString(dp));
        		}
        		
        		
        		if(dp[total/2])
        			sb.append(1).append('\n');
        		else
        			sb.append(0).append('\n');
        	}
        	else sb.append(0).append('\n');
        	line = null;
        }
        
        System.out.println(sb);
	}
}