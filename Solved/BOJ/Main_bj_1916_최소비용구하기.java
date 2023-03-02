import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<node>[] graph;
	static int[] dist;
	static boolean[] check;
    static int end;
	
	static class node{
		int to, cost;
		node(int to, int cost){
			this.to = to;
			this.cost = cost;
		}
	}
	
	static void Dijkstra(int start) {
		PriorityQueue<node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.add(new node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			node now = pq.poll();
            
            if (check[end]){
                break;
            }
            
			if (check[now.to]){
                continue;
            }
            
			if (!check[now.to]) {
				check[now.to] = true;
			}
			
			for (node next : graph[now.to]) {
				if (!check[next.to] && dist[next.to] > now.cost + next.cost) {
					dist[next.to] = now.cost + next.cost;
					pq.add(new node(next.to, dist[next.to]));
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[N + 1];
		check = new boolean[N + 1];
		dist = new int[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = 2100000000;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new node(to, cost));
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		Dijkstra(start);
		
		sb.append(dist[end]);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}