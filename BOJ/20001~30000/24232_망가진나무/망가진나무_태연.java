import java.io.*;
import java.util.*;

public class 망가진나무_태연 {
	
	/*
	 *  - 748ms
	 *  
	 *  첫번째 DFS -> 내 기준으로 밑으로 내려가려면 드는 비용 계산
	 *  
	 *  두번째 DFS -> 원래 순회했던 방향에서 오는 비용 합산
	 *  
	 *  최소비용인 노드 계산
	 *  
	 *  세번째 DFS -> 뒤집는지 안뒤집는지 계산
	 *  
	 *  edge에 정방향인지 역방향인지 넣으면 되는데 바보같이 리스트 쪼갰다가 메소트 스파게티됨
	 */
	
	static class Node{
		int id;
		List<Edge> froms;
		List<Edge> tos;
		int cost;
		
		Node(int id){
			this.id=id;
			this.froms = new ArrayList<>();
			this.tos = new ArrayList<>();
			this.cost = 0;
		}
	}
	
	static class Edge{
		Node from;
		Node to;
		int id;
		
		Edge(int id, Node from, Node to){
			this.from=from;
			this.to=to;
			this.id=id;
		}
	}
	
	static Node[] nodes;
	static Edge[] edges;
	static int[] costs;
	static int[] results;
	
	static int dfs(Node before, Node cur) {
		
		for(Edge e : cur.froms) {
			Node next = e.to;
			
			if(next.id == before.id) {
				continue; 
			} else {
				cur.cost += dfs(cur, next);
			}
		}
		
		for(Edge e : cur.tos) {
			Node next = e.from;
			
			if(next.id == before.id) {
				continue; 
			} else {
				cur.cost += dfs(cur, next) + 1;
			}
		}
		
		return cur.cost;
	}
	
	static void backProp(Node before, Node cur) {
        for (Edge e : cur.froms) {
        	Node next = e.to;
        	
        	if(next.id == before.id) {
        		continue;
        	} else {
        		next.cost = cur.cost+1;
        	}
        	
        	backProp(cur, next);
        }
        
        for (Edge e : cur.tos) {
        	Node next = e.from;
        	
        	if(next.id == before.id) {
        		continue;
        	} else {
        		next.cost = cur.cost-1;
        	}
        	
        	backProp(cur, next);
        }
    }
	
	static void getResults(Node before, Node cur) {
		 for (Edge e : cur.froms) {
	        	Node next = e.to;
	        	
	        	if(next.id == before.id) {
	        		continue;
	        	} else {
	        		results[e.id] = 0; 
	        	}
	        	
	        	getResults(cur, next);
	        }
		 
		 for (Edge e : cur.tos) {
	        	Node next = e.from;
	        	
	        	if(next.id == before.id) {
	        		continue;
	        	} else {
	        		results[e.id]= 1; 
	        	}
	        	
	        	getResults(cur, next);
	        }
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		nodes = new Node[n+1];
		edges = new Edge[n];
		costs = new int[n+1];
		results = new int[n];
		
		for(int i=0; i<=n; i++) {
			nodes[i] = new Node(i);
		}
		
		for(int i=0; i<n-1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Node n1 = nodes[Integer.parseInt(st.nextToken())];
			Node n2 = nodes[Integer.parseInt(st.nextToken())];
			
			edges[i] = new Edge(i, n1, n2);
			n1.froms.add(edges[i]);
			n2.tos.add(edges[i]);
		}
		
		dfs(nodes[0], nodes[1]);
		backProp(nodes[0], nodes[1]);
		
		int minCost = 11111111;
		int idx = 0;
		for(int i=1; i<=n; i++) {
			//System.out.println(i + " : "  + nodes[i].cost);
			if(nodes[i].cost < minCost) {
				minCost = nodes[i].cost;
				idx = i;
			}
		}
		
		getResults(nodes[0], nodes[idx]);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n-1; i++) {
			sb.append(results[i]);
		}
		
		System.out.print(sb);
	}
}
