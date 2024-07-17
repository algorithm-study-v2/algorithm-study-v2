import java.io.*;
import java.util.*;

public class 벼룩시장_태연 {

    /*
     * - 240ms
     */
	
	static int n;
	static long[] market;
	
	static int getNext(int cur, boolean isBuy) {
		cur++;
		
		if(isBuy) {
			while(cur<n && market[cur]>=0) {
				cur++;
			}
		} else {
			while(cur<n && market[cur]<=0) {
				cur++;
			}
		}
		
		return cur;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		
		market = new long[n];
		
		for(int i=0; i<n; i++) {
			market[i] = Long.parseLong(st.nextToken());
		}
		
		int sellPlace = 0;
		while(sellPlace < n && market[sellPlace]<=0) {
			sellPlace++;
		}
		
		int buyPlace = 0;
		while(buyPlace < n && market[buyPlace]>=0) {
			buyPlace++;
		}
		
		long distSum = 0L;
		
		while(sellPlace < n && buyPlace < n) {
			long cur = market[sellPlace] + market[buyPlace];
//			System.out.println(Arrays.toString(market));
//			System.out.println(distSum + " " + sellPlace + " " + buyPlace);
			
			if(cur > 0) {
				market[sellPlace] += market[buyPlace];
				distSum -= Math.abs(sellPlace - buyPlace) * market[buyPlace];
				buyPlace = getNext(buyPlace, true);
			} else if (cur == 0) {
				distSum += Math.abs(sellPlace - buyPlace) * market[sellPlace];
				sellPlace = getNext(sellPlace, false);
				buyPlace = getNext(buyPlace, true);
			} else {
				market[buyPlace] += market[sellPlace];
				distSum += Math.abs(sellPlace - buyPlace) * market[sellPlace];
				sellPlace = getNext(sellPlace, false);
			}
		}
		
		System.out.println(distSum);
	}
}
