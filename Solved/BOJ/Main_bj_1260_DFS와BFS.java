import java.io.*;
import java.util.*;

public class Main {

	static int N, M, V;
	static List<Integer>[] g;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();

	static void DFS(int i) {
		check[i] = true;
		sb.append(i).append(" ");

		for (int j : g[i]) {
			if (!check[j]) {
				DFS(j);
			}
		}
	}

	static void BFS(int i) {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		check[i] = true;
		q.offer(i);
		sb.append(i).append(" ");
		
		while (!q.isEmpty()) {
			i = q.poll();
			for (int j : g[i]) {
				if (!check[j]) {
					sb.append(j).append(" ");
					check[j] = true;
					q.add(j);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		g = new List[N + 1];
		check = new boolean[N + 1];
		for (int i = 0; i < N + 1; i++) {
			g[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			g[from].add(to);
			g[to].add(from);
		}
		
		for (int i = 1 ; i <= N; i++) {
			Collections.sort(g[i]);
		}

		DFS(V);
		sb.append("\n");
		check = new boolean[N + 1];
		BFS(V);
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
