import java.io.*;
import java.util.*;

public class Main {
	static ArrayList<Integer> graph[];
	static int N, M;
	static boolean[] check;
	static boolean endCheck;
	
	static void DFS(int idx, int depth) {
		if (endCheck) {
			return;
		}
		
		if (depth == 5) {
			endCheck = true;
			return;
		}
		
		for (int i : graph[idx]) {
			if (check[i]) {
				continue;
			}
			check[i] = true;
			DFS(i, depth + 1);
			check[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N];
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
			graph[to].add(from);
		}
		
		for (int i = 0; i < N; i++) {
			if (endCheck) {
				break;
			}
			check[i] = true;
			DFS(i, 1);
			check[i] = false;
		}
		
		if (endCheck) {
			sb.append("1");
		} else {
			sb.append("0");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
