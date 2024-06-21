import java.io.*;
import java.util.*;

public class 인공신경망_태연 {
	
	/*
	 *  - 3296ms
	 * 
	 *  - 1차원 선형결합 연산
	 */ 
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int dataSize = Integer.parseInt(st.nextToken());
		int nNeural = Integer.parseInt(st.nextToken());
		int nData = Integer.parseInt(st.nextToken());
		
		long[] weightSum = new long[dataSize+1];
		long biasSum = 0L;
				
		int[][] inputs = new int[nNeural][];
		long[][] weights = new long[nNeural][];
		long[] bias = new long[nNeural];
		
		for(int index=0; index<nNeural; index++) {
			st = new StringTokenizer(br.readLine());
			
			int nInput = Integer.parseInt(st.nextToken());
			inputs[index] = new int[nInput];
			weights[index] = new long[nInput];
			
			for(int i=0; i<nInput; i++) {
				inputs[index][i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<nInput; i++) {
				weights[index][i] = Long.parseLong(st.nextToken());
			}
			
			bias[index] = Long.parseLong(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int index=0; index<nNeural; index++) {
			
			long weight = Long.parseLong(st.nextToken());
			
			for(int i=0; i<inputs[index].length; i++) {
				weightSum[inputs[index][i]]  += weight * weights[index][i];
			}
			
			biasSum += weight * bias[index];
		}
		
		biasSum += Long.parseLong(st.nextToken());
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<nData; i++) {
			st = new StringTokenizer(br.readLine());
			long result = 0;
			
			for(int data=1; data<dataSize+1; data++) {
				result += weightSum[data] * Long.parseLong(st.nextToken());
			}
			
			sb.append(result+biasSum).append("\n");
		}
		
		System.out.print(sb);
	}
}
