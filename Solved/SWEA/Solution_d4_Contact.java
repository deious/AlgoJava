import java.io.*;
import java.util.*;

public class Solution {

	static ArrayList<Integer>[] arr;
	static boolean[] check;
	static int maxNum = 0;

	static class pair {
		int x, depth;

		pair(int x, int depth) {
			this.x = x;
			this.depth = depth;
		}
	}

	static int BFS(int start, int depth) {
		ArrayDeque<pair> q = new ArrayDeque<>();
		q.offer(new pair(start, depth));
		int cMax = 0, cDepth = 0;
		while (!q.isEmpty()) {
			pair p = q.poll();
			if (p.depth > cDepth) {
				cDepth = p.depth;
				cMax = 0;
			}
			
			if (p.x > cMax) {
				cMax = p.x;
			}
			
			for (int i : arr[p.x]) {
				if (!check[i]) {
					check[i] = true;
					q.offer(new pair(i, p.depth + 1));
				}
			}
		}
		return cMax;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int i = 1; i <= 10; i++) {
			arr = new ArrayList[101];
			check = new boolean[101];
			maxNum = 0;
			for (int j = 0; j <= 100; j++) {
				arr[j] = new ArrayList<>();
			}
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			len /= 2;
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < len; j++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				arr[from].add(to);
			}

			check[start] = true;
			sb.append("#").append(i).append(" ").append(BFS(start, 0)).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

}
