import java.io.*;

public class K번째수_규태 {
	/*
	 * 92ms
	 * 이분 탐색으로 시작, 끝 점을 지정한 후 반복하여 체크
	 * 끝점은 k를 넘지 않으므로 end = k+1로 지정할 수 있다.
	 */
	static int n,k;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		
		int start = 1;
		int end = k+1;
		while(start+1<end) {
			int mid = (start+end)/2;
			int tmp = check(mid);
			
			if(tmp<=k) start=mid;
			else end=mid;
		}
		System.out.println(start);
	}
	static int check(int x) {
		int result=0;
		for(int i=1; i<=n; i++) {
			if(1L*n*i<1L*x) {
				result+=n;
			}
			else {
				if(x%i==0) result+=x/i-1;
				else result+=x/i;
			}
		}
		return result+1;
	}
}