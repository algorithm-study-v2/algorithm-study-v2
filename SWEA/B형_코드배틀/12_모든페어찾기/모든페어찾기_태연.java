import java.io.*;
import java.util.*;

public class 모든페어찾기_태연
{
	/**
	 *  SSAFY Pro 연습4 12번 모든페어찾기 UserSoultion
	 */


	static HashMap<Integer, Integer> map;

	static final int ADD = 100_000;

	public void playGame(int N)
	{
		// Solution.checkCards(mIndexA, mIndexB, mDiff);
		map = new HashMap<>();
		map.put(0,0);

		for(int i=1; i<2*N; i++){
			int diff = binarySearch(i, N);

			if(map.containsKey(diff)){
				if(!getAns(map.get(diff), i, diff)){
					if(map.containsKey(diff+ADD)){
						getAns(map.get(diff+ADD), i, diff + ADD);
					} else {
						map.put(diff+ADD,i);
					}
				}
			} else {
				if(map.containsKey(diff+ADD)){
					getAns(map.get(diff+ADD), i, diff + ADD);
				} else {
					map.put(diff, i);
				}
			}
		}

	}

	private int binarySearch(int target, int max){
		int left = 0;
		int right = max;

		while(left<right){
			int mid = (left+right)/2;
			if(!check(target, mid)){
				left = mid+1;
			} else {
				right = mid;
			}
		}

		return left;
	}

	private boolean check(int index, int diff){
		return Solution.checkCards(0, index, diff);
	}

	private boolean getAns(int n1, int n2, int toRemove){
		if(Solution.checkCards(n1,n2,0)){
			map.remove(toRemove);
			return true;
		} else {
			return false;
		}
	}
}
