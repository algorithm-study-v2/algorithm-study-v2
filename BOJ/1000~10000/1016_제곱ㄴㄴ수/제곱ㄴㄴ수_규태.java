import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 제곱ㄴㄴ수_규태 {
	/*
	 * 124ms
	 * (max의 제곱근)번째까지 제곱수의 배수를 확인해가며 제곱수를 소거하고 최종적으로 나눠떨어지지 않는 제곱ㄴㄴ수를 판별
	 */
	static int ans;
	static long min,max;
	static boolean[] sqnum;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		min = Long.parseLong(st.nextToken());
		max = Long.parseLong(st.nextToken());
		
		int length = (int) (max-min);
		sqnum = new boolean[length+1];
		
		for(long i=2; i*i<=max; i++) {
			long sq=i*i;
			for(long j=min/sq; j<=max/sq; j++) {
				if(sq*j>=min) sqnum[(int)(sq*j-min)]=true;
			}
		}
		
		for(int i=0; i<=length; i++) {
			if(!sqnum[i]) ans++;
		}
		System.out.println(ans);
	}
}
