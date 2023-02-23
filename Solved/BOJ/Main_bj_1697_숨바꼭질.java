import java.io.*;
import java.util.*;

public class Main {

	static int N, K, answer = 0;
	static boolean[] check;
	
	static class Pair{
		int x, time;
		Pair(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
	
	static boolean InRange(int x) {
		return x >= 0 && x <= 100000;
	}
	
	static void BFS(int x, int time) {
		ArrayDeque<Pair> q = new ArrayDeque<Pair>();
		q.offer(new Pair(x, time));
		
		while(!q.isEmpty()) {
			Pair p = q.poll();
			
			if (p.x == K) {
				answer = p.time;
				break;
			}
			
			if (InRange(p.x * 2) && !check[p.x * 2]) {
				check[p.x * 2] = true;
				q.offer(new Pair(p.x * 2 , p.time + 1));
			}
			
			if (InRange(p.x + 1) && !check[p.x + 1]) {
				check[p.x + 1] = true;
				q.offer(new Pair(p.x + 1, p.time + 1));
			}
			
			if (InRange(p.x - 1) && !check[p.x - 1]) {
				check[p.x - 1] = true;
				q.offer(new Pair(p.x - 1, p.time + 1));
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		check = new boolean[100001];
		
		check[N] = true;
		BFS(N, 0);
		sb.append(answer);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
