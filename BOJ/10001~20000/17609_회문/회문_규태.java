import java.io.*;

public class 회문_규태 {
	/*
	 * 260ms
	 * 처음과 끝을 잡고 좌우대칭인 경우 회문 0으로,
	 * 한번 좌우가 맞지 않은 경우 왼쪽 한글자 제외하고 유사회문 확인, 오른쪽 한글자 제외하고 유사회문 확인 메서드로 넘어가 1,2 중 결정
	 */
	static StringBuilder sb = new StringBuilder(); 
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for(int i=0; i<n; i++) {
			String word = br.readLine();
			int ans=0;
			for(int idx=0; idx<word.length()/2; idx++) {
				if(word.charAt(idx)!=word.charAt(word.length()-1-idx)) {
					ans=Math.min(check(word,idx,word.length()-2-idx),check(word,idx+1,word.length()-1-idx));
					break;
				}
			}
			sb.append(ans).append('\n');
		}
		System.out.println(sb);
	}
	static int check(String word, int start, int end) {
		while(start<end) {
			if(word.charAt(start)!=word.charAt(end))
				return 2;
			start++; end--;
		}
		return 1;
	}
}
