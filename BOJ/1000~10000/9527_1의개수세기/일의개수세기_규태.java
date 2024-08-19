import java.util.*;
import java.io.*;

public class 일의개수세기_규태 {
	/*
	 * 68ms
	 * 이진수로 표현되었을때 전부 1로 표현되는 수 (2^n-1)를 기준으로 
	 * 각 지점에서의 1의 개수 누적합을 1차원 배열 digit[n]에 저장하고
	 * 시작과 끝 수를 비트마스킹과 digit 배열을 통해 해당 수까지의 1의 개수 누적합을 계산한 후 답 도출  
	 */
	static long[] digit = new long[55];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Long a = Long.parseLong(st.nextToken())-1;
		Long b = Long.parseLong(st.nextToken());
		
		digit[1]=1;
		for(int i=2; i<55; i++) {
			digit[i]=digit[i-1]*2+pow(i-1);
		}
		
		long acnt=0,bcnt=0;
		for(int i=55; i>=0; i--) {
			long tmp = pow(i);
			if((a|1L<<i)==a) {
				acnt+=(digit[i]+(a-tmp+1));
				a-=tmp;
			}
			if((b|1L<<i)==b) {
				bcnt+=(digit[i]+(b-tmp+1));
				b-=tmp;
			}
//			System.out.println(a+" "+acnt+"\t"+b+" "+bcnt);
		}
		
		
		System.out.println(bcnt-acnt);
	}
	static long pow(int x) {
		if(x==0) return 1;
		if(x==1) return 2;
		long half = pow(x/2);
		if(x%2==0) return half*half*1L;
		else return half*half*2L;
	}
}
//
//1    - 1
//10
//11   - 4 = 2 + 1*2
//100
//101
//110
//111  - 12 = 4 + 4*2
//1000
//1001
//1010
//1011
//1100  --> 12 + 5 + 4 + 1
//1101  --> 12 + 6 + 4 + 2 + 1
//1110
//1111 - 32 = 8 + 12*2