import java.io.*;
import java.util.*;

public class Solution {

	static int num, len;
	static List<Integer>[] g;
	static boolean[] check;
	static boolean totalEnd;
	static StringBuilder sb = new StringBuilder();

	static void DFS(int i) {
		check[i] = true;
		
		if (totalEnd) {
			return;
		}

		for (int j : g[i]) {
			if (!check[j]) {
				if (j == 99) {
					totalEnd = true;
				}
				DFS(j);
			}
		}
	}

	static void BFS(int i) {
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		check[i] = true;
		q.offer(i);

		while (!q.isEmpty()) {
			i = q.poll();
			
			if (totalEnd) {
				return;
			}
			
			for (int j : g[i]) {
				if (!check[j]) {
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

		for (int i = 1; i <= 10; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			len = Integer.parseInt(st.nextToken());

			g = new List[100];
			check = new boolean[100];
			totalEnd = false;
			for (int j = 0; j < 100; j++) {
				g[j] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < len; j++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				g[from].add(to);
			}

			//DFS(0);
			BFS(0);
			sb.append("#").append(i).append(" ");
			if (check[99]) {
				sb.append("1");
			}else {
				sb.append("0");
			}
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
