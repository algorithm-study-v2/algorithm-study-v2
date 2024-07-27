import java.io.*;
import java.util.*;

public class 문자열생성_태연 {
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * - 80ms
		 * 
		 * - 투포인터
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		char[] word = new char[n];
		for(int i=0; i<n; i++){
			word[i] = br.readLine().charAt(0);
		}
		
		int left = 0;
		int right = n-1;
		
		StringBuilder sb = new StringBuilder();
		
		int cnt = 0;
		
		while(left < right) {
			if(word[left] < word[right]) {
				sb.append(word[left++]);
			} else if(word[left] > word[right]) {
				sb.append(word[right--]);
			} else {
				int tempL = left + 1;
				int tempR = right -1;
				boolean popLeft = true;
				
				while(tempL < tempR) {
					if(word[tempL] < word[tempR]) {
						popLeft = true;
						break;
					} else if (word[tempL] > word[tempR]){
						popLeft = false;
						break;
					} else {
						tempL++;
						tempR--;
					}
				}
				
				if(popLeft) {
					sb.append(word[left++]);
				} else {
					sb.append(word[right--]);
				}
			}
			if((++cnt)%80==0) {
				sb.append('\n');
			}
		}
		sb.append(word[left]);
		System.out.print(sb);

	}
}
