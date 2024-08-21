import java.io.*;
import java.util.*;

/*
	26800KB 264ms
 */

public class 벼룩시장_규영 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		ArrayList<Integer> buyer = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			if ((arr[i] = Integer.parseInt(st.nextToken())) < 0) buyer.add(i);
		}
		int idx = 0, buyerIdx = 0;
		long ans = 0;
		while (idx < N) {
			if (0 < arr[idx]) {
				while (0 < arr[idx]) {
					int val = -arr[buyer.get(buyerIdx)];
					if (arr[idx] < val) {
						arr[buyer.get(buyerIdx)] += arr[idx];
						ans += arr[idx]*Math.abs(idx-buyer.get(buyerIdx));
						arr[idx] = 0;
					} else {
						arr[buyer.get(buyerIdx)] = 0;
						ans += val*Math.abs(idx-buyer.get(buyerIdx++));
						arr[idx] -= val;
					}
				}
			}
			idx++;
		}
		System.out.print(ans);
	}
}