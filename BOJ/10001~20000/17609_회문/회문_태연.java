import java.io.*;
import java.util.*;

public class 회문_태연 {
	
	/*
	 * - 212ms
	 * 
	 * - 체크 -> 안되는지점에서 하나씩 빼보고 체크
	 */
	
	static boolean checkPalindrome(String word) {
		
		int left = 0;
		int right = word.length()-1;
		
		while(left<right) {
			if(word.charAt(left) == word.charAt(right)) {
				left++;
				right--;
			} else {
				return false;
			}
		}
		return true;
	}
	
	static int checkSemiPalindrome(String word) {
		
		int left = 0;
		int right = word.length()-1;
		
		while(left < right) {
			if(word.charAt(left) == word.charAt(right)) {
				left++;
				right--;
			} else {
				if(checkPalindrome(word.substring(left, right))
				|| checkPalindrome(word.substring(left+1, right+1))){
					return 1;
				} else {
					return 2;
				}
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<n; i++) {
			sb.append(checkSemiPalindrome(br.readLine().trim())).append('\n');
		}
		
		System.out.print(sb);

	}
}
