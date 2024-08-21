import java.util.*;

class UserSolution
{
	Robot[] robots;
	int[][] works;
	myPQ PQ;

	static class myPQ{

		TreeSet<Robot> pq;

		myPQ(){
			pq = new TreeSet<>();
		}

		void add(Robot r) {
			pq.add(r);
		}

		Robot peekFirst() {
			return pq.first();
		}

		Robot peekLast() {
			return pq.last();
		}

		TreeSet<Robot> subset(int op) {
			if(op==0)
				return (TreeSet<Robot>)pq.tailSet(new Robot(100000,peekLast().value+1), false);
			else
				return (TreeSet<Robot>)pq.headSet(new Robot(100000,peekFirst().value-1), false);
		}
	}

	static class Robot implements Comparable<Robot>{
		int id;
		int value;	//	 지금까지 손해본 지능 지수
		//	 투입 시간		cTime에서의 지능 => cTime-value
		boolean isBroken;
		boolean onWork;
		int wID;

		Robot(int id){
			this.id=id;
			this.value=0;
			this.isBroken=false;
			this.onWork=false;
		}

		Robot(int id, int value){
			this.id=id;
			this.value=value;
		}

		@Override
		public int compareTo(Robot o) {

			if(o.value!=this.value)
				return o.value-this.value;
			else
				return this.id-o.id;
		}
	}

	public void init(int N)
	{
		robots = new Robot[N+1];
		PQ = new myPQ();
		for(int i=1; i<N+1; i++) {
			robots[i] = new Robot(i);
			PQ.add(robots[i]);
		}

		works = new int[50001][];
	}

	public int callJob(int cTime, int wID, int mNum, int mOpt)
	{

		int sum=0;
		works[wID] = new int[mNum+1];
		works[wID][0] = cTime;

		TreeSet<Robot> subset = PQ.subset(mOpt);

		for(int i=1; i<=mNum; i++) {
			if(subset.isEmpty()) subset = PQ.subset(mOpt);
			Robot r = subset.pollFirst();

			if(r.isBroken || r.onWork) {
				i--;
				continue;
			}
			PQ.pq.remove(r);
			sum += r.id;
			works[wID][i] = r.id;
			r.onWork=true;
			r.wID = wID;
		}

		return sum;
	}

	public void returnJob(int cTime, int wID)
	{

		int minus = works[wID][0];

		for(int i=1; i<works[wID].length; i++) {
			Robot r = robots[works[wID][i]];
			if(r.wID != wID) continue;
			if(r.onWork) {

				r.value += cTime-minus;
				r.onWork=false;
				r.isBroken=false;
				PQ.add(r);
			}
		}

		works[wID]=null;
	}

	public void broken(int cTime, int rID)
	{

		Robot r = robots[rID];
		if(r.isBroken || !r.onWork) return;
		PQ.pq.remove(r);
		r.onWork=false;
		r.isBroken=true;
		r.wID=0;
	}

	public void repair(int cTime, int rID)
	{
		Robot r = robots[rID];
		if(r.isBroken) {


			r.value = cTime;
			r.isBroken=false;
			r.onWork=false;
			r.wID=0;
			PQ.add(r);
		}
	}

	public int check(int cTime, int rID)
	{
		Robot r = robots[rID];
		int result=0;

		if(r.isBroken)
			result= 0;
		else if(r.onWork) {
			result= (-1)*r.wID;
		}
		else result= cTime-r.value;


		return result;
	}
}