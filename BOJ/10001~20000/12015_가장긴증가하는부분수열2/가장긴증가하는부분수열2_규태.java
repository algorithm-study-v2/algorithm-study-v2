import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열2_규태 {
	/*
	 * 476ms
	 * 증가하는 부분수열의 인덱스를 binarySearch를 통해 찾고 인덱스를 저장해가며 최장 증가수열의 길이를 찾는 방식으로 구현
	 */
	static int n,num[],incr[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        num = new int[n]; incr = new int[n];
        
        st  = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
        	num[i] = Integer.parseInt(st.nextToken());
        
        incr[0]=num[0]; int idx=0;
        for(int i=1; i<n; i++) {
        	if(num[i]>incr[idx]) incr[++idx]=num[i];
        	else if(num[i]<incr[0]) incr[0]=num[i];
        	else {
        		int index = Arrays.binarySearch(incr, 0, idx, num[i]);
        		if(index>=0) continue;
        		else incr[-1*index-1]=num[i];
        	}
        }
        System.out.println(idx+1);
	}
}