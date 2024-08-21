import java.util.*;

class 추석트래픽_규영 {
	static class Log {
		int start, end;
		Log (int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public int solution(String[] lines) {
		StringTokenizer st, st2;
		ArrayList<Log> info = new ArrayList<>();
		for (String str : lines) {
			st = new StringTokenizer(str);
			st.nextToken();
			st2 = new StringTokenizer(st.nextToken(), ":");
			int end = Integer.parseInt(st2.nextToken())*3600000+Integer.parseInt(st2.nextToken())*60000+Integer.parseInt(st2.nextToken().replace(".", ""));
			// System.out.println(end);
			int T = (int)(Double.parseDouble(st.nextToken().replace("s", ""))*1000);
			// System.out.println((end-T+1) + " " + end + " " + T);
			info.add(new Log(end-T+1, end));
		}
		int ans = 0;
		for (Log l1 : info) {
			int s1 = l1.start, e1 = l1.end, cntS = 0, cntE = 0;
			for (Log l2 : info) {
				int s2 = l2.start, e2 = l2.end;
				if ((s2 <= s1 && s1 <= e2) || (s2 <= s1+999 && s1+999 <= e2) || (s2 <= s1 && s1+999 <= e2) || (s1 <= s2 && e2 <= s1+999)) cntS++;
				if ((s2 <= e1 && e1 <= e2) || (s2 <= e1+999 && e1+999 <= e2) || (s2 <= e1 && e1+999 <= e2) || (e1 <= s2 && e2 <= e1+999)) cntE++;
			}
			ans = Math.max(ans, Math.max(cntS, cntE));
		}
		return ans;
	}
}