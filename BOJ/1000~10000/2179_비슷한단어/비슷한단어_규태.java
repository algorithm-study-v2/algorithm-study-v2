import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 비슷한단어_규태 {
	/*
	 * 868ms
	 * i번째 단어와 j번째 단어의 유사도를 체크하며 계속 업데이트하며 진행하는 방식으로 해결
	 */
	static int n;
	static String[] word;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		word = new String[n];
		for(int i=0; i<n; i++) 
			word[i] = br.readLine();
		
		int a=-1,b=-1;
		int max=Integer.MIN_VALUE;
		
		for(int i=0; i<n-1; i++) {
			String word1 = word[i];
			for(int j=i+1; j<n; j++) {
				String word2 = word[j];
				int cnt = check(word1,word2);
				if(cnt>max) {
					a = i; b = j;
					max = cnt;
				}
			}
		}
		
		System.out.println(word[a]);
		System.out.println(word[b]);
	}
	static int check(String s1, String s2) {
		int cnt = 0;
		int size = Math.min(s1.length(), s2.length());
		for(int i=0; i<size; i++) {
			if(s1.charAt(i)==s2.charAt(i)) cnt++;
			else return cnt;
		}
		return cnt;
	}
}
