class 단어변환_규영 {
	int ans, size, len;
	boolean[] visited;
	public int solution(String begin, String target, String[] words) {
		ans = 0;
		if (contains(words, target)) return ans;
		len = begin.length();
		visited = new boolean[size = words.length];
		dfs(begin, target, words, 0);
		return ans;
	}

	// 시간 터질 우려 있음
	void dfs(String begin, String target, String[] words, int depth) {
		if (begin.equals(target)) {
			ans = depth;
			return;
		}
		for (int i = 0; i < size; i++) {
			if (visited[i]) continue;
			int cnt = 0;
			for (int j = 0; j < len; j++) {
				if (begin.charAt(j) == words[i].charAt(j)) continue;
				cnt++;
				if (cnt > 1) break;
			}
			if (cnt == 1) {
				visited[i] = true;
				dfs(words[i], target, words, depth+1);
				visited[i] = false;
			}
		}
	}

	boolean contains(String[] words, String target) {
		for (int i = 0; i < size; i++) {
			if (!words[i].equals(target)) continue;
			visited[i] = true;
			return true;
		}
		return false;
	}
}