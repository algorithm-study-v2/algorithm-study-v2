import java.io.*;

public class 정상회담2_규태 {
	/*
	 * 164ms
	 * 1차원 dp를 생성하여 고정된 하나의 선을 만들었을때 기준으로 좌우로 만들 수 있는 경우의 수를 곱해 더해줌
	 */
	static long[] ppl = new long[10001];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		ppl[0]=1; ppl[2]=1;
		for(int i=4; i<=n; i++) {
			for(int j=2; j<=i; j+=2) {
				ppl[i]+=ppl[j-2]*ppl[i-j];
				ppl[i]%=987654321;
			}
		}
		System.out.println(ppl[n]);
	}
}