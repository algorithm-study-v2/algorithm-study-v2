import java.io.*;
import java.util.*;

public class 과제_태연 {
	
	/*
	 * - 244ms
	 * 
	 * - PQ 2개써서 남은일수 뒤에서부터 넣으면서 과제 하나씩 함
	 */
	
	static class Task{
		int dayLeft;
		int score;
		
		Task(int dayLeft, int score){
			this.dayLeft=dayLeft;
			this.score=score;
		}
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Task> tasksByDayLeft = new PriorityQueue<>((Task t1, Task t2)
				-> {
					return t2.dayLeft - t1.dayLeft;
				});
		
		PriorityQueue<Task> tasksByScore = new PriorityQueue<>((Task t1, Task t2)
				-> {
					return t2.score - t1.score;
				});
		
		int lastDay = -1;
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			
			int dayLeft = Integer.parseInt(st.nextToken());
			if(dayLeft > lastDay) {
				lastDay = dayLeft;
			}
			int score = Integer.parseInt(st.nextToken());
			
			tasksByDayLeft.add(new Task(dayLeft, score));
		}
		
		int curDay = lastDay;
		int sumScore = 0;

		while(curDay>0) {
			
			while(!tasksByDayLeft.isEmpty() 
					&& tasksByDayLeft.peek().dayLeft >= curDay) {
				tasksByScore.add(tasksByDayLeft.poll());
			}

			if(!tasksByScore.isEmpty()) {
				sumScore += tasksByScore.poll().score;
			}
			curDay--;
		}
		
		System.out.println(sumScore);
	}
}
