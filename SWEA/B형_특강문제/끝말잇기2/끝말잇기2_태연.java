import java.util.*;

class UserSolution {

	HashSet<String> set;
	TreeSet<String>[] trees;
	boolean[] dead;
	int N;
	int M;

	public void init(int N, int M, char[][] mWords)
	{
		this.N=N+1;
		this.M=M;
		dead = new boolean[N+1];
		dead[0] = true;

		set = new HashSet<>();
		trees = new TreeSet[26+'a'];
		for(int i='a'; i<='z'; i++) trees[i] = new TreeSet<>();

		for(int i=0; i<M; i++) {
			char ch = mWords[i][0];

			StringBuilder sb = new StringBuilder();
			for(int c=1; c<mWords[i].length; c++) {
				if(mWords[i][c] != '\0')
					sb.append(mWords[i][c]);
				else break;
			}
			trees[ch].add(sb.toString());
		}
	}

	public int playRound(int mID, char mCh)
	{
		int curPlayer = mID;
		char curChar = mCh;
		List<String> toAdd = new ArrayList<>();

		while(true) {
			if(trees[curChar].isEmpty()) break;

			String turn = trees[curChar].pollFirst();

			set.add((curChar + turn));
			String add = curChar + turn;

			curChar = turn.charAt(turn.length()-1);

			toAdd.add(add);
			while(dead[(++curPlayer)%N]) {}
		}

		for(String st: toAdd) {
			char ch = st.charAt(st.length()-1);
			StringBuilder sb = new StringBuilder();
			for(int i=st.length()-2; i>=0; i--) {
				sb.append(st.charAt(i));
			}

			if(!set.contains((ch+sb.toString()))) {
				trees[ch].add(sb.toString());
			}
		}
		dead[curPlayer%N]=true;
		return curPlayer%N;
	}
}